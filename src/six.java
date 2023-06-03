
import java.util.Random;
import java.util.ArrayList;

public class six{
    static volatile boolean achouSenha = false;
    static volatile boolean policialChegou = false;

    public static void main(String[] args){
        int start;
        int end;
        int numLadrao = 2;
        safe safe = new safe();
        ArrayList<Ladrao> ladroes = new ArrayList<Ladrao>();
        //Maior numero possivel da senha = 9999
        for(int i = 0; i < numLadrao; i++){
            if(i != numLadrao - 1){
                 start = (i * 9999) / numLadrao;
                 end = ((i + 1) * 9999) / numLadrao;
            }else{
                start = (i * 9999) / numLadrao;
                end = 9999;
            }
            ladroes.add(new Ladrao(Integer.toString(i), safe, start, end));
        }

        for (Ladrao ladrao : ladroes) {
            ladrao.start();
        }

        Policial policial = new Policial(10);
        policial.start();

    }
}

class safe{
    private int password;

    public safe(){
        this.password = new Random().nextInt(9999);
    }

    public int getPassword(){
        return this.password;
    }
}

class Ladrao extends Thread{
    private String name;
    private safe safe;
    private int start;
    private int end;

    public Ladrao(String name, safe safe, int start, int end){
        this.name = name;
        this.safe = safe;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        while(!six.achouSenha && !six.policialChegou){
            for(int i = start; i < end; i++){
                try{
                    Thread.sleep(2);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(six.achouSenha || six.policialChegou){
                    break;
                }
                //System.out.println("Ladrao " + name + " tentando senha: " + i);
                if(i == safe.getPassword()){
                    System.out.println("Ladrao " + name + " achou a senha: " + i);
                    six.achouSenha = true;
                    break;
                }
            }
        }
        if(!six.achouSenha && six.policialChegou){
            System.out.println("Ladrao " + name + " foi pego pelo policial");
        }
    }
}

class Policial extends Thread{
    private int tempoResposta;

    public Policial(int tempoResposta){
        this.tempoResposta = tempoResposta;
    }

    @Override
    public void run(){
        while(!six.achouSenha && !six.policialChegou){
            for(int i = tempoResposta; i > 0; i--){
                if(six.achouSenha || six.policialChegou){
                    break;
                }
                System.out.println("Policial chegando em " + i + " segundos...");
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(!six.achouSenha && !six.policialChegou){
                System.out.println("Policial chegou");
                six.policialChegou = true;
            }
        }
    }
}