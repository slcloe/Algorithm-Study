'''
아무것도 연결되지 않은 노드가 짱큰 값을 가질 수 있으므로
1 ~ n에 대해 모든 노드를 고려해야 한다.
'''
import sys
input = sys.__stdin__.readline

def main(): 
    n, m, r = map(int, input().split())
    t = [0] + list(map(int, input().split()))
    
    graph = {}
    for _ in range(r):
        a, b, l = map(int, input().split())

        if a in graph:
            graph[a].append((l, b))
        else:
            graph[a] = [(l, b)]

        if b in graph:
            graph[b].append((l, a))
        else:
            graph[b] = [(l, a)]

    # 모든 노드를 고려해야 한다.
    for i in range(1, n + 1):
        if i not in graph:
            graph[i] = []


    def dijkstra(a):
        import heapq
        
        d = [99999 for _ in range(n + 1)]
        queue = []
        d[a] = 0
        heapq.heappush(queue, (0, a))

        while queue:
            curr_weight, curr_node  = heapq.heappop(queue)
            if curr_weight > d[curr_node]: # 더 볼 거 없다
                continue
                
            for ne in graph[curr_node]:
                if d[ne[1]] > curr_weight + ne[0]:
                    d[ne[1]] = curr_weight + ne[0]
                    heapq.heappush(queue, (d[ne[1]], ne[1]))
        
        return d


    answer = -1
    
    for a in range(1, n + 1):
        d = dijkstra(a)
        ans = 0
        for i in range(1, n + 1):
            if d[i] <= m:
                ans += t[i]
            
            answer = max(answer, ans)

    print(answer)


main()
