import sys
import heapq
input=sys.stdin.readline

def main():
  n=int(input())
  board=[]
  for _ in range(n):
    h,o=map(int,input().split())
    if h>o:
      h,o=o,h
    board.append((h,o)) # h<o
  d=int(input())
  board.sort(key=lambda x:x[1])
  ans=0
  hq=[] # 현재 종료 지점에서 가능한 점들
  for start,end in board: # 종료지점기준
    if end-start>d:
      continue
    while hq and end-d>hq[0][0]: # 불가능한 점들을 제외
      heapq.heappop(hq)
    heapq.heappush(hq,(start,end))
    ans=max(ans,len(hq))
  print(ans)
main()

"""
(start,end)에서 end 기준으로 왼쪽 방향으로 선분을 만든다고 생각하면
선분안의 모든 값들은 end-d<=x<=end 이다.
1. start 값이 만족하지 않는 경우 제외
2. end를 기준으로 오름차순 정렬을 하면 일단 hq안에 들어있는 값듩(xi) 중에서 x[0]값이 조건을 만족하는지만 체크하면 된다.
  - 일단 모든 x[0], x[1]는 x[0]<x[1]<=end 이다.
  - x[0]가 end-d<=x[0]이면 (x[0],x[1])=> end-d<=x[0]<x[1]<=end 이다.
"""
