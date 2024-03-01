import sys
input = sys.__stdin__.readline

from collections import deque


def main():
    board = [list(input().rstrip()) for i in range(8)]

    print(int(bfs(board)))


def bfs(board):
    visited = set()
    dx = [-1, -1, 0, 1, 1,  1,  0, -1, 0]
    dy = [ 0,  1, 1, 1, 0, -1, -1, -1, 0]
    
    queue = deque([(7, 0, 0)])
    while queue:
        curr = queue.popleft()

        if curr[0] == 0 and curr[1] == 7:
            return True
        
        # 현재 시점, 즉 curr[2]초 시점이 기준이므로
        # 현재 위치가 벽이다 ≡ 현재 위치보다 curr[2]칸 위가 벽이다
        # curr[0] - curr[2] < 0인 경우는 벽이랑 만날 일이 없는 경우
        if curr[0] - curr[2] >= 0 \
        and board[curr[0] - curr[2]][curr[1]] == '#':
            continue


        for idx in range(9):
            next = (curr[0] + dx[idx], curr[1] + dy[idx], curr[2] + 1)

            # 인덱스 범위를 벗어나는 위치로는 진행하지 않는다.
            if not check_range(next):
                continue

            # 그 시점의 그 위치에 방문한 적이 있다면 진행하지 않는다.
            if next in visited:
                continue

            # 내가 먼저 진행한 후에 벽이 한 칸 내려오는 것이므로
            # 진행한 후 벽이 내려오는 위치(이동이 불가능해지는 위치)는
            # 다음 진행할 위치보다 한 칸 위에 해당한다.
            # 이 경우에는 그 위치로 가지 않는다.
            if next[0] - next[2] + 1 >= 0 \
            and board[next[0] - next[2] + 1][next[1]] == '#':
                continue
            
            # 방문 여부는 위치 뿐 아니라 그 시점에 그 위치로 체크한다.
            # t = t0 시점에 (i, j)에 방문한 적이 있다면
            # t0 초에 (i, j)에 도달할 수 있는 것으로 간주한다.
            visited.add(next)
            queue.append(next)

    return False


def check_range(indices):
    return (0 <= indices[0] < 8) and (0 <= indices[1] < 8)


main()