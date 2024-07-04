from collections import deque
def solution(maps):
    answer = []
    maps=[list(m) for m in maps]
    n=len(maps)
    m=len(maps[0])
    d=[(-1,0),(1,0),(0,-1),(0,1)]
    for i in range(n):
        for j in range(m):
            if maps[i][j]!="X":
                cnt=int(maps[i][j])
                q=deque([(i,j)])
                maps[i][j]="X"
                while q:
                    x,y=q.popleft()
                    for dx,dy in d:
                        nx=x+dx
                        ny=y+dy
                        if -1<nx<n and -1<ny<m and maps[nx][ny]!="X":
                            cnt+=int(maps[nx][ny])
                            maps[nx][ny]="X"
                            q.append((nx,ny))
                answer.append(cnt)
    return sorted(answer)

print(solution(["X591X","X1X5X","X231X", "1XXX1"]))