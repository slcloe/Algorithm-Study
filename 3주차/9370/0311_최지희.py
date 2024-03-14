import sys
import heapq
from collections import defaultdict

input=sys.stdin.readline

def main():
    def dijkstra(start):
        dist=[1e9]*(n+1)
        dist[start]=0
        hq=[]
        heapq.heappush(hq,(0,start))
        while hq:
            cost,x=heapq.heappop(hq)
            if dist[x]<cost:
                continue
            for nx,nc in board[x]:
                tmp=cost+nc
                if tmp<dist[nx]:
                    dist[nx]=tmp
                    heapq.heappush(hq,(tmp,nx))
        return dist

    T=int(input())
    for _ in range(T):
        n,m,t=map(int,input().split())
        s,g,h=map(int,input().split())
        board=defaultdict(list)
        for _ in range(m):
            a,b,d=map(int,input().split())
            board[a].append((b,d))
            board[b].append((a,d))
        cand=[int(input()) for _ in range(t)]
        dist1=dijkstra(s)
        last=h
        if dist1[g]>dist1[h]:
            last=g
        dist2=dijkstra(last)
        ans=[]
        for c in cand:
            if dist1[c]==dist1[last]+dist2[c]:
                ans.append(c)
        print(*sorted(ans))
main()

"""
접근 방법: 다익스트라를 두개의 정점에서 계산한다.

dist1: s에서 시작해서 모든 정점으로의 최단 거리
-> dist[g], dist[h] 중에서 큰값이 도로의 마지막 점이다.
(dist1은 각 점에서 최단 거리이므로 한 점을 거친 값이 더 클 수 밖에 없다)
dist2: g,h중에서 마지막점에서 시작해서 모든 정점으로의 최단 거리

가능한 경우: s->t 거리가 s->last->t와 같은 경우
"""