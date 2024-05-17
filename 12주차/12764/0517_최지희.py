import sys
input=sys.stdin.readline

N=int(input())
board=[list(map(int,input().split())) for _ in range(N)]
board.sort()
computer=[0]*N # 컴퓨터 끝나는 시간
cnt=[0]*N # 컴퓨터별 사용 사람 수
for p,q in board:
    for i in range(N):
        if computer[i]<=p:
            cnt[i]+=1
            computer[i]=q
            break
zero=cnt.count(0)
print(N-zero)
print(*cnt[:N-zero])


"""
단순하게 필요한 최대 컴퓨터의 수와 컴퓨터별 사람수를 구하는 배열을 만들고
처음부터 탐색해서 끝나는 시간이 현재 시간보다 작으면 그 컴퓨터를 사용하도록 했음
시간초과라고 생각했으나 pypy로는 통과
"""
