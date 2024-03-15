import sys
input = sys.__stdin__.readline

# 세그먼트 트리까지는 어케 했는데 왜 틀렸는지는 몰르겠음......

def main():
    n, m, k = list(map(int, input().split()))

    global arr, tree
    arr = [int(input()) for _ in range(n)]
    tree = [0] * (len(arr) * 4)
    init(0, n - 1, 1)

    for _ in range(m + k):
        a, b, c = list(map(int, input().split()))

        if a == 1:
            update(0, n - 1, 1, b - 1, c - arr[b - 1])
        elif a == 2:
            print(interval_sum(0, n - 1, 1, b - 1, c))


def init(start, end, index):
    if start == end:
        tree[index] = arr[start]
        return tree[index]

    mid = (start + end) // 2
    tree[index] = init(start, mid, index * 2) + init(mid + 1, end, index * 2 + 1)
    return tree[index]


def interval_sum(start, end, index, left, right):
    if left > end or right < start:
        return 0

    if left <= start and right >= end:
        return tree[index]

    mid = (start + end) // 2
    return interval_sum(start, mid, index * 2, left, right) + interval_sum(mid + 1, end, index * 2 + 1, left, right)


def update(start, end, index, what, value):
    if what < start or what > end:
        return

    tree[index] += value
    if start == end:
        return
    mid = (start + end) // 2
    update(start, mid, index * 2, what, value)
    update(mid + 1, end, index * 2 + 1, what, value)


main()