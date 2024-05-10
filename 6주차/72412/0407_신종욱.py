from bisect import bisect_left


l = ["cpp", "python", "java"]
p = ["backend", "frontend"]
e = ["junior", "senior"]
f = ["pizza", "chicken"]

def make_dict(lan):
    for i in p:
        lan[i] = {}

    for i in p:
        for j in e:
            lan[i][j] = {}

    for i in p:
        for j in e:
            for k in f:
                lan[i][j][k] = []

    return lan


def sort_score(lan):
    for i in p:
        for j in e:
            for k in f:
                lan[i][j][k] = sorted(lan[i][j][k])

    return lan


def solution(info, query):
    answer = []
    cpp, python,java ={},{},{}

    cpp = make_dict(cpp)
    python=make_dict(python)
    java = make_dict(java)

    for i in info:
        lan,pos,exp,food,score = i.split(" ")
        if(lan == "cpp"):
            cpp[pos][exp][food].append(int(score))
        elif(lan == "java"):
            java[pos][exp][food].append(int(score))
        else:
            python[pos][exp][food].append(int(score))

    cpp = sort_score(cpp)
    python=sort_score(python)
    java = sort_score(java)

    for q in query:
        lan, pos, exp, food_and_score = q.split(" and")
        food, score = food_and_score.strip().split(" ")
        lan = [lan.strip()]
        pos = [pos.strip()]
        exp = [exp.strip()]
        food = [food]
        score = int(score)

        if(lan[0] == "-"):
            lan = l
        if(pos[0] == "-"):
            pos = p
        if(exp[0] == "-"):
            exp = e
        if(food[0] == "-"):
            food = f
        cnt = 0

        for i in lan:
            if(i == "cpp"):
                curr_lan = cpp
            elif(i == "java"):
                curr_lan = java
            else:
                curr_lan = python

            for j in pos:
                for k in exp:
                    for l1 in food:
                        if(len(curr_lan[j][k][l1])!=0):
                            cnt += len(curr_lan[j][k][l1]) - bisect_left(curr_lan[j][k][l1],score)

        answer.append(cnt)

    return answer
