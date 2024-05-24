import java.util.*;
class Solution {
    
    /*
    접근 방식 : 처음에는 그리디라고 해서, 최적화된 위치의 출발 지점을 정해야 한다고 생각함.
               근데 무조건 처음부터 시작하므로 틀렸고, A가 아닌 것들의 인덱스를 저장 후 dfs
               힌트를 보고 풀었음
    
    주안점
    1. 문자열의 길이를 넘어 가는 방향은, 큰 값과 작은 값을 다르게 하여 비교해야 함
    2. A가 아닌 것들을 저장하고 왼쪽 오른쪽을 비교한 후 dq가 비었을 경우 갱신
    */
    
    static Deque<Integer> dq;
    static char[] arr;
    static int answer;
    public int solution(String name) {
        dq = new ArrayDeque<>();
        arr = name.toCharArray();
        for(int i=1; i<name.length(); i++) {
            if(name.charAt(i) != 'A') dq.offer(i);
        }
        answer = Integer.MAX_VALUE;
        dfs(0, 0);
        return answer;
    }
    
    static void dfs(int idx, int sum) {
        int now = arr[idx] - 'A';
        sum += Math.min(now, 26 - now);
        
        if(dq.isEmpty()) {
            answer = Math.min(sum, answer);
            return;
        }
        
        // 오른쪽
        int rightIdx = dq.poll();
        int maxIdx = rightIdx > idx ? rightIdx : idx;
        int minIdx = rightIdx > idx ? idx : rightIdx;
        dfs(rightIdx, sum + Math.min(arr.length-maxIdx+minIdx, Math.abs(rightIdx-idx)));
        dq.offerFirst(rightIdx);
        
        // 왼쪽
        int leftIdx = dq.pollLast();
        maxIdx = leftIdx > idx ? leftIdx : idx;
        minIdx = leftIdx > idx ? idx : leftIdx;
        dfs(leftIdx, sum + Math.min(arr.length-maxIdx+minIdx, Math.abs(leftIdx - idx)));
        dq.offer(leftIdx);
    }
}