import heapq

def solution(jobs):
    answer = 0
    wait=0 # 기다린 시간
    cur=0 # 현재 디스크 종료 시간
    hq=[]
    for js,jt in sorted(jobs):
        while hq and cur<js:
            ht,hs=heapq.heappop(hq)
            if hs>cur:
                wait-=hs-cur
                cur=hs+ht
            else:
                wait+=cur-hs
                cur+=ht
        heapq.heappush(hq,(jt,js))
    # 남은 값 처리
    while hq:
        ht,hs=heapq.heappop(hq)
        if hs>cur:
            wait-=hs-cur
            cur=hs+ht
        else:
            wait+=cur-hs
            cur+=ht
    return (wait+cur)//len(jobs)

"""
자료구조: 우선순위 큐
짧은 시간인 작업부터 우선 수행한다 -> 평균 대기 시간이 짧아야함
- jobs를 정렬하고 하나씩 꺼내면서 수행(바로 모든 값을 heap에 넣지 않는 이유는 현재 시점에서 가능한 값들을 먼저 처리)
    - 값이 들어오기전에 미리 들어온 값들을 먼저 끝낸다 (현재 기다리고 있는 것들)
    - wait에는 음수도 가능하다 cur이 끝나기를 기다리는게 아니라 끝나고 아무것도 안한 상태에서 바로 시작하는 경우

+++ 남은 값을 처리하기 위해 같은 코드를 중복해서 사용하는데 이 부분은 추후 수정...
"""