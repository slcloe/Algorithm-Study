import sys
from collections import deque
input=sys.stdin.readline
def main():
  d={"N":(-1,0),"E":(0,1),"W":(0,-1),"S":(1,0)}
  T=int(input())
  ans=[]
  for _ in range(T):
    M,N=map(int,input().split())
    board=[list(input().strip()) for _ in range(M)]
    pacman=[]
    for i in range(M):
      for j in range(N):
        if board[i][j]=="P":
          pacman.append(i)
          pacman.append(j)
          board[i][j]="."
    q=deque([(*pacman,0,"")]) #팩맨1,팩맨2,이동횟수,조작방향
    visited=set()
    visited.add((pacman[0],pacman[1],pacman[2],pacman[3]))
    visited.add((pacman[2],pacman[3],pacman[0],pacman[1]))
    while q:
      x1,y1,x2,y2,cnt,route=q.popleft()
      for k,v in d.items():
        nx1=(x1+v[0])%M
        ny1=(y1+v[1])%N
        nx2=(x2+v[0])%M
        ny2=(y2+v[1])%N
        if board[nx1][ny1]=="G" or board[nx2][ny2]=="G":
          continue
        if board[nx1][ny1]=="X":
          nx1=x1
          ny1=y1
        if board[nx2][ny2]=="X":
          nx2=x2
          ny2=y2
        if nx1==nx2 and ny1==ny2:
          ans.append(f"{cnt+1} {route+k}")
          break
        if (nx1,ny1,nx2,ny2) not in visited and (nx2,ny2,nx1,ny1) not in visited:
          visited.add((nx1,ny1,nx2,ny2))
          visited.add((nx2,ny2,nx1,ny1))
          q.append((nx1,ny1,nx2,ny2,cnt+1,route+k))
      else:
        continue
      break
    else:
      ans.append("IMPOSSIBLE")
  print("\n".join(ans))
main()

"""
고려할 점: 방문처리
bfs를 이용해서 팩맨의 위치를 탐색한다
방문처리를 할때 하나의 좌표가 아니라 다른 팩맨의 위치도 고려 해야 한다.
처음에는 좌표를 하나의 정수로 만들어 이차원 배열로 방문 처리를 했다.
  (x1,y1), (x2,y2) == x1*N+y1, x2*N+y2 ... 
매번 좌표를 계산 => 4차원 배열 혹은 튜플로 저장해서 집합으로 방문처리를 하는 것이 더 좋을 것이라고 생각
"""
