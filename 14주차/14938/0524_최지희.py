import sys
from collections import defaultdict
import heapq
input=sys.stdin.readline

def main():
  def dijkstra(start):
    dist=[1e9]*n
    dist[start]=0
    hq=[(0,start)]
    while hq:
      cost,x=heapq.heappop(hq)
      if dist[x]<cost:
        continue
      for nx,nc in board[x]:
        if nc+dist[x]<dist[nx]:
          dist[nx]=nc+dist[x]
          heapq.heappush(hq,(nc+dist[x],nx))
    cur=0
    for x in range(n):
      if dist[x]<=m:
        cur+=item[x]
    return cur
  n,m,r=map(int,input().split())
  item=list(map(int,input().split()))
  board=defaultdict(list)
  for _ in range(r):
    a,b,l=map(int,input().split())
    board[a-1].append((b-1,l))
    board[b-1].append((a-1,l))
  ans=0
  for i in range(n):
    ans=max(ans,dijkstra(i))
  print(ans)  
main()

"""
다익스트라를 이용해서 각 점에서 최소거리를 구한다
최소거리가 수색범위안에 들어가는지 확인
"""
