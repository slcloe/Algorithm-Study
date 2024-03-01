"""
!! 아직 못품 !!

먼지가 있는 장소로 이동할 경우 다시 방문할 여지가 반드시 있으므로 방문 체크 하지 않음.
로봇 청소기가 종료될 경우는 영역 밖으로 이동하거나, 먼지가 없는 장소들만으로 이어진 사이클이 존재하는 경우.
먼지가 없는 장소들로만 이루어진 사이클을 확인하기 위해서는 먼지가 없는 장소로 접근할 때 같은 방향으로 방문하는지를 확인하면 된다.
=> 방문 배열에 depth를 하나 더 두어 어떤 방향에서 방문하는지를 확인.
"""
import sys

input = sys.stdin.readline

dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

H, W = map(int, input().split())
cy, cx, direction = map(int, input().split())
rule = [[list(map(int, input().rstrip())) for _ in range(H)] for _ in range(2)]

dust = [[True for _ in range(W)] for _ in range(H)]
visited = [[[False for _ in range(W)] for _ in range(H)] for _ in range(4)]

count = 0
tempCount = 0

def getDirection(cd ,y, x, t):
    return (cd + rule[t][y][x]) % 4

def isMovable(y, x):
    if not (0 <= y < H and 0 <= x < W):
        return False

    return True

def isVisited(y, x, d):
    if visited[d][y][x]:
        return False
    
    return True

while True:
    if dust[cy][cx]:
        dust[cy][cx] = False

        direction = getDirection(direction, cy, cx, 0)
        ny, nx = cy + dy[direction], cx + dx[direction]

        if not isMovable(ny, nx):
            count += 1
            break

        if not isVisited(ny, nx, direction):
            break

        cy, cx = ny, nx
        count += tempCount + 1
        tempCount = 0

    else:
        visited[direction][cy][cx] = True

        direction = getDirection(direction, cy, cx, 1)
        ny, nx = cy + dy[direction], cx + dx[direction]

        if not isMovable(ny, nx):
            count += 1
            break

        if not isVisited(ny, nx, direction):
            break

        cy, cx = ny, nx
        tempCount += 1

    print(f"y : {cy}, x : {cx}, count : {count}, tc : {tempCount}, positon : {dust[cy][cx]}")

print(count)
