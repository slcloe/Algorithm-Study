import sys
input=sys.stdin.readline

N=int(input())

board=list(map(int,input().split()))
board.sort()
start_idx={board[0]:0}
for i in range(1,N):
  if board[i-1]!=board[i]:
    start_idx[board[i]]=i
ans=0
for i in range(N-2):
  target=-board[i]
  s=i+1
  e=N-1
  same_idx=N
  while s<e:
    cur=board[s]+board[e]
    if cur<target:
      s+=1
    elif cur>target:
      e-=1
    else:
      if board[s]==board[e]:
        ans+=e-s
      else:
        ans+=e-start_idx[board[e]]+1
      s+=1
print(ans)

"""
pypy로 풀이
미리 몇개의 수가 있는지 저장해두고 사용
세개중에 가장 작은값과 나머지 두개를 투포인터를 이용해서 값을 찾는다
"""
