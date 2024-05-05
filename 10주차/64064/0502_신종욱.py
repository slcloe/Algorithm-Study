def solution(user_id, banned_id):
    global answer, g_visited
    answer = 0
    g_visited = set()

    ans = []
    keys = []

    for banned in banned_id:
        keys.append(banned)
        a = []
        for uid in user_id:
            l = len(uid)
            if l != len(banned):
                continue
            
            flag = True
            for i in range(l):
                if banned[i] != '*' and banned[i] != uid[i]:
                    flag = False
                    break
            if flag:
                a.append(uid)
        ans.append(a)
    
    dfs(ans, keys, res=list(), lim=len(keys), visited=set(), idx=0)
    return len(g_visited)


def dfs(ans, keys, res, lim, visited, idx):
    global answer, g_visited

    # 최종적으로 만들어진 결과에 대한 중복 체크가 이루어져야 함
    # 다른 더 좋은 방법이 있는지 모르겠으나 그냥 정렬 때려버렸음...
    # 이렇게 하면 문제의 테스트케이스 3에서 발생하는 다음과 같은 경우의 중복도 체크됨
    # ['frodo', 'crodo', 'abc123', 'frodoc']
    # ['frodo', 'crodo', 'frodoc', 'abc123'] 
    # 요소가 같으면 정렬한 결과는 같아지니까 그 결과를 set에 넣고 set의 사이즈를 반환하면 정답이 된다
    if idx == lim:
        result = ' '.join(sorted(res))
        g_visited.add(result)
        return

    for _user_id in ans[idx]:
        if _user_id not in visited:
            res.append(_user_id)
            visited.add(_user_id)
            dfs(ans, keys, res, lim, visited, idx + 1)
            visited.remove(_user_id)
            res.pop()
