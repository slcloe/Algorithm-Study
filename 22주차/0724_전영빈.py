"""
고민할 부분이 있는 bfs 문제. 3번 질의가 좀 까다롭다.

하나의 벽을 제거한다 -> 결국 두 방은 벽을 끼고 맞닿아 있어야 한다.
a에서 탐색을 했을 때 b를 만났다는 것은 b에서 탐색을 해도 a를 만날 수 있음을 의미한다.
따라서, 현재 탐색을 돌렸을 때, 이전에 찾은 방에 대해서만 3번 질의를 계산해도 모든 케이스를 구할 수 있다.
(이후의 탐색에서 찾게 될 방을 고려하지 않아도 된다.)

bfs로 방을 구하면서 찾게 된 다른 방들을 set을 통해서 관리했다.
"""

import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(M)]
visited = [[-1 for _ in range(N)] for _ in range(M)]
room = []
sol = -1

direction = [ 1 << 0, 1 << 1, 1 << 2, 1 << 3 ]
dy = [0, -1, 0, 1]
dx = [-1, 0, 1, 0]

def bfs(y, x, seq):
    queue = deque()
    queue.append((y, x))
    size = 1
    visited[y][x] = seq
    catch = set()

    while queue:
        cy, cx = queue.popleft()

        for i in range(4):
            # 사이에 벽이 있는 경우
            ny, nx = cy + dy[i], cx + dx[i]
            if board[cy][cx] & direction[i] == direction[i]:
                if 0 <= ny < M and 0 <= nx < N and visited[ny][nx] != -1 and visited[ny][nx] != seq:
                    catch.add(visited[ny][nx])

            # 벽이 없는 경우
            else:
                if 0 <= ny < M and 0 <= nx < N and visited[ny][nx] == -1:
                    queue.append((ny, nx))
                    size += 1
                    visited[ny][nx] = seq

    room.append(size)
    res = size
    for index in catch:
        res = max(res, size + room[index])

    return res

sequence = 0
for i in range(M):
    for j in range(N):
        if visited[i][j] == -1:
            sol = max(sol, bfs(i, j, sequence))
            sequence += 1

print(len(room))
print(max(room))
print(sol)
