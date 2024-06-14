import sys
input = sys.__stdin__.readline

def main():
    '''
    주의할 점: 0 <= x, y <= 100
    즉 배열 길이는 100이 아니라 101이다.
    '''
    LIM = 100 # 좌표 최대 범위

    '''
    배열에서 x 좌표 증가는 열 인덱스 증가라서 헷갈리지만
    그냥 xy 좌표계 기준으로 하면 된다.
    어차피 방향과 방문 처리는 좌표 값을 기준으로 하기 때문에, 배열 인덱스와 관계가 없다.
    '''
    DIR = [
        (1, 0),
        (0, -1),
        (-1, 0),
        (0, 1)
    ]

    # 정사각형 체크 때문에 좌표 리스트로 하는 거보다 이게 낫다.
    V = [[False for c in range(LIM + 1)] for r in range(LIM + 1)]

    def solve(x, y, d, g):
        V[x][y] = True
        cx, cy = x + DIR[d][0], y + DIR[d][1] # 0 0 0 (0 세 대ㅋㅋ 엌ㅋㅋ)
        V[cx][cy] = True
        moved = [d] # 움직인 방향 리스트

        for _g in range(g):
            to_move = [(_moved + 1)%4 for _moved in moved.__reversed__()]

            for _to_move in to_move:
                cx, cy = cx + DIR[_to_move][0], cy + DIR[_to_move][1]
                moved.append(_to_move)

                if range_check(cx, cy):
                    V[cx][cy] = True

    def range_check(x, y):
        return ((0 <= x < LIM) and (0 <= y < LIM))

    n = int(input())
    answer = 0
    for _ in range(n):
        x, y, d, g = map(int, input().split())
        solve(x, y, d, g)
    
    for i in range(LIM):
        for j in range(LIM):
            if V[i][j] and V[i + 1][j] and V[i][j + 1] and V[i + 1][j + 1]:
                answer += 1

    print(answer)

    
main()
