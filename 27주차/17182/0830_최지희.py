import sys
input=sys.stdin.readline

global ans
def main():
    global ans
    def dfs(cur,dist,cnt):
        global ans
        if cnt==N:
            ans=min(dist,ans)
            return
        for x in range(N):
            if not visited[x]:
                visited[x]=1
                dfs(x,dist+board[cur][x],cnt+1)
                visited[x]=0

    N,K=map(int,input().split())
    board=[list(map(int,input().split())) for _ in range(N)]

    for k in range(N):
        for i in range(N):
            for j in range(N):
                board[i][j]=min(board[i][j],board[i][k]+board[k][j])
    ans=10e9
    visited=[0]*N
    visited[K]=1
    dfs(K,0,1)
    print(ans)

main()

"""
최소거리라는 조건이 없으므로 각 점에서의 최소거리로 만든다음에 풀이
dfs를 통해 완전탐색
"""
