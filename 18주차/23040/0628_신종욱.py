import sys
from collections import defaultdict, deque

def main():
    N = int(input())
    graph = defaultdict(list)
    leaf_color = []
    for i in range(N-1):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)
    leaf_color = list(input().strip())

    if not exist_black(leaf_color):
        print(0)
    else:
        print(get_nutella_tree_count(N, graph, leaf_color))

def get_nutella_tree_count(N, graph, leaf_color):
    nutella = [0] * (N+1)
    visit = [0] * (N+1)
    ret = 0
    for i in range(1, N+1):
        if leaf_color[i-1] != 'R':
            ret += enter_nutella(i, i, graph, leaf_color, nutella, visit)
    return ret

def enter_nutella(idx, v, graph, leaf_color, nutella, visit):
    if nutella[idx] > 0 and leaf_color[idx-1] != 'B':
        return nutella[idx]
    if leaf_color[idx-1] != 'B':
        nutellastack = deque()
        nutellastack.append(idx)
    visit[idx] = v
    ret = 0
    for neighbor in graph[idx]:
        if visit[neighbor] == v or leaf_color[neighbor-1] == 'B':
            continue
        cnt = enter_nutella(neighbor, v, graph, leaf_color, nutella, visit)
        ret += cnt + 1
        if leaf_color[idx-1] == 'B':
            while nutellastack:
                nutella[nutellastack.pop()] = cnt
    return ret

def exist_black(leaf_color):
    for color in leaf_color:
        if color == 'B':
            return True
    return False


main()
