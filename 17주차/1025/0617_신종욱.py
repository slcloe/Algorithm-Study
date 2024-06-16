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
        #   2. 모든 공차(행은 -(n - 1) ~ n - 1, 열은 -(m - 1) ~ m - 1)에 대해
        sub_ans = table[i][j]
        for di in range(-n + 1, n, 1):
            for dj in range(-m + 1, m, 1):
                if di == 0 and dj == 0:
                    continue

                curr_ans = [str(table[i][j])]
                ni, nj = i + di, j + dj

                while range_check(ni, nj):
                    curr_ans.append(str(table[ni][nj]))

                    # 만들 때마다 sub_ans를 갱신해야 함
                    # 시작점은 무조건 포함되므로 중간 일부나 끝 일부를 검사할 필요는 없음
                    curr_ans_int = int(''.join(curr_ans))
                    if is_square(curr_ans_int):
                        sub_ans = max(sub_ans, curr_ans_int)

                    ni, nj = ni + di, nj + dj

        return sub_ans

    
    for i in range(n):
        for j in range(m):
            curr_answer = solve(i, j)
            if is_square(curr_answer):
                answer = max(answer, curr_answer)

    print(answer)

main()
