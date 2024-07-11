import sys
input=sys.stdin.readline
def main():
    N=int(input())
    board=[int(input()) for _ in range(N)]
    dp=[1]*N
    for i in range(N):
        for j in range(i):
            if board[i]>board[j]:
                dp[i]=max(dp[i],dp[j]+1)
    print(N-max(dp))
main()

# 가장 긴 부분증가수열 찾기
