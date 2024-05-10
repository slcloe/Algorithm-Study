import sys
input=sys.stdin.readline

def dfs(idx,x,y):
  if dp[x][y][idx]!=-1:
    return dp[x][y][idx]
  if board[x][y]!=target[idx]:
    dp[x][y][idx]=0
    return 0
  if idx==0:
    dp[x][y][idx]=1
    return 1
  dp[x][y][idx]=0
  for dx,dy in d:
    for i in range(1,K+1):
      nx=x+dx*i
      ny=y+dy*i
      if -1<nx<N and -1<ny<M:
        dp[x][y][idx] += dfs(idx-1, nx, ny)
  return dp[x][y][idx]

d=[(0,1),(0,-1),(1,0),(-1,0)]
N,M,K=map(int,input().split())
board=[list(input().strip()) for _ in range(N)]

target=list(input().strip())
T=len(target)
dp=[[[-1]*T for _ in range(M)] for _ in range(N)]
ans=0
for r in range(N):
  for c in range(M):
    if board[r][c]==target[-1]:
      ans+=dfs(T-1,r,c)
print(ans)

"""
메모이제이션을 이용해서 이전에 탐색한 부분에 대해서는 구해둔 값을 이용한다.

???
처음에는 pypy로만 통과하고 python은 통과하지 못함
시간이 적게 나오는 코드를 확인하면 인덱스를 뒤에서부터 확인함
"""
