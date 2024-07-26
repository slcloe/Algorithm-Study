/*
 * | 활용 알고리즘 | BFS, BitMask
 *
 * | 접근 방법 |
 *  1. BFS를 돌며 각각의 영역을 방의 번호로 표시한다
 *     1-1. 벽이 있는지 여부는 비트마스킹으로 판별한다
 *     1-2. 한 구역의 BFS가 끝나면 {방 번호: 방 크기}로 Map에 저장한다
 *     1-3. 최대 방 크기를 갱신한다
 *  2. 모든 좌표를 돌며 벽 하나가 없을 때의 경우를 센다
 *     2-1. 사방 탐색을 하며 대상 방과 탐색 방의 번호가 다를 경우 두 방 크기를 합치고 최대값을 갱신한다
 */

package a2407.study.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class bj_g3_19535_ㄷㄷㄷㅈ {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int n=0; n<N-1; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
        }
    }

}
