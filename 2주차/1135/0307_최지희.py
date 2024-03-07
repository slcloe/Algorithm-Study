import sys
input = sys.stdin.readline


def main():
    def dfs(x):
        if not board[x]:
            return 0
        children = []
        for nx in board[x]:
            children.append(dfs(nx))
        children.sort(reverse=True)
        children = [children[i] + i + 1 for i in range(len(children))]
        return max(children)

    n = int(input())
    tmp = list(map(int, input().split()))
    board = [[] for _ in range(n)]
    for i in range(1, n):
        board[tmp[i]].append(i)
    print(dfs(0))

main()

"""
접근방법:
    하위 노드부터 걸리는 시간을 상위 노드로 전달 이때 하위 노드가 여러 개라면 가장 오래 걸리는 경우를 전달해 줘야함
    가장 오래 걸리는 노드를 판별하는 방법
        - 자식 노드들에서 가져온 값들과 순서에 따라 더해지는 시간(전화하는데 걸리는 시간)을 합쳐서 비교
        - 가장 오래 걸리는 노드를 가장 먼저 전달(큰 값에는 가장 작은 값을 더해야 최솟값을 만들 수 있음)
초반 접근 방법:
    자식 중에서 (하위 노드에서 올라온 가장 큰값+1, 가장 작은값+자식의 수)를 비교해서 상위 노드로 전달하려고 했음
    중간에 있는 값이 더 클 수 있는 예외가 발생: 모든 값들에 대해서 비교를 하고 전달을 해줘야 함
"""