import sys
input= sys.stdin.readline
def main():
    d=[(-1,0),(0,1),(1,0),(0,-1)]
    N,K=map(int,input().split())
    board=[list(map(int,input().split())) for _ in range(N)]
    S,X,Y=map(int,input().split())
    q=[]
    for i in range(N):
        for j in range(N):
            if board[i][j]:
                q.append((board[i][j],i,j))
    q.sort()
    for _ in range(S):
        nq=[]
        for v,x,y in q:
            for dx,dy in d:
                nx=x+dx
                ny=y+dy
                if -1<nx<N and -1<ny<N and not board[nx][ny]:
                    board[nx][ny]=v
                    nq.append((v,nx,ny))
        if board[X-1][Y-1]:
            break
        q=sorted(nq)
    print(board[X-1][Y-1])
main()

"""
바이러스가 매초마다 새로 이동하는 위치를 저장한다
작은 번호부터 이동을 한다고 했으니 정렬을 통해 이동 순서를 정해둔다
"""
