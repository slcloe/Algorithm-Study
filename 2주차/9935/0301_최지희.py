import sys
input=sys.stdin.readline

def main():
    s=list(input().strip()) # 문자열
    t=list(input().strip()) # 폭발패턴
    n=len(s)
    m=len(t)
    stack=[]
    for i in range(n):
        stack.append(s[i])
        if s[i]==t[-1] and stack[-m:]==t:
            del stack[-m:]
    print("".join(stack) if stack else "FRULA")
main()

"""
접근방법: 
    1. 스택을 이용
    2. 현재 문자가 패턴의 마지막 문자와 같다면 앞의 값들도 같은지 비교후 삭제
del: 인덱스나 슬라이스로 삭제 가능
    (ex) stack[-m:] : 뒤에서 m개의 문자 삭제
"""