/*
 * | 활용 알고리즘 | 중복 순열
 *
 * | 접근 방법 |
 *  1. 이모티콘 할인률에 대한 중복 순열을 만든다
 *  2. 순열이 완성될 때마다 아래 과정을 반복한다
 *     2-1. 각 유저에 대해 이모티콘을 살지 / 이모티콘 플러스에 가입할지 구한다
 *     2-2. 결과에 따라 최대값을 갱신한다
 */

package a2405.study.week14;

public class pr_2_150368_이모티콘_할인행사 {
    static int lenUser, lenEmoticons, answerUser = 0, answerPrice = 0;
    static int[] perm, percentages = { 10, 20, 30, 40 };

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        lenUser = users.length;
        lenEmoticons = emoticons.length;
        perm = new int[lenEmoticons];
        makePerm(users, emoticons, 0);
        answer = new int[] {answerUser, answerPrice};

        return answer;
    }

    static void makePerm(int[][] users, int[] emoticons, int cnt){
        if(cnt == lenEmoticons){
            int totalUser = 0;
            int totalPrice = 0;
            int personalTotalPrice = 0;
            for(int[] user: users){
                boolean isPlus = false;
                personalTotalPrice = 0;
                for(int i=0; i<lenEmoticons; i++){
                    int percentage = perm[i];
                    if(user[0] <= percentage){
                        personalTotalPrice += ((emoticons[i] * (100-percentage))/100);
                        if(user[1] <= personalTotalPrice){
                            isPlus = true;
                            totalUser++;
                            break;
                        }
                    }
                }
                if(!isPlus){
                    totalPrice += personalTotalPrice;
                }
                if(answerUser < totalUser){
                    answerUser = totalUser;
                    answerPrice = totalPrice;
                }
                else if(answerUser == totalUser){
                    answerPrice = Math.max(answerPrice, totalPrice);
                }
            }
            return;
        }
        for(int i=0; i<4; i++){
            perm[cnt] = percentages[i];
            makePerm(users, emoticons, cnt+1);
        }
    }
}
