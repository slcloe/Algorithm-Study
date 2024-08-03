import sys
input=sys.stdin.readline

def main():
    N=int(input())
    board=[list(map(int,input().split())) for _ in range(N)]
    dp=[[[0]*3 for _ in range(N)] for _ in range(N)]
    for j in range(1,N):
        if board[0][j]:
            break
        dp[0][j][0]=1
    for i in range(1,N):
        for j in range(1,N):
            if board[i][j]:
                continue
            dp[i][j][0]=dp[i][j-1][0]+dp[i][j-1][2]
            dp[i][j][1]=dp[i-1][j][1]+dp[i-1][j][2]
            if not board[i-1][j] + board[i][j-1]:
                dp[i][j][2]=sum(dp[i-1][j-1])

    print(sum(dp[N-1][N-1]))
main()


"""
끝점 i,j 좌표에 어떤 형태로 도착할 지 0:-,1:|,2:대각
"""
