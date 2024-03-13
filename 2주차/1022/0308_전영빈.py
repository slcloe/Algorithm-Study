"""
처음엔 그냥 단순히 허리케인 배열을 만들고 좌표값을 적절히 조절하여 출력하는 식으로 해결하려 했다.
하지만 좌표 값의 범위가 n <= |5000| 이므로 심할 경우 10000^10000의 배열을 만들어야 하기 때문에
메모리 초과가 날 것이라고 판단했다.
따라서 필요한 크기만큼 배열을 선언한 뒤, 허리케인 진행 방식으로 좌표 값 순회를 하며
해당 배열의 좌표 값에 포함될 때만 배열을 갱신했다.
"""
import sys

input = sys.stdin.readline

r1, c1, r2, c2 = map(int, input().split())
height = r2 - r1 + 1
width = c2 -c1 + 1

board = [[0 for _ in range(width)] for _ in range(height)]
endPoint = height * width
visited = 0
count = 2

def positionCheck(y, x):
    if 0 <= y < height and 0 <= x < width:
        return True
    
    return False

cy = -r1
cx = -c1
if positionCheck(cy, cx):
    board[cy][cx] = 1
    visited += 1

dy = [0, -1, 0, 1]
dx = [1, 0, -1, 0]
direction = 0
length = 1

while visited < endPoint:
    for _ in range(length):
        cy += dy[direction]
        cx += dx[direction]

        if positionCheck(cy, cx):
            board[cy][cx] = count
            visited += 1

        if (visited == endPoint):
            break

        count += 1

    if direction == 1 or direction == 3:
        length += 1

    direction = (direction + 1) % 4

digit = len(str(count))

for bo in board:
    for b in bo:
        print(str(b).rjust(digit, ' '), end=" ")

    print()
