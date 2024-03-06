import sys
input = sys.__stdin__.readline

def main():
    global n
    n = int(input())

    global dices
    dices = []
    for _ in range(n):
        dices.append(list(map(int, input().split())))

    '''
    0 - 5
    1 - 3
    2 - 4
    '''

    answer = -1
    for head in range(6):  
        ans = 0
        prev_top = dices[0][head]
        
        ans += max_of_side(0, head)

        for idx in range(1, n):
            # 현재 주사위에서 이전 주사위의 윗면 숫자 인덱스를 구한다.
            bottom = dices[idx].index(prev_top)

            # 이전에 쌓은 주사위의 윗면 숫자가 아래로 가야 하므로
            # 방금 구한 면(인덱스)의 반대편이 새로운 윗면이 된다.
            if bottom == 0:
                prev_top = dices[idx][5]
            elif bottom == 1:
                prev_top = dices[idx][3]
            elif bottom == 2:
                prev_top = dices[idx][4]
            elif bottom == 3:
                prev_top = dices[idx][1]
            elif bottom == 4:
                prev_top = dices[idx][2]
            elif bottom == 5:
                prev_top = dices[idx][0]
                pass

            ans += max_of_side(idx, bottom)

        answer = max(ans, answer)

    print(answer)

# 이거 더 깔끔하게 하고 싶은데 방법을 모르겠음....
def max_of_side(dice_idx, idx_in_dice):
    val = 0
    if idx_in_dice == 0 or idx_in_dice == 5:
        temp1, temp2 = dices[dice_idx][idx_in_dice], dices[dice_idx][5 - idx_in_dice]
        dices[dice_idx][idx_in_dice], dices[dice_idx][5 - idx_in_dice] = -1, -1
        val = max(dices[dice_idx])
        dices[dice_idx][idx_in_dice], dices[dice_idx][5 - idx_in_dice] = temp1, temp2
    elif idx_in_dice == 1 or idx_in_dice == 3:
        temp1, temp2 = dices[dice_idx][idx_in_dice], dices[dice_idx][4 - idx_in_dice]
        dices[dice_idx][idx_in_dice], dices[dice_idx][4 - idx_in_dice] = -1, -1
        val = max(dices[dice_idx])
        dices[dice_idx][idx_in_dice], dices[dice_idx][4 - idx_in_dice] = temp1, temp2
    else: # idx_in_dice == 2 or idx_in_dice == 4
        temp1, temp2 = dices[dice_idx][idx_in_dice], dices[dice_idx][6 - idx_in_dice]
        dices[dice_idx][idx_in_dice], dices[dice_idx][6 - idx_in_dice] = -1, -1
        val = max(dices[dice_idx])
        dices[dice_idx][idx_in_dice], dices[dice_idx][6 - idx_in_dice] = temp1, temp2

    return val

main()
