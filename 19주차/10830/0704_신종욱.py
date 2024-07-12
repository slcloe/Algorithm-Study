import sys
input = sys.__stdin__.readline


def main():
    n, b = map(int, input().split())
    a = [list(map(int, input().split())) for _ in range(n)]

    solve(n, a, b)


def solve(n, a, b):
    # 정의에 따라 그대로 구현했음
    # 대신 
    def sqre(left, right):
        res = [[left[i][j] for j in range(n)] for i in range(n)]

        for i in range(n):
            for j in range(n):
                res[i][j] = sum([(left[i][k])*(right[k][j]) for k in range(n)])%1000

        return res


    ans = [[int(i == j) for j in range(n)] for i in range(n)] # Identity matrix
    # https://www.acmicpc.net/problem/1629와 사실상 똑같은 풀이인데 정수 간 곱셈이 아닌 행렬 간 곱셈일 뿐
    # 즉 여기서는 행렬 곱 자체는 빠르게 하지 않고(n 범위가 작으므로) 거듭제곱의 횟수를 줄였을 뿐이다.
    # 행렬 곱을 분할정복으로 빠르게 하는 방법은 https://t0pli.tistory.com/214의 Matrix multiplication 부분 참고
    while b > 0:
        if b & 1: # b 범위가 크길래 b%2와 유효한 차이가 있을 줄 알았으나 없는 듯 함...
            ans = sqre(ans, a)

        b >>= 1 # 마찬가지로 b 범위가 커서 b //= 2와 유효한 차이가 있을 줄 알았으나 없는 듯
        a = sqre(a, a)

    print('\n'.join([' '.join(str(ans[i][j]) for j in range(n)) for i in range(n)]))

    
main() 
