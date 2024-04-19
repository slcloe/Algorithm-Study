import copy
from collections import deque
def solution(maze):
  def to_int(x,y):
    return x*m+y
  def to_tuple(x):
    return x//m,x%m
  n=len(maze)
  m=len(maze[0])
  flags=[0]*6
  for i in range(n):
    for j in range(m):
      flags[maze[i][j]]=i*m+j
  dx=[-1,0,1,0]
  dy=[0,1,0,-1]
  q=deque([(0,flags[1],flags[2],{flags[1]},{flags[2]})]) # 이동칸, 빨간수레위치, 파란수레위치, 빨간수레방문기록, 파란수레방문기록
  while q:
    cnt,red,blue,rvisited,bvisited=q.popleft()
    if red==flags[3] and blue==flags[4]:
      return cnt
    rx,ry=to_tuple(red)
    bx,by=to_tuple(blue)
    for i in range(4): # 16가지 경우
      if red==flags[3]: # 도착지면 이동 안함
        nrx=rx
        nry=ry
      else:
        nrx=rx+dx[i]
        nry=ry+dy[i]
        if to_int(nrx,nry) in rvisited: # 방문한 칸
          continue
        if nrx<0 or nrx>=n or nry<0 or nry>=m: # 격자 밖
          continue
        if maze[nrx][nry]==5: # 벽
          continue
      rvisited.add(to_int(nrx,nry))
      for j in range(4): # 빨간수레는 최소한의 조건만 체크하고 파란수레까지 이동하고 나서 전체 조건을 체크
        if blue==flags[4]: # 도착지면 이동 안함
          if to_int(nrx,nry)==blue: # 파란수레가 이미 도착 -> 움직이지 않음 -> 빨간 수레 못감
            continue
          nbx=bx
          nby=by
        else:
          nbx=bx+dx[j]
          nby=by+dy[j]
          if to_int(nrx,nry)==blue and to_int(nbx,nby)==red: # 자리를 바꾸며 움직일 수 없음
            continue
          if to_int(nbx,nby)==to_int(nrx,nry): # 같은 칸으로 움직일 수 없음
            continue
          if to_int(nbx,nby) in bvisited: # 방문한 칸
            continue
          if nbx<0 or nbx>=n or nby<0 or nby>=m: # 격자 밖
            continue
          if maze[nbx][nby]==5: # 벽
            continue
        bvisited.add(to_int(nbx,nby))
        q.append((cnt+1,to_int(nrx,nry),to_int(nbx,nby),copy.deepcopy(rvisited),copy.deepcopy(bvisited)))
        bvisited.remove(to_int(nbx,nby))
      rvisited.remove(to_int(nrx,nry))
  return 0

"""
bfs를 이용 - 매변 현재 상태를 저장하는 자료형도 함께 큐에 넣어준다. 4*4 크기이기 때문에 가능(크면 메모리초과)
자료형의 경우 그냥 이름을 넣으면 참조만 하기 때문에 깊은 복사를 해서 매번 현재 위치를 저장
이 과정에서 시간이 오래 걸린다 
  -> rvisited, bvisited는 비트 마스킹을 통해서 최적화해야함 (최대 2**16 이니까 가능)
"""
