"""
입력 정보를 바탕으로 2차원 배열을 만들고 빈 공간에 대해 좌우로 탐색하여 빗물이 고일 수 있는 공간을 센다.
이 때, 탐색의 범위가 배열의 범위를 넘어갈 경우 벽이 없음 => 해당 라인은 빗물이 고일 수 없다.
"""
import sys
from collections import deque

input = sys.stdin.readline

def rain(y, x):
    sum = 0
    flag = True
    dq = deque([x])
    block[y][x] = 1

    while dq:
        cx = dq.popleft()
        sum += 1
        for i in range(2):
            nx = cx + dx[i]

            if 0 <= nx < W and block[y][nx] == 0:
                block[y][nx] = 1
                dq.append(nx)
            elif nx >= W or nx < 0:
                flag = False

    if flag == False:
        return 0
    else:
        return sum

H, W = map(int, input().split())
block = [[0 for _ in range(W)] for _ in range(H)]

row = list(map(int, input().split()))
for i in range(W):
    for j in range(row[i]):
        block[j][i] = 1

sol = 0
dx = [-1, 1]
for i in range(H):
    for j in range(W):
        if block[i][j] == 0:
            sol += rain(i, j)

print(sol)
