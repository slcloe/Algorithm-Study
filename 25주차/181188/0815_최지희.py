def solution(targets):
    answer=1
    targets.sort(key=lambda x:x[1])
    end=targets[0][1]
    for s,e in targets:
        if s>=end:
            answer+=1
            end=e
    return answer

"""
정렬 후 끝점을 이용해서 비교
"""
