import sys
input=sys.stdin.readline
global max_rm,min_mv
def main():
  global max_rm,min_mv
  def dfs(rm,mv):
    global max_rm,min_mv
    for x in range(5):
      for y in range(9):
        if board[x][y]=="o":
          for dx,dy in d:
            nx=x+dx
            ny=y+dy
            if -1<nx<5 and -1<ny<9 and board[nx][ny]=="o":
              nnx=nx+dx
              nny=ny+dy
              if -1<nnx<5 and -1<nny<9 and board[nnx][nny]==".":
                board[x][y]="."
                board[nx][ny]="."
                board[nnx][nny]="o"
                dfs(rm+1,mv+1)
                board[x][y]="o"
                board[nx][ny]="o"
                board[nnx][nny]="."
    if max_rm<rm:
      max_rm=rm
      min_mv=mv
    elif max_rm==rm and min_mv>mv:
      min_mv=mv

  d=[(-1,0),(1,0),(0,-1),(0,1)]
  N=int(input())
  ans=[]
  for _ in range(N):
    board=[list(input().strip()) for _ in range(5)]
    _=input()
    max_rm=0
    min_mv=10e9
    dfs(0,0) #없앤 핀의 개수
    pins = sum(map(lambda row: row.count("o"), board))
    ans.append(f"{pins-max_rm} {min_mv}")
  print("\n".join(ans))
main()
