def solution(name):
    # 위 아래는 Greedy choice
    # 왼쪽 갈지 오른쪽 갈지는 Greedy choice가 불가능하다: "ABAAAABB"
    # -> 위 아래는 Greedy choice, 왼쪽 오른쪽은 완전탐색

    def solve(curr, idx=0, ans=0):
        # 1. 현재 idx에 대해 알파벳을 일치시키는 위아래 횟수 체크
        ud = up_or_down(name, curr, idx)
        curr[idx] = name[idx]
        ans += ud

        if curr == name:
            return ans

        # 2. 다음 인덱스와 거기까지 가는 최소 횟수를 구함(좌우 다)
        right_next, right_cnt = get_right_next(curr, name, idx)
        left_next, left_cnt = get_left_next(curr, name, idx)

        r = [cu for cu in curr]
        right_ans = solve(r, right_next, ans + right_cnt)
        l = [cu for cu in curr]
        left_ans = solve(l, left_next, ans + left_cnt)

        
        # 3. 왼쪽으로 간 경우의 답과 오른쪽으로 간 경우의 답 비교 후 최소값 리턴
        return min(right_ans, left_ans)

    name = list(name)
    return solve(curr=['A' for _ in range(len(name))])


def get_right_next(curr, name, idx):
    n = len(curr)
    next = (idx + 1)%n
    cnt = 1
    while next != idx and curr[next] == name[next]:
        next = (next + 1) % n
        cnt += 1

    return next, cnt


def get_left_next(curr, name, idx):
    n = len(name) 
    next = idx - 1
    cnt = 1

    while 0 <= next and curr[next] == name[next]:
        next -= 1
        cnt += 1

    if next == -1:
        next = n - 1
        while idx < next and curr[next] == name[next]:
            next -= 1
            cnt += 1

    return next, cnt


def up_or_down(name: str, curr, idx: int):
    a = ord(name[idx]) - ord(curr[idx])
    b = 26 - a
    
    return min(a, b)
