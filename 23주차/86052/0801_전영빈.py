"""
사이클을 찾기 위한 가장 쉬운 방법은 dfs를 이용하는 것이다.
탐색을 돌렸을 때, 이번 탐색 중에서 방문했던 노드에 다시 방문하게 된다면 해당 탐색 경로에 사이클이 존재함을 의미한다.
탐색을 진행하면서 방문한 노드들에게 순서 값을 부여하고, 이미 방문했던 노드에 다시 방문했을 때
(이번 차례의 순서 값) - (이전에 방문했던 노드의 순서 값)로 사이클의 길이를 얻을 수 있다.
단, 이번 탐색이 아닌 이전 탐색에서 방문했던 노드를 방문하는 경우 사이클은 생성되지 않으므로
각 탐색마다 방문 노드들에 대한 처리가 필요하다.

처음에 접근했을 때 2차원 배열에 네 방향으로 이동이 가능하므로 3차원 방문 배열을 사용했지만 시간초과가 발생했다.
이전 탐색에서 방문한 노드들을 처리하는 과정에서 최악의 경우 (500 * 500 * 4)^2 의 연산이 발생할 수 있기 때문으로 생각된다.
따라서, dict을 사용하여 탐색 노드에 대한 방문 여부를 O(1)로 찾을 수 있도록 개선했다.
"""

def solution(grid):
    answer = []
    
    width = len(grid[0])
    height = len(grid)
    
    visited = dict()
    
    dy = [-1, 0, 1, 0]
    dx = [0, 1, 0, -1]
    
    def search(y, x, d):
        cycle = dict()
        count = 1
        
        while True:
            cycle[(y, x, d)] = count
            count += 1
            
            if grid[y][x] == 'L':
                d = (d + 3) % 4
            elif grid[y][x] == 'R':
                d = (d + 1) % 4
                
            y = (y + dy[d]) % height
            x = (x + dx[d]) % width
            
            if (y, x, d) in visited:
                visited.update(cycle)
                break
            elif (y, x, d) in cycle:
                answer.append(count - cycle[(y, x, d)])
                visited.update(cycle)
                break

    for i in range(height):
        for j in range(width):
            for k in range(4):
                if (i, j, k) not in visited:
                    search(i, j, k)
    
    answer.sort()
    return answer
