from collections import deque

def get_cost(d1, d2, cost):
    if (d1 == 'R' or d1 == 'L') and (d2 == 'L' or d2 == 'R'):
        return cost + 100
    elif (d1 == 'D' or d1 == 'U') and (d2 == 'D' or d2 == 'U'):
        return cost + 100
    elif (d1 == 'R' or d1 == 'L') and (d2 == 'D' or d2 == 'U'):
        return cost + 600
    elif (d1 == 'U' or d1 == 'D') and (d2 == 'L' or d2 == 'R'):
        return cost + 600

def bfs(x, y, cost, direct, answer, board):
    global n
    q = deque([(x, y, cost, direct)])
    check = [[0 for _ in range(n)] for _ in range(n)]
    check[y][x] = 1

    while q:
        x, y, cost, cur_dir = q.popleft()

        if x == n - 1 and y == n - 1:
            answer.append(cost)
            continue

        for dy, dx, new_dir in (0, 1, 'R'), (1, 0, 'D'), (0, -1, 'L'), (-1, 0, 'U'):
            ny, nx = y + dy, x + dx

            if (0 <= ny < n and 0 <= nx < n) and not board[ny][nx]:
                new_cost = get_cost(cur_dir, new_dir, cost)
                if not check[ny][nx] or new_cost < check[ny][nx]:
                    check[ny][nx] = new_cost
                    q.append((nx, ny, new_cost, new_dir))

def solution(board):
    global n
    answer = []
    n = len(board)
    bfs(0, 0, 0, 'R', answer, board)
    bfs(0, 0, 0, 'D', answer, board)

    print(answer)

    return min(answer)
