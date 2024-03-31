import sys
import heapq
input=sys.stdin.readline

def main():
  N=int(input())
  arr=[list(input().strip()) for _ in range(N)]
  gate=[]
  m=0
  for i in range(N):
    for j in range(N):
      if arr[i][j]=="#":
        gate.append((i,j))
      elif arr[i][j]=="!":
        arr[i][j]=m
        m+=1
  for gx, gy in gate: # 문도 거울에 포함 인덱스를 구하기 쉽도록 맨 마지막에 넣는다
    arr[gx][gy]=m
    m+=1
  board=[[[] for _ in range(2)] for _ in range(m)] # 연결관계
  for i in range(N):
    prior=[]
    for j in range(N): # 가로줄에서 연결된 경우
      if arr[i][j]=="*": # 벽이면 초기화
        prior=[]
      elif arr[i][j]==".":
        continue
      else:
        if prior:
          for p in prior:
            board[p][0].append(arr[i][j])
            board[arr[i][j]][0].append(p)
        prior.append(arr[i][j])
    prior=[]
    for j in range(N): # 세로줄에서 연결된 경우
      if arr[j][i]=="*":
        prior=[]
      elif arr[j][i]==".":
        continue
      else:
        if prior:
          for p in prior:
            board[p][1].append(arr[j][i])
            board[arr[j][i]][1].append(p)
        prior.append(arr[j][i])
  dist=[N*N]*m
  dist[m-1]=0 # 문에서 시작
  hq=[(0,m-1,1),(0,m-1,0)]
  while hq:
    cnt,x,d=heapq.heappop(hq)
    if dist[x]<cnt:
      continue
    if x==m-2:
      break
    nd=abs(d-1) # 0->1, 1->0 (만나면 90도로 회전하기 때문에 줄이 바뀐다)
    for nx in board[x][d]:
      tmp=1+cnt
      if dist[nx]>tmp:
        dist[nx]=tmp
        heapq.heappush(hq,(tmp,nx,nd))
  print(dist[m-2]-1)
main()

"""
빛은 직진하고 90도로 반사된다.
미리 연결관계를 설정한 후에 다익스트라를 통해 필요한 거울의 수를 구했음

연결관계 설정 방법:
  - 거울 1에서 2까지 둘 사이에 벽이 없고 직선상에 있으면 둘 사이는 연결되어 있다.
  - 0은 가로줄에서 연결된 경우, 1은 세로줄에서 연결된 경우
  - 벽이 있으면 갈 수 없다
  
dist는 해당 거울까지 가야할 때 필요한 거울의 수
  문도 거울이라 생각하고 하나의 문에서 시작
가중치가 항상 1이므로 만약 목표 지점에 도달할 경우 멈춰도 된다.
"""