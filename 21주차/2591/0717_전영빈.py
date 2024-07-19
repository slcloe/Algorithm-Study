"""
i번째 숫자의 방법을 정하기 위해서는 i-1번째까지의 숫자가 어떻게 정해졌는지 알아야 한다.
즉, 부문제를 통해 현재 문제를 해결할 수 있으므로 dp.

숫자 카드의 범위가 1~32이므로 다음과 같은 조건을 주의하자.
1. 0은 혼자서 사용될 수 없다.
2. 두 개의 숫자를 하나의 카드로 처리하기 위해서는 다음과 같은 조건을 확인해야 한다.
    1. 10의 자리 숫자의 범위는 1~3이다.
    2. 10의 자리 숫자가 3일때, 1의 자리 숫자의 범위는 0~4 이다.
"""

number =  list(map(int, "9" + str(int(input()))))

dp = [[0 for _ in range(2)] for _ in range(len(number))]
dp[0][1] = 1

for i in range(1, len(number)):
    if number[i] != 0:
        dp[i][0] = dp[i-1][0] + dp[i-1][1]
    if (0 < number[i-1] < 3) or (number[i-1] == 3 and number[i] <= 4):
        dp[i][1] = dp[i-1][0]

print(dp[len(number)-1][0] + dp[len(number)-1][1])
