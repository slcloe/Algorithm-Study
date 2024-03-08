import sys
input=sys.stdin.readline

def main():
    n=int(input())
    board=[[0]*7 for _ in range(n)]

    for i in range(n):
        tmp=list(map(int,input().split()))
        board[i][tmp[0]] = tmp[5]
        board[i][tmp[1]] = tmp[3]
        board[i][tmp[2]] = tmp[4]
        board[i][tmp[3]] = tmp[1]
        board[i][tmp[4]] = tmp[2]
        board[i][tmp[5]] = tmp[0]

    ans=0
    for i in range(1,7):
        cur=0
        down=i
        for j in range(n):
            if (n-j)*6+cur<ans:
                break
            up=board[j][down]
            if up==6 and down==5:
                cur+=4
            elif up==5 and down==6:
                cur+=4
            elif up==6 or down==6:
                cur+=5
            else:
                cur+=6
            down=up
        if ans<cur:
            ans=cur
    print(ans)

main()

"""
접근방법: 1번 주사위의 밑면이 위에 모든 값들을 결정한다
- 값을 비교 할때 주사위는 6을 가지는 경우가 최대
    - 4나 5를 더해야 하는 경우를 찾고 나머지는 6을 더한다
- (0,5),(1,3),(2,4) 의 pair
    board: board[층][번호]=번호와 마주 보는 번호로 저장
"""