def solution(enroll, referral, seller, amount):
    answer = [0]*len(enroll)
    idx=dict()
    for i in range(len(enroll)):
        idx[enroll[i]]=i
    for s,a in zip(seller,amount):
        a=100*a
        while a!=0 and s!="-":
            answer[idx[s]]+=a-a//10
            a//=10
            s=referral[idx[s]]
    return answer

"""
노드간의 연관관계가 referral로 표현
부모를 표현하는 것이고 문제에서도 부모가 필요
다만 배열에서는 이름으로 인덱스를 접근해야하므로 두개를 묶은 map이 필요함
딕셔너리 형태로 해당 값을 저장해준다.
"""