import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

import heapq

INF = int(1e9)

def main():
    t = int(input())

    for _ in range(t):
        n, m, k = map(int, input().split())
        s, g, h = map(int, input().split())

        global graph
        graph = [[] for _ in range(n + 1)]

        for _ in range(m):
            a, b, c = map(int, input().split())
            # a -> b의 비용 c
            graph[a].append((b, c))
            graph[b].append((a, c))

        a = []
        for _ in range(k):
            a.append(int(input()))

        first = dijkstra(s)
        g_dijk = dijkstra(g)
        h_dijk = dijkstra(h)

        arr = []
        for b in a:
            if first[g] + g_dijk[h] + h_dijk[b] == first[b] or first[h] + h_dijk[g] + g_dijk[b] == first[b]:
                arr.append(b)
        arr.sort()

        for w in arr:
            print(w, end=' ')
        print()

def dijkstra(start):
    q = []

    # 최단 거리 테이블을 모두 무한으로 초기화
    dist = [INF] * (n + 1)

    heapq.heappush(q, (0, start))
    dist[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if dist[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]

            if cost < dist[i[0]]:
                dist[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

    return dist

