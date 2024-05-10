from sys import stdin
import heapq

class Edge:
    def __init__(self, u, v, w):
        self.u = u
        self.v = v
        self.w = w

    def __lt__(self, other):
        return self.w < other.w

def find(parent, u):
    if parent[u] < 0:
        return u
    parent[u] = find(parent, parent[u])
    return parent[u]

def union(parent, u, v):
    root_u = find(parent, u)
    root_v = find(parent, v)
    
    if root_u == root_v:
        return False

    if parent[root_u] < parent[root_v]:
        parent[root_u] += parent[root_v]
        parent[root_v] = root_u
    else:
        parent[root_v] += parent[root_u]
        parent[root_u] = root_v
    
    return True

def kruskal(edges, num_nodes):
    parent = [-1] * (num_nodes + 1)
    day = 0
    while edges and day < num_nodes - 1:
        edge = heapq.heappop(edges)
        if not union(parent, edge.u, edge.v):
            continue
        if day + 1 < edge.w:
            break
        day += 1
    return day + 1

input_data = stdin.read().strip().splitlines()
N, M = map(int, input_data[0].split())
edges = []
for i in range(1, M + 1):
    u, v, w = map(int, input_data[i].split())
    heapq.heappush(edges, Edge(u, v, w))

result = kruskal(edges, N)
print(result)
