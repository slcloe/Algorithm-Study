import sys
input=sys.stdin.readline

def main():
    N,M=map(int,input().split())
    A=[list(map(int,input().split())) for _ in range(N)]
    d=[(0,0),(0,-1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1)]
    cloud={(N-1,0),(N-1,1),(N-2,0),(N-2,1)}
    for _ in range(M):
        dd,ss=map(int,input().split())
        # 1~3 구름 이동 + 비
        move_cloud=set()
        for cx,cy in cloud:
            cx=(cx+d[dd][0]*ss)%N
            cy=(cy+d[dd][1]*ss)%N
            A[cx][cy]+=1
            move_cloud.add((cx,cy))
        # 4 물복사버그
        for cx,cy in move_cloud:
            cnt=0
            for di in range(2,9,2):
                nx=cx+d[di][0]
                ny=cy+d[di][1]
                if -1<nx<N and -1<ny<N and A[nx][ny]:
                    cnt+=1
            A[cx][cy]+=cnt
        # 5 구름
        cloud=set()
        for i in range(N):
            for j in range(N):
                if A[i][j]>=2 and (i,j) not in move_cloud:
                    cloud.add((i,j))
                    A[i][j]-=2
    answer = 0
    for x in range(N):
        for y in range(N):
            answer += A[x][y]
    print(answer)
main()

"""
1~5번을 순서대로 구현
"""
