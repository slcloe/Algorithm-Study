import sys
input = sys.__stdin__.readline

def main():
    n = int(input())

    dt = sorted(
        [tuple(map(int, input().split())) for _ in range(n)],
        key=lambda x: x[1],
        reverse=True
    )

    idx = 0
    curr = dt[idx][1]
    while curr > 0 and idx < n:
        # 그냥 과제를 최대한 뒤로 미룬다.
        if dt[idx][1] < curr:
            curr = dt[idx][1]
        
        curr -=  dt[idx][0]
        idx += 1

    print(curr)

main()
