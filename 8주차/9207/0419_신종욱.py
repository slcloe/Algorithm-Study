import sys
input = sys.__stdin__.readline
 
dx = [0, 0, 1, -1] 
dy = [1, -1, 0, 0]
 
def move(board, count_moved): 
    global min_count, min_moved
 
    pinloc = []
    for i in range(5):
        for j in range(9):
            if board[i][j] == 'o':
                pinloc.append((j, i))
 
    l = len(pinloc)
    if min_count > l:
        min_moved = count_moved
        min_count =  l

 
    for x, y in pinloc: # 각 핀의 위치를 가져온다.
        for idx in range(4): # 4방향 이동
            nx = x + dx[idx]
            ny = y + dy[idx]
            if 0 <= nx + dx[idx] < 9 and -1 < ny + dy[idx] < 5:
                if board[ny][nx] == 'o' and board[ny + dy[idx]][nx + dx[idx]] == '.':
                    board[ny][nx] = '.'
                    board[ny+dy[idx]][nx+dx[idx]] = 'o'
                    board[y][x] = '.'
                    move(board, count_moved + 1)
                    board[ny][nx] = 'o'
                    board[ny + dy[idx]][nx + dx[idx]] = '.'
                    board[y][x] = 'o'

def main():
    global min_count, min_moved
    for _ in range(int(input())):
        min_count = 10 
        min_moved = 10
        board = [list(input().rstrip()) for i in range(5)]
        input()
        move(board, 0)
        print(min_count, min_moved)

main()
