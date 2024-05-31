import sys
input = sys.__stdin__.readline

def main(): 
    def solve():
        n = int(input())

        # x좌표: y좌표 리스트
        coors = {}
        for _ in range(n):
            x, y = list(map(int, input().split()))
            if x not in coors:
                coors[x] = [y]
            else:
                coors[x].append(y)

        keys = sorted(coors.keys())

        # (-1, 0)은 Sentinel
        route = [(-1, 0)]

        # key는 x좌표
        # route[-1]의 y좌표와 coors[key]에 해당하는 y 중 최소값이 다르면
        # 내려갈 차례라는 뜻
        # 아니면 올라갈 차례
        for key in keys:
            coors[key].sort(reverse=route[-1][1] != min(coors[key]))
            for coor in coors[key]:
                route.append((key, coor))

        data = list(map(int, input().split()))
        m = data[0] # 안 씀
        numbers = data[1:]
        
        for number in numbers:
            print(route[number][0], route[number][1])

    t = int(input())
    for tc in range(t):
        solve()


main()
