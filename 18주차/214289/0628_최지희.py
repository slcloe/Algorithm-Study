def solution(temperature, t1, t2, a, b, onboard):
    answer = 0
    temperature+=10
    t1+=10
    t2+=10
    n=len(onboard)
    dp=[[1e6]*51 for _ in range(n)] # i분에 온도 j를 만들기 위한 최소전력
    dp[0][temperature]=0
    air=(t2-temperature)//abs(t2-temperature)
    for i in range(1,n):
        x=0
        y=50
        if onboard[i]==1:
            x=t1
            y=t2
        for j in range(x,y+1):
            # 온도 변화 에어컨 x
            if 0<=j+air<=50:
                dp[i][j]=min(dp[i][j],dp[i-1][j+air])
            # 온도 변화 에어컨 o
            if 0<=j-air<=50:
                dp[i][j]=min(dp[i][j],dp[i-1][j-air]+a)
            # 온도 유지 에어컨 x
            if j==temperature:
                dp[i][j]=min(dp[i][j],dp[i-1][j])
            # 온도 유지 에어컨 o
            if t1<=j<=t2:
                dp[i][j]=min(dp[i][j],dp[i-1][j]+b)
    return min(dp[n-1])
