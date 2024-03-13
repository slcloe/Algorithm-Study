import sys
input = sys.__stdin__.readline


def main():
    data = tuple(map(int, input().split()))
    l = -1
    for d in data:
        l = max(l, abs(d))


    r1, c1, r2, c2 = (x + l for x in data)
    arr = get_swirl(l, r1, c1, r2, c2)

    l = -1
    for r in range(r2 - r1 + 1):
        for c in range(c2 - c1 + 1):
            l = max(l,  len(arr[r][c]))

    for c in range(c2 - c1 + 1):
        for r in range(r2 - r1 + 1):
            spaces = []
            for _ in range(l - len(arr[r][c])):
                spaces.append(' ')

            arr[r][c] = ''.join(spaces) + arr[r][c]

    print('\n'.join([' '.join(row) for row in arr]))    
   

def get_swirl(l, r1, c1, r2, c2):
    ci, cj = l, l

    arr = [[-1 for c in range(c2 - c1 + 1)] for r in range(r2 - r1 + 1) ]
    val = 1
    if range_check(ci, cj, r1, c1, r2, c2):
        arr[ci - r1][cj - c1] = str(val)

    for lim in range(1, l + 1):
        # first >
        cj += 1
        val += 1
        
        if range_check(ci, cj, r1, c1, r2, c2):
            arr[ci - r1][cj - c1] = str(val)

        # ∧
        for offset in range(2*lim - 1):
            ci -= 1
            val += 1
            
            if range_check(ci, cj, r1, c1, r2, c2):
                arr[ci - r1][cj - c1] = str(val)

        # <
        for offset in range(2*lim):
            cj -= 1
            val += 1
            if range_check(ci, cj, r1, c1, r2, c2):
                arr[ci - r1][cj - c1] = str(val)
        
        # ∨
        for offset in range(2*lim):
            ci += 1
            val += 1
            if range_check(ci, cj, r1, c1, r2, c2):
                arr[ci - r1][cj - c1] = str(val)

        # >
        for offset in range(2*lim):
            cj += 1
            val += 1
            if range_check(ci, cj, r1, c1, r2, c2):
                arr[ci - r1][cj - c1] = str(val)

    return arr


def range_check(i, j, r1, c1, r2, c2):
    return (r1 <= i <= r2) and (c1 <= j <= c2)


main()