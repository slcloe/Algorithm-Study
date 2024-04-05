/*
 * | 활용 알고리즘 | Two Pointers
 *
 * | 접근 방법 |
 *  1. 1~N-2까지 타겟 인덱스를 잡는다
 *  2. 타겟 인덱스~N까지 투포인터로 두 수를 더해 타겟 인덱스에 있는 값의 음수인지 확인한다
 */

package a2403.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_g4_3151_합이_0 {
    static int N;
    static int[] scores;

    public static void main(String[] args) throws Exception{
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scores = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){ scores[n] = Integer.parseInt(st.nextToken()); }
        Arrays.sort(scores);

        for(int i=0; i<N-2; i++){
            int target = -scores[i];
            int idxS = i+1, idxE = N-1;
            while(idxS < idxE){
                int sum = scores[idxS] + scores[idxE];
                if(sum == target){
                    if(scores[idxS] == scores[idxE]){
                        int diff = idxE - idxS + 1;
                        answer += ((long) diff * (diff-1))/2;
                        break;
                    }
                    else{
                        long sumS = 1, sumE = 1;
                        while(scores[idxS] == scores[idxS+1] && idxS+1 < idxE){
                            idxS++;
                            sumS++;
                        }
                        while(scores[idxE] == scores[idxE-1] && idxS < idxE-1){
                            idxE--;
                            sumE++;
                        }
                        answer += (sumS * sumE);
                    }
                    idxS++;
                    idxE--;
                }
                else if(sum < target){ idxS++; }
                else{ idxE--; }
            }
        }
        System.out.println(answer);
    }
}