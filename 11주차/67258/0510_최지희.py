from collections import defaultdict


def solution(gems):
    count=len(set(gems))
    N = len(gems)
    answer = [0, N-1]
    start = 0
    end = 0

    cur=defaultdict(int)
    cur[gems[0]]=1
    count-=1
    while start<N and end < N:
        if not count:
            if answer[1] - answer[0]> end - start:
                answer = [start, end]
            cur[gems[start]]-=1
            if not cur[gems[start]]:
                count+=1
            start += 1
        else:
            end += 1
            if end==N:
                break
            cur[gems[end]]+=1
            if(cur[gems[end]]==1):
                count-=1

    return [answer[0]+1,answer[1]+1]

"""
종류를 찾기위해 집합과 딕셔너리를 활용
거리가 같더라도 시작점이 작은 값이 우선순위 이므로 작은 값부터 값을 시작한다
투포인터
"""
