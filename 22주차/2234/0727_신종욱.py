# 벽 하나 없애는 부분에서 엣지 케이스 걸리는 것 같은데 진짜 모르겠다 뚂땽해


import sys
input = sys.__stdin__.readline

from collections import deque

def main():
    n, m = map(int, input().split())

    # 1111 = 남동북서
    directions = [ (1, 0), (0, 1), (-1, 0), (0, -1)]
    
    rooms = [list(map(int, input().split())) for row in range(m)]
    
    def range_check(i, j):
        return (0 <= i < m) and (0 <= j < n)


    def solve_count_and_max_sparse():
        v = set()

        def bfs1(i, j):
            c = 0
            q = deque() 
            q.append((i, j))
            while q:
                ci, cj = q.popleft()
                c += 1

                next = set()
                val = format(rooms[ci][cj], '04b')
                factor = 0
                for k in range(4):
                    if val[k] == '0':
                        next.add(factor)
                    factor += 1

                for nidx in next:
                    ne = (ci + directions[nidx][0], cj + directions[nidx][1])
                    if ne not in v and range_check(ne[0], ne[1]):
                        v.add(ne)
                        q.append(ne)
            
            return c

        cnt = 0
        max_sparse = -1
        for i in range(m):
            for j in range(n):
                if (i, j) not in v:
                    cnt += 1
                    v.add((i, j)) # Answer 1
                    max_sparse = max(max_sparse, bfs1(i, j)) # Answer 2

        print(cnt)
        print(max_sparse)


    def solve_without_one_wall():
        walls = {}
        for i in range(m):
            for j in range(n):
                val = format(rooms[i][j], '04b')
                factor = 0
                key = (i, j)
                for ch in val:
                    if ch == '1':
                        if key not in walls:
                            walls[key] = set([factor])
                        else:
                            walls[key].add(factor)

                    factor += 1

        def bfs2(i, j, v):
            c = 0
            q = deque() 
            q.append((i, j))
            while q:
                ci, cj = q.popleft()
                c += 1

                if (ci, cj) not in walls:
                    continue

                for nidx in range(4):
                    if nidx in walls[(ci, cj)]:
                        continue 

                    ne = (ci + directions[nidx][0], cj + directions[nidx][1])
                    if ne not in v and range_check(ne[0], ne[1]):
                        v.add(ne)
                        q.append(ne)

            return c

        max_sparse_without_one_wall = -1

        # 벽이 있는 좌표에 대해
        for key in sorted(walls.keys()):
            # 해당 좌표에 있는 벽 중 하나를 제거해보고
            for to_eliminate in walls[key]:
                walls[key].remove(to_eliminate)
                v = set()
                for i in range(key[0], m):
                    for j in range(key[1], n):
                        if (i, j) not in v:
                            v.add((i, j))
                            res = bfs2(i, j, v)
                            max_sparse_without_one_wall = max(max_sparse_without_one_wall, res)
                walls[key].add(to_eliminate)


        print(max_sparse_without_one_wall)

    solve_count_and_max_sparse()
    solve_without_one_wall()

main()
