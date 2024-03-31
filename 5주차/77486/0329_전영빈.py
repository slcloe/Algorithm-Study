"""
amount 배열으로 트리의 value 값을 다 구해놓고 dfs로 한번에 구하려 했는데 안됐다.
문제를 잘 읽어보니 판매가 일어나는 그 순간 그 금액에 대해서만 정산이 일어나기 때문에 미리 각 노드의 토탈 금액을 계산해놓고
dfs로 부모를 거슬로 올라가며 배분값을 전달해주는 방식은 사용할 수 없다.
따라서 단순히 각 케이스마다 배분값을 계산해주는 쪽으로 로직을 작성했다.

반복문을 통해 구현하니 테스트 11, 12, 13 케이스에 대해 시간초과가 났다.
enroll이 최대 10^4이고 seller의 길이가 최대 10^5 이니 트리가 일렬로 연결된 최악의 경우 시간 복잡도가 O(10^9)
라서 그런듯하다. 다만, 문제에서 한 번에 벌어들일 수 있는 최대 금액이 10^4번이고 부모로 올라갈 때마다 10%씩 가져간다.
따라서, 부모로 올라갈 때의 금액이 0일 때 반복문을 종료시키는 것으로 한 케이스의 트리 이동 횟수를 5번으로 줄일 수 있다.
"""

def solution(enroll, referral, seller, amount):
    
    nameTag = dict()
    for index, er in enumerate(enroll, start=1):
        nameTag[er] = index

    link = [0 for _ in range(len(enroll)+1)]
    
    for index, refer in enumerate(referral, start=1):
        if refer == "-":
            link[index] = 0
        else:
            link[index] = nameTag[refer]
    
    answer = [0 for _ in range(len(enroll)+1)]
    for index, sel in enumerate(seller):
        si = nameTag[sel]
        value = amount[index] * 100
        
        while si != link[si] and value != 0:
            loss = value // 10
            answer[si] += (value - loss)
     
            si = link[si]
            value = loss
      
    return answer[1:]
