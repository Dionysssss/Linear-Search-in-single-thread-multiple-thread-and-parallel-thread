import java.util.concurrent.RecursiveTask;

public class Parallel_linear extends RecursiveTask<Integer> {

    public static int[] arr;

    public static timer time;

    private int min;
    private int max;

    private int obj;

    public Parallel_linear(int start, int end, int obj){
        this.min = start;
        this.max = end;
        this.obj = obj;
    }

    protected Integer compute(){
        for(int i = min; i < max; i++){
            if(arr[i] == obj){
                System.out.println("the Parallel_linear search finish time is"+time);
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }
}
