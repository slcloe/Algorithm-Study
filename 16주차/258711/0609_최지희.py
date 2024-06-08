from collections import defaultdict,deque

def solution(edges):
    def bfs(flag):
        q=deque([flag])
        visited.add(flag)
        while q:
            x=q.popleft()
            for nx in board[x]:
                if nx not in visited:
                    visited.add(nx)
                    q.append(nx)
                else:
                    return True
        return False

    answer=[0,0,0,0]
    in_edge=defaultdict(int) # 정점에서 들어오는 간선
    out_edge=defaultdict(int) # 정점에서 나가는 간선
    board=defaultdict(list) # 그래프

    for a,b in edges:
        in_edge[b]+=1
        out_edge[a]+=1
        board[a].append(b)

    visited=set()
    for k,v in out_edge.items():
        if k in visited:
            continue
        # 중간점
        if v>1 and in_edge[k]==0:
            visited.add(k)
            answer[0]=k
        # 8자
        elif v==2 and in_edge[k]>=2:
            bfs(k)
            answer[3]+=1

    for i in board[answer[0]]:
        if i in visited:
            continue
        # 도넛
        if bfs(i):
            answer[1]+=1
        # 막대
        else:
            answer[2]+=1
    return answer

"""
8: 중간점이 in==out==2 (중간점이랑 연결되어있으면 in==3)
도넛: 8자를 찾고도 사이클이 있으면 도넛
막대: 나머지
중간: out>1 and in==0
"""
