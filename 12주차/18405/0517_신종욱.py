import sys
input = sys.__stdin__.readline

from collections import deque

def main():
    global n
    
    n, k = list(map(int, input().split()))
    tubes = [list(map(int, input().split())) for _ in range(n)]

    locs = {}
    viruses = set()
    for i in range(n):
        for j in range(n):
            if tubes[i][j] != 0:
                virus = tubes[i][j]
                if virus not in viruses:
                    viruses.add(virus)
                
                if virus not in locs.keys():
                    locs[virus] = deque([(i, j)])
                else:
                    locs[virus].append((i, j))

    se, x, y = list(map(int, input().split()))

    bfs(tubes, sorted(viruses), locs, se)

    print(tubes[x - 1][y - 1])
    

def bfs(tubes, viruses, locs, se):
    d = [-1, 0, 1, 0]

    for s in range(se):
        # 각 바이러스에 대해 
        for virus in viruses:

            # 바이러스의 현재 위치에서
            loc_list  = list(locs[virus])
            for loc in loc_list:

                # 사방을 전염시킨다.
                for idx in range(4):
                    ni, nj = loc[0] + d[idx], loc[1] + d[-idx - 1]

                    if not range_check(ni, nj):
                        continue

                    if tubes[ni][nj] != 0:
                        continue

                    tubes[ni][nj] = virus
                    locs[virus].append((ni, nj))
                locs[virus].popleft() # 사실상 방문 처리     

def range_check(i, j):
    return (0 <= i < n) and (0 <= j < n)

main()
