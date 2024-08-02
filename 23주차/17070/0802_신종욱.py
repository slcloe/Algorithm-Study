import sys
input = sys.__stdin__.readline

def main():
    def vsum(*args):
        s = 0
        for arg in args:
            s += arg

        return s

    n = int(input())
    house = [list(map(int, input().split())) for _ in range(n)]


    # direction, i, j
    # 현재 파이프가 놓여 있는 방향과 위치 정보
	# direction은 0: 가로, 1: 세로, 2: 대각선
    dp = [[[0 for z in range(n)] for y in range(n)] for x in range(3)]
    
    # Sentinel
    for j in range(1, n):
        if house[0][j]:
            break
        dp[0][0][j] = 1

    for i in range(1, n):
        for j in range(1, n):
            if house[i][j] == 1:
                continue
        
            # 움직인 결과가 가로인 경우
            ### 가로로 있던 걸 그냥 민 경우
            ### 대각선으로 있던 걸 돌린 경우
            dp[0][i][j] += vsum(dp[0][i][j - 1], dp[2][i][j - 1])

            # 움직인 결과가 세로인 경우	
            ### 세로로 있던 걸 그냥 민 경우
            ### 대각선으로 있던 걸 돌린 경우
            dp[1][i][j] += vsum(dp[1][i - 1][j], dp[2][i - 1][j]);

            # 움직인 결과가 대각선인 경우
            if house[i - 1][j] != 1 and house[i][j - 1] != 1:
                dp[2][i][j] += vsum(
                    dp[0][i - 1][j - 1], # 가로로 있던 걸 돌린 경우
                    dp[1][i - 1][j - 1], # 세로로 있던 걸 돌린 경우
                    dp[2][i - 1][j - 1]  # 대각선으로 있던 걸 그냥 민 경우
                )
        
    print(
        vsum(
            dp[0][-1][-1],
            dp[1][-1][-1],
            dp[2][-1][-1]
        )
    )

main()
