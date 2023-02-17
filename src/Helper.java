import java.util.Random;
import java.util.concurrent.*;

public class Helper {
    public static void main(String[] args) {
        Random rand = new Random();
        int num = 100000000;
        int arr[] = new int[num];
        for(int i = 0; i < num; i++){
            arr[i] = i;
        }
        for(int i = 0; i < num/2; i++){
            //shuffle
            if(rand.nextBoolean())
            //swap
            {
                int temp = arr[i];
                arr[i] = arr[num-1-i];
                arr[i] = temp;
            }
        }

        int num1 = rand.nextInt(100000000);
        int num2 = 100_000_000;

        Single_linear f1 = new Single_linear(arr );
        System.out.println(f1.find(num1));
        System.out.println(f1.find(num2));

        int numOfThread = 4;
        Multi_linear.arr = arr;
        Multi_linear.num_thread = numOfThread;
        Multi_linear.time = new timer();
        ExecutorService executor1 = Executors.newCachedThreadPool();
        int partLen = num/numOfThread;
        for(int i=0; i<4; i++){
            executor1.execute(new Multi_linear(partLen*i, partLen*(i+1), num1));
        }

        executor1.shutdown();

        while(!executor1.isTerminated()){
            Thread.yield();
        }
        if( executor1.isTerminated()){

            if(Multi_linear.result == -1 ){
                System.out.println("not found the num. The complete time is "+Multi_linear.time);
            }else{
                Multi_linear.result = -1;
            }
        }



        ExecutorService executor2 = Executors.newCachedThreadPool();
        Multi_linear.time = new timer();
        for(int i=0; i<4; i++){
            executor2.execute(new Multi_linear(partLen*i, partLen*(i+1), num2));
        }

        executor2.shutdown();
        while(!executor2.isTerminated()){
            Thread.yield();
        }
        if(executor2.isTerminated()){
            if(Multi_linear.result == -1 ){
                System.out.println("not found the num. The complete time is "+Multi_linear.time);
            }else{
                Multi_linear.result = -1;
            }
        }



        ForkJoinPool pool = new ForkJoinPool();
        Parallel_linear[] find = new Parallel_linear[numOfThread];
        Parallel_linear.arr = arr;

        Parallel_linear.time = new timer();
        for(int i=0; i<numOfThread; i++){
            find[i] = new Parallel_linear(partLen*i, partLen*(i+1), num1);
            pool.execute(find[i]);
        }

        boolean hasResult = false;
        for(int i=0; i<numOfThread; i++){
            if(find[i].join() != -1){
                hasResult = true;
            }
        }
        if(!hasResult){
            System.out.println("the Parallel_linear search didn't find result");
            System.out.println("the Parallel_linear search finish time is"+Parallel_linear.time);

        }

        for(int i=0; i<numOfThread; i++){
            find[i] = new Parallel_linear(partLen*i, partLen*(i+1), num2);
            pool.execute(find[i]);
        }

        hasResult = false;
        for(int i=0; i<numOfThread; i++){
            if(find[i].join() != -1){
                hasResult = true;
            }
        }
        if(!hasResult){
            System.out.println("the Parallel_linear search didn't find result");
            System.out.println("the Parallel_linear search finish time is"+Parallel_linear.time);

        }





    }
}