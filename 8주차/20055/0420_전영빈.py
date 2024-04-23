"""
한 번의 반복문에 벨트는 한 칸만큼 이동하지만 벨트 위의 로봇들은 두 칸만큼 이동한다.
따라서, 벨트의 내구도와 로봇을 다른 배열로 관리했다.
또한, 로봇은 1번 위치에서 생성되어 N번 위치에서 제거되므로
벨트를 관리하는 배열의 크기가 2N인 반면 로봇은 크기가 N인 배열로 관리할 수 있다.
"""

import sys
from collections import deque

input = sys.stdin.readline

N, K = map(int, input().split())

belt = deque(list(map(int, input().split())))
robot = deque([False for _ in range(N)])

solve = 0
count = 0

while True:
    solve += 1

    belt.rotate(1)
    robot.rotate(1)

    robot[N-1] = False

    for i in range(N - 2, -1, -1):
        if robot[i] and not robot[i+1] and belt[i+1] > 0:
            robot[i] = False
            robot[i+1] = True
            belt[i+1] -= 1

            if belt[i+1] == 0:
                count += 1

    robot[N-1] = False

    if belt[0] > 0:
        robot[0] = True
        belt[0] -= 1

        if belt[0] == 0:
            count += 1

    if count >= K:
        break

print(solve)
