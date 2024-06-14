import sys
input = sys.__stdin__.readline

def main():
    n, m  = map(int, input().split())

    # (n, 1), (n, 2), (n - 1, 1), (n - 1, 2)
    # ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    dx = [0, -1, -1, -1, 0, 1, 1, 1]
    dy = [-1, -1, 0, 1, 1, 1, 0, -1]

    # 행: 1, 2, 3, ..., n
    # 열: 1, 2, 3, ..., n
    # 인덱스 넘어가면 되돌아옴
    
    basket = [list(map(int, input().split())) for r in range(n)]
    clouds = set() # 구름의 좌표
    # 비바라기 시전
    clouds.add((n - 1, 0))
    clouds.add((n - 1, 1))
    clouds.add((n - 2, 0))
    clouds.add((n - 2, 1))

    for _ in range(m):
        d, s = map(int, input().split())
        d -= 1


        # 구름 이동
        moved = set()
        for cloud in clouds:
            ni = cloud[0]
            for _ in range(s):
                ni += dx[d]
                if ni < 0:
                    ni = n - 1
                elif ni >= n:
                    ni = 0

            nj = cloud[1]
            for _ in range(s):
                nj += dy[d]
                if nj < 0:
                    nj = n - 1
                elif nj >= n:
                    nj = 0

            moved.add((ni, nj))
        clouds = moved


        # 비 내림
        for cloud in clouds:
            basket[cloud[0]][cloud[1]] += 1

        # 방금 비 내린 칸에 물복사버그
        for cloud in clouds:
            cnt = 0

            for idx in [1, 3, 5, 7]:
                ni, nj = cloud[0] + dx[idx], cloud[1] + dy[idx]
                if not ((0 <= ni < n) and (0 <= nj < n)):
                    continue
                if basket[ni][nj] > 0:
                    cnt += 1

            basket[cloud[0]][cloud[1]] += cnt

        # 물 양이 2 이상인 모든 칸에 구름 생성
        # 근데 현재 시점의 cloud와 겹치면 안됨
        new_cloud = set()
        for i in range(n):
            for j in range(n):
                if basket[i][j] >= 2 and (i, j) not in clouds:
                    new_cloud.add((i, j))
                    basket[i][j] -= 2

        # 구름 삭제
        clouds = new_cloud

    print(sum([sum(row) for row in basket]))

main()
