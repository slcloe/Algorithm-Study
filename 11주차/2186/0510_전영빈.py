import sys

input = sys.stdin.readline

N, M, K = map(int, input().split())

board = [list(input().rstrip()) for _ in range(N)]
keyword = list(input().rstrip())
dp = [[[ -1 for _ in range(M)] for _ in range(N)] for _ in range(len(keyword))]
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def dfs(y, x, idx):
    if dp[idx][y][x] != -1:
        return dp[idx][y][x]
    
    if idx == len(keyword) -1:
        return 1
    
    dp[idx][y][x] = 0
    for i in range(1, K+1):
        for j in range(4):
            ny, nx = y + dy[j] * i, x + dx[j] * i

            if 0 <= ny < N and 0 <= nx < M and board[ny][nx] == keyword[idx+1]:
                dp[idx][y][x] += dfs(ny, nx, idx+1)

    return dp[idx][y][x]

solve = 0
for i in range(N):
    for j in range(M):
        if board[i][j] == keyword[0]:
            solve += dfs(i, j, 0)

print(solve)
