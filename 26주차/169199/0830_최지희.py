import heapq
def solution(board):
    r=len(board)
    c=len(board[0])
    q=[]
    dist=[[1e9]*c for _ in range(r)]
    d=[(-1,0),(0,-1),(0,1),(1,0)]
    for i in range(r):
        for j in range(c):
            if board[i][j]=="R":
                q.append((0,i,j))
                dist[i][j]=0
    while q:
        cnt,x,y=heapq.heappop(q)
        if dist[x][y]<cnt:
            continue
        for dx,dy in d:
            nx=x
            ny=y
            while True:
                nx+=dx
                ny+=dy
                if -1<nx<r and -1<ny<c:
                    if board[nx][ny]=="D":
                        break
                else:
                    break
            nx-=dx
            ny-=dy
            if board[nx][ny]=="G":
                return cnt+1
            if dist[nx][ny]>cnt+1:
                heapq.heappush(q,(cnt+1,nx,ny))
                dist[nx][ny]=cnt+1
    return -1
