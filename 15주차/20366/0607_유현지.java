/*
 * | 활용 자료구조 | Comparable Class
 *
 * | 접근 방법 |
 *  1. 두 눈덩이의 인덱스, 두 눈덩이의 높이의 합을 저장하는 Snowman 클래스를 만든다
 *     1-1. 눈덩이의 높이의 합을 기준으로 오름차순 정렬되도록 compareTo를 설정한다
 *  2. (1,2), (1,3), ..., (N-1, N)으로 눈덩이를 짝지어 Snowman 배열에 저장한다
 *  3. (1,2), (1,3), ..., ((N*(N-1)/2)-1, (N*(N-1)/2))로 Snowman을 짝지어 아래 과정을 수행한다
 *     3-1. 두 Snowman의 height 차이를 구한다
 *     3-1. 현재 최소값보다 작으면서 두 Snowman을 이룬 눈덩이가 겹치지 않는다면 최소값을 갱신한다
 */

package a2406.study.week15;

import java.io.*;
import java.util.*;

public class bj_g3_20366_같이_눈사람_만들래 {
    static class Snowman implements Comparable<Snowman>{
        int idx1;
        int idx2;
        int height;

        public Snowman(int idx1, int idx2, int height){
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.height = height;
        }

        @Override
        public int compareTo(Snowman o){
            return Integer.compare(height, o.height);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            arr[n] = Integer.parseInt(st.nextToken());
        }
        Snowman[] snowmans = new Snowman[N*(N-1)/2];
        int cnt = 0;
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                snowmans[cnt++] = new Snowman(i, j, arr[i]+arr[j]);
            }
        }
        Arrays.sort(snowmans);
        int gap = Integer.MAX_VALUE;
        for(int i=1; i<N*(N-1)/2; i++){
            if(snowmans[i].height-snowmans[i-1].height < gap && !usingSameSnowball(snowmans[i-1], snowmans[i])){
                gap = snowmans[i].height-snowmans[i-1].height;
            }
        }
        System.out.println(gap);
    }
    static boolean usingSameSnowball(Snowman s1, Snowman s2){
        return s1.idx1 == s2.idx1 || s1.idx1 == s2.idx2 || s1.idx2 == s2.idx1 || s1.idx2 == s2.idx2;
    }
}
