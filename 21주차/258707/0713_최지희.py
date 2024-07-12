def solution(coin, cards):
    answer = 1
    n=len(cards)
    cur=set() # 양수: 처음 가진 카드, 음수: 이후
    # 현재 n+1이 되는 쌍 -> 필요한 코인 수
    zero=0
    one=0 
    two=0
    # 초기 조건
    for i in range(n//3):
        cur.add(cards[i])
        if n+1-cards[i] in cur:
            zero+=1
    for i in range(n//3,n,2):
        for d in range(2):
            cur.add(-cards[i+d])
            if n+1-cards[i+d] in cur:
                one+=1
            elif -n-1+cards[i+d] in cur:
                two+=1
        # 다음 라운드
        if zero:
            zero-=1
        elif one and coin:
            one-=1
            coin-=1
        elif two and coin>=2:
            two-=1
            coin-=2
        else:
            break
        answer+=1
    return answer

# 카드를 미리 가지지 않고 필요할때 가지는 순서로 생각
# 이전에 방문한 카드들이 쌍을 이루면 몇 개의 코인으로 가질 수 있을지 기록
