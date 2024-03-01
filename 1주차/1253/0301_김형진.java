package week1;

import java.io.*;
import java.util.*;

//좋다
public class BOJ1253 {
    public static void main(String[] args) throws IOException {
        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        for (int i = 0; i < N; i++) {
            int current = nums[i];
            int left  = 0;
            int right = N-1;

            while(left<right){
                if(left == i){
                    left ++;
                    continue;
                }
                if(right ==i){
                    right--;
                    continue;
                }
                if(nums[left]+nums[right] < current){
                    left ++;
                    continue;
                }
                if(nums[left]+nums[right]> current) {
                    right--;
                    continue;
                }
                result ++;
                break;
            }
        }

        System.out.println(result);
    }
}
