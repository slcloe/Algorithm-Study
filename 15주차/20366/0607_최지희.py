import sys
input=sys.stdin.readline

N=int(input())
board=list(map(int,input().split()))
board.sort()

ans=10e9
for i in range(N-3):
    for j in range(i+3,N):
        s=i+1
        e=j-1
        while s<e:
            gap=board[i]+board[j]-(board[s]+board[e])
            if gap<0:
                e-=1
            else:
                s+=1
            ans=min(ans,abs(gap))
print(ans)

"""
투포인터 문제
총 2개를 만들어야 한다
우선 하나를 고정하고 나머지를 찾아본다
이렇게 되면 4중 for문이 되는데 -> 2중 for문으로 고정점을 만들고 안에서 투포인터로 찾는다
"""
