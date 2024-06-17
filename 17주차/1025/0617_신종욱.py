import sys
input = sys.__stdin__.readline

from math import isqrt

def main():
    n, m  = map(int, input().split())
    table = [list(map(int, list(input().strip()))) for _ in range(n)]

    answer = -1

    def range_check(i, j):
        return (0 <= i < n) and (0 <= j < m)

    def is_square(num):
        return (num >= 0) and ((isqrt(num))**2 == num)

    def solve(i, j):
        # 1. 시작 위치 (i, j)에 대해
        #   2. 모든 공차(행은 -(n - 1) ~ n - 1, 열은 -(m - 1) ~ m - 1
        sub_ans = table[i][j]
        for di in range(-n + 1, n):
            for dj in range(-m + 1, m):
                if di == 0 and dj == 0:
                    continue

                curr_ans = table[i][j]
                ni, nj = i + di, j + dj
                while range_check(ni, nj):
                    # 계속 갱신해야 됨
                    # 시작 위치는 정해져 있지만 끝 위치는 정해져 있지 않다.
                    curr_ans = curr_ans*10 + table[ni][nj]
                    if is_square(curr_ans):
                        sub_ans = max(sub_ans, curr_ans)

                    ni, nj = ni + di, nj + dj

        return sub_ans

    
    for i in range(n):
        for j in range(m):
            curr_answer = solve(i, j)
            if is_square(curr_answer):
                answer = max(answer, curr_answer)

    print(answer)

main()
