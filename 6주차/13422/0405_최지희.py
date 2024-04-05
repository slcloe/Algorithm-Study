import sys
input=sys.stdin.readline
def main():
  T=int(input())
  for _ in range(T):
    N,M,K=map(int,input().split())
    board=list(map(int,input().split()))
    if N>M:
      board.extend(board[:M-1])
    total=sum(board[:M-1])
    ans=0
    for end in range(M-1,len(board)):
      total+=board[end]
      if total<K:
        ans+=1
      total-=board[end-M+1]
    print(ans)
main()
"""
슬라이딩 윈도우: 연속된 집의 수가 정해져 있다 이중에서 조건에 맞는 최대를 찾으면 된다.
만약 집의수와 연속된 집의 수가 같으면 123 231 312 는 같은 경우이므로 뒤에 붙일 필요가 없다
"""
