"""
스택은 진입점이 하나인 자료구조이므로 네 개의 스택의 진입점을 하나의 리스트를 통해 관리할 수 있을 것이다.
네 개의 진입점 중에 어떤 것을 사용할지 고민해보자.

1. 스택의 peek 값이 가장 낮은 것에 우선적으로 추가하자.
-> 두 개의 스택이 존재한다고 가정했을 때, 4 6 9 5 과 같은 방식으로 들어오면 처리하지 못한다.

2. 스택의 peek 값이 가장 높은 것에 우선적으로 추가하자.
-> 가능한 스택 중에서 현재 추가할 값과 가장 가까운 값을 가진 스택에 원소를 삽입하게 되므로 가장 Fit한 방식이다.

2번 방식을 사용했고, peek 값의 크기에 따라 어떤 스택을 사용할 것인지 결정하므로 스택의 진입점을 힙을 통해 관리했다.
"""

import sys
import heapq

input = sys.stdin.readline

N = int(input())
sequence = list(map(lambda x: int(x) * -1, input().split()))

heap = []

for _ in range(4):
    heapq.heappush(heap, 0)

for i in range(N):
    remainder = []
    for _ in range(4):
        current = heapq.heappop(heap)

        if sequence[i] < current:
            heapq.heappush(heap, sequence[i])
            break
        else:
            remainder.append(current)
    else:
        print("NO")
        break

    for rm in remainder:
        heapq.heappush(heap, rm)

else:
    print("YES")
