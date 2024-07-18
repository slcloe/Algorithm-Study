import sys
input=sys.stdin.readline

def main():
    board=list(map(int,list(input().strip())))
    n=len(board)
    dp=[[0,0] for _ in range(n)]
    dp[0][0]=1
    for i in range(1,n):
        # 한자리
        if board[i]:
            dp[i][0]=dp[i-1][0]+dp[i-1][1]
        # 두자리
        if 10<=board[i-1]*10+board[i]<=34:
            dp[i][1]=dp[i-1][0] # dp[i-2][0]+dp[i-2][1]
    print(sum(dp[-1]))
main()

# 현재 자리 포함 한자리/두자리를 만들수 있는지 고려
# '0' 카드가 없다는 것을 고려
