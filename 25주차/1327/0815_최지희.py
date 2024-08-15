import sys
from collections import deque
input=sys.stdin.readline

def main():
    N,K=map(int,input().split())
    board="".join(input().strip().split())
    target=[str(i) for i in range(1,N+1)]
    target="".join(target)
    q=deque()
    q.append((0,board))
    visited=set(board)
    while q:
        cnt,x=q.popleft()
        if x==target:
            print(cnt)
            break
        for i in range(N-K+1):
            nx=x[:i]+x[i:i+K][::-1]+x[i+K:]
            if nx not in visited:
                q.append((cnt+1,nx))
                visited.add(nx)
    else:
        print(-1)
main()

"""
문자열 문제
미리 만들어야하는 문자열을 저장하고
비교하면서 계산
"""
