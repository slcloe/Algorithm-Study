def solution(temperature, t1, t2, a, b, onboard):
    # dp[i][j] = i분이 지난 시점에 온도를 j로 맞추는 최소 비용
    # 2 <= i <= len(onboard)
    # -10 <= temperature <= 40이므로 -10 <= j <= 40

    # 온도 비교는 배열 인덱스인 j로 하므로 최소 범위가 0이 되도록 10을 더한다.
    t1 += 10
    t2 += 10
    temperature += 10

    # 1 <= a, b <= 100이고 2 <= len(onboard) <= 1000이므로 dp의 Sentinel은 100*1000
    SENTINEL = 100*1000 
    dp = [[SENTINEL for j in range(-10, 40 + 1)] for i in range(len(onboard))]
    # 현재 시점의 현재 온도로 맞추는 최소 비용은 당연히 0
    dp[0][temperature] = 0

    # 자연적으로 변하는 방향
    factor = -1 if temperature < t1 else 1

    for i in range(1, len(onboard)):
        for j in range(51): # 희망온도 j에 대해
            temp = SENTINEL
            # 현재 시간(t=i)에서 유효한 j에 대해서만 
            # j는 배열 인덱스이므로 t1, t2의 범위도 배열 인덱스 범위인 0 이상으로 맞춰진 상태여야 함
            if not((onboard[i] == 1 and (t1 <= j <= t2)) or (onboard[i] == 0)):
                continue

            # 1. 온도가 변하게 하는 경우
            #  1-1. 실내온도와 실외온도가 달라서 변하게 하는 경우(에어컨 끔) -> 0
            if 0 <= j - factor <= 50: # 인덱스 체크
                temp = min(temp, dp[i - 1][j - factor] + 0)

            #  1-2. 희망온도 설정으로 변하게 하는 경우 -> a
            if 0 <= j + factor <= 50: # 인덱스 체크
                temp = min(temp, dp[i - 1][j + factor] + a)

            # 3. 온도가 유지되는 경우
            #  3-1. 희망온도 = 실외온도(에어컨 끔) -> 0
            if j == temperature:
                temp = min(temp, dp[i - 1][j] + 0)

            #  3-2. 희망온도 설정으로 유지한 경우(에어컨 켬) -> b
            if t1 <= j <= t2:
                temp = min(temp, dp[i - 1][j] + b)

            dp[i][j] = temp

    # dp[-1]은 마지막 시점에 해당 온도를 맞추는 최소 비용이므로 min(dp[-1])이 답이 된다.
    answer = min(dp[-1])

    return answer
