"""
우선 각 지점에서 알파벳을 바꾸기 위해 조이스틱을 위아래로 움직여야 하는 횟수는 정해져 있다.
구해야 하는 것은 어떠한 루트로 조이스틱을 좌우로 움직일 것인가.

조이스틱을 좌우로 움직이는 횟수를 줄이기 위해서 접근한 방식은 다음과 같다.

1. 좌우 방향으로 움직여 실제 이동 좌표가 초기화되는 상황을 최소화하자.
해당 케이스의 경우 조이스틱을 오른쪽으로 혹은 왼쪽으로 움직여야만 한다.
하지만 이 경우, 알파벳 길이가 최대값이면서 0번 1번 N-1번 인덱스에만 알파벳 변동이 필요한 경우에 대해 최적해를 구하지 못한다.

2. 그냥 좌우로 움직일 수 있는 모든 상황 중에 최소값을 구하자.
오른쪽으로 쭉 움직였을 때 조이스틱이 이동하는 횟수는 N-1번이다.
해당 이동 횟수 내에서 가능한 모든 경우를 구해 그 중 최소값을 찾았다.
이 때, 각 환경마다 자신이 어떤 좌표에 방문했었는지를 기억하고 있어야 하기 때문에 파라메터로 방문 좌표 데이터를 추가하여 구헌했다.
"""

import sys
from collections import deque
from copy import deepcopy

def solution(name):
    name = list(map(lambda x : min(ord(x) - 65, ord('Z') - ord(x) + 1), list(name)))
    length = len(name)
    total = sum(name)
    answer = sys.maxsize
    
    queue = deque()
    temp = deepcopy(name)
    temp[0] = 0
    queue.append((0, 0, name[0], temp))
    answer = sys.maxsize
    
    while queue:
        idx, count, res, seq = queue.popleft()
        
        if res == total:
            answer = min(answer, res + count)
            continue
            
        if count == length - 1:
            continue
            
        frontIdx = (idx + 1) % length
        frontRes = res + seq[frontIdx]
        frontSeq = deepcopy(seq)
        frontSeq[frontIdx] = 0
        queue.append((frontIdx, count+1, frontRes, frontSeq))
        
        backIdx = (length + idx - 1) % length
        backRes = res + seq[backIdx]
        backSeq = deepcopy(seq)
        backSeq[backIdx] = 0
        queue.append((backIdx, count+1, backRes, backSeq))

    return answer
