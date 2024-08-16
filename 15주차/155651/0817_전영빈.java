import java.util.*;

/*
    전형적인 그리디 문제.
    시간이 문자열로 관리되어 구현이 귀찮았다.
    함수화해서 편하게 사용하자.
*/

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
    
        Arrays.sort(book_time, (o1, o2) -> stringCompare(o1[0], o2[0]));
      
        PriorityQueue<String[]> heap = new PriorityQueue<>(
            (o1, o2) -> stringCompare(o1[1], o2[1]));
        
        for (String[] bt : book_time) {
            while(!heap.isEmpty() && stringCompare(heap.peek()[1], bt[0]) <= 0) {
                heap.poll();
            }
            
            if (heap.size() == answer) {
                answer++;
            }
            
            heap.offer(new String[] {bt[0], addTen(bt[1])});
        }
        
        return answer;
    }
    
    static int stringCompare(String a, String b) {
        StringTokenizer sta = new StringTokenizer(a, ":");
        StringTokenizer stb = new StringTokenizer(b, ":");
        
        int ah = Integer.parseInt(sta.nextToken());
        int am = Integer.parseInt(sta.nextToken());
        int bh = Integer.parseInt(stb.nextToken());
        int bm = Integer.parseInt(stb.nextToken());

        if (ah > bh) {
            return 1;
        } else if (ah < bh) {
            return -1;
        } else {
            if (am > bm) {
                return 1;
            } else if (am < bm) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    
    static String addTen(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        minute += 10;
        hour += (minute / 60);
        minute %= 60;
        
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour)
            .append(":")
            .append(minute);
        
        return sb.toString();
    }
}
