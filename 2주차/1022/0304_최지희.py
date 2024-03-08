import sys
input=sys.stdin.readline

def main():
    r1,c1,r2,c2=map(int,input().split())
    r=r2-r1+1
    c=c2-c1+1
    max_len=0
    board=[['0']*c for _ in range(r)]
    for i in range(r1,r2+1):
        for j in range(c1,c2+1):
            n=max(abs(i),abs(j))
            max_num=(2*n+1)**2
            if i==-n:
                cur=max_num-5*n-j
            elif i==n:
                cur=max_num-n+j
            elif j==-n:
                cur=max_num-3*n+i
            elif j==n:
                cur=max_num-7*n-i
            cur = str(cur)
            board[i - r1][j - c1] = cur
            max_len = max(max_len, len(cur))
    for i in range(r):
        for j in range(c):
            print(board[i][j].rjust(max_len), end=" ")
        print()
main()
"""
접근방법: 각 좌표의 규칙성 구하기
1을 기준으로 십자를 그린다
십자를 기준으로 좌표를 빼거나 더하면 각 좌표의 숫자가 된다

1을 가장 0번 정사각형이라고 하면, 2~9는 1번 정사각형 ...
n번 정사각형애서 가장 큰 수는 (2*n+1)**2=max_num
그러면 십자가를 그리면서 만나는 수는 남서북동 순서로
    max_num-n
    max_num-2*n-n
    max_num-4*n-n
    max_num-6*n-n
++++
- n을 구하는 방법: i,j 좌표일때 절대값이 큰 값이 n
"""