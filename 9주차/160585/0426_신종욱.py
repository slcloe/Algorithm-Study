def solution(board):
    o_count = count(board, 'O')
    x_count = count(board, 'X')

    # O의 갯수보다 X의 갯수가 많을 수 없다.
    if o_count < x_count:
        return 0

    # O의 갯수와 X 갯수의 차이는 항상 0 또는 1이다.
    if o_count - x_count >= 2:
        return 0

    # O가 승리한 상황이면 O의 갯수는 커야 한다.
    if is_winner(board, 'O'):
        if o_count <= x_count:
            return 0
    # X가 승리한 상황이면 O의 갯수는 O보다 작거나 같아야 한다.
    if is_winner(board, 'X'):
        if x_count < o_count:
            return 0

    return 1


def count(board, factor='O'):
    res = 0

    for i in range(3):
        for j in range(3):
            if board[i][j] == factor:
                res += 1
    
    return res


def is_winner(board, factor='O'):
    target = [factor for _ in range(3)]

    # 대각선 \
    if [board[i][i] for i in range(3)] == target:
        return True

    # 대각선 /
    if [board[i][-i - 1] for i in range(3)] == target:
        return True
    
    # 행
    for i in range(3):
        if [board[i][j] for j in range(3)] == target:
            return True
    
    # 열
    for j in range(3):
        if [board[i][j] for i in range(3)] == target:
            return True
    
    return False
            
