"""
한 주사위의 아랫면이 정해지는 순간 윗 주사위의 아랫면 또한 정해진다.
따라서 가장 아래의 주사위의 아랫면을 정하는 순간 모든 주사위의 아랫면과 네 방향의 옆면이 정해진다.
주사위의 눈의 개수는 6개이므로 가장 아래의 주사위 아랫면이 가질 수 있는 여섯 가지 경우에 대해
얻을 수 있는 최대값을 구하면 된다.
"""
import sys

input = sys.stdin.readline

N = int(input())
dice = [list(map(int, input().split())) for _ in range(N)]

choosen = [(1, 2, 3, 4),
            (0, 2, 4, 5),
            (0, 1, 3, 5),
            (0, 1, 3, 5),
            (0, 2, 4, 5),
            (1, 2, 3, 4)
            ]
next = [5, 3, 4, 1, 2, 0]
sol = 0

for start in range(1, 7):
    res = 0
    current = start

    for i in range(N):
        idx = dice[i].index(current)
        res += max(dice[i][choosen[idx][0]], dice[i][choosen[idx][1]], dice[i][choosen[idx][2]], dice[i][choosen[idx][3]])

        current = dice[i][next[idx]]

    print(f"start : {start}, res : {res}")
    sol = max(sol, res)

print(sol)
