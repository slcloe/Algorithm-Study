"""
직관적으로 생각하면 하나의 숫자에 대해 다른 모든 숫자들의 조합을 비교하는 것을 N번 반복.
모든 숫자들의 조합을 비교하는 것의 시간복잡도를 A라고 하면 전체 시간 복잡도는 O(A*n).
숫자를 비교하는 알고리즘의 시간 복잡도를 O(nlogn) 정도로 만들면 n <= 2000 이므로
시간내에 통과될 수 있을 것이라 예상.
기본적으로 두 숫자의 합이 타겟 숫자보다 작으려면 두 숫자는 각각 타겟 숫자보다 작거나 같아야 한다.
두 숫자의 합이 타겟 숫자보다 작으면 숫자 하나를 증가시키고, 타겟 숫자보다 크면 숫자 하나를 감소시킨다.
두 숫자를 가장 낮은 수와 타겟 숫자보다 낮은 수중 가장 큰수로 잡고, 적절히 수를 증감시키면 된다. => 투포인터
"""
N = int(input())

numbers = list(map(int, input().split()))
numbers.sort()

count = 0
for target in range(N):
    left = 0
    right = N-1

    while left != right:
        if left == target:
            left +=1
        elif right == target:
            right -= 1
        elif numbers[left] + numbers[right] == numbers[target]:
            count += 1
            break
        elif numbers[left] + numbers[right] < numbers[target]:
            left += 1
        else:
            right -= 1

print(count)
