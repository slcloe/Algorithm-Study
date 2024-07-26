import sys
input=sys.stdin.readline

def main():
    n=int(input())
    edges=[]
    degree=[0]*n
    for _ in range(n-1):
        u,v=map(int,input().split())
        degree[v-1]+=1
        degree[u-1]+=1
        edges.append((u-1,v-1))
    ㄷ=0
    ㅈ=0
    #ㅈ
    for v in range(n):
        if degree[v]>=3:
            ㅈ+=degree[v]*(degree[v]-1)*(degree[v]-2)//6
    #ㄷ
    for a,b in edges:
        ㄷ+=(degree[a]-1)*(degree[b]-1)

    if ㄷ>3*ㅈ:
        print("D")
    elif ㄷ<3*ㅈ:
        print("G")
    else:
        print("DUDUDUNGA")
main()

"""
ㄷ,ㅈ의 규칙을 고려해서 구현
- ㄷ: 하나의 간선에 두개의 간선을 선택
- ㅈ: 하나의 정점에서 3개의 정점을 선택
"""
