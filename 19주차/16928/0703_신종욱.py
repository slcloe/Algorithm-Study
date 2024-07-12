import sys
input = sys.__stdin__.readline

from collections import deque

def main():
    n, m = [int(x) for x in input().split()]
    d = {}
    for _ in range(n + m):
        u, v = [int(x) for x in input().split()]
        d[u] = v

    def bfs():
        visited = set([1])
        q = deque([(1, 0)])
        while q:
            v = q.popleft()
            curr, c = v
            
            if curr == 100:
                print(c)
                return

            for i in range(1, 6 + 1):
                next = v[0] + i
                if next not in visited:
                    if next <= 100:
                        if next in d:
                            q.append((d[next], c + 1))
                            visited.add(d[next])
                        else:
                            q.append((next, c + 1))
                        visited.add(next)
                        
    return bfs()

print(main())
