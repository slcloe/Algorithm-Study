/*
 * | 활용 자료구조 | PriorityQueue, ArrayList
 *
 * | 접근 방법 |
 *  1. 각 컴퓨터 이용 시간을 시작 시간 기준 오름차순 정렬한다
 *  2. 리스트를 순회하며 이번 이용 시간의 시작 시간보다 작은 종료 시간이 있는지(기존 자리에 앉을 수 있는지) 확인한다
 *     2-1. 가능하다면 종료 시간을 갱신하고 해당 자리의 cnt를 증가한다
 *  3. 리스트가 비었거나 위의 경우에 해당되지 않는다면 리스트에 새 배열을 추가한다
 *     3-1. 배열에 새로 추가할 때마다 최대 길이를 갱신한다
 *  4. 리스트의 사이즈와 리스트의 cnt를 차례대로 출력한다
 */

package a2405.study.week12;

import java.io.*;
import java.util.*;

public class bj_g3_12764_싸지방에_간_준하 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[0]-o2[0]);
        ArrayList<int[]> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for(int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new int[] {start, end});
        }
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int start = now[0];
            int end = now[1];
            boolean isReplaceable = false;
            for(int i=0; i<list.size(); i++){
                if(list.get(i)[0] <= start){
                    list.get(i)[0] = end;
                    list.get(i)[1]++;
                    isReplaceable = true;
                    break;
                }
            }
            if(!isReplaceable){
                list.add(new int[] {end, 1});
                answer++;
            }
        }
        sb.append(answer).append("\n");
        for(int[] seat: list){
            sb.append(seat[1]).append(" ");
        }
        System.out.println(sb);
    }
}