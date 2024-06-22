import sys
input = sys.__stdin__.readline
DIV = 1000

def main():
    a, b, d, n = map(int, input().split())

    dp = [0]*(n+1)
    dp[0] = 1 # Sentinel

    prefix_sum = 0
    for i in range(1, n+1):    
        dp[i] = prefix_sum = (prefix_sum + dp[i - a] - dp[i - b] + DIV) % DIV

    answer = 0
    for i in range(max(0, n - d + 1), n+1):
        answer = (answer + dp[i]) % DIV
    print(answer)
