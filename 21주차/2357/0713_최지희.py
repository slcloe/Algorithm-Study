import math
import sys
input=sys.stdin.readline

def main():
    def minmax(left,right):
        res = [1e9,1]
        while left <= right:
            if left % 2:
                res[0] =min(res[0],tree[left][0])
                res[1] =max(res[1],tree[left][1])
                left += 1
            if not right % 2:
                res[0] =min(res[0],tree[right][0])
                res[1] =max(res[1],tree[right][1])
                right -= 1
            left //= 2
            right //= 2
        return f"{res[0]} {res[1]}"

    N,M=map(int,input().split())
    H=math.ceil(math.log2(N)+1)
    size=2**H
    tree=[[-1,-1] for _ in range(2*size)]
    for i in range(N): # 말단 노드
        tree[i+size][0]=tree[i+size][1]=int(input())
    for i in range(size-1,0,-1):
        tree[i][0]=min(tree[2*i][0],tree[2*i+1][0])
        tree[i][1]=max(tree[2*i][1],tree[2*i+1][1])
    ans=[]
    for _ in range(M):
        a,b=map(int,input().split())
        ans.append(minmax(a-1+size,b-1+size))
    print("\n".join(ans))
main()

# 세그먼트 트리..
