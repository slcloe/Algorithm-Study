import sys
input = sys.__stdin__.readline

def main():
    n = int(input())
    numbers = sorted(list(map(int, input().split())))
    good_numbers_set = set()
    count = 0
    for i in range(n):
        number = numbers[i]

        # number가 이미 좋은 수로 판별됐으면 그냥 스킵해도 된다.
        # 서로 다른 인덱스 a, b, c에 대해 n[a] = n[b] + n[c]면 되는데 n[a] == n[b]여도 성립한다.
        # Ex) 4, 0, 4일 때 4 = 0 + 4 = 4 + 0이니까 while문 진입 안 하고 카운팅만 해주면 된다.
        # 근데 사실 이거 있으나 없으나 실행시간 차이 별로 없음(중복이 엄청 많아야 유의미할 듯)
        if number in good_numbers_set: 
            count += 1
            continue

        # 투 포인터
        left, right = 0, n - 1
        while left < right:
            if left == i: # 전제조건: 서로 다른(인덱스를 갖는) 수 세 개
                left += 1
                continue
            
            if right == i: # 전제조건: 서로 다른(인덱스를 갖는) 수 세 개
                right -= 1
                continue

            
            val = numbers[left] + numbers[right]    
            if val > number
                right -= 1
            elif val < number:
                left += 1
            else:
                count += 1
                good_numbers_set.add(number) # number가 좋은 수면 좋은 수 set에 추가 
                break

    print(count)


main()