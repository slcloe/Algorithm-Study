import sys; input = sys.stdin.readline
d = [(1, -1), (1, 0), (1, 1), (0, -1), (0, 0), (0, 1), (-1, -1), (-1, 0), (-1, 1), ]



def main():
    # 0 input
    x, y = map(int, input().split())
    board = []
    js = [-1, -1]
    insane_arduino = set()
    
    for r in range(x):
        board.append(list(input().rstrip()))
        for c in range(y):
            if board[r][c] == '.':
                continue
            
            if board[r][c] == 'R':
                insane_arduino.add((r, c))
            if board[r][c] == 'I':
                js[0], js[1] = r, c

    directions = input().rstrip()

    # 1 simulation
    break_chk = 0
    for i in range(len(directions)):
        # 1-1 Jongsu move first
        # 1-1-1 move directions[i]
        dr, dc = d[int(directions[i]) - 1]
        nr, nc = js[0] + dr, js[1] + dc
        board[js[0]][js[1]] = '.'
        board[nr][nc] = 'I'
        js[0], js[1] = nr, nc
        # 1-1-2 Check position. if JS move on insane arduino's position, JS loses
        if tuple(js) in insane_arduino:
            break_chk = i + 1
            break

        # 1-2 insane arduino move
        new_arduino = set()
        overlap_arduino = set()
        while insane_arduino:
            arduino = insane_arduino.pop()
            # 1-2-1 move closer with JS
            dr, dc = calculate_direction(arduino, js)
            nr, nc = arduino[0] + dr, arduino[1] + dc
            # 1-2-2 Check position. if arduino move on JS's position, JS loses
            if js[0] == nr and js[1] == nc:
                break_chk = i + 1
                break
            board[arduino[0]][arduino[1]] = '.'

            tmp_arduino = (nr, nc)
            if tmp_arduino in new_arduino:
                overlap_arduino.add(tmp_arduino)
            else:
                new_arduino.add(tmp_arduino)
        # 1-2-2 Check position. if arduino move on JS's position, JS loses
        if break_chk:
            break
        # 1-2-3 check overlap
        while overlap_arduino:
            arduino = overlap_arduino.pop()
            board[arduino[0]][arduino[1]] = '.'
            new_arduino.remove(arduino)
        for arduino in new_arduino:
            board[arduino[0]][arduino[1]] = 'R'
        insane_arduino = new_arduino


    # 2 output
    if break_chk:
        print("kraj", break_chk)
    else:
        print('\n'.join([''.join(board[r]) for r in range(R)]))

def calculate_direction(arduino, js):
    r1, s1 = js
    r2, s2 = arduino
    if r1 < r2:         dr = -1
    elif r1 == r2:      dr = 0
    else:               dr = 1

    if s1 < s2:         dc = -1
    elif s1 == s2:      dc = 0
    else:               dc = 1

    return dr, dc

if __name__ == "__main__":
    main()