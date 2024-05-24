def solution(name):
    global move
    def dfs(x,cnt):
        global move
        if move<=cnt:
            return
        if not max(zero):
            move=min(move,cnt)
            return
        
        for d in [-1,1]:
            for k in range(1,m):
                nx=(x+k*d)%m
                if zero[nx]:
                    break
            zero[nx]=0
            dfs(nx,cnt+k)
            zero[nx]=1
        
    alpha={"A":0,"B":1,"C":2,"D":3,"E":4,"F":5,"G":6,"H":7,"I":8,"J":9,"K":10,"L":11,"M":12,"N":13,"O":12,"P":11,"Q":10,"R":9,"S":8,"T":7,"U":6,"V":5,"W":4,"X":3,"Y":2,"Z":1}
    answer = 0
    m=len(name)
    move=m
    zero=[]
    for n in name:
        if n=="A":
            zero.append(0)
        else:
            zero.append(1)
            answer+=alpha[n]
    zero[0]=0
    dfs(0,0)
    return answer+move
"""
위아래로 돌아가는 순서를 미리 저장해서 값을 더해줬다.
최소 이동을 구하면 된다
dfs를 통해서 "a"가 아닌 곳으로 점프를 하고 그 값을 더해가면서 최소 이동을 구한다
"""
