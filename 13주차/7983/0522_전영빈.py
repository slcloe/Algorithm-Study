"""
오늘부터 가장 오래 쉬기 위해서는 할 수 있는 한 숙제를 최대한 미뤄서 수행해야 한다.
즉, 최대한 숙제가 데드라인에 맞춰서 끝날 수 있도록 스케줄링을 해야한다.

모든 숙제가 데드라인에 맞춰서 끝날 가능성을 높이기 위해서는 숙제 중 데드라인이 가장 먼 숙제부터 데드라인에 맞춰 해결해야 한다.
남은 숙제들 중 데드라인이 가장 먼 숙제를 우선적으로 해결하는 부문제를 모든 숙제가 해결될때까지 풀이한다고 생각할 수도 있겠다.
초기 남은 시간을 숙제의 데드라인 중 가장 먼 데드라인으로 잡고 각 숙제를 데드라인을 기준으로 하는 최대힙에 넣은 후, 하나씩 빼며 처리했다.

모든 숙제를 완료했을 때 남은 시간이 내일부터 연속으로 쉴 수 있는 기간이다.
"""

import sys
import heapq

input = sys.stdin.readline

n = int(input())
heap = []
for _ in range(n):
    d, t = map(int, input().split())
    heapq.heappush(heap, (-t, d))

time = abs(heap[0][0])

while heap:
    d, t = map(lambda x : abs(int(x)), heapq.heappop(heap))
    time = min(time - t, d -t)

print(time)
