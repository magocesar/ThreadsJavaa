public class oneThread {
    public static void main(String[] args){
        Thread t = new Thread(new Runnable(){
            public void run(){
            System.out.println("Using Thread name: " + Thread.currentThread().getName());
            getPrimeNumbers(2, 500000);
            }
        });
        t.start();
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
