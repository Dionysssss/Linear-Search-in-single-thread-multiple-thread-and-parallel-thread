import java.util.concurrent.Semaphore;

public class Multi_linear extends Thread {
    public  static  int [] arr;
    public static int num_thread;
    //private static Semaphore semaphore = new Semaphore(num_thread);
    public static timer time;
    private int start;
    private int end;

    private int obj;

    public static int result = -1;


    public Multi_linear(int start, int end, int obj){
        this.start = start;
        this.end = end;
        this.obj = obj;
    }



    public void run(){
        for( int i = start; i < end; i++){
            if( arr[i] == obj){
                result = i;
                System.out.println("Multi-thread linear search finish time is "+time);
                System.out.println(i);
                Thread.yield();
            }
        }
    }

}
