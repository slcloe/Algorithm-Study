import sys

input = sys.stdin.readline

# 이동방향 -> 대각선/상하좌우/제자리
dx = [-1, 0, 1, 0, 0, -1, 1, 1, -1]
dy = [0, 1, 0, -1, 0, 1, 1, -1, -1]

board = [list(input().strip()) for _ in range(8)]

q = [(7, 0)] # 시작 위치
for t in range(8): # 8번만 하면 모든 벽들이 사라짐 (그때까지 이동할 수 있으면 목적 위치까지 도착 가능)
    next = [] # 다음 turn에서 이동을 시작할 위치
    min_x = 8
    for x, y in q:
        for di in range(9):
            nx = x + dx[di]
            ny = y + dy[di]
            if -1 < nx < 8 and -1 < ny < 8:
                if board[nx - t][ny] == "#" or board[nx - t - 1][ny] == "#": # 현재 벽이 있거나 곧 벽이 내려올 경우
                    continue
                if (nx, ny) in next:
                    continue
                next.append((nx, ny))
                if min_x > nx:
                    min_x = nx
    if min_x <= t: # 아래에 벽이 없음
        print(1)
        break
    if not next:
        print(0)
        break
    q = next
else:
    print(1)

"""
접근 방식: 벽이 최대 8번만 움직이면 이후에는 무조건 도달할 수 있음
벽이 8번 동안 움직이면서 살아 남을 수 있는지 or 그 전에 도달할 조건에 만족하는지
조건-> 목적지부터 가는 길목에 벽이 없다(아래로 다 내려간 경우)
"""