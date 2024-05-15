"""
모든 인원은 시작 시작이 되면 바로 컴퓨터를 사용할 수 있어야 한다. 따라서 우선 시작 시간을 기준으로 인원들을 정렬한다.

한 인원이 컴퓨터를 바로 사용하기 위해서는 기존에 배치된 컴퓨터 중 사용 되지 않지 않는 것을 이용하거나
새로운 컴퓨터를 배치해야 된다.

한 인원이 기존에 배치된 컴퓨터를 사용하기 위해서는 기존에 사용 중이던 컴퓨터가 해제되어야 한다. 사용 중이던 컴퓨터의 해제는
사용 중이던 인원의 종료 시간이 접근을 시도한 인원의 시작 시간보다 짧을 때 가능하다.
=> 사용 중인 인원을 종료 시간을 기준으로 하는 힙을 통해 관리한다.

또한, 모든 인원은 현재 사용 가능한 컴퓨터 중 번호가 가장 낮은 컴퓨터를 우선으로 사용해야 한다.
=> 사용 가능한 컴퓨터를 번호를 기준으로 하는 힙을 통해 관리한다.
"""

import sys
import heapq

input = sys.stdin.readline

N = int(input())
person = [ list(map(int, input().split()))for _ in range(N)]

person.sort(key=lambda x : (x[0], x[1]))

solve = []
running = []
ready = []

for start, end in person:
    while running and running[0][0] <= start:
        tempEnd, idx = heapq.heappop(running)
        heapq.heappush(ready, idx)

    if len(ready) == 0:
        solve.append(1)
        heapq.heappush(running, (end, len(solve)-1))
    else:
        idx = heapq.heappop(ready)
        solve[idx] += 1
        heapq.heappush(running, (end, idx))

print(len(solve))
print(*solve)
