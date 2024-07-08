from collections import deque
def solution(places):
    def bfs(r,c):
        visited={(r,c)}
        q=deque([(r,c)])
        while q:
            x,y=q.popleft()
            for dx,dy in d:
                nx=x+dx
                ny=y+dy
                if -1<nx<5 and -1<ny<5 and (nx,ny) not in visited:
                    if abs(r-nx)+abs(c-ny)<=2:
                        if place[nx][ny]=="P":
                            return False
                        elif place[nx][ny]=="O":
                            q.append((nx,ny))
                            visited.add((nx,ny))
        return True
    
    def check():
        for r in range(5):
            for c in range(5):
                if place[r][c]=="P":
                    if not bfs(r,c):
                        return 0
        return 1
    
    answer = []
    d=[(-1,0),(0,1),(1,0),(0,-1)]
    for place in places:
        answer.append(check())
        
    return answer

#bfs 이용
