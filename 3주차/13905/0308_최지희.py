import sys
input=sys.stdin.readline

def union(a,b):
  a=find(a)
  b=find(b)
  if a==b:
    return False
  if a>b:
    parent[a]=b
  else:
    parent[b]=a
  return True

def find(a):
  if a!=parent[a]:
    parent[a]=find(parent[a])
  return parent[a]

n,m=map(int,input().split())
s,e=map(int,input().split())
board=[]
for _ in range(m):
  board.append(list(map(int,input().split())))
parent=[i for i in range(n+1)]
ans=1e6
for x,y,w in sorted(board,key=lambda x:-x[2]):
  if not union(x,y):
    continue
  if ans>w:
    ans=w
  if find(s)==find(e):
    print(ans)
    break
else:
  print(0)



"""
알고리즘: 크루스칼 MST
자료구조: 리스트 정렬
파이썬의 경우에는 최소힙이므로 최대힙을 하기 위해서는 마이너스를 붙여야한다
"""