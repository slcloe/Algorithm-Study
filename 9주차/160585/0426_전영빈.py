"""
불가능한 경우의 수를 모두 찾아서 0을 반환하도록 구현.
1. x 수가 o 수보다 많은 경우
2. o 수가 x 수보다 2개 이상으로 많은 경우
3. o와 x가 모두 승리한 경우
4. o가 승리했지만 o의 수가 x와 같거나 더 많은 경우. 즉, o가 승리했음에도 턴을 더 지속한 경우
5. x가 승리했지만 o와 x의 수가 다른 경우. 즉, x가 승리했음에도 턴을 더 지속한 경우
"""

def solution(board):
    
    def count(target):
        res = 0
        
        for i in range(3):
            for j in range(3):
                if board[i][j] == target:
                    res += 1
        
        return res
    
    def winable(target):
        if board[0][0] == board[1][1] == board[2][2] == target:
            return True
        
        if board[0][2] == board[1][1] == board[2][0] == target:
            return True
        
        for i in range(3):
            if board[i][0] == board[i][1] == board[i][2] == target:
                return True
            
            if board[0][i] == board[1][i] == board[2][i] == target:
                return True

        return False
    
    oc = count('O')
    xc = count('X')
    
    if xc > oc:
        return 0
    
    if oc - xc > 1:
        return 0
    
    ow = winable('O')
    xw = winable('X')
    
    if ow and xw:
        return 0
    
    if (ow and not xw) and xc >= oc:
        return 0
    
    if (not ow and xw) and xc != oc:
        return 0

    return 1
