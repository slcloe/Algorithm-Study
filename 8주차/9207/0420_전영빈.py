"""
핀이 움직이는 제약 조건은 해당 핀 상하좌우 바로 옆에 다른 핀이 존재할 것과 그 핀의 바로 다음 칸으로만 이동이 가능하다는 것.
핀의 개수가 8개 밖에 되지 않으므로 브루트 포스로 해결했다.
"""

import sys

input = sys.stdin.readline

N = int(input())
board = []
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

minPin, minMove = sys.maxsize, sys.maxsize

def dfs(moveCount):
    global minPin, minMove

    pin = []
    
    for i in range(5):
        for j in range(9):
            if board[i][j] == 'o':
                pin.append((i, j))

    if len(pin) == minPin and moveCount < minMove:
        minMove = moveCount

    if len(pin) < minPin:
        minPin = len(pin)
        minMove = moveCount

    for y, x in pin:
        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]

            if 0 <= ny + dy[i] < 5 and 0 <= nx + dx[i] < 9 and board[ny][nx] == 'o' and board[ny + dy[i]][nx + dx[i]] == '.':
                board[ny][nx] = '.'
                board[ny + dy[i]][nx + dx[i]] = 'o'
                board[y][x] = '.'
                dfs(moveCount + 1)
                board[ny][nx] = 'o'
                board[ny + dy[i]][nx + dx[i]] = '.'
                board[y][x] = 'o'


for t in range(N):
    board = [list(input().rstrip()) for _ in range(5)]
    minPin, minMove = sys.maxsize, sys.maxsize

    dfs(0)

    print(minPin, minMove)

    if t != N-1:
        input()

    
