import sys
from collections import defaultdict
import heapq
input=sys.stdin.readline

def main():
    def wd():
        dist=[[1e9,1e9] for _ in range(N)]
        dist[0][0]=0
        hq=[]
        heapq.heappush(hq,(0,0,0))
        while hq:
            cost,x,flag=heapq.heappop(hq)
            nf=abs(flag-1)
            if dist[x][flag]<cost:
                continue
            for nx,nc in board[x]:
                if nf:
                    nc/=2
                else:
                    nc*=2
                if dist[nx][nf]>cost+nc:
                    dist[nx][nf]=cost+nc
                    heapq.heappush(hq,(cost+nc,nx,nf))
        return dist

    def fd():
        dist=[1e9]*N
        dist[0]=0
        hq=[]
        heapq.heappush(hq,(0,0))
        while hq:
            cost,x=heapq.heappop(hq)
            if dist[x]<cost:
                continue
            for nx,nc in board[x]:
                if dist[nx]>cost+nc:
                    dist[nx]=cost+nc
                    heapq.heappush(hq,(cost+nc,nx))
        return dist

    N,M=map(int,input().split())
    board=defaultdict(list)
    for _ in range(M):
        a,b,d=map(int,input().split())
        board[a-1].append((b-1,d))
        board[b-1].append((a-1,d))
    w=wd()
    f=fd()
    ans=0
    for i in range(N):
        if min(w[i])>f[i]:
            ans+=1
    print(ans)
main()

"""
다익스트라
이전의 조건에 따라서 값이 달라지는 경우이기 때문에
이전의 경우 두가지를 나눠서 계산 (flag)
    flag: 0: 속도 절반x, 1: 속도 두배
"""
