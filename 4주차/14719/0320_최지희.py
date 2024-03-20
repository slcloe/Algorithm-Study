import sys
input=sys.stdin.readline

H,W=map(int,input().split())
board=list(map(int,input().split()))

ans=0
for i in range(1,W-1):
  h=min(max(board[:i]),max(board[i+1:]))
  if board[i]<h:
    ans+=h-board[i]
print(ans)

"""
자신의 양옆에 자신보다 큰 값이 있어야지 채울 수 있다.
그리고 그 중에서 작은 값이 해당 idx에서 채울 수 있는 최대 깊이 이다.(넘치게 채울수는 없으니)
"""