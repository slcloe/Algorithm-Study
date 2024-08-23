#  그냥 bfs

from collections import deque

def solution(board):
    answer = -1
    
    height = len(board)
    width = len(board[0])
    visited = [[False for _ in range(width)] for _ in range(height)]
    sy, sx = 0, 0
    for i in range(height):
        for j in range(width):
            if board[i][j] == 'R':
                sy, sx = i, j
    
    queue = deque()
    queue.append((sy, sx, 0))
    visited[sy][sx] = True
    
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    while queue:
        cy, cx, cc = queue.popleft()
        if board[cy][cx] == 'G':
            answer = cc
            break
            
        for i in range(4):
            ny, nx = cy, cx
            
            while True:
                ty, tx = ny + dy[i], nx + dx[i]
                if 0 <= ty < height and 0 <= tx < width and board[ty][tx] != 'D':
                    ny, nx = ty, tx
                else:
                    break
                  
            if not visited[ny][nx]:
                queue.append((ny, nx, cc+1))
                visited[ny][nx] = True
    
    return answer
