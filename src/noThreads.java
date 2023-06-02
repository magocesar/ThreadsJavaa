public class noThreads{
    public static void main(String[] args){
        getPrimeNumbers(2, 1_000_000);
    }

    public static boolean isPrime(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void getPrimeNumbers(int min, int max){
        for(int i = min; i <= max; i++){
            if (isPrime(i)){
                System.out.println(i);
            }
        }
    }

    
}
