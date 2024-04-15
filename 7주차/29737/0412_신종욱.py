class UserInfo:
    def __init__(self, name, maxStreak=-1, usedFreeze=0, startMax=0, failedCount=0):
        self.name = name
        self.maxStreak = maxStreak
        self.usedFreeze = usedFreeze
        self.startMax = startMax
        self.failedCount = failedCount

def compare(a, b):
    if a.maxStreak != b.maxStreak:
        return b.maxStreak - a.maxStreak
    if a.usedFreeze != b.usedFreeze:
        return a.usedFreeze - b.usedFreeze
    if a.startMax != b.startMax:
        return a.startMax - b.startMax
    if a.failedCount != b.failedCount:
        return a.failedCount - b.failedCount
    if a.name < b.name:
        return -1
    return 1

N, W = map(int, input().split())
users = []

for _ in range(N):
    name = input()
    usi = UserInfo(name)
    pan = [input() for _ in range(7)]
    
    streak = False
    frezeinst = 0
    now_date = 0
    start_date = 0
    end_date = 0

    for x in range(W):
        for y in range(7):
            if pan[y][x] == 'O':
                if not streak:
                    start_date = now_date
                    end_date = now_date
                    streak = True
                else:
                    end_date = now_date
            elif pan[y][x] == 'F':
                if not streak:
                    continue
                end_date = now_date
                frezeinst += 1
            else: 
                usi.failedCount += 1
                if not streak:
                    continue
                if end_date - start_date > usi.maxStreak:
                    usi = UserInfo(name, end_date - start_date, frezeinst, start_date, usi.failedCount)
                streak = False
                frezeinst = 0
            now_date += 1

    if end_date - start_date > usi.maxStreak:
        usi = UserInfo(name, end_date - start_date, frezeinst, start_date, usi.failedCount)

    users.append(usi)

# 정렬
users.sort(key=lambda x: (-x.maxStreak, x.usedFreeze, x.startMax, x.failedCount, x.name))

ct = 1
last = None

for i, user in enumerate(users):
    if i > 0:
        if (last.maxStreak != user.maxStreak or last.usedFreeze != user.usedFreeze or
                last.startMax != user.startMax or last.failedCount != user.failedCount):
            ct += 1
    print(f"{ct}. {user.name}")
    last = user
