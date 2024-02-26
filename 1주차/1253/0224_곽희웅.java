import java.io.*;
import java.util.*;
public class 0224_곽희웅 {

    /*
    접근 방식 : 절대값 10억의 경우에서 두 수를 더했을 경우 20억이면 int형을 벗어나지 않을 것이라고 고려
    자료 구조 : int 배열을 투 포인터 형식으로 문제 해결

    주안점
    1. 절대값 10억 범위이기 때문에 음수와 0을 포함한 범위 고려
    2. 자신을 제외한 두 수이기 때문에 처음 left, right를 정할 때와 포인터의 위치를 조정할 때 수식 추가
    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int count = 0;
        for(int i=0; i<N; i++) {
            int target = nums[i];
            int left = i == 0 ? 1 : 0;
            int right = i == N-1 ? N-2 : N-1;
            while(left < right) {
                if(nums[left] + nums[right] == target) {
                    count++;
                    break;
                } else if(nums[left] + nums[right] > target) {
                    if(right - 1 != i) right--;
                    else right -= 2;
                } else {
                    if(left + 1 != i) left++;
                    else left += 2;
                }
            }
        }
        System.out.println(count);
    }
}
