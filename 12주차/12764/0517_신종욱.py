import sys
input = sys.__stdin__.readline

import heapq # 최소 힙


def main():
    n = int(sys.stdin.readline())

    hip = []

    # i - 1번 좌석의 종료 시간
    seats = [0 for _ in range(n)]

    # i - 1번 좌석에 앉은 횟수
    counts = [0 for _ in range(n)]
    

    # 시작 시간을 기준으로 pq에 삽입
    for _ in range(n):
        p, q = map(int, sys.stdin.readline().split())
        heapq.heappush(hip, (p, q))

    required_computers = 0
    while hip:
        # 시작 시간이 가장 빠른 걸 꺼낸다.
        temp = heapq.heappop(hip)

        # 가장 낮은 번호의 좌석에 대해
        for i in range(n):
            # 해당 좌석의 종료 시간보다 시작 시간이 크면
            if seats[i] <= temp[0]:
                # 그 좌석에 앉히고
                if seats[i] == 0:
                    # 컴퓨터 개수를 카운트한다.
                    required_computers += 1
                
                # 좌석의 값을 방금 앉은 사용자의 종료 시간으로 갱신하고
                seats[i] = temp[1]

                # 앉은 사람 수를 센다.
                counts[i] += 1
                break

    print(required_computers)

    # required_computers 부터는 앉은 적 없는 자리 -> 필요 없는 자리
    print(' '.join([str(c) for c in counts[:required_computers]]))


main()

