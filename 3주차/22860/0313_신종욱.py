import sys
input = sys.__stdin__.readline
sys.setrecursionlimit(10**8)

def main():
    n, m = list(map(int, input().split()))

    global fs
    fs = {
        'main': set() # 상위폴더 : 그 폴더에 있는 폴더 또는 디렉토리들(set)
    }
    
    for _ in range(n + m):
        # parent, folder_or_file, isFolder
        p, f, c = input().split()

        # p in fs = 이미 파일 시스템에 추가된 폴더임
        if p in fs:
            fs[p].add(f)
        else:
            # parent가 아직 fs에 없다면 main의 하위폴더니까 main의 하위에 추가한다.
            fs['main'].add(p)
            fs[p] = set()
            fs[p].add(f)

        # p의 하위에 있는 f가 폴더인데 fs에 없으면 추가해야 한다.
        if c == '1':
            if f not in fs:
                fs[f] = set()


    global file_list
    q = int(input())
    for _ in range(q):
        query = input().rstrip()
        dir = query.split('/')[-1]
        file_list = []
        dfs(dir, visited=set())
        a, b = len(set(file_list)), len(file_list)
        print(a, b)
        

def dfs(dir, visited=set()):
    if dir in visited:
        return

    visited.add(dir)
    if dir in fs:
        for file in fs[dir]:
            if file in fs: # 폴더면 재귀호출하고
                dfs(dir=file, visited=visited)
            else: # 아니면 파일 목록에 추가한다.
                file_list.append(file)


main()