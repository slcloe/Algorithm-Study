import sys
input = sys.__stdin__.readline


def main():
    n = int(input())
    grid = [list(map(int, input().split())) for _ in range(n)]

    directions = [
        (0, -1),
        (1, 0),
        (0, 1),
        (-1, 0)
    ]


    def range_check(i, j):
        return (0 <= i < n) and (0 <= j < n)

    def spread(direction, grid, ci, cj):
        # 기준 위치는 y
        # 각각 흩어질 위치의 x, y좌표와 그 비율이다.
        # 순서는 directions의 그것과 일치해야 한다.
        proportions = [
            [ # 좌
                (-2, 0, 0.02),
                (-1, -1, 0.10), (-1, 0, 0.07), (-1, 1, 0.01),
                (0, -2, 0.05),
                (1, -1, 0.10), (1, 0, 0.07), (1, 1, 0.01),
                (2, 0, 0.02),
            ], 
            [ # 하
                (-1, -1, 0.01), (-1, 1, 0.01),
                (0, -2, 0.02), (0, -1, 0.07), (0, 1, 0.07), (0, 2, 0.02),
                (1, -1, 0.10), (1, 1, 0.10),
                (2, 0, 0.05),
            ],
            [ # 우
                (-2, 0, 0.02),
                (-1, -1, 0.01), (-1, 0, 0.07), (-1, 1, 0.10),
                (0, 2, 0.05),
                (1, -1, 0.01), (1, 0, 0.07), (1, 1, 0.10),
                (2, 0, 0.02)
            ],
            [ # 상
                (-2, 0, 0.05),
                (-1, -1, 0.10), (-1, 1, 0.10),
                (0, -2, 0.02), (0, -1, 0.07), (0, 1, 0.07), (0, 2, 0.02),
                (1, -1, 0.01), (1, 1, 0.01),
            ],
        ]

        ans = 0

        # old에서 일정 비율씩 흩어진다.
        # 한꺼번에 흩어지는 게 아니기 때문에
        # grid[ci][cj] 값을 미리 저장하지 않으면
        # 앞에서 흩어지고 남은 부분에서 일정 비율 날아가게 된다.
        old = grid[ci][cj]
        for proportion in proportions[direction]:
            ni, nj = ci + proportion[0], cj + proportion[1]
            
            val = int(old * proportion[2])

            grid[ci][cj] -= val
            if not range_check(ni, nj):
                ans += val
            else:
                grid[ni][nj] += val

        ni, nj = ci + directions[direction][0], cj + directions[direction][1]
        if not range_check(ni, nj):
            ans += grid[ci][cj]
        else:
            grid[ni][nj] += grid[ci][cj]

        grid[ci][cj] = 0
        
        # 격자 바깥으로 흩어진 모래 양의 합을 반환한다.
        return ans


    def solve(n, grid):
        ci, cj = n // 2, n // 2
        step = 1
        answer = 0

        while step <= n:
            for direction in range(4):
                for s in range(step):
                    ci += directions[direction][0]
                    cj += directions[direction][1]

                    if not range_check(ci, cj):
                        print(answer)
                        return

                    answer += spread(direction=direction, grid=grid, ci=ci, cj=cj)

                # direction = 0 ~ 3은 각각 왼쪽, 오른쪽, 위, 아래를 나타내며
                # step만큼 왼쪽, 아래로 이동한 후
                # step + 1만큼 오른쪽, 위로 이동한다.
                # 그리고 다시 step + 2만큼 왼쪽 아래로 이동하는 패턴을 반복한다.
                if direction & 1:
                    step += 1

    solve(n=n, grid=grid)

main()
