"""
우선, 두 카드의 합이 n+1가 되기 위해서는 두 카드의 관계가 a와 n+1-a가 되어야 한다.

라운드를 오래 지속하기 위해서는 최대한 n+1이 완성될 확률이 높은 카드들만 가져가야 한다.
1. 손에 있는 카드 안에서 n+1 완성이 가능한 경우 -> 코인 0개 소모
2. 손에 있는 카드와 n+1 완성이 가능한 경우 -> 코인 1개 소모
3. 카드 뭉치에 있는 카드를 통해 n+1 완성이 가능한 순간이 빠른 경우 -> 코인 2개 소모

그렇다면, 굳이 그 순간에 카드를 손에 가져올 필요가 있을까?
다른 곳에 두고 필요할 때마다 "이전 차례에 그 카드를 가져왔다고 치고" 넘어가면 되지 않을까?
그렇다면 굳이 미래에 완성될 가능성이 높은 카드를 우선적으로 가져올 필요도 없다.
코인이 적게 드는 쌍 중 아무거나 선택해서 그 라운드를 버티기만 하면 되는거니까.

각 라운드마다 발생할 수 있는 경우의 수를 필요한 코인 수를 매개로 하는 힙을 사용해서 풀이했다.
1. 그 라운드에 힙이 비어있다면 -> 현재 상황에서 버릴 수 있는 조합이 없다.
2. 버리는 행동을 마친 이후에 코인이 0 미만이라면 -> 가능한 조합들을 이행할 수 없다.
"""

import heapq

def solution(coin, cards):
    answer = 1
    
    n = len(cards)
    hands = dict()
    side = dict()
    heap = []
    
    def addSide(num):
        if n + 1 - num in hands:
            heapq.heappush(heap, 1)
        elif n + 1 - num in side:
            heapq.heappush(heap, 2)
        else:
            side[num] = True
    
    for i in range(n//3):
        if n+1 - cards[i] in hands:
            heapq.heappush(heap, 0)
        else:
            hands[cards[i]] = True
    
    for i in range(n//3, n, 2):
        addSide(cards[i])
        addSide(cards[i+1])
        
        if not heap:
            break
            
        coin -= heapq.heappop(heap)
        if coin < 0:
            break
        
        answer += 1

    return answer
