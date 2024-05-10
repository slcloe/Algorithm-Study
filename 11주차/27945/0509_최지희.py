import sys
input = sys.stdin.readline


def main():
    def union(a, b):
        a = find(a)
        b = find(b)
        if a == b:
            return False
        p[a]=p[b]
        return True

    def find(a):
        if a != p[a]:
            p[a] = find(p[a])
        return p[a]

    N, M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(M)]
    board.sort(key=lambda x: x[2])
    p = [i for i in range(N + 1)]
    ans = 1
    for u, v, t in board:
        if ans != t:
            break
        if not union(u,v):
            break
        ans += 1
    print(ans)
main()

"""
모든 점을 방문하는 조건
- 1일씩 증가
- N개의 모든 점을 잇는 선분은 N-1개이다. 하나라도 사이클이 생기면 안된다
"""
