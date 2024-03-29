"""
일반적인 bfs문제로 보이지만 한 가지 특별한 점이 있다.
최단 거리가 아닌 거울을 최소로 사용한 횟수를 구한다는 것이다.
이를 다르게 말하면 거울을 사용하지 않는 경로는 가중치가 없는 것이고 거울을 사용한 경로에만
가중치가 부과된다는 것과 동일하다 => 0-1 bfs

풀이 방법은 어떤 상황이든 직진방향에 대해 가중치를 0로 탐색하되,
거울을 설치할 수 있는 자리에 도달하면 추가로 90도와 270도 방향에 대해 가중치를 1로 탐색한다.
거울 자리에 도달해도 거울을 설치하지 않을 수 있다는 점을 명심해야 한다.
"""

import sys
from collections import deque

input = sys.stdin.readline

N = int(input())

board = [list(input().rstrip()) for _ in range(N)]
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

sy , sx = 0, 0
for i in range(N):
    for j in range(N):
        if board[i][j] == '#':
            sy, sx = i, j

queue = deque() # cnt, direction, cy, cx
queue.append((0, 0, sy, sx))
queue.append((0, 1, sy, sx))
queue.append((0, 2, sy, sx))
queue.append((0, 3, sy, sx))
board[sy][sx] = '*'

while queue:
    cnt, dct, cy, cx = queue.popleft()

    if board[cy][cx] == '#':
        print(cnt)
        break

    if 0 <= cy + dy[dct] < N and 0 <= cx + dx[dct] < N and board[cy + dy[dct]][cx + dx[dct]] != '*':
        queue.appendleft((cnt, dct, cy+dy[dct], cx+dx[dct]))

    if board[cy][cx] == '!':
        for i in range(-1, 2, 2):
            tdct = (dct+i)%4
            ny, nx = cy + dy[tdct], cx + dx[tdct]
            if 0 <= ny < N and 0 <= nx < N and board[ny][nx] != '*':
                queue.append((cnt+1, tdct, ny, nx))
