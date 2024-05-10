'''
투 포인터?
a는 왼쪽 끝, b는 오른쪽 끝을 가리킨다.
각각 가리키는 보석이 2개 이상이면 안쪽으로 포인터를 움직인다

로직 자체는 맞는 것 같은데 답이 여러 개일 때의 처리를 어케 해야 할지 모르겠음....
'''


def solution(gems):
    answer = []

    count = {}
    for gem in gems:
        count[gem] = count.get(gem, 0) + 1
    
    value_sum = 0
    for key in count.keys():
        value_sum += count[key]

    print(count)

    pl, pr = -1, -1
    left, right = 0, len(gems) - 1
    while left <= right and (pl != left or pr != right):
        pl, pr = left, right
        if count[gems[left]] > 1:
            count[gems[left]] -= 1
            left += 1
        elif count[gems[right]] > 1:
            count[gems[right]] -= 1
            right -= 1
        
    answer = [left + 1, right + 1]

    return answer
