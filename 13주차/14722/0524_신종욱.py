import sys
input = sys.__stdin__.readline

def main(): 
    n = int(input())
    city = [list(map(int, input().split())) for _ in range(n)]

    dp = [[0 for j in range(n + 1)] for i in range(n + 1)]
    dp[0][0] = int(city[0][0] == 0)

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

            # 먹는 순서는 0, 1, 2, 0, 1, 2, ...로 정해져 있으므로
            # 현재까지 먹은 갯수 % 3이 city의 그것과 같다면
            # 현재 위치의 우유를 먹을 수 있는 경우에 해당한다.
            # 따라서 1을 더한다.
            if dp[i][j] % 3 == city[i - 1][j - 1]:
                dp[i][j] += 1

    print(dp[-1][-1])

main()
