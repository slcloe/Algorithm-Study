import sys
input = sys.__stdin__.readline


def main():
    h, w = tuple(map(int, input().split()))
    
    # d 0: ↑, 1: →, 2: ↓, 3: ←
    r, c, d = tuple(map(int, input().split()))

    direction = [
        (-1, 0),
        (0, 1),
        (1, 0),
        (0, -1),
    ]

    rule_table_a = []
    for _ in range(h):
        rule_table_a.append([int(c) for c in input().rstrip()])

    rule_table_b = []
    for _ in range(h):
        rule_table_b.append([int(c) for c in input().rstrip()])
    
    rule_tables = {
        True: rule_table_a,
        False: rule_table_b
    }

    is_clean = [[False for j in range(w)] for i in range(h)]
    ci, cj = r, c
    rule_table = None
    di = d
    count = 0
    just_cleaned = False # 방금 청소했으면 True, 아니면 False

    visited = set() # (i, j, direction, state, )
    visited.add((r, c, is_clean[r][c], di))
    while True:
        print(f'({ci}, {cj})')
        # 밖으로 나갔는지
        if not range_check(i=ci, j=cj, h=h, w=w):
            break

        # 사이클 여부
        # 1. 방문한 적 있는 위치인가?
        # 1-1. 이전과 같은 방향으로 방문했는가?
        # 1-2. 방문할 때 그 칸의 먼지 상태가 이전과 동일한가?
        # 1-3. 이전 방문과 현재 방문 사이에 먼지를 지운 적이 있는가?
        # 1-3을 체크해야 하는 이유: 동일한 이동 경로가 발생하는 것이 사이클이므로
        if (ci, cj, is_clean[ci][cj], di) in visited and just_cleaned: 
            break

        if is_clean[ci][cj]: # 현재 칸에 먼지가 없다면
            just_cleaned = False
        else:
            just_cleaned = True # 청소했으니까 True
            is_clean[ci][cj] = True

        rule_table = rule_tables[just_cleaned]
        visited.add((ci, cj, is_clean[ci][cj], di))
    
        di = (di + rule_table[ci][cj]) % 4
        
        ci += direction[di][0]
        cj += direction[di][1]
        count += 1
    
    print(count)

def range_check(i, j, h, w):
    return (0 <= i < h) and (0 <= j < w)



main()