"""
  전형적인 배낭문제 -> dp
"""

import sys

input = sys.stdin.readline

N, M = map(int, input().split())
app = list(map(int, input().split()))
cost = list(map(int, input().split()))
full_cost = sum(cost)
dp = [0 for _ in range(full_cost+1)]

for i in range(N):
    for j in range(full_cost, cost[i]-1, -1):
        dp[j] = max(dp[j], dp[j-cost[i]] + app[i])

for c in range(full_cost+1):
    if dp[c] >= M:
        print(c)
        break
