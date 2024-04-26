import sys
import heapq
from collections import defaultdict
input=sys.stdin.readline

def dijkstra(i,j):
  dist=[1e9]*N
  dist[0]=0
  q=[(0,0)]
  while q:
    cnt,x=heapq.heappop(q)
    if dist[x]<cnt:
      continue
    for nx,ncnt in board[x]:
      if x==i and nx==j:
        continue
      if ncnt+cnt<dist[nx]:
        if i==-1:
          prior[nx]=x
        dist[nx]=ncnt+cnt
        heapq.heappush(q,(cnt+ncnt,nx))
  return dist[-1]
N,M=map(int,input().split())
board=defaultdict(list)
for _ in range(M):
  a,b,t=map(int,input().split())
  board[a-1].append((b-1,t))
  board[b-1].append((a-1,t))

prior=[-1]*N
origin=dijkstra(-1,-1)
ans=0
post=N-1
while post!=-1:
  block=dijkstra(prior[post],post)
  if block>=1e9:
    print(-1)
    break
  if ans<block-origin:
    ans=block-origin
  post=prior[post]
else:
  print(ans)

"""
다익스트라와 최단경로를 저장해서 사용하는 문제

다익스트라가 갱신되는 조건을 생각해보면 현재 지점 직전에 방문한 지점을 찾을 수 있다.
-> 갱신할 때마다 해당 값을 저장해둔다.
-> 이렇게 되면 N-1지점부터 시작해서 prior배열에서 이어진 도로를 찾을 수 있다.

이어진 도로를 하나씩 지워보면서 최대값을 찾는다.
"""
