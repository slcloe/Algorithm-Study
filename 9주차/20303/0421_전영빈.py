"""
문제의 조건에서 한 명의 아이로부터 사탕을 빼앗으면 그 아이의 친구들의 사탕도 모두 빼앗아야 한다.
따라서, 아이들을 유니온 파인드를 이용해 친구 관계에 맞춰 클러스터링 해주었다.

이렇게 아이들을 그룹화하게 되면 각 그룹의 아이 수와 그 그룹의 사탕 합을 알 수 있게 된다.
아이의 총 수가 K명 미만이면서 사탕 수가 최대가 될 수 있게 그룹을 선택하는 것은 결국 용량이 K인 가방 문제를 푸는 것과 같다.
가방의 최대 크기가 3000이므로 크기가 3000 * 그룹 개수인 배열을 사용하는 일반적인 가방 문제를 풀이하는 방식으로는 오버헤드가
클 것이라고 판단하여 dictionary를 사용해 풀이했다.
"""

import sys

input = sys.stdin.readline

N, M, K = map(int, input().split())

candy = list(map(int, input().split()))
parent = [-1 for _ in range(N)]

def find(a):
    if parent[a] <= -1:
        return a

    parent[a] = find(parent[a])
    return parent[a]

def union(a, b):
    a = find(a)
    b = find(b)

    if a == b:
        return
    
    if candy[a] <= candy[b]:
        candy[a] += candy[b]
        parent[a] += parent[b]
        parent[b] = a
    else :
        candy[b] += candy[a]
        parent[b] += parent[a]
        parent[a] = b
    
for _ in range(M):
    a, b = map(lambda x : int(x) - 1, input().split())

    union(a, b)

weight = []
value = []
for i in range(N):
    if parent[i] < 0:
        weight.append(-parent[i])
        value.append(candy[i])

bag = {0 : 0}
for i in range(len(weight)):
    cache = []
    for cw, cv in bag.items():
        if cw + weight[i] < K:
            cache.append((cw + weight[i], cv + value[i]))

    for nw, nv in cache:
        if nw in bag:
            bag[nw] = max(nv, bag[nw])
        
        else:
            bag[nw] = nv

print(max(bag.values()))
