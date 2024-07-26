import sys
from collections import deque,defaultdict
input=sys.stdin.readline

def main():
    N,M=map(int,input().split())
    wall=[[[0]*4 for _ in range(N)] for _ in range(M)]
    board=[[0]*N for _ in range(M)]
    dx=[0,-1,0,1]
    dy=[-1,0,1,0]
    for i in range(M):
        tmp=list(map(int,input().split()))
        for j in range(N):
            for k in range(4):
                wall[i][j][k]=tmp[j]%2
                tmp[j]//=2
    neighbor=defaultdict(set)
    cnt=defaultdict(int)
    cur=0
    for i in range(M):
        for j in range(N):
            if not board[i][j]:
                cur+=1
                q=deque([(i,j)])
                cnt[cur]+=1
                board[i][j]=cur
                while q:
                    x,y=q.popleft()
                    for d in range(4):
                        nx=x+dx[d]
                        ny=y+dy[d]
                        if -1<nx<M and -1<ny<N:
                            if wall[x][y][d]:
                                if board[nx][ny] and board[nx][ny]!=cur:
                                    neighbor[board[nx][ny]].add(cur)
                                    neighbor[cur].add(board[nx][ny])
                            elif not board[nx][ny]:
                                board[nx][ny]=cur
                                cnt[cur]+=1
                                q.append((nx,ny))
    print(len(cnt.keys()))
    print(max(cnt.values()))
    ans=0
    for k,vs in neighbor.items():
        for v in vs:
            ans=max(ans,cnt[v]+cnt[k])
    print(ans)
main()

"""
bfs를 이용
3번: bfs돌면서 주변의 이웃들을 찾아 저장한다.
"""
