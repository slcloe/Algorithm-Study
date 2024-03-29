def find(parents, money, number, answer):
    if parents[number] == number or money // 10 == 0:
        answer[number] += money
        return
    
    send = money // 10
    mine = money - send
    answer[number] += mine
    find(parents, send, parents[number], answer)
    return


def solution(enroll, referral, seller, amount):
    n = len(enroll)  # 총 사람 수(민호 제외)
    answer = [0] * (n + 1)  # 민호 포함
    d = {} 
    parents = [i for i in range(n + 1)]
    
    # 이름: 번호
    for i in range(n):
        d[enroll[i]] = i + 1
    
    # 추천인 입력
    for i in range(n):
        if referral[i] == "-":  # 민호가 추천인이라면
            parents[i + 1] = 0
        else:
            parents[i + 1] = d[referral[i]]
    # 정산
    for i in range(len(seller)):
        find(parents, amount[i] * 100, d[seller[i]], answer)
    return answer[1:]