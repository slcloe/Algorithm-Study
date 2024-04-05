from collections import defaultdict
def solution(info, query):
    answer = []
    apply=defaultdict(list)
    for i in info:
        i=i.split(" ")
        for a in [i[0],"-"]:
            for b in [i[1],"-"]:
                for c in [i[2],"-"]:
                    for d in [i[3],"-"]:
                        apply[a+b+c+d].append(int(i[4]))
    for k in apply.keys():
        apply[k].sort()
    for q in query:
        q=q.split(" ")
        target=int(q[7])
        cur=apply[q[0]+q[2]+q[4]+q[6]]
        n=len(cur)
        start=0
        end=n
        while start<end:
            mid=(start+end)//2
            if cur[mid]>=target:
                end=mid
            else:
                start=mid+1
        answer.append(n-start)              
    return answer

"""
4*3*3*3개의 후보군이 있으므로 미리 생성해서 점수를 저장
이분탐색을 통해서 점수 이상의 사람들을 구한다.
중복 값이 있는 이분탐색 -> 가장 왼쪽의 값을 반환하면 된다
"""
