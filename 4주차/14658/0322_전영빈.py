"""
    별똥별이 떨어질 수 있는 범위가 500000^2 이고 트램펄린의 최소 크기가 1이므로 최악의 경우
    트램펄린이 놓여질 수 있는 경우의 수인 500000^2 가지를 다 고려해봐야 한다.
    하지만 별똥별의 개수인 K는 최대 100 이다.
    특정 별똥별이 포함되면서 다른 별똥별들을 가장 많이 받아낼 수 있기 위해서는 특정 별똥별이 범위의 가장자리에 위치해야 한다고 생각.
    별똥별이 해당 범위의 모서리에 해당하는 네 가지의 경우에 대해 별똥별이 포함되는 수를 탐색하는 작업을 K번 반복하여 풀이.
    
    ...로 풀어보았으나 그럴 경우 별똥별이 가장자리의 중 모서리가 아닌 애매한 지점에 있는 경우를 탐색하지 못한다.
    따라서, 별똥별 그룹 중 2개를 조합으로 뽑아 두 별똥별이 만드는 사각형 범위에서 탐색하는 작업을 K번 반복하여 풀이했다.
"""

import sys

input = sys.stdin.readline

N, M, L, K = map(int, input().split())

star = []
for _ in range(K):
    star.append(tuple(map(int, input().split())))

def getScore(x, y):
    res = 0

    for cx, cy in star:
        if x <= cx <= x+L and y <= cy <= y+L:
            res += 1

    return res

sol = 0
for i in range(K):
    for j in range(K):
        sol = max(sol, getScore(min(star[i][0], star[j][0]), min(star[i][1], star[j][1])))

print(K-sol)
