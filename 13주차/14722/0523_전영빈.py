"""
전형적인 dp 문제이지만 해당 좌표에서 우유를 마실 수도 있고 안 마실 수도 있다는 점이 까다롭다.
해당 좌표에서 어떤 주스를 마셨는지가 아닌 해당 좌표에서 마지막으로 마신 주스가 어떤 것인지를 기준으로 dp를 진행해야 한다.
오른쪽 혹은 아래로만 이동이 가능하므로 행 우선으로 영역을 탐색하며 dp를 진행했다.

우선 도착한 좌표에 대해 좌표에서 우유를 마시지 않은 경우를 계산했다.
우유를 마시지 않는 경우는 마지막으로 마신 주스가 변하지 않으므로 증감 없이 이전 dp 최댓값을 이어 받으면 된다.
이후, 도착한 좌표에서 우유를 마신 경우를 계산했다.
우유를 마시기 위해서는 이번 좌표에서 규칙에 맞는 이전 차례의 우유를 마신 dp 최댓값을 이어 받고 1만큼 증가시키면 된다.

이 때, 주의할 것은 우유를 마시는 규칙은 딸기 우유를 먹음으로서 시작된다는 것.
따라서, 해당 좌표가 딸기 우유가 아니고 dp 최댓값이 1인 경우 규칙을 시작할 수 없으므로 dp 최댓값을 0으로 롤백해주었다.
"""

import sys

input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
dp = [[[0 for _ in range(3)] for _ in range(N+1)] for _ in range(N+1)]

for i in range(1, N+1):
    for j in range(1, N+1):
        # non drink
        for milk in range(3):
            prev = (3 + milk - 1) % 3
            dp[i][j][milk] = max(dp[i-1][j][milk], dp[i][j-1][milk])

        # drink
        current = board[i-1][j-1]
        prev = (3 + current - 1) % 3
        dp[i][j][current] = max(dp[i-1][j][prev], dp[i][j-1][prev]) + 1

        # strawberry init check
        if max(dp[i][j]) == 1 and board[i-1][j-1] != 0:
            dp[i][j][current] = 0

print(max(dp[N][N]))
