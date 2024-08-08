import sys
input = sys.stdin.readline
def main():
    def dfs(n,prior,near,s,nums):
        if n==N:
            if not prior+near*nums:
                ans.append(s)
            return
        dfs(n+1,prior,near,s+" "+str(n+1),nums*10+n+1)
        dfs(n+1,prior+near*nums,1,s+"+"+str(n+1),n+1)
        dfs(n+1,prior+near*nums,-1,s+"-"+str(n+1),n+1)


    tc=int(input())
    ans=[]
    for _ in range(tc):
        N=int(input())
        dfs(1,0,1,"1",1)
        ans.append("")
    print("\n".join(ans))
main()

"""
이전 값에 flag*num을 계속 더해준다.
- prior: 이전까지 연산 결과 
- nums: 현재 위치에서 연산자를 만나기 전까지의 숫자모음
- near: 현재 위치에서 가장 가까운 연산자
- s: 다음 단계에 연산될(수도-공백이면 연산 X) 표현식
"""
