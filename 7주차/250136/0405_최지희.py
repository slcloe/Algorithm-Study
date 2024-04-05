from collections import deque
def solution(land):
    answer = 0
    n=len(land)
    m=len(land[0])
    group=dict()
    cur=1
    d=[(0,1),(1,0),(0,-1),(-1,0)]
    for i in range(n):
        for j in range(m):
            if land[i][j]==1:
                cur+=1
                q=deque([(i,j)])
                land[i][j]=cur
                cnt=1
                while q:
                    x,y=q.popleft()
                    for dx,dy in d:
                        nx=x+dx
                        ny=y+dy
                        if -1<nx<n and -1<ny<m:
                            if land[nx][ny]==1:
                                land[nx][ny]=cur
                                q.append((nx,ny))
                                cnt+=1
                group[cur]=cnt
    for j in range(m):
        visited=set()
        res=0
        for i in range(n):
            if land[i][j]>1 and land[i][j] not in visited:
                res+=group[land[i][j]]
                visited.add(land[i][j])
        if res>answer:
            answer=res          
    return answer


"""
bfs를 이용해서 미리 석유량을 계산
모든 col를 돌면서 시추관의 위치와 획득한 덩어리들 중 최대를 구함

미리 값을 저장하고 해당 값을 사용
	방문배열을 따로 만들지 않고 
	주어진 land에서 그룹 정보를 저장하면서 방문처리를 진행
"""
