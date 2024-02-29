import sys

input = sys.stdin.readline


def main():
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    H, W = map(int, input().split())
    x, y, d = map(int, input().split())
    A = [list(map(int, list(input().strip()))) for _ in range(H)]
    B = [list(map(int, list(input().strip()))) for _ in range(H)]

    visited = [[0] * W for _ in range(H)]  # 방문 체크
    ans = 0
    cnt = 1  # 이동을 하고 종료 -> 1로 시작 (아래도)
    while True:
        if not visited[x][y]:  # 방금 먼지를 제거
            visited[x][y] = 1
            d = (d + A[x][y]) % 4
            x += dx[d]
            y += dy[d]
            ans += cnt
            cnt = 1
            if x < 0 or x >= H or y < 0 or y >= W: # 범위 밖
                return ans
        else:
            # 처음 시작과 값이 같은지 확인 하기 위해 시작값을 저장
            sx = x
            sy = y
            sd = d
            while -1 < x < H and -1 < y < W and visited[x][y]:  # 먼지 없는 칸들만 계속 이동
                d = (d + B[x][y]) % 4
                x += dx[d]
                y += dy[d]
                cnt += 1
                if x < 0 or x >= H or y < 0 or y >= W: # 범위 밖
                    return ans
                if x == sx and y == sy and d == sd:  # 순환하는 경우
                    return ans
print(main())

"""
접근 방법: 종료 조건을 최단 시간 안에 찾는다.
- 종료 조건: 순환하는 경우 or 범위를 벗어나는 경우에 동작을 멈춘다
- 순환하는 경우에는 방향도 함께 고려해야 한다 -> 어떤 방향으로 들어온 지에 따라서 나가는 방향이 달라지기 때문에
- 먼지를 제거하는 경우에 해당 칸을 오기 위해 움직인 칸 수도 함께 더해 준다
    -> 먼지 없는 칸만 돌아다니는 경우 종료 조건이 생기기 때문에(순환) 미리 정답에 더하지 않는다

시간 줄이는 접근 방법: 이미 먼지가 제거된 칸들을 지나는 경로를 압축한다 (유니언파인드를 통해서)
    먼지가 없는 칸들의 경로는 정해져 있으므로 먼지가 없는 칸들의 집합이 종료되는 칸을 조상으로 하는 그룹을 만들어서 이동 경로를 줄인다
    -> while문 안에서 cnt++을 매번 하지 말고 루트가 있으면 해당 칸으로 바로 이동
"""
