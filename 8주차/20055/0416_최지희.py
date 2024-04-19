import sys
from collections import deque
input=sys.stdin.readline

def main():
  N,K=map(int,input().split())
  A=deque(map(int,input().split()))
  R=deque([0 for _ in range(N)])
  zeros=A.count(0)
  ans=0
  while zeros<K:
    ans+=1
    R[-1]=0
    R.rotate()
    A.rotate()
    R[-1]=0
    for i in range(N-2,-1,-1):
      if R[i] and not R[i+1] and A[i+1]:
        R[i]=0
        R[i+1]=1
        A[i+1]-=1
        if not A[i+1]:
          zeros+=1
    if A[0]:
      R[0]=1
      A[0]-=1
      if not A[0]:
        zeros+=1
  print(ans)
main()

"""
deque의 rotate를 이용 -  배열을 회전 시켜줌
"""
