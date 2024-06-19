import sys
input=sys.stdin.readline

def main():
    N,M=map(int,input().split())
    board=[list(input().strip()) for _ in range(N)]
    answer=-1
    for i in range(N):
        for j in range(M): # 시작 x,y 좌표
            for di in range(-N,N): # x의 공차
                for dj in range(-M,M): # y의 공차
                    if not di and not dj:
                        continue
                    cur=""
                    x,y=i,j
                    while 0<=x<N and 0<=y<M:
                        cur+=board[x][y]
                        if not (int(cur)**0.5)%1:
                            answer=max(answer,int(cur))
                        x+=di
                        y+=dj
    print(answer)
main()
