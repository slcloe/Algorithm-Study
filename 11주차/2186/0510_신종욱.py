import sys
input = sys.__stdin__.readline

def main():
    global n, m, k, word_board, target

    # input
    n, m, k = list(map(int, input().split()))
    word_board = [list(input().rstrip()) for _ in range(n)]
    target = input().rstrip()

    # init
    global d, dp
  
    d = [-1, 0, 1, 0]

    # 3글자 째에 word_board[i][j] 밟은 거랑 4글자 째에 밟은 거랑 다름
    dp = [[[-1 for j in range(m)] for i in range(n)] for idx in range(len(target))]

    # Get starting points
    start_points = []
    for i in range(n):
        for j in range(m):
            if word_board[i][j] == target[0]:
                start_points.append((i, j))

    target_length = len(target) - 1
    answer = 0
    for start_point in start_points:
        v = dfs(start_point[0], start_point[1], 0, target_length)
        answer += v;


    print(answer)
    

def dfs(ci, cj, idx, target_length):
    global k, word_board, d, dp

    # 단어가 완성되면 1을 반환해 dp에 더한다.
    if idx == target_length:
        return 1
    
    # -1이 아니라면 이미 점화식에 맞는 값이 채워진 상태이므로 바로 반환
    if dp[idx][ci][cj] != -1:
        return dp[idx][ci][cj]

    # 최종적으로 여기에 반환 값이 다 더해져서
    dp[idx][ci][cj] = 0
    for dist in range(1, k + 1):
        for iidx in range(4):
            ni = ci + (dist * d[iidx])
            nj = cj + (dist * d[-iidx - 1])
            
            if not range_check(ni, nj):
                continue
            
            if word_board[ni][nj] == target[idx + 1]:
                dp[idx][ci][cj] += dfs(ni, nj, idx + 1, target_length)
    
    # 이게 subanswer(start point 하나에 대한 answer)가 된다.
    return dp[idx][ci][cj]


def range_check(ni, nj):
    global n, m
    return (0 <= ni < n) and (0 <= nj < m)


main()
