import sys
from collections import defaultdict
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline


def main():
    def dfs(cur):
        res=[]
        for f in files[cur]:
            res.append(f)
        for f in folders[cur]:
            res.extend(dfs(f))
        folder_info[cur].extend(res)
        return res
    N, M = map(int, input().split())
    folders = defaultdict(list)
    folder_info=defaultdict(list)
    files = defaultdict(list)
    for _ in range(N + M):
        ins = list(input().strip().split())
        if ins[-1] == "1":
            folders[ins[0]].append(ins[1])
        else:
            files[ins[0]].append(ins[1])
    dfs("main")
    Q = int(input())
    for _ in range(Q):
        query = list(input().strip().split("/"))
        print(f"{len(set(folder_info[query[-1]]))} {len(folder_info[query[-1]])}")
main()

"""
접근 방법: dfs
하위 계층에서 상위 계층으로 값을 계속 더해준다.
처음에는 쿼리마다 dfs를 돌면서 가진 파일의 수를 반환하고 이후에 len(set()) len()을 구했다.
매번 dfs를 도는 것이 비효율적이라 생각해서 미리 값을 구하고 쿼리에 해당하는 폴더의 정보를 전달해 주는 방식으로 변경
- 가능한 이유는 중복된 폴더의 이름이 없기 때문에 dict을 이용하여 바로 접근할 수 있다
"""