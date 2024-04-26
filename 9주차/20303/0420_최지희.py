import sys
input=sys.stdin.readline

def main():
  def union(a,b):
    a=find(a)
    b=find(b)
    if a>b:
      parent[a]=b
    else:
      parent[b]=a
  def find(a):
    if parent[a]!=a:
      parent[a]=find(parent[a])
    return parent[a]

  N,M,K=map(int,input().split())
  c=list(map(int,input().split()))
  parent=[i for i in range(N)]
  for _ in range(M):
    x,y=map(int,input().split())
    union(x-1,y-1)
  cnt=[1]*N
  for i in range(N):
    if i!=parent[i]:
      parent[i]=find(i)
      cnt[parent[i]]+=1
      c[parent[i]]+=c[i]
  dp=[0]*K
  for i in range(N):
    if parent[i]!=i:
      continue
    for j in range(K-1,cnt[i]-1,-1):
      dp[j]=max(dp[j],dp[j-cnt[i]]+c[i])
  print(dp[-1])

main()

"""
union-find를 통해서 친구들의 group 관계에서 인원수와 사탕수를 구한다.
dp를 통해서 최대 값을 구한다
  이때 앞에서 시작하면 갱신된 값과의 비교가 일어나기 때문에 뒤에서부터 시작한다.
"""
