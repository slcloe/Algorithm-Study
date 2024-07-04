import sys
input=sys.stdin.readline

def main():
    def multiple(m1,m2):
        res=[[0]*N for _ in range(N)]
        for i in range(N):
            for j in range(N):
                for k in range(N):
                    res[i][j]=(res[i][j]+m1[i][k]*m2[k][j])%1000
        return res

    N,B=map(int,input().split())
    A=[[0]*N for _ in range(N)]
    C=[[0]*N for _ in range(N)]
    for r in range(N):
        tmp=list(map(int,input().split()))
        for c in range(N):
            A[r][c]=C[r][c]=tmp[c]%1000

    for b in list(map(int,(format(B,'b'))))[1:]:
        if b:
            C=multiple(A,multiple(C,C))
        else:
            C=multiple(C,C)

    for r in C:
        print(*r)
main()

# 이진수로 바꿔서 계산
