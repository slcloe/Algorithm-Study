import sys
from collections import defaultdict
input=sys.stdin.readline

def main():
  N,W=map(int,input().split())
  friends=defaultdict(list)
  for _ in range(N):
    S=input().strip()
    board=["X"]*(7*W+1)
    for i in range(7):
      cur=input().strip()
      for j in range(W):
        board[7*j+i]=cur[j]
    o=0 # 현재 스트릭 수
    f=0 # 프리즈
    x=0 # 스트릭 실패
    long_f=0 # 최장 스트릭 프리즈
    long_o=0 # 최장 스트릭
    long_d=0 # 최장 스트릭 시작
    for d,b in enumerate(board):
      if b=="X":
        x+=1
        tmp=f
        for nd in range(d-1,-1,-1):
          if board[nd]=="F":
            tmp-=1
          else:
            break
        if o>long_o or (o==long_o and tmp<long_f):
          long_o=o
          long_f=tmp
          long_d=d-(o+f)
        o=0
        f=0
      elif b=="O":
        o+=1
      elif b=="F":
        if o:
          f+=1
    friends[(-long_o,long_f,long_d,x-1)].append(S)
  cur=1
  for k in sorted(friends.keys()):
    for v in sorted(friends[k]):
      print(f"{cur}. {v}")
    cur+=1
main()

"""
고려한점
- 우선순위가 작아지는 경우 
    스트릭이 큰 경우와, 
    스트릭이 같고 프리징이 적은 경우도 함께 생각해야한다.
- 등수가 같은 조건이면 같은 등수이다.
마지막에 무조건 "X"를 넣어서 그동안의 스트릭을 계산하고 종료할 수 있도록 했다.
"""
