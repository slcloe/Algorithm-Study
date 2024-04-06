import java.io.*;
import java.util.*;

public class BOJ_3151 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int arr[] = new int[T];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long cnt = 0;
        for (int i = 0; i < T; i++) {
            if(arr[i] > 0)break;
            int left = i+1;
            int right = T-1;

            while(left<right){
                int l = 1;
                int r = 1;
                int sum = arr[i] + arr[left] + arr[right];

                // 1. 같을때
                if(sum ==0){
                    if(arr[left] == arr[right]){
                        int range = right - left + 1;
                        cnt += (range*(range-1))/2;
                        break;
                    }
                    while(arr[left] == arr[left+1] && left+1<right ){
                        l++;
                        left++;
                    }
                    while(arr[right] == arr[right-1] && left<right-1){
                        r++;
                        right--;
                    }
                    cnt += l*r;
                }
                if(sum > 0) right --;
                else left ++;
            }
        }

        System.out.println(cnt);
    }
}
