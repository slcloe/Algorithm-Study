"""
결국 구간합을 구하는 문제인데 구간이 1씩 옆으로 이동한다. => 슬라이딩 윈도우
해당 문제에서 중요한 점은 N==M 인 케이스. N==M==8 이라고 했을 때, 일반적인 방식으로 슬라이딩
윈도우를 적용하면 경우의 수가 8이 나온다. 하지만 8가지의 경우는 결국 모두 같은 경우이기 때문에
모든 집의 합이 K 보다 작을 경우 1을, 그 이외의 경우 0을 출력해야 한다.
"""

import sys

input = sys.stdin.readline

T = int(input())

for _  in range(T):
    N, M, K = map(int, input().split())
    amount = list(map(int, input().split()))

    current = sum(amount[:M])
    count = 1
    if current >= K:
        count = 0

    if N == M:
        print(count)
        continue

    left = 1
    right = M

    while left < N:
        current = current + amount[right % N] - amount[left-1]

        if current < K:
            count += 1

        left += 1
        right += 1

    print(count)
