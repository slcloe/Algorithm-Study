"""
문제에서 순서가 맞지 않는 원소를 원하는 곳에 꼽을 수 있다.
그러면 나이브하게 생각했을 때, 가장 길게 순서대로 서 있는 조합을 찾은 뒤에
이에 해당되지 않는 원소들만 움직이면 되지 않을까?
-> 최장증가 부분 수열

N이 최대 200이기 때문에 O(N^2)의 시간 복잡도로도 구할 수 있지만 O(NlogN)로 풀었다.
N번 루프를 돌며 원소들을 탐색하면서 해당 원소에 붙일 수 있는 최장 부분 수열을 이분 탐색을 통해 구했다.
"""

import sys

input = sys.stdin.readline

N = int(input())
sequence = [0] + [int(input()) for _ in range(N)]
dp = [0 for _ in range(N+1)]
bound = [201 for _ in range(N+1)]
bound[0] = 0

for i in range(1, N+1):
    left = 0
    right = N
    while left < right:
        mid = (left + right) // 2
        if bound[mid] <= sequence[i]:
            left = mid + 1
        else:
            right = mid

    bound[right] = sequence[i]
    dp[i] = max(dp[i-1], right)

print(N - dp[N])
