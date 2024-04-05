import sys
sys.setrecursionlimit(10**9)
from collections import defaultdict
input=sys.stdin.readline

def main():
  def dfs(x):
    if not board[x]: # 리프노드이면 얼리어답터가 아님
      return 0,0
    res=0 # 자식 노드에서 얼리어답터 수
    child=0 # 얼리어답터인 자식의 수
    cnt=0 # 전체 자식의 수
    for nx in board[x]:
      if nx in visited: # 부모 노드
        continue
      cnt+=1
      visited.add(nx)
      cur=dfs(nx)
      visited.remove(nx)
      res+=cur[0]
      child+=cur[1]
    if child==cnt: # 모든 자식이 얼리어답터이면 부모는 얼리어답터일 필요없음
      return res,0
    else:
      return res+1,1


  N=int(input())
  board=defaultdict(list)
  visited=set()
  for _ in range(N-1):
    u,v=map(int,input().split())
    board[u].append(v)
    board[v].append(u)
  visited.add(1)
  print(dfs(1)[0])
main()

"""
풀이방법: dfs
현재 노드에서 두가지 경우
  1. 내가 반드시 얼리어답터
  2. 얼리어답터 아니어도 됨
1번의 경우는 자식들 중에서 하나라도 얼리어답터가 아닌 사람이 있음
2번의 경우는 모든 자식들이 얼리어답터임
++ 리프노드의 경우에는 부모노드가 얼리어답터야 최소가 됨
"""
