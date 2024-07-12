"""
  배열 크기가 5by5 밖에 되지 않으니 그냥 응시자 좌표를 구한 다음 쌍으로 묶어서 각 쌍이 조건에 해당되는지 파악했다.
  두 응시자간의 거리가 1일 경우, 맞닿아 있으므로 0.
  두 응시자 간의 거리가 2일 경우, 응시자의 모습이 십자인지 일자인지 판단하여 처리했다.
"""

from itertools import combinations

def solution(places):
    answer = []
    person = []
    
    def solve(board):
        for a, b in combinations(range(len(person)), 2):
            ay, ax = person[a][0], person[a][1]
            by, bx = person[b][0], person[b][1]
            distance = abs(ay - by) + abs(ax - bx)
            
            if distance == 1:
                return 0
            elif distance == 2:
                # 십자
                if abs(ay - by) == 1:
                    if board[min(ay, by)][min(ax, bx)] == 'O' or board[max(ay, by)][max(ax, bx)] == 'O' or board[min(ay, by)][max(ax, bx)] == 'O' or board[max(ay, by)][min(ax, bx)] == 'O':
                        return 0
                    
                # 일자
                else:
                    if board[(ay + by) // 2][(ax + bx) // 2] == 'O':
                        return 0
        
        return 1

    for place in places:
        person = []
        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P':
                    person.append((i, j))
               
        answer.append(solve(place))
    
    return answer
