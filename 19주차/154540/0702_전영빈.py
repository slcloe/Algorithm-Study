"""
그냥 dfs/bfs 탐색 문제.
정답 리스트을 정렬해서 반환하는 것과 정답 리스트가 비어 있으면 -1을 넣어서 반환하는 것에만 유의.
"""
from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def solution(maps):
    answer = []
    height = len(maps)
    width = len(maps[0])
    board = [list(maps[i]) for i in range(len(maps))]
    
    def bfs(y, x):
        res = int(board[y][x])
        queue = deque()
        queue.append((y, x))
        board[y][x] = 'X'
        
        while queue:
            cy, cx = queue.popleft()
            
            for i in range(4):
                ny, nx = cy + dy[i], cx + dx[i]
        
                if 0 <= ny < height and 0 <= nx < width and board[ny][nx] != 'X':
                    queue.append((ny, nx))
                    res += int(board[ny][nx])
                    board[ny][nx] = 'X'
                
        return res
    
    for i in range(height):
        for j in range(width):
            if board[i][j] != 'X':
                answer.append(bfs(i, j))
    
    answer.sort()
    if len(answer) == 0:
        answer.append(-1)
    return answer
