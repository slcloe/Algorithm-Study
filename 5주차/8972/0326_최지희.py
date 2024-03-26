import sys
input=sys.stdin.readline
def main():
  R,C=map(int,input().split())
  dx=[0,1,1,1,0,0,0,-1,-1,-1]
  dy=[0,-1,0,1,-1,0,1,-1,0,1]
  board=[list(input().strip()) for _ in range(R)]
  cmd=list(map(int,list(input().strip())))
  arduino=set()
  jx=0
  jy=0
  for i in range(R):
    for j in range(C):
      if board[i][j]=="I":
        jx=i
        jy=j
      elif board[i][j]=="R":
        arduino.add((i,j))
      else:
        continue
      board[i][j]="."
  for i,c in enumerate(cmd):
    jx+=dx[c]
    jy+=dy[c]
    if (jx,jy) in arduino:
      print(f"kraj {i+1}")
      return
    explode=set()
    new=set()
    for ax,ay in arduino:
      cx=jx-ax
      cy=jy-ay
      if cx:
        ax+=cx//abs(cx)
      if cy:
        ay+=cy//abs(cy)
      if ax==jx and ay==jy:
        print(f"kraj {i+1}")
        return
      if (ax,ay) in explode:
        continue
      elif (ax,ay) in new:
        new.remove((ax,ay))
        explode.add((ax,ay))
      else:
        new.add((ax,ay))
      arduino=new
  board[jx][jy]="I"
  for ax,ay in arduino:
    board[ax][ay]="R"
  for b in board:
    print("".join(b))
main()

"""
set 자료구조를 이용
좌표평면에서 하는 것이 아닌 아누이노의 좌표를 집합으로 저장 -> 매번 좌평평면에 값을 넣고 뺄 필요가 없음
explode, new에 해당하는 값을 넣어야함 (이때 순서에 주의)

가까워진다 == x,y좌표가 가까운 쪽으로 한칸씩 이동한다
이때 종수 위치에서 아두이노의 좌표를 빼면 양으로 갈지 음으로 갈지 정해짐 + 한칸 씩 움직인다고 했으므로 절대값으로 나눠줌
"""