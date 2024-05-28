def solution(users, emoticons):
    def permutation(cur):
        if cur==n:
            plus=0
            buy=0
            for ratio,price in users:
                total=0
                for i in range(n):
                    if (10-visited[i])*10>=ratio:
                        total+=emoticons[i]*visited[i]//10
                if total>=price:
                    plus+=1
                else:
                    buy+=total
            if answer[0]<plus:
                answer[0]=plus
                answer[1]=buy
            elif answer[0]==plus and answer[1]<buy:
                answer[1]=buy
            return
        for i in range(6,10):
            visited[cur]=i
            permutation(cur+1)
    n=len(emoticons)
    visited=[0]*n
    answer = [0,0]
    permutation(0)
    return answer

"""
중복순열을 이용해서 할인율을 구할 수 있다.
4**7이므로 모든 경우를 구해도 충분하다.
주어진 조건을 만족하는 값들을 출력한다.
"""
