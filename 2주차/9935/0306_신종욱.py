import sys
input = sys.__stdin__.readline


def main():
    s = input().rstrip()
    bomb = list(input().rstrip())
    l = len(bomb)
    result = [] # stack
    
    # 스택에 따따따따 추가하다가 폭발문자열 완성되면 제거
    for c in s:
        result.append(c)
        if result[-l:] == bomb:
            for _ in range(l):
                result.pop()

    print(''.join(result) if result else 'FRULA')


main()