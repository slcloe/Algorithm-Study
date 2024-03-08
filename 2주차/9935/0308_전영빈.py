"""
입력 문자열을 순서대로 읽어가며 폭발 문자열이 체크한다.
하지만 전에 읽은 문자가 안전한 문자임은 확신할 수 없다. 다음에 어떤 문자를 읽느냐에 따라 폭발 문자열이 될 수도 있다.
따라서 이전에 읽은 문자를 기억하여 관리해야 한다. => 스택
"""
from collections import deque

line = list(input().rstrip())
target = list(input().rstrip())
trigger = target[-1]
tLen = len(target)
stack = deque()

def peek(num):
    if len(stack)-1-num < 0:
        return '/'
    
    return stack[len(stack)-1-num]

for l in line:
    # 현재 스택에 들어가는 문자가 target 문자열의 가장 마지막 문자일 경우, 문자열 폭발을 체크한다.
    if l == trigger:
        count = 1
        while count < tLen:
            if target[tLen-1-count] != peek(count-1):
                break

            count += 1

        # 체크된 문자열의 길이 == 폭발 문자열의 길이 => 스택 안의 문자 + 현재 들어온 문자 == 폭발 문자열.
        if count == len(target):
            for _ in range(len(target)-1):
                stack.pop()
        else:
            stack.append(l)

    else:
        stack.append(l)

if len(stack) == 0:
    print("FRULA")
else:
    print(*stack, sep="")
