public class oneThread {
    public static void main(String[] args){
        Thread t = new Thread(new Runnable(){
            public void run(){
            System.out.println("Using Thread name: " + Thread.currentThread().getName());
            getPrimeNumbers(2, 1_000_000);
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
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
