import sys
input = sys.__stdin__.readline

# dp[i]: numbers[:i]에서 가장 긴 LIS의 길이
# LIS는 내버려두고 나머지 수들을 적절한 위치로 넣으면 답이 된다.

# 즉 LIS에 속하지 않는 수들만 움직이면 되므로
# 전체 길이에서 가장 긴 LIS의 길이를 뺀 값이 답이 된다.

def main():
    n = int(input())

    dp = [1 for _ in range(n)]
    numbers = [int(input()) for _ in range(n)]

    # LIS
    for i in range(n):
        for j in range(1, i):
            if numbers[j] < numbers[i]:
                dp[i] = max(dp[i], dp[j] + 1)

    print(n - max(dp))

main()
