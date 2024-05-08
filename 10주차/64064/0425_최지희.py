from itertools import permutations
def solution(user_id, banned_id):
    answer = set()
    for ps in permutations(user_id,len(banned_id)):
        for p,b in zip(ps,banned_id):
            if len(p)!=len(b):
                break
            for i,j in zip(p,b):
                if j=="*":
                    continue
                if i!=j:
                    break
            else:
                continue
            break
        else:
            answer.add(tuple(sorted(ps)))
    return len(answer)

"""
최대 8!*8*8 정도 완탐으로 가능
파이썬에서 제공하는 permutations 라이브러리를 사용함
"""
