from collections import deque
import sys
input = sys.stdin.readline

# 루트 기록 어떻게 할지 모르겠음....

def bfs():
    q = deque([(p[0], p[1])])
    while q:
        node1, node2 = q.popleft()
        if node1['x'] == node2['x'] and node1['y'] == node2['y']:
            print(check[node1['x']][node1['y']][node2['x']][node2['y']] - 1)
            return
        for i in range(4):
            next1 = {'x': (node1['x'] + dx[i]) % n, 'y': (node1['y'] + dy[i]) % m, 'ans': node1['ans'] + d[i]}
            next2 = {'x': (node2['x'] + dx[i]) % n, 'y': (node2['y'] + dy[i]) % m, 'ans': node2['ans'] + d[i]}
            if a[next1['x']][next1['y']] == 'G' or a[next2['x']][next2['y']] == 'G':
                continue
            if a[next1['x']][next1['y']] == 'X':
                next1['x'], next1['y'] = node1['x'], node1['y']
            if a[next2['x']][next2['y']] == 'X':
                next2['x'], next2['y'] = node2['x'], node2['y']
            if check[next1['x']][next1['y']][next2['x']][next2['y']] > check[node1['x']][node1['y']][node2['x']][node2['y']] + 1:
                check[next1['x']][next1['y']][next2['x']][next2['y']] = check[node1['x']][node1['y']][node2['x']][node2['y']] + 1
                q.append((next1, next2))
    print('IMPOSSIBLE')

def main():
    
    data = input().split()
    idx = 0
    t = int(data[idx])
    idx += 1
    for _ in range(t):
        n = int(data[idx])
        m = int(data[idx + 1])
        idx += 2
        global a, p, check
        a = []
        p = []
        check = [[[[float('inf')] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]
        for j in range(n):
            a.append(list(data[idx]))
            idx += 1
            for k in range(m):
                if a[j][k] == 'P':
                    a[j][k] = '.'
                    p.append({'x': j, 'y': k, 'ans': ''})
        check[p[0]['x']][p[0]['y']][p[1]['x']][p[1]['y']] = 1
        bfs()

main()
