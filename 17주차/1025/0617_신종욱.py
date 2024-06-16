import sys
input = sys.__stdin__.readline

from math import isqrt

def main():
    n, m  = map(int, input().split())
    table = [list(map(int, list(input().strip()))) for _ in range(n)]

    # 완전제곱수를 만들 수 없는 경우 초기 값이 출력 됨
    answer = -1

    def range_check(i, j):
        return (0 <= i < n) and (0 <= j < m)

    def is_square(num):
        return (num >= 0) and ((isqrt(num))**2 == num)

    def solve(i, j):
        # 1. 시작 위치 (i, j)에 대해
        #   2. 모든 공차(행은 -(n - 1) ~ n - 1, 열은 -(m - 1) ~ m - 1
        sub_ans = table[i][j]
        for di in range(-n + 1, n, 1):
            for dj in range(-m + 1, m, 1):
                if di == 0 and dj == 0:
                    continue

                curr_ans = [str(table[i][j])]
                ni, nj = i + di, j + dj
                while range_check(ni, nj):
                    curr_ans.append(str(table[ni][nj]))
                    ni, nj = ni + di, nj + dj

                curr_ans_int = int(''.join(curr_ans))
                if is_square(curr_ans_int):
                    sub_ans = max(sub_ans, curr_ans_int)

                # 인덱스 등차수열에 의해 만들어진 수에서 일부만 떼서 쓸 수도 있음
                # 가능한 모든 길이(1 ~ curr_ans 길이)에 대해 슬라이딩 윈도우를 적용한다.
                l = len(curr_ans)
                for cl in range(len(str(answer)), l):
                    for _cl in range(l - cl):
                        curr_ans_int = int(''.join(curr_ans[_cl:_cl + cl]))
                        if is_square(curr_ans_int):
                            sub_ans = max(sub_ans, curr_ans_int)

        return sub_ans

    
    for i in range(n):
        for j in range(m):
            curr_answer = solve(i, j)
            if is_square(curr_answer):
                answer = max(answer, curr_answer)
    
    print(answer)

main()
