"""
query에서 코딩 테스트 점수 이외의 조건들은 boolean 값으로 적용된다.
따라서 코딩 테스트 점수를 value로 그 이외의 조건들을 key 값으로 하는 딕셔너리를 이용하여 지원자들의 정보를 저장했다.
이 때, 주의해야 할 점은 개발언어가 java이고 직군이 backend인 지원자는 java, backend, java and backend, all 네 가지
방법으로 모두 조회가 가능해야 한다는 것이다.
따라서 조합을 이용해 가능한 모든 경우에 대한 key 값을 만들어야 한다.

info가 최대 50000개이고 query가 최대 100000개이므로, 모든 쿼리의 조건이 러프하여 인포를 모두 찾아봐야 하는 경우
O(N)의 시간 복잡도로 탐색하게 되면 10^9의 시간 복잡도로 시간 초과가 날 것이다.
따라서, O(logN) 의 시간복잡도를 가진 탐색 방법을 선택해야 하고 이분 탐색을 이용했다.
"""

from collections import defaultdict
from itertools import combinations

def solution(infos, querys):
    
    answer = []
    
    database = defaultdict(list)
    
    for info in infos:
        info = info.split()
        
        keys = info[:-1]
        value = int(info[-1])
        
        for i in range(5):
            for choice in combinations(keys, i):
                tk = "/".join(choice)
                database[tk].append(value)
                
    for key in database.keys():
        database[key].sort()
    
    # print(database)
    
    for query in querys:
        query = query.split()
        realQuery = []
        for q in query[:-1]:
            if q != "-" and q != 'and':
                realQuery.append(q)
        
        searchKey = "/".join(realQuery)
        searchValue = int(query[-1])
        # print(searchKey, database[searchKey], searchValue)
        
        searchList = database[searchKey]
        left = 0
        right = len(searchList)-1
        mid = right
        
        while left <= right:
            mid = (left + right) // 2
            
            if searchList[mid] < searchValue:
                left = mid + 1
            else:
                right = mid - 1
                
        answer.append(len(searchList)-right-1)
        
    return answer
