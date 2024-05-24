import sys
input = sys.stdin.readline

n=int(input())
board=[list(map(int,input().split())) for _ in range(n)]
board.sort(key=lambda x: x[1],reverse=True)
ans=board[0][1]
for d,t in board:
    ans=min(ans,t)-d
print(ans)

"""
숙제가 데드라인에 가깝게 수행되도록 설계한다
"""
