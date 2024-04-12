"""
한 열에서 얻을 수 있는 석유양을 얻기 위해서는 그 열에서 석유가 존재하는 모든 행에 대해 탐색을 하면 된다.
근데 그러면 중복으로 탐색하게될 영역이 딱 봐도 많아 보인다.
미리 bfs를 통해 각 영역마다 얻을 수 있는 석유양을 구해놓고 dictory를 이용하여 해당 열에서 이미 탐색한 영역을
기록하여 중복으로 탐색하지 않도록 했다.
"""

from collections import deque

def solution(land):
    answer = 0
    
    oil = dict()
    width = len(land[0])
    depth = len(land)

    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]
    
    def bfs(y, x, num):
        count = 0
        queue = deque()
        queue.append((y, x))
        land[y][x] = num
        
        while queue:
            cy, cx = queue.popleft()
            count += 1
            
            for i in range(4):
                ny, nx = cy + dy[i], cx + dx[i]
                
                if 0 <= ny < depth and 0 <= nx < width and land[ny][nx] == 1:
                    queue.append((ny, nx))
                    land[ny][nx] = num
                    
        oil[num] = count
        
    oilNumber = -1
    
    for i in range(depth):
        for j in range(width):
            if land[i][j] == 1:
                bfs(i, j, oilNumber)
                oilNumber -= 1
    
    for j in range(width):
        value = 0
        selected = set()
        for i in range(depth):
            if land[i][j] < 0 and land[i][j] not in selected:
                selected.add(land[i][j])
                value += oil.get(land[i][j])
                
        answer = max(answer, value)

    return answer
