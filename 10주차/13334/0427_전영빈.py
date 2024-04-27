"""
처음에 문제를 보고 좌표 상에 간선 정보를 기록하고 슬라이딩 윈도우를 이용하여 좌표를 d 크기의 슬라이드로 스캔해
해당 슬라이드 안에 포함 가능한 선분을 세려고 했다.
하지만 좌표의 범위가 2억이기 때문에 단순히 탐색을 돌려도 2억번의 연산을 하게 된다.
따라서, 간선의 정보를 좌표에 저장하는 것이 아닌 간선 그 자체를 이용해 정답을 구해야 함을 알 수 있다.

문제의 포인트는 길이가 d 미만인 두 점 h와 o가 있을 때 어떠한 기준으로 선분을 만드느냐라고 생각했다.
(h1, o1)점을 기준으로 선분을 만든다고 하면 해당 선분에는 다음의 조건을 만족하는 선분 (h2, o2)가 포함될 수 있다.
1. h2 >= h2 - d
2. o2 <= o1
각 선분을 o를 기준으로 오름차정렬하면 2번째 조건을 항상 만족할 수 있고,
h를 기준으로 힙을 사용하여 선분이 들어올 때 마다 1번째 조건을 만족하지 않는 선분들을 힙에서 제거했다.

이를 통해 각 선분이 포함될 때마다 힙의 길이를 보면 해당 선분이 몇 개의 선분을 포함할 수 있는지 알 수 있다.
"""

import sys
import heapq

input = sys.stdin.readline

N = int(input())

lane = []
for _ in range(N):
    h, o = map(int, input().split())
    if h < o:
        lane.append((h, o))
    else:
        lane.append((o, h))

d = int(input())

lane.sort(key=lambda x: (x[1], x[0]))

heap = []
sol = 0
for start, end in lane:
    if abs(end - start) > d:
        continue

    while heap and end - d > heap[0][0]:
        heapq.heappop(heap)

    heapq.heappush(heap, (start, end))

    sol = max(sol, len(heap))

print(sol)
