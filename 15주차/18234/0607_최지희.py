import sys
input=sys.stdin.readline

N,T=map(int,input().split())
board=[list(map(int,input().split())) for _ in range(N)]
board.sort(key=lambda x:(-x[1],-x[0]))

ans=0
day=T-1
for w,p in board:
    ans+=w+p*day
    day-=1
print(ans)


"""
1. 당근을 먹지 않을 수도 있다
2. w<=p이다.
처음부터 먹는게 아니라 당근 수만큼만 먹으면 됨
p가 가장 큰 당근은 마지막에 키워서 먹어야한다
"""
