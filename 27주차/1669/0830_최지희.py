import sys
input=sys.stdin.readline

x,y=map(int,input().split())
z=y-x
if y==x: # 같은 경우
    print(0)
else:
    n=int(z**0.5)
    if n**2==z: # 증가->감소 형식
        print(2*n-1)
    else: # 중간에 동일한 값이 존재
        if z-n**2>n: # n보다 크면 2일을 해서 n을 만들어야함
            print(2*n+1)
        else: # 하루만 추가하면 됨
            print(2*n)


"""
최소가 되려면 계속 증가하고 최대에서 감소하는 형식이어야 한다.
모자란 부분만 유지하는 방법으로
즉, 기본은 
- 1,2,3,...,n,n-1,n-2,...,1
- 중간에 모자라는 값이 있으면 추가하는 방법으로
    만약에 모자라는 값이 n보다 크면 2개가 필요하다.
"""
