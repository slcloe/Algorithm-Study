import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

def djt(s, e):
    time = [INF for _ in range(n+1)]
    time[1] = 0
    q = [(0, 1)]
    while q:
        t, now = heapq.heappop(q)
        if now==n: break
        for next, plus in graph[now]:
            # s,e는 선택된 노드들로 그 노드들의 경우는 고려하지 않도록 한다.
            # s,e가 선택되면 그곳은 검문소가 되므로 continue해준다.
            if s==now and e==next or s==next and e==now: continue
            if t+plus < time[next]:
                time[next] = t+plus
                if not s: pre[next] = now
                heapq.heappush(q, (time[next], next))
    return time[n]

def main():
    global n, m, graph
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    for _ in range(m):
        a, b, t = map(int, input().split())
        graph[a].append((b,t))
        graph[b].append((a,t))

    global pre
    pre = [0 for _ in range(n + 1)]

    result = djt(0,0)

    ans = -1
    e = n

    # 끝점부터 시작점으로 돌아오면서 하나씩 검문소로 만들어 본다.
    while pre[e] != 0:
        s = pre[e]
        output = djt(s, e)
        # 처음 값과 비교해서 답을 갱신해준다.
        if output != INF:
            diff = output-result
            ans = max(ans, diff)
        else:
            ans = -1
            break
        # 시작점을 다시 끝점으로 바꾸고 다음 경로에 검문소를 만든다.
        e = s

    print(ans)
