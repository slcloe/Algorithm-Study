import sys
input = sys.__stdin__.readline

'''
두 명의 부하 직원에게 연락하라고 시켰을 때
시간은 둘 중에 큰 값이 총 시간이 된다.
'''

def solution():
    n = int(input())
    bose_info = tuple(map(int, input().split()))
    tree = [[] for _ in range(n)]
    for me, bose in enumerate(bose_info):
        if bose != -1:
            tree[bose].append(me)
	
    # dp[i] : i번째 노드의 서브트리에 뉴스를 전달하는데 필요한 최대 시간
    dp = [0 for _ in range(n)]
    def dfs(idx):
        temp = []
       
        for slave in tree[idx]:# 내 부하들을 대상으로
            dfs(slave) #leaf 노드까지 내려가준다.
            temp.append(dp[slave]) #부하 놈들이 소식을 전파하는데 걸리는 시간을 담아준다.
        
        if temp: # 부하가 있다면
            """
            부하 놈들중 시간이 많이 걸리는 순으로 뉴스를 전달해줘야 전체 시간을 아낄 수 있다. 
            그래서 정렬 후 이번 단계에서 걸리는 시간을 더해줬다.
            """
            temp.sort(reverse=True)
            # 서브트리에 전파하는 시간 + 이번 단계에서 걸리는 시간(i + 1)
            next_time = [temp[i] + i + 1 for i in range(len(temp))] 
            # ..의 최댓값을 더해준다.
            dp[idx] = max(next_time)

    dfs(0)
    print(dp[0])

solution()