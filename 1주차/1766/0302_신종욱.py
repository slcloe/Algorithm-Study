import sys
input = sys.__stdin__.readline

import heapq


def main():
    n, m = input().split()
    n, m = int(n), int(m)
    
    answer = []
    graph = {p: [] for p in range(n + 1)}
    in_degree = [0 for _ in range(n + 1)]
    pq = [] # 최소 힙

    for _ in range(m):
        a, b = map(int, tuple(input().split()))
        a, b = int(a), int(b)
        graph[a].append(b) 
        in_degree[b] += 1  # degree of a -> b 

    for i in range(1, n + 1):
        # 선행 문제가 없는 문제는 쉬운 순으로 차례로 큐에 추가된다.
        if in_degree[i] == 0:
            heapq.heappush(pq, i)
            
    
    answer = []
    while pq:
        # 쉬운 문제부터 꺼내면서 그 문제의 후행 문제는 큐에 추가한다.
        # 결과적으로 쉬운 문제부터 풀되 선행 문제를 먼저, 난이도 순서대로 풀게 된다.
        val = heapq.heappop(pq) # 현재 우선순위 큐의 문제 중 가장 쉬운 문제부터 pop된다.
        answer.append(val)
        for i in graph[val]: # 방금 꺼낸 문제의 후행문제들에 대해
            in_degree[i] -= 1 # 진입 차수를 내리고
            if in_degree[i] == 0: # 더이상 선행문제가 없다면 우선 순위 큐에 추가한다.
                heapq.heappush(pq, i)
        

    print(' '.join([str(a) for a in answer]))


main()
