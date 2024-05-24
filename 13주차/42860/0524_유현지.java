package a2405.study.week13;

public class pr_2_42860_조이스틱 {
    public int solution(String name) {
        int N = name.length();
        int answer = 0;

        boolean isA = false;
        int maxA = 0;
        int cntA = 0;
        int idxFrom = 0;
        int idxTo = 0;

        char[] nameArr = name.toCharArray();
        for(int i=0; i<N; i++){
            char n = nameArr[i];
            answer += 13 - Math.abs(13 - (n-'A'));

            if(n == 'A'){
                if(isA){
                    cntA++;
                }
                else{
                    isA = true;
                    cntA++;
                }
                if(i == N-1){
                    System.out.println(cntA);
                    if(maxA < cntA){
                        maxA = cntA;
                        idxFrom = i-cntA;
                        idxTo = i;
                    }
                }
            }
            else{
                if(isA){
                    System.out.println(cntA);
                    if(maxA < cntA){
                        maxA = cntA;
                        idxFrom = i-cntA;
                        idxTo = i;
                    }
                    isA = false;
                    cntA = 0;
                }
            }
        }
        System.out.println(idxFrom+" "+idxTo+" "+maxA);

        return answer;
    }
}
