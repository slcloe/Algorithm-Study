import sys

input = sys.stdin.readline

def main():
    n, t = map(int, input().split())
    carrots = sorted(
        [tuple(map(int, input().split())) for _ in range(n)],
        key=lambda x: x[1],
        reverse=True
    )

    answer = 0
    for w, p in carrots:
        if t == 0:
            break
        answer += p * (t - 1) + w
        t -= 1

    print(answer)


main()
