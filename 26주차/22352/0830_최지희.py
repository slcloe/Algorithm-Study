import sys
from collections import deque
input=sys.stdin.readline

def main():
    def check():
        changed=0
        for i in range(N):
            for j in range(M):
                if before[i][j]:
                    if after[i][j]!=before[i][j]:
                        changed+=1
                    cur=before[i][j]
                    flag=after[i][j]
                    q=deque([(i,j)])
                    before[i][j]=0
                    while q:
                        x,y=q.popleft()
                        for dx,dy in d:
                            nx=dx+x
                            ny=dy+y
                            if -1<nx<N and -1<ny<M:
                                if before[nx][ny]==cur:
                                    if flag!=after[nx][ny]:
                                        return "NO"
                                    q.append((nx,ny))
                                    before[nx][ny]=0
        return "YES" if changed<=1 else "NO"

    N,M=map(int,input().split())
    before=[list(map(int,input().split())) for _ in range(N)]
    after=[list(map(int,input().split())) for _ in range(N)]
    d=[(-1,0),(0,1),(1,0),(0,-1)]
    print(check())
main()

"""
아닌 조건
1. 같은 그룹내 다른 숫자인 경우
2. 색이 여러번 바뀐 경우
"""
