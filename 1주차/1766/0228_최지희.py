import sys
from collections import defaultdict
import heapq

input = sys.stdin.readline


def main():
    N, M = map(int, input().split())
    inorder = [0] * (N + 1)  # 해당 번호보다 먼저 시작해야하는 번호수
    board = defaultdict(list)
    for _ in range(M):
        a, b = map(int, input().split())
        inorder[b] += 1
        board[a].append(b)
    hq = []
    for i in range(1, N + 1):
        if not inorder[i]:
            heapq.heappush(hq, i)
    ans = []
    while hq:
        ans.append(heapq.heappop(hq))
        for x in board[ans[-1]]:
            inorder[x] -= 1
            if not inorder[x]:
                heapq.heappush(hq, x)
    print(" ".join(map(str, ans)))


main()

"""
접근방법: 위상정렬과 우선순위큐를 사용
"""
