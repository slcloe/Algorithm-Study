import sys
from collections import defaultdict
input=sys.stdin.readline
def main():
  T=int(input())
  answer=[]
  for _ in range(T):
    n=int(input())
    board=defaultdict(list)
    for _ in range(n):
      x,y=map(int,input().split())
      board[x].append(y)
    last=0
    cur=[]
    for k in sorted(board.keys()):
      if max(board[k])<=last:
        board[k].sort(reverse=True)
      else:
        board[k].sort()
      for v in board[k]:
        cur.append(f"{k} {v}")
      last=board[k][-1]
    cafe=list(map(int,input().split()))
    for c in cafe[1:]:
      answer.append(cur[c-1])
  print("\n".join(answer))
main()

"""
x좌표는 작아지지 않는다 -> y좌표만 고려해주면 된다.
- 같은 x를 가진 좌표끼리 모아서
- 현재 i 번째 x값이라고 할때
  i-1번째 종료지점이 현재 리스트의 y값들보다 큰 경우에는 내림차순 
  작은 경우에는 오름차순 정렬을 해주었다.
"""
