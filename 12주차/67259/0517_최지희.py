from collections import deque
def solution(board):
    answer = 0
    n=len(board)
    visited=[[[10e9,10e9,10e9,10e9] for _ in range(n)] for _ in range(n)] # 어떤 방향으로 들어오는지에 따른 가격
    dd=[(-1,0),(0,1),(1,0),(0,-1)] # 북동남서
    q=deque([(1,0,0),(2,0,0)])
    visited[0][0][1]=0
    visited[0][0][2]=0
    while q:
        d,x,y=q.popleft()
        for di in range(4):
            if (di+2)%4==d: # 역주행
                continue
            nx=x+dd[di][0]
            ny=y+dd[di][1]
            cost=100
            if di!=d:
                cost+=500
            if -1<nx<n and -1<ny<n and not board[nx][ny]:
                if visited[x][y][d]+cost<visited[nx][ny][di]:
                    visited[nx][ny][di]=visited[x][y][d]+cost
                    q.append((di,nx,ny))    
    return min(visited[-1][-1])

"""
bfs를 통한 탐색 문제
방문처리: 3차원 배열
들어오는 방향에 따라서 가격이 달라지기 때문에 방문처리를 방향에 따라서 해줘야 한다.
"""
