from collections import deque

def solution(land):   
    global n, m
    n = len(land)
    m = len(land[0])
    d = {}

    # search()에서는 BFS로 각 영역의 값을 unique value로 채움(사실상 방문 처리)
    # search()의 결과로 반환된 넓이는 uniq: sparse 형태로 딕셔너리에 저장됨
    uniq = 2
    for j in range(m):
        for i in range(n):
            if land[i][j] == 1:
                sparse = search(land, i, j, uniq)
                d[uniq] = sparse
                uniq += 1

    
    answer = -1
    for j in range(m):
        s = set()
        # 앞서 저장한 딕셔너리에서 해당 열의 합을 구한다.
        for i in range(n):
            if land[i][j] > 1 and land[i][j] not in s:
                s.add(land[i][j])

        # land의 각 영역은 그 영역에 대응되는 uniq로 저장되어 있으므로
        # 그냥 딕셔너리에서 d[land[i][j]]로 가져와 합을 구하면 된다.
        ans = 0
        for u in s:
            ans += d[u]
        answer = max(answer, ans)
        
    return answer 
    

def search(land, i, j, uniq):
    sparse = 0
    diff = [-1, 0, 1, 0]

    q = deque()
    q.append((i, j))

    while q:
        ci, cj = q.popleft()
        land[ci][cj] = uniq
        sparse += 1

        for idx in range(4):
            ni, nj = ci + diff[idx], cj + diff[-1 - idx]
            ne =  (ni, nj)
            
            if not range_check(ni, nj):
                continue

            if land[ni][nj] != 1:
                continue

            land[ni][nj] = uniq # 방문 처리
            q.append(ne)

    return sparse
    
    
def range_check(i, j):
    return (0 <= i < n) and (0 <= j < m)
