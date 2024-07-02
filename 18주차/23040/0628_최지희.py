import sys
from collections import defaultdict,deque
input = sys.stdin.readline

def main():
    n=int(input())
    board=defaultdict(list)
    for _ in range(n-1):
        u,v=map(int,input().split())
        board[u-1].append(v-1)
        board[v-1].append(u-1)
    s=input().strip()

    visited=[-1]*n
    idx=0
    cnt=[]
    for i in range(n):
        if s[i]=="R" and visited[i]==-1:
            q=deque()
            q.append(i)
            visited[i]=idx
            cnt.append(1)
            while q:
                x=q.popleft()
                for nx in board[x]:
                    if s[nx]=="R" and visited[nx]==-1:
                        cnt[-1]+=1
                        q.append(nx)
                        visited[nx]=idx
            idx+=1
    ans=0
    for i in range(n):
        if s[i]=="B":
            for ni in board[i]:
                if s[ni]=="R":
                    ans+=cnt[visited[ni]]
    print(ans)
main()
