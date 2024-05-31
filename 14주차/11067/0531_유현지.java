/*
 * | 활용 자료구조 | Sorted Map, ArrayList
 *
 * | 접근 방법 |
 *  1. 카페의 좌표를 Sorted Map에 저장한다
 *     1-1. key: x 좌표
 *     1-2. value: y 좌표 리스트
 *  2. 각각의 y 좌표 리스트에 대해 오름차순 정렬한다
 *  3. 리스트의 첫 번째 원소 값이 직전 카페의 y좌표와 일치하는지 확인한 뒤 아래의 과정을 거치고 직전 y 좌표를 갱신한다
 *     3-1. 일치한다면 순방향으로 저장한다
 *     3-2. 불일치한다면 역방향으로 저장한다
 *  4. 각각의 입력에 대해 해당하는 index로 접근한다
 */


package a2405.study.week14;

import java.io.*;
import java.util.*;

public class bj_g5_11067_모노톤길 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            SortedMap<Integer, ArrayList<Integer>> cafes = new TreeMap<>();
            int N = Integer.parseInt(br.readLine());
            int[][] result = new int[N][2];
            for(int n=0; n<N; n++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if(!cafes.containsKey(x)){
                    cafes.put(x, new ArrayList<>());
                }
                cafes.get(x).add(y);
            }
            int idx = 0;
            int finalY = 0;
            for(int x: cafes.keySet()){
                cafes.get(x).sort((o1, o2)->o1-o2);
                int size = cafes.get(x).size();
                if(cafes.get(x).get(0) == finalY){
                    for(int i=0; i<size; i++){
                        result[idx++] = new int[] {x, cafes.get(x).get(i)};
                    }
                }
                else{
                    for(int i=size-1; i>=0; i--){
                        result[idx++] = new int[] {x, cafes.get(x).get(i)};
                    }
                }
                finalY = result[idx-1][1];
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for(int i=0; i<M; i++){
                int num = Integer.parseInt(st.nextToken()) - 1;
                sb.append(result[num][0]).append(" ").append(result[num][1]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
