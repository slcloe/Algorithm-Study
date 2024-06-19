import sys
input = sys.stdin.readline

def main():
    a,b,d,N=map(int,input().split())
    dp=[0]*(N+1)
    dp[0]=1
    for k in range(1,N+1):
        if k>=b:
            dp[k]=(dp[k-1]+dp[k-a]-dp[k-b]+1000)%1000
        elif k>=a:
            dp[k]=(dp[k-1]+dp[k-a])%1000
        else:
            dp[k]=dp[k-1]
    ans=dp[N]
    if d<=N:
        ans=(ans-dp[N-d]+1000)%1000
    print(ans)
main()

"""
dp[k]: 현재 k일 일때 짚신벌레 수(죽은 짚신벌레 포함)
dp[k]=dp[k-1]+dp[k-a]-dp[k-b] // dp[k-a]-dp[k-b]에 있는 짚신벌레의 경우에만 새로운 개체 생성
죽은 짚신벌레는 b<d이므로 계속 k-1을 통해서 더해지긴 하지만 개체 생성에는 영향을 주지 않음 (dp[k-a],dp[k-b]에 동시에 포함 == 0)
마지막에 최종적으로 dp[N-d]에 있는 짚신벌레를 빼면 지금까지 죽은 짚신벌레 + 죽을 짚신벌레는 제외됨 
"""
