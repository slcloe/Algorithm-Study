import sys
input = sys.__stdin__.readline

from collections import deque


def main():
    n, m = list(map(int, input().split()))
    s, e = list(map(int, input().split()))
    
    global graph
    graph = {}
    for _ in range(m):
        h1, h2, k = list(map(int, input().split()))
        
        if h1 not in graph: 
            graph[h1] = []
        if h2 not in graph: 
            graph[h2] = []
        graph[h1].append((h2, k))
        graph[h2].append((h1, k))

    
    # 들고 갈 수 있는 빼빼로 갯수는 1 ~ 1000000이다(다리 무게 제한이 1 ~ 1000000이니까).
    # 1 ~ 1000000에 대해 이분 탐색을 수행하는 시간복잡도는 O(1)이고,
    # 실제 횟수는 log_2(1000000) ≒ 20이므로
    # 사실상 무시할 수 있다.
    left = 1
    right = 1000000 
    answer = 0
    prev_left, prev_right = 1000000, -1 # 이분탐색 탈출 조건을 위함
    while left < right and (prev_left != left or prev_right != right):
        mid = (left + right) // 2
        flag = bfs(src=s, dest=e, mid=mid)

        prev_left, prev_right = left, right
        if flag:
            left = mid
            answer = mid
        else:
            right = mid
        

    print(answer)


# 목적지에 도달할 수 있으면 True, 아니면 False
# 그냥 BFS 때려서 가능한 경로가 있는지 알아본다.
def bfs(src: int, dest: int, mid):
    queue = deque()
    visited = set()

    queue.append(src)
    visited.add(src)
    while queue:
        c = queue.popleft()
        if c == dest:
            return True

        if c not in graph:
            continue

        for node, weight in graph[c]:
            if weight >= mid: # 내가 들고갈 무게 mid보다 무게 제한 weight가 크다면
                if node not in visited:
                    queue.append(node)
                    visited.add(node)

    return False


main()