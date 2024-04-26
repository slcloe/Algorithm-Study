'''
1. 친구끼리 그룹을 만든다.
2. 그룹 구성원이 k 미만인 경우 사탕의 합을 구한다.
3. 그 중에 최대 값을 구한다.

근데 쉽지 않음
'''

import sys
input = sys.stdin.readline

def make_set(n):
    return [i for i in range(n)]

def find_set(parent_of, x):
    if parent_of[x] == x:
        return x

    parent_of[x] = find_set(parent_of, parent_of[x])
    return parent_of[x]

def union(parent_of, x, y):
    x_parent = find_set(parent_of, x)
    y_parent = find_set(parent_of, y)

    if(x_parent == y_parent):
        return False

    parent_of[x_parent] = y_parent

    return True

def main():
    n, m, k = list(map(int, input().split()))
    candies = list(map(int, input().split()))
    parent_of = make_set(n)
    for _ in range(m):
        a, b = list(map(int, input().split()))
        a -= 1
        b -= 1
        union(parent_of, a, b)

    groups = set()
    for i in range(n):
        groups.add(find_set(parent_of, i))

    answer = -1
    for i in range(n):
        record = []
        ans = 0
        count = 0
        curr = i
        while count <= k:
            record.append(curr)
            count += 1
            ans += candies[curr]
            if parent_of[curr] == curr:
                break
            curr = parent_of[curr]

        if count < k:
            answer = max(answer, ans)

    print(answer)

main()
