import sys
input=sys.stdin.readline

def main():
    N=int(input())
    A=list(map(int,input().split()))

    stacks=[0,0,0,0]
    for k in A:
        for j in range(4):
            if stacks[j]<k:
                stacks[j]=k
                break
            if stacks[j]==0:
                stacks[j]=k
                break
        else:
            print("NO")
            break
    else:
        print("YES")
main()

"""
하나의 스택에 증가수열이 되는지 확인
-> 스택의 가장 큰값이 마지막에 온다 == 마지막 값만 저장하고 비교 
"""
