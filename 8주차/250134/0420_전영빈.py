"""
    수레는 일반적인 2차원 배열 탐색 방법과 동일하게 이동한다.
    두 수레가 도착지까지 도달하는데 걸리는 최단 시간을 구해야 하므로 bfs로 풀이하되
    두 수레가 독립적으로 움직이므로 한 번의 반복문에서 두 개의 bfs를 돌려야 한다.
    이를 위해, bfs의 파라메터로 현재 퍼즐판 상황 정보와 두 수레의 방문 정보를 함께 더해 탐색했다.

    이 때, 아래의 조건을 주의하여 풀이한다.
    - 수레는 한 번 방문했던 칸에 다시 방문할 수 없다.
    - 두 개의 수레가 한 번에 같은 칸으로 움직일 수 없다.
    - 도착 칸에 도달한 수레는 더 이상 움직이지 않는다.
"""

from collections import deque
from copy import deepcopy

def solution(maze):
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]
    
    ry, rx, by, bx = 0, 0, 0, 0
    roy, rox, boy, box = 0, 0, 0, 0
    n, m = len(maze), len(maze[0])
    redVisited = [[False for _ in range(m)] for _ in range(n)]
    blueVisited = [[False for _ in range(m)] for _ in range(n)]
    
    for i in range(n):
        for j in range(m):
            if maze[i][j] == 1:
                ry, rx = i, j
            if maze[i][j] == 2:
                by, bx = i, j
            if maze[i][j] == 3:
                roy, rox = i, j
                maze[i][j] = 0
            if maze[i][j] == 4:
                boy, box = i, j
                maze[i][j] = 0
     
    def bfs(maze, rv, bv, ry, rx, by, bx):
        queue = deque()
        rv[ry][rx] = True
        bv[by][bx] = True
        
        queue.append((maze, rv, bv, ry, rx, by, bx, 0))
        
        while queue:
            cmaze, crv, cbv, cry, crx, cby, cbx, cc = queue.popleft()
            # print(cry, crx, cby, cbx, cc)
            
            if cry == roy and crx == rox and cby == boy and cbx == box:
                return cc
            
            # 두 수레가 모두 도착 지점에 도착하지 않은 경우
            if (cry != roy or crx != rox) and (cby != boy or cbx != box):
                for i in range(4):
                    nry, nrx = cry + dy[i], crx + dx[i]

                    if not (0 <= nry < n and 0 <= nrx < m and not crv[nry][nrx]):
                        continue

                    for j in range(4):
                        nby, nbx = cby + dy[j], cbx + dx[j]

                        if not (0 <= nby < n and 0 <= nbx < m and not cbv[nby][nbx]):
                            continue

                        nmaze = deepcopy(cmaze)
                        nrv = deepcopy(crv)
                        nbv = deepcopy(cbv)

                        if nmaze[nry][nrx] == 0:
                            nmaze[nry][nrx] = 1
                            nrv[nry][nrx] = True
                        else:
                            continue

                        if nmaze[nby][nbx] == 0:
                            nmaze[nby][nbx] = 2
                            nbv[nby][nbx] = True
                            nmaze[cby][cbx] = 0
                        else:
                            continue

                        nmaze[cry][crx] = 0
                        queue.append((nmaze, nrv, nbv, nry, nrx, nby, nbx, cc+1))

            #  빨간 수레가 이미 도착 칸에 도달한 경우
            if (cry == roy and crx == rox) and (cby != boy or cbx != box):
                for i in range(4):
                    nry, nrx = cry + dy[i], crx + dx[i]

                    if not (0 <= nry < n and 0 <= nrx < m and not crv[nry][nrx]):
                        continue

                    nmaze = deepcopy(cmaze)
                    nrv = deepcopy(crv)
                    nbv = deepcopy(cbv)

                    if nmaze[nry][nrx] == 0:
                        nmaze[nry][nrx] = 1
                        nrv[nry][nrx] = True
                        nmaze[cry][crx] = 0
                    else:
                        continue

                    queue.append((nmaze, nrv, nbv, nry, nrx, cby, cbx, cc+1))
            
            # 파란 수레가 이미 도착 칸에 도달한 경우
            if (cry != roy or crx != rox) and (cby == boy and cbx == box):
                for j in range(4):
                    nby, nbx = cby + dy[j], cbx + dx[j]

                    if not (0 <= nby < n and 0 <= nbx < m and not cbv[nby][nbx]):
                        continue

                    nmaze = deepcopy(cmaze)
                    nrv = deepcopy(crv)
                    nbv = deepcopy(cbv)


                    if nmaze[nby][nbx] == 0:
                        nmaze[nby][nbx] = 2
                        nbv[nby][nbx] = True
                        nmaze[cby][cbx] = 0
                    else:
                        continue

                    queue.append((nmaze, nrv, nbv, cry, crx, nby, nbx, cc+1))

        return 0
   
    answer = bfs(maze, redVisited, blueVisited, ry, rx, by, bx)
    
    return answer
