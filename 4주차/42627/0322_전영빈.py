"""
문제의 포인트는 현재 시간에 처리할 수 있는 작업이 여러 개가 있는 경우, 소요 시간이 가장 짧은 작업을 처리하는 것.
"""
import heapq

def solution(jobs):
    # 작업을 시작 시점이 짧은 순으로, 그 안에서 소요 시간이 짧은 순으로 정렬한다.
    jobs.sort(key=lambda x:(x[0], x[1]))
  
    heap = []
    seq = 1
    # 초기 시점. 즉, 하드디스크가 작업을 수행하고 있지 않는 경우에 맨 처음 들어온 요청을 처리하는 것이 제한 사항이므로 첫 번째 작업 처리.
    heapq.heappush(heap, (jobs[0][1], jobs[0][0]))

    current, response = 0, 0

    while heap:
        # 우선 순위가 가장 높은 작업 처리.
        duration, start = heapq.heappop(heap)

        current = max(current + duration, start + duration)
        response += (current - start)
        
        # 현재 시점에서 처리 가능한 작업들을 모두 힙에 추가.
        while seq < len(jobs) and jobs[seq][0] <= current:
            heapq.heappush(heap, (jobs[seq][1], jobs[seq][0]))
            seq += 1

        # 힙은 비어있는데 작업은 남아있는 경우 => 다음 작업의 시간으로 이동해서 해당 작업을 처리.
        if seq < len(jobs) and len(heap) == 0:
            heapq.heappush(heap, (jobs[seq][1], jobs[seq][0]))
            seq += 1
            
    return response // len(jobs)
