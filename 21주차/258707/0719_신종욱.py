from collections import deque

def solution(coin, cards):
    n = len(cards)
    
    my_cards = set(cards[:n//3])
    cards = deque(cards[n//3:])

    # 합이 n + 1이 되도록
    # a에서 한 장, b에서 한 장 뽑는다.
    def pick(a, b):
        for x in a:
            # 카드의 숫자는 1 ~ n이므로 체크할 필요 없음
            # if x == n + 1:
            #   continue

            # 중복 카드는 없으므로 x != n + 1 - x는 체크할 필요 없음
            y = n + 1 - x
            if y in b:
                a.remove(x)
                b.remove(y)
                return True

        return False


    round = 1
    drawed = set()
    while cards:
        # 2장 뽑기
        i = 0
        while cards and i < 2:
            drawed.add(cards.popleft())
            i += 1

        # 코인을 최대핸 아껴야 최대 라운드가 나오니까 이 순서로 해야 됨
        # 1. 코인 0개 소모 + 버리기
        # 2. 코인 1개 소모 + 1장 갖기
        # 3. 코인 2개 소모 + 2장 갖기
        if pick(my_cards, my_cards):
            round += 1
        elif coin >= 1 and pick(my_cards, drawed):
            coin -= 1
            round += 1
        elif coin >= 2 and pick(drawed, drawed):
            coin -= 2
            round += 1
        else: # 코인이 없거나 
            break

    answer = round
    return answer
