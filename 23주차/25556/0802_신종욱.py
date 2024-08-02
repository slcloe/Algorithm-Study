import sys
input = sys.__stdin__.readline

def main():
    stack = [[] for _ in range(4)]
    n = int(input())
    arr = list(map(int, input().split()))

    flag = True
    for num in arr:
        for i in range(4):
            if not stack[i]:
                stack[i].append(num)
                break
            else:
                if stack[i][-1] < num:
                    stack[i].append(num)
                    break
        else:
            flag = False
            break

    print('YES' if flag else 'NO')


main()
