import java.util.ArrayList;

public class twoThreads {
    public static void main(String[] args){
        start(2, 1_000_000);
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

    public static void start(int min, int max){
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 2; i++){
            int init = min + (max - min) / 2 * i;
            int end = min + (max - min) / 2 * (i + 1);
            Thread t = new Thread(new Runnable(){
                @Override
                public void run(){
                    getPrimeNumbers(init, end);
                }
            });
            threads.add(t);
        }
        for (Thread t : threads){
            t.setPriority(Thread.MAX_PRIORITY);
        }
        for (Thread t : threads){
            t.start();
        }

    }
}
