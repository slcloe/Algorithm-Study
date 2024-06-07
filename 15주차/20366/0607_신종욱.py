import sys
input = sys.stdin.readline

def main():
    n = int(input())
    h = sorted(list(map(int, input().split())))

    min_diff = abs((h[3] + h[0]) - (h[1] + h[2]))
    for i in range(n - 3):
        for j in range(i + 3, n):
            anna = h[i] + h[j]

            l = i + 1
            r = j - 1
            while l < r:
                elsa = h[l] + h[r]
                min_diff = min(min_diff, abs(anna - elsa))
                if min_diff == 0:
                    print('0')
                    return

                if anna < elsa:
                    r -= 1
                else:
                    l += 1

    print(min_diff)


main()
