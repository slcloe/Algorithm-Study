import java.util.*;
class Solution {
    
    /*
    접근 방식 : 전형적인 정렬 문제. PQ를 두 개 사용해서 방과 손님들을 관리
    
    주안점
    1. 다음 고객의 시작시간이 현재 방의 끝 시간 + 10과 같거나 크면 방 번호를 반납
    2. 1번을 가장 앞에 있는 방 번호를 선택하기 전에 진행하는 것에 유의
    3. 숫자 비교의 편의를 위해 분 단위로 변환하여 진행
    */
    
    public int solution(String[][] book_time) {
        PriorityQueue<Integer> roomPQ = new PriorityQueue<>();
        for(int i=1; i<=1000; i++) roomPQ.offer(i);
        
        PriorityQueue<int[]> consumerPQ = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        
        int maxRoom = 0;
        
        for(int i=0; i<book_time.length; i++) {
            // 시작 시간
            int startTime = ((book_time[i][0].charAt(0) - '0')*10 + (book_time[i][0].charAt(1) - '0')) * 60;
            startTime += (book_time[i][0].charAt(3) - '0')*10 + (book_time[i][0].charAt(4) - '0');
            
            // 끝나는 시간
            int endTime = ((book_time[i][1].charAt(0) - '0')*10 + (book_time[i][1].charAt(1) - '0')) * 60;
            endTime += (book_time[i][1].charAt(3) - '0')*10 + (book_time[i][1].charAt(4) - '0');
            
            // 방 비우기
            while(!consumerPQ.isEmpty() && startTime >= consumerPQ.peek()[0] + 10) {
                roomPQ.offer(consumerPQ.poll()[1]);
            }
            
            // 비어있는 방
            int emptyRoom = roomPQ.poll();
            maxRoom = Math.max(emptyRoom, maxRoom);
            
            consumerPQ.offer(new int[] {endTime, emptyRoom});
        }
        
        return maxRoom;
    }
}