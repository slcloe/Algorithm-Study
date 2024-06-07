/*
 * | 활용 자료구조 | PriorityQueue
 *
 * | 접근 방법 |
 *  1. 예약 시간을 pqBook에 저장한다
 *     1-1. 대실 시작 시간을 오름차순으로 정렬한다
 *     1-2. 퇴실 후 10분 간 정리 시간이 필요하므로 10분을 더해 저장한다
 *  2. 대실 종료 시간을 내림차순으로 정렬하는 pqEnd를 만든다
 *  3. pqBook으로 정렬된 예약 내역들에 대해 아래 과정을 수행한다
 *     3-1. pqEnd가 초기 상태이거나
 *      pq의 최상위 원소보다 pqBook의 대실 시작 시간이 더 작을 경우(빈 방이 없는 경우)에는
 *      필요한 방의 개수를 하나 늘린 다음 현재 예약 내역의 종료 시간을 저장한다
 *     3-1. 빈 방이 있는 경우에는 pqEnd의 최상위 원소를 제거하고 현재 예약 내역의 종료 시간을 저장한다
 */

package a2406.study.week15;

import java.util.*;

public class pr_2_155651_호텔_대실 {
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<int[][]> pqBook = new PriorityQueue<>(
                (o1, o2)->{
                    if(o1[0][0] == o2[0][0]){ return Integer.compare(o1[0][1], o2[0][1]); }
                    else{ return o1[0][0]-o2[0][0]; }
                }
        );
        PriorityQueue<int[]> pqEnd = new PriorityQueue<>(
                (o1, o2)->{
                    if(o1[0] == o2[0]){ return Integer.compare(o1[1], o2[1]); }
                    else{ return o1[0]-o2[0]; }
                }
        );

        for(String[] book: book_time){
            int[][] bookInt = new int[2][2];
            StringTokenizer st = new StringTokenizer(book[0], ":");
            bookInt[0][0] = Integer.parseInt(st.nextToken());
            bookInt[0][1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book[1], ":");
            bookInt[1][0] = Integer.parseInt(st.nextToken());
            bookInt[1][1] = Integer.parseInt(st.nextToken())+10;
            if(60 <= bookInt[1][1]){
                bookInt[1][1] -= 60;
                bookInt[1][0] += 1;
            }
            pqBook.offer(bookInt);
        }

        while(!pqBook.isEmpty()){
            int[][] book = pqBook.poll();
            int[] start = book[0];
            int[] end = book[1];

            if(pqEnd.isEmpty() || !canEnter(start, pqEnd.peek())){
                pqEnd.offer(end);
                answer++;
            }
            else{
                pqEnd.poll();
                pqEnd.offer(end);
            }
        }
        return answer;
    }

    static boolean canEnter(int[] start, int[] end){
        if(start[0] == end[0]){
            return end[1] <= start[1];
        }
        return end[0] < start[0];
    }
}