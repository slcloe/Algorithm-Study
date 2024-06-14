import sys
input = sys.stdin.readline

def main():
    d=[(1,0),(0,-1),(-1,0),(0,1)]
    board=[[0]*101 for _ in range(101)]
    N=int(input())
    for _ in range(N):
        x,y,sd,g=map(int,input().split())
        curve=[sd]
        board[x][y]=1
        x+=d[sd][0]
        y+=d[sd][1]
        board[x][y]=1
        for i in range(g):
            for j in range(2**i-1,-1,-1):
                nd=(curve[j]+1)%4
                x+=d[nd][0]
                y+=d[nd][1]
                board[x][y]=1
                curve.append(nd)
    answer=0
    for i in range(100):
        for j in range(100):
            if board[i][j] and board[i+1][j] and board[i][j+1] and board[i+1][j+1]:
                answer+=1
    print(answer)
main()

"""
드래곤커브의 방향의 규칙을 찾으면 된다.
방향  0: 남    1: 서    2: 북    3: 동
이전 세대의 드래곤 커브 선을 거꾸로 확인하면서 90도씩 돌리면 된다.
i세대의 경우 선의 수는 2**i
"""
