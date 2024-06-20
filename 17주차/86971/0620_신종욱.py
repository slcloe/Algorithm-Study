def solution(n, wires):
    answer = 100

    graph = {}
    for wire in wires:
        a, b = wire
        if a not in graph:
            graph[a] = set()
        graph[a].add(b)

        if b not in graph:
            graph[b] = set()
        graph[b].add(a)

    for to_cut in wires:
        answer = min(answer, solve(graph=graph, to_cut=to_cut))

    return answer


def solve(graph, to_cut):
    # 간선과 노드 모두 방문 처리를 한다.
    visited = set()
    visited.add((to_cut[0], to_cut[1]))
    visited.add((to_cut[1], to_cut[0]))

    def bfs(root):
        from collections import deque

        q = deque()
        q.append(root)

        cnt = 0
        while q:
            curr = q.popleft()
            # 노드는 여기서 방문 처리한다.
            visited.add(curr)
            cnt += 1

            for next in graph[curr]:
                # 간선은 여기서 방문 처리한다.
                if (curr, next) not in visited and (next, curr) not in visited:
                    visited.add((curr, next))
                    visited.add((next, curr))
                    q.append(next)

        return cnt

    ans = [] 
    for root in graph:
        if root not in visited:
            ans.append(bfs(root))

    # bfs() 함수에서 노드 역시 방문처리하므로 
    # bfs() 함수의 correctness가 보장된다면 ans의 길이는 항상 2다.
    return abs(ans[0] - ans[1])
