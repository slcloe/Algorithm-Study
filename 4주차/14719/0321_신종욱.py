import sys
input = sys.__stdin__.readline

def main():
    h, w = list(map(int, input().split()))
    heights = list(map(int, input().split()))

    prev = heights[0]
    answer = 0
    
    i = 1
    while i < w:
        curr = heights[i]

        if prev > curr:
            j = i
            local_heights = []
            while j < w and prev > heights[j]:
                local_heights.append(heights[j])
                j += 1

            ans = 0
            m = min(heights[min(j, w - 1)], prev)
            for local_height in local_heights:
                ans += (m - local_height)
            
            answer += ans
            if j < w:
                i = j
            else:
                l = -1
                for idx in range(i, w):
                    if l < heights[idx]:
                        l = heights[idx]
                l = min(l, prev)
                for idx in range(i, w):
                    answer += (l - heights[idx])
    
        prev = heights[i]
        i += 1
    
    print(answer)

main()