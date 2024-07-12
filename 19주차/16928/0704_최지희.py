import sys
from collections import deque
input=sys.stdin.readline

def main():
    N,M=map(int,input().split())
    board=[i for i in range(100)]
    for _ in range(N+M):
        x,y=map(int,input().split())
        board[x-1]=y-1

    visited=[0]*100
    q=deque([0])
    while q:
        x=q.popleft()
        for i in range(1,7):
            if x+i>99:
                continue
            nx=board[x+i]
            if nx==99:
                print(visited[x]+1)
                break
            if nx<99 and not visited[nx]:
                visited[nx]=visited[x]+1
                q.append(nx)
        else:
            continue
        break
main()


