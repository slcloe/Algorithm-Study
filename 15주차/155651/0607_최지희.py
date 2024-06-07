import heapq
def solution(book_time):
    def to_int(ss):
        hh,mm=map(int,ss.split(":"))
        return hh*60+mm
    answer = 0
    booking=[[to_int(bt[0]),to_int(bt[1])]for bt in book_time]
    booking.sort()
    end=[]
    for s,e in booking:
        heapq.heappush(end,e)
        while end[0]<=s-10:
            heapq.heappop(end)
        if len(end)>answer:
            answer+=1
    return answer

"""
"HH:MM" 형태의 값을 정수로 바꾼다-> 오름차순 정렬
end 배열에 현재 대실중인 상황의 종료시간(e)을 넣는다. -> end에서 가장 빠른 종료시간이 현재 s시간 전에 끝난 것들은 지운다.
end배열의 길이를 기준으로 현재 사용중인 방의 수
"""
