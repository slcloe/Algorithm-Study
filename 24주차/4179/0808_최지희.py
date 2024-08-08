import sys
from collections import deque
input=sys.stdin.readline

def main():
    d=[(-1,0),(0,1),(1,0),(0,-1)]
    R,C=map(int,input().split())
    board=[]
    fq=deque()
    fl=0
    jq=deque()
    jl=0
    for i in range(R):
        board.append(list(input().strip()))
        for j in range(C):
            if board[i][j]=="J":
                jq.append((i,j))
                jl+=1
            elif board[i][j]=="F":
                fq.append((i,j))
                fl+=1
    ans=1
    while jq:
        nfl=0
        for _ in range(fl):
            fx,fy=fq.popleft()
            for dx,dy in d:
                nx=fx+dx
                ny=fy+dy
                if -1<nx<R and -1<ny<C:
                    if board[nx][ny]!="F" and board[nx][ny]!="#":
                        board[nx][ny]="F"
                        fq.append((nx,ny))
                        nfl+=1
        fl=nfl
        njl=0
        for _ in range(jl):
            jx,jy=jq.popleft()
            for dx,dy in d:
                nx=jx+dx
                ny=jy+dy
                if -1<nx<R and -1<ny<C:
                    if board[nx][ny]==".":
                        board[nx][ny]="J"
                        jq.append((nx,ny))
                        njl+=1
                else:
                    return ans
        jl=njl
        ans+=1
    return "IMPOSSIBLE"

print(main())

"""
bfs를 이용하여 탐색
지훈이가 돌기 전에 불을 먼저 탐색
"""
