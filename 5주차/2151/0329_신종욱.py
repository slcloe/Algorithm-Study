import sys
input = sys.__stdin__.readline

from collections import deque

def main():
    n = int(input())

    house = []
    door_loc = []
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    for i in range(n):
        house.append(list(input().rstrip()))
        for j in range(n):
            if house[i][j] == '#':
                door_loc.append((i, j))

    x1, y1, x2, y2 = door_loc[0][0], door_loc[0][1], door_loc[1][0], door_loc[1][1]
    queue = deque([(door_loc[0], 0, direction) for direction in range(4)])

    while queue:
        (cx, cy), mirror_count, direction = queue.popleft()
        
        nx = cx + dx[direction]
        ny = cy + dy[direction]
            
        while (0 <= nx < n) and (0 <= ny < n) and (house[nx][ny] != '*'):
            if house[nx][ny] == "!":
                if direction == 0 or direction == 2:
                    queue.append(((nx,ny), mirror_count + 1, 1))
                    queue.append(((nx,ny), mirror_count + 1, 3))
                else:
                    queue.append(((nx,ny), mirror_count + 1, 0))
                    queue.append(((nx,ny), mirror_count + 1, 2))

            if nx == x2 and ny == y2:
                queue.clear()
                break

            nx += dx[direction]
            ny += dy[direction]        

    print(mirror_count)


main()