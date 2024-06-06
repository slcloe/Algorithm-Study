"""
처음에 엘사와 안나의 눈사람을 둘 다 투 포인터를 통해 구하려고 하다가 된통 당했다.
정렬된 눈덩이의 지름 크기에 따라 특정 조합의 엘사 눈사람이 구해지지 않을 수 있기 때문이다.

따라서 엘사의 눈사람은 2중 루프를 이용한 완전 탐색으로 구하고, 안나의 눈사람만 투 포인터를 통해 구했다.
엘사가 선택한 눈덩이 사이에는 최소 2개의 눈덩이가 포함되어야하므로 2중 루프의 범위를 적절히 바꿔주었다.
엘사의 눈사람은 고정되어 있으니 안나의 눈사람과의 차이값이 양수 혹은 0 이면 왼 포인터를 움직이고, 음수면 오른 포인터를 움직이면 된다.
"""

import sys

N = int(input())
diameter = list(map(int, input().split()))
diameter.sort()

sol = sys.maxsize
for left in range(0, N-3, 1):
    for right in range(left+3, N, 1):
        if sol == 0:
            print(sol)
            sys.exit(0)

        elsa = diameter[left] + diameter[right]
        tl, tr = left+1, right-1

        while tl < tr:
            anna = diameter[tl] + diameter[tr]
            gap = elsa - anna

            if gap >= 0:
                tl += 1
            else:
                tr -= 1

            sol = min(sol, abs(gap))

print(sol)
