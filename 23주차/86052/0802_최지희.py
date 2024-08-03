def solution(grid):
    def cycle(x,y,w):
        cnt=0
        while not visited[x][y][w]:
            visited[x][y][w] = 1
            w=(w+dd[grid[x][y]])%4
            x=(x+d[w][0])%r
            y=(y+d[w][1])%c
            cnt+=1
        return cnt
    d=[(0,1),(1,0),(0,-1),(-1,0)]# 동서남북
    dd={"S":0, "L":-1,"R":1}
    answer=[]
    r=len(grid)
    c=len(grid[0])
    visited=[[[0]*4 for _ in range(c)] for _ in range(r)]
    for i in range(r):
        for j in range(c):
            for k in range(4):
                if not visited[i][j][k]:
                    answer.append(cycle(i, j, k))
    return sorted(answer)


"""
빛의 경로를 동서남북 모든 방향에서 탐색
"""
