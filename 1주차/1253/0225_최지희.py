import sys

input = sys.stdin.readline


def two_pointer():
    N = int(input())
    board = sorted(list(map(int, input().split())))
    ans = 0
    for i in range(N):
        target = board[i]
        left = 0
        right = N - 1
        while left < right:
            cur = board[left] + board[right]
            if right == i or cur > target:
                right -= 1
            elif left == i or cur < target:
                left += 1
            else:
                ans += 1
                break
    print(ans)


two_pointer()

"""
접근방식: 투포인터
- 음수인 경우도 고려
"""
