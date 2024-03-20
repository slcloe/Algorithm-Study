import sys
input=sys.stdin.readline

def main():
  N,M,L,K=map(int,input().split())
  board=[list(map(int,input().split())) for _ in range(K)]
  board.sort()
  ans=0
  for i in range(K):
    stack=[]
    for j in range(i,K):
      if board[j][0]-board[i][0]<=L:
        stack.append(board[j])
      else:
        break
    stack.sort(key=lambda x:(x[1]))
    s=len(stack)
    for x in range(s):
      tmp=0
      for y in range(x,s):
        if stack[y][1]-stack[x][1]<=L:
          tmp+=1
      if ans<tmp:
        ans=tmp
  print(K-ans)

main()

"""
처음생각: 한 별똥별이 꼭짓점에 있다고 생각했다 -> 꽉체워서 사각형을 그려야 하므로
예외: 별이 변에 포함하는 경우여도 꽉채워서 사각형을 만들 수 있다.
=> 하나는 가로변에 다른 하나는 세로변에 있는 경우 중에서 최대 값을 찾으면 된다.

1. x좌표의 값만 고려해서 가능한 모든 좌표들을 하나의 리스트에 담는다. 
..........
##########
##########
##########
..........
..........

2. 리스트내에서 y좌표들을 고려해서 가능한 점들의 수를 구하고 최대를 구한다.
#***######
#***######
#***######
...
###***####
###***####
###***####
계속 이런식으로 맨 앞의 y좌표와 포함가능한 범위들의 별을 구한다.

"""