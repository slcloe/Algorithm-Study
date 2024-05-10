"""
가중치가 양수인 그래프에서의 최단 경로를 구하는 문제이으로 다익스트라를 이용해야 한다는 것은 바로 알 수 있다.
처음엔 나이브하게 모든 간선에 대해 해당 간선을 사용할 수 없을 때의 대한 최단 경로를 계산하여 풀이했지만 당연히 시간 초과가 났다.

연산을 줄이기 위해 모든 간선을 사용할 수 있을 때의 최단 경로를 구하고, 그 때 사용된 간선들을 별도로 저장했다.
다익스트라를 돌린 이후에 A까지의 최단 거리 - A와 B 사이의 가중치 == B까지의 최단 거리 일 경우, 해당 간선이 최단 경로에 사용되었음을 알 수 있다.
이후, 각 간선을 사용할 수 없을 때의 최단 거리를 구해 풀이했다.
"""

import sys
import heapq

input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[-1 for _ in range(N)] for _ in range(N)]
selected = []
dist = []

for _ in range(M):
    a, b, t = map(int, input().split())
    graph[a-1][b-1] = t
    graph[b-1][a-1] = t

def djikstra(start):
    global dist
    dist = [sys.maxsize for _ in range(N)]
    dist[start] = 0
    heap = []
    heapq.heappush(heap, (dist[start], start))

    while heap:
        cd, cn = heapq.heappop(heap)

        if cn == N-1:
            return cd
        
        for nn in range(N):
            if graph[cn][nn] == -1:
                continue

            nd = cd + graph[cn][nn]

            if nd < dist[nn]:
                heapq.heappush(heap, (nd, nn))
                dist[nn] = nd

    return -1

def initialSearch():
    global selected

    res = djikstra(0)

    cn = N-1
    cd = res
    while cn != 0:
        for nn in range(N):
            if graph[cn][nn] == -1:
                continue

            if cd - graph[cn][nn] == dist[nn]:
                selected.append((cn, nn))
                cd = dist[nn]
                cn = nn
                break

    return res

defaultDistance = initialSearch()

solve = 0
for a, b in selected:
    tw = graph[a][b]
    graph[a][b], graph[b][a] = -1, -1
    tempDistance = djikstra(0)
    if tempDistance == -1:
        solve = -1
        break
    else:
        solve = max(solve, tempDistance - defaultDistance)
    graph[a][b], graph[b][a] = tw, tw

print(solve)
