import sys
from collections import deque
input=sys.stdin.readline

def main():
    R,C=map(int,input().split())
    board=[list(input().strip()) for _ in range(R)]
    N=int(input())
    throw=list(map(int,input().split()))
    d=[(-1,0),(0,1),(1,0),(0,-1)]
    
    for t in range(N):
        i=R-throw[t]
        destroy=0
        if t%2:
            for j in range(C-1,-1,-1):
                if board[i][j]=="x":
                    board[i][j]="."
                    destroy=1
                    break
        else:
            for j in range(C):
                if board[i][j]=="x":
                    board[i][j]="."
                    destroy=1
                    break
        if not destroy:
            continue
        for di,dj in d:
            ni=di+i
            nj=dj+j
            if -1<ni<R and -1<nj<C and board[ni][nj]=="x":
                q=deque([(ni,nj)])
                visited=set()
                visited.add((ni,nj))
                while q:
                    x,y=q.popleft()
                    if x==R-1:
                        break
                    for dx,dy in d:
                        nx=dx+x
                        ny=dy+y
                        if -1<nx<R and -1<ny<C:
                            if board[nx][ny]=="x" and (nx,ny) not in visited:
                                q.append((nx,ny))
                                visited.add((nx,ny))
                else:
                    px=-1
                    diff=C
                    for y in range(C):
                        for x in range(R):
                            if board[x][y]=="x":
                                if (x,y) in visited:
                                    px=x
                                else:
                                    if px!=-1:
                                        diff=min(diff,x-px-1)
                                        px=-1
                        if px!=-1:
                            diff=min(diff,x-px)
                            px=-1
                    for y in range(C):
                        for x in range(R-1,-1,-1):
                            if (x,y) in visited:
                                board[x+diff][y]="x"
                                board[x][y]="."
    
    for b in board:
        print("".join(b))
main()
"""
없어진 미네랄의 4방향만 탐색하면 됨
bfs를 순차적으로 돌려서 한 그룹씩 아래로 내리면 됨
-> 만약에 다른 그룹1이랑 만나면 그룹1에 포함됨 -> 만약에 그룹1이 이동하게되면 같이 이동 
"""
