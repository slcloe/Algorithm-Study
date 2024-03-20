import math
import sys
input=sys.stdin.readline

def main():
    def update(node,diff):
        while node:
            tree[node]+=diff
            node//=2

    def prefix(left,right):
        res = 0
        while left <= right:
            if left % 2:
                res += tree[left]
                left += 1
            if not right % 2:
                res += tree[right]
                right -= 1
            left //= 2
            right //= 2
        return res

    N,M,K=map(int,input().split())
    H=math.ceil(math.log2(N)+1)
    size=2**H # 말단 리스트의 크기: 안채워진 값은 0이다.
    tree=[0]*(2*size)
    for i in range(N):
        tree[i+size]=int(input())
    for i in range(size-1,0,-1): # 초기화 과정
        tree[i]=tree[2*i]+tree[2*i+1]
    ans=[]
    for _ in range(M+K):
        a,b,c=map(int,input().split())
        if a==1:
            update(b-1+size,c-tree[b-1+size])
        elif a==2:
            ans.append(str(prefix(b-1+size,c-1+size)))
    print("\n".join(ans))

main()

"""
바텀업 세그먼트 트리
항상 길이가 2**H라고 생각한다
=> 포화 이진트리이고 리프노드의 첫번째부터 리스트가 있다고 생각한다 -> 부족한 값들은 0
prefix를 구할 때 만약 left 인경우와 right인 경우에 더하는 방법
left는 구간합 구간에서 가장 작은 값이다. 
    - 왼쪽에 있으면 해당 값을 포함한 부모의 구간 중에서 가장 작은 값->부모의 값이 범위 내에 있음
    - 오른쪽에 있으면 해당 값을 포함한 부모의 구간 중에서 가장 큰 값->부모의 값은 범위 내에 없음
right는 구간합 구간에서 가장 큰 값이다.
    - 왼쪽에 있으면 부모의 값이 범위 내에 없음
    - 오른쪽에 있으면 부모의 값이 범위 내에 있음
"""