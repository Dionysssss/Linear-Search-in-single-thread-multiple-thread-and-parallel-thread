public class Single_linear {
    int[] arr;


    timer time;
    public Single_linear(int[] arr){
        this.arr = arr;
        time = new timer();
    }

    public int find(int obj){

        for( int i = 0; i < arr.length; i++){
            if(arr[i] == obj){
                System.out.println("the linear search process time is "+time);
                return i;
            }
        }
        System.out.println("No result; the linear search process time is "+time);
        return -1;
    }

}
