public class noThreads{
    public static void main(String[] args){
        getPrimeNumbers(2, 2000000);
    }

    public static void getPrimeNumbers(int min, int max){
        for(int i = min; i <= max; i++){
            int count = 0;
            for(int j = 1; j <= i; j++){
                if(i % j == 0){
                    count++;
                }
            }
            if(count == 2){
                System.out.println(i);
            }
        }
    }

    
}
