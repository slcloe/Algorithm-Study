import math
import sys

input = sys.stdin.readline
sys.setrecursionlimit(10**6)


def main():
    n = int(input())

    incidences = list([] for _ in range(n + 1))
    parents = [-1] * (n + 1)
    child_cnt = [0] * (n + 1)
    edges = []

    cnt_d, cnt_g = 0, 0

    for _ in range(n - 1):
        u, v = map(int, input().split())
        incidences[v].append(u)
        incidences[u].append(v)
        edges.append([u, v])


    def solve_g(n):
        return math.comb(len(incidences[n]), 3)


    # ㅈ
    for i in range(1, n + 1):
        cnt_g += solve_g(i)

    # ㄷ
    for edge in edges:
        u, v = edge
        cnt_d += (len(incidences[u]) - 1) * (len(incidences[v]) - 1)

    if cnt_d > cnt_g * 3:
        print("D")
    elif cnt_d < cnt_g * 3:
        print("G")
    else:
        print("DUDUDUNGA")

main()
