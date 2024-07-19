import sys
input = sys.__stdin__.readline


def main():
    # 0인 카드는 없지만 10, 20, 30인 카드는 있으므로 숫자는 0일 수 있다
    digits = list(map(int, list(input().rstrip())))
    n = len(digits)

    # dp[i]= 1 ~ i번째 숫자로 만들 수 있는 모든 경우의 수
    dp = [1 for _ in range(n)]


    for i in range(1, n):
        # 둘 다 가능한 경우
        if (digits[i - 1] != 0) and (digits[i] != 0) and (digits[i - 1]*10) + digits[i] <= 34:
            dp[i] = dp[i - 1] + dp[i - 2]
            continue
        
        # 한 자리 수만 가능한 경우
        elif digits[i - 1] == 0 or (digits[i - 1]*10) + digits[i] > 34:
            dp[i] = dp[i - 1]
        
        # 두 자리 수만 가능한 경우
        else:
            # digits[i - 1]과 digits[i]를 합치므로
            # 여기에서는 dp[i - 2]가 들어간다.
            dp[i] = dp[i - 2]

    print(dp[-1])

main()
