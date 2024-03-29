"""
전형적인 구현 문제이고 폭탄에 움직임에 따른 폭탄 폭발 처리를 어떻게 구현하느냐가 키 포인트라고 생각한다.
본인의 경우 폭탄을 set을 통해 관리했다.
기존 폭탄의 위치 set과 이동한 폭탄 위치의 set을 통해 각 폭탄이 이동한 자리에서 중복이 일어나지 않도록 관리했다.

또한, 한 위치에 폭탄이 두 개 이상 존재하면 폭탄이 사라지게 되는데 이때 중요한 점은 모든 폭탄이 이동한 뒤에 폭탄
폭발 처리를 진행해야 한다는 것이다.
따라서, 폭탄 이동 시에 이동할 좌표가 이미 이동한 폭탄 위치인 tempMad에 존재하면 폭발 예정 좌표 값 set인
boomMad에 넣어놓은 뒤 모든 이동이 끝난 후 처리했다..
"""

import sys

input = sys.stdin.readline

R, C = map(int, input().split())

board = [list(input().rstrip()) for _ in range(R)]
move = list(map(int, input().rstrip()))

dy = [1, 1, 1, 0, 0, 0, -1, -1, -1]
dx = [-1, 0, 1, -1, 0, 1, -1, 0, 1]

def getGap(o, c):
    sign = o - c
    if sign < 0:
        return -1
    elif sign > 0:
        return 1
    else:
        return 0

y, x = 0, 0
mad = set()
for i in range(R):
    for j in range(C):
        if board[i][j] == 'I':
            y, x = i, j

        if board[i][j] == 'R':
            mad.add((i, j))

for i, m in enumerate(move, start=1):
    board[y][x] = '.'

    y += dy[m-1]
    x += dx[m-1]

    if board[y][x] == 'R':
        print("kraj", i)
        sys.exit(0)

    board[y][x] = 'I'

    tempMad = set()
    boomMad = set()
    for ry, rx in mad:
        board[ry][rx] = '.'
 
        nry = ry + getGap(y, ry)
        nrx = rx + getGap(x, rx)

        if (nry, nrx) in tempMad:
            boomMad.add((nry, nrx))
        else:
            tempMad.add((nry, nrx))

    for dry, drx in boomMad:
        tempMad.discard((dry, drx))

    for ry, rx in tempMad:
        if board[ry][rx] == 'I':
            print("kraj", i)
            sys.exit(0)

        board[ry][rx] = 'R'

    mad = tempMad

for bo in board:
    print(*bo, sep='')
