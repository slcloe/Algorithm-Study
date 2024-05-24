import sys
input=sys.stdin.readline

N=int(input())
board=[list(map(int,input().split())) for _ in range(N)]
dp=[[0]*(N+1) for _ in range(N+1)]
if not board[0][0]:
    dp[0][0]=1
for i in range(1,N+1):
    for j in range(1,N+1):
        dp[i][j]=max(dp[i-1][j],dp[i][j-1])
        if dp[i][j]%3==board[i-1][j-1]:
            dp[i][j]+=1
print(dp[-1][-1])

"""
이동방향이 x,y가 증가하는 방향이다
0,1,2 순서대로 먹고 있다
  현재 DP의 값은 몇개 먹었는지를 알 수 있다.
  dp[i][j]%3을 하면
    0 -> 현재 2번까지 먹음
    1 -> 현재 0번까지 먹음
    2 -> 현재 1번까지 먹음 인것을 알 수 있다.
"""
