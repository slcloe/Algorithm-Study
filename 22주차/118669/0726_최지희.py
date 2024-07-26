import heapq
from collections import defaultdict

def solution(n, paths, gates, summits):
    board=defaultdict(list)
    for i,j,w in paths:
        board[i].append((j,w))
        board[j].append((i,w))
        
    summits=set(summits)
    dist=[10e9]*(n+1)
    hq=[]
    for g in gates:
        heapq.heappush(hq,(0,g))
        dist[g]=0
    while hq:
        cur,x=heapq.heappop(hq)
        if dist[x]<cur:
            continue
        for nx,nc in board[x]:
            cost=max(cur,nc)
            if dist[nx]>cost:
                dist[nx]=cost
                if nx not in summits:
                    heapq.heappush(hq,(cost,nx))
    answer=[0,10e9]
    for s in sorted(summits):
        if dist[s]<answer[1]:
            answer=[s,dist[s]]
    return answer

"""
모든 출입구에서 각 점에 도착할때까지의 intensity를 구함
summits가 되면 다른 곳으로 가는 것을 멈춘다 -> 조건x
"""
