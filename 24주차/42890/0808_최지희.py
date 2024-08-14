def solution(relation):
    answer = set()
    row=len(relation)
    col=len(relation[0])
    trans_relation=list(zip(*relation)) # 전치
    for cand in range(1,2**col):
        for ans in answer: # 최소성
            if ans&cand==ans:
                break
        else: # 유일성
            cur=[]
            i=0
            k=cand
            while k>0:
                if k%2:
                    cur.append(trans_relation[i])
                k//=2
                i+=1
            if len(set(zip(*cur)))==row:
                answer.add(cand)
    return len(answer)

"""
8개의 컬럼이 있으므로 모든 경우의 수 - 비트로 표현
최소성: and 연산 이용
유일성: 키(1)인 컬럼들의 유일성 확인 -> 집합을 이용해서 중복체크
"""
