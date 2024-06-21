from collections import defaultdict,deque
            
def solution(n, wires):
    def bfs(a,b):
        exclude={(a,b),(a,b)} # 제외
        q=deque()
        q.append(a)
        towers=[0]+[-1]*(len(board.keys()))
        towers[a]=1
        while q:
            x=q.popleft()
            for nx in board[x]:
                if (x,nx) in exclude:
                    continue
                if towers[nx]==-1:
                    towers[nx]=1
                    q.append(nx)
        return abs(sum(towers))
    answer = 101
    global board
    board=defaultdict(list)
    for v1,v2 in wires:
        board[v1].append(v2)
        board[v2].append(v1)
    for k,v in sorted(board.items()):
        for e in v:
            if k>e:
                continue
            answer=min(bfs(k,e),answer)
    return answer
