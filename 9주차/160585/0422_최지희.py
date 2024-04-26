def solution(board):
    score={"X":0,"O":0}
    for i in range(3):
        if board[0][i]==board[1][i]==board[2][i]!=".":
            score[board[0][i]]+=1
        if board[i][0]==board[i][1]==board[i][2]!=".":
            score[board[i][0]]+=1
    if board[0][0]==board[1][1]==board[2][2]!=".":
        score[board[1][1]]+=1
    if board[0][2]==board[1][1]==board[2][0]!=".":
        score[board[1][1]]+=1
    count={"X":0,"O":0}
    for i in range(3):
        for j in range(3):
            if board[i][j]!=".":
                count[board[i][j]]+=1
    if count["X"]>count["O"]:
        return 0
    if count["O"]-count["X"]>1:
        return 0
    if count["O"]==count["X"] and score["O"]:
        return 0
    if count["O"]==count["X"]+1 and score["X"]:
        return 0
    return 1

"""
실수한 경우를 체크해서 종료
"O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
1. x의 수가 o의 수보다 많은 경우
2. o의 수가 x의 수보다 2개 이상으로 많은 경우

선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.
3. 선공이 승리했는데 게임을 진행한 경우
4. 후공이 승리했는데 게임을 진행한 경우  
"""
