import sys
input = sys.stdin.readline


def main():
    ratio = {
        (0, -1): [(-1, 1, 0.01), (1, 1, 0.01), (-1, 0, 0.07), (1, 0, 0.07), (-2, 0, 0.02), (2, 0, 0.02), (-1, -1, 0.1),
                  (1, -1, 0.1), (0, -2, 0.05)],
        (0, 1): [(-1, -1, 0.01), (1, -1, 0.01), (-1, 0, 0.07), (1, 0, 0.07), (-2, 0, 0.02), (2, 0, 0.02), (-1, 1, 0.1),
                 (1, 1, 0.1), (0, 2, 0.05)],
        (-1, 0): [(1, -1, 0.01), (1, 1, 0.01), (0, -1, 0.07), (0, 1, 0.07), (0, -2, 0.02), (0, 2, 0.02), (-1, -1, 0.1),
                  (-1, 1, 0.1), (-2, 0, 0.05)],
        (1, 0): [(-1, -1, 0.01), (-1, 1, 0.01), (0, -1, 0.07), (0, 1, 0.07), (0, -2, 0.02), (0, 2, 0.02), (1, -1, 0.1),
                 (1, 1, 0.1), (2, 0, 0.05)]}

    def move(x, y, k):
        res = 0
        moved = 0
        for dx, dy, dr in ratio[k]:
            nx = x + dx
            ny = y + dy
            cur = int(board[x][y] * dr)
            moved += cur
            if -1 < nx < N and -1 < ny < N:
                board[nx][ny] += cur
            else:
                res += cur
        nx = x + k[0]
        ny = y + k[1]
        if -1 < nx < N and -1 < ny < N:
            board[nx][ny] += board[x][y] - moved
        else:
            res += board[x][y] - moved
        board[x][y] = 0
        return res

    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]
    i = N // 2
    j = N // 2
    ans = 0
    for d in range(1, N + 1):
        flag = (-1) ** d
        for _ in range(d):
            j += flag
            if j == -1:
                break
            ans += move(i, j, (0, flag))
        else:
            flag *= -1
            for _ in range(d):
                i += flag
                ans += move(i, j, (flag, 0))
    print(ans)


main()
