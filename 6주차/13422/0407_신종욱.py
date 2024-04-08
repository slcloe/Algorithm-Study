import sys
input = sys.__stdin__.readline

def main():
    for _ in range(int(input())):
        n, m, k = map(int, input().split())
        l = list(map(int, input().split()))
        
        if m != n:
            for j in range(m - 1):
                l.append(l[j])
        leng = len(l)
        start = 0
        end = m - 1
        count = sum(l[:m])
        check = 0

        if count < k:
            check += 1

        while end < leng - 1:
            count -= l[start]

            start += 1
            end += 1

            count += l[end]
            if count < k:
                check += 1

        print(check)

main()
