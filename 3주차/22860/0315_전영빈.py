"""
    dictory를 통해 폴더 구조를 정리한다.
    dfs를 통해 폴더 아래로 쭉 내려가면서 파일 개수를 카운팅.
    이 때, dictory에 있는 key 값이면 폴더이고 아니면 파일이다.
    파일의 종류는 이름이 같은 파일일 경우 같은 종류로 판단하므로
    SET을 통해 탐색한 파일의 이름을 관리했다.
"""
import sys
from collections import defaultdict

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

N, M = map(int, input().split())
folder = defaultdict(list)

fileCount = 0

def dfs(current, fs):
    global fileCount
    # 현재 검색 위치가 folder가 아니면 종료.
    if current not in folder:
        return
    
    for next in folder[current]:
        if next in folder:
            dfs(next, fs)
        else:
            # 해당 파일과 같은 이름의 파일을 아직 탐색하지 않은 경우
            if next not in fs:
                fs.add(next)
            fileCount += 1
    
for _ in range(N+M):
    P, F, C = input().rstrip().split()
    if P not in folder:
        folder[P]
    if F not in folder and C == '1':
        folder[F]

    folder[P].append(F)

Q = int(input())
for _ in range(Q):
    fileCount = 0
    fileSet = set()
    path = list(input().rstrip().split("/"))
    dfs(path[-1], fileSet)

    print(len(fileSet), fileCount)
