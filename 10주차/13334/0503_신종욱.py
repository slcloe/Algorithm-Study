import sys
import heapq
n = int(input())

def main():
    data = []
    for _ in range(n):
        a, b = list(map(int, sys.stdin.readline().split()))
        data.append((max(a, b), min(a, b)))  # 끝점, 시작점

    # 입력받은 데이터들을 '끝점'을 기준으로 오름차순 정렬
    data.sort(key=lambda x: x[1])

    d = int(input())
    pq = []
    answer = -1

    for item in data:
        if item[0] - item[1] <= d:
            heapq.heappush(pq, item[1])
        else:
            continue

        while pq:
            start = pq[0]
            
            if item[0] - start > d:
                heapq.heappop(pq)
            else:
                break

        answer = max(len(pq), answer)

    print(answer)

main()
