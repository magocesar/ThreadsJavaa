import java.util.ArrayList;

public class myThread {
    public static void main(String[] args) {
        int start = 1;
        int end = 7;
        int numThreads = 8;
        ArrayList<ThreadCalculaPrimo> threads = new ArrayList<>();
        for(int i = 0; i < numThreads; i++){
            int startThread;
            int endThread;
            if(i != numThreads - 1){
                startThread = start + (end / numThreads) * i;
                endThread = start + (end / numThreads) * (i + 1) - 1;
            }else{
                startThread = start + (end / numThreads) * i;
                endThread = end;
            }

            ThreadCalculaPrimo t = new ThreadCalculaPrimo(startThread, endThread);
            t.setPriority(Thread.MAX_PRIORITY);
            threads.add(t);
            t.start();
        }

        try{
            for(ThreadCalculaPrimo t : threads){
                t.join();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        int numPrimos = 0;
        for(ThreadCalculaPrimo t : threads){
            numPrimos += t.getNumPrimos();
        }

        System.out.println("Numero de primos: " + numPrimos);

    }
}

class ThreadCalculaPrimo extends Thread{
    private int start, end;
    private int numPrimos = 0;


    public ThreadCalculaPrimo(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        for(int i = start; i <= end; i++){
            if(isPrimo(i)){
                numPrimos++;
                System.out.println(i);
            }
        }
    }

    private boolean isPrimo(int n){
        if(n == 1){
            return false;
        }

        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public int getNumPrimos(){
        return numPrimos;
    }
}   