import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class timer {

    public static void main(String args[]) throws InterruptedException {

        timer test = new timer();
        Thread.sleep(1000);
        System.out.println(test.timeFromStart());
        System.out.println(test);

    }
    private long startTime;
    private DateFormat simple;
    public timer(){
        this.simple = new SimpleDateFormat("H:mm:ss:SSS");
        startTime = getPresentInMill();
    }

    public long timeFromStart()
    {
        return  getPresentInMill() - startTime;
    }


    public String toString(){
        long dur = getPresentInMill() - startTime;
        return "["+printTime(dur)+"]";
    }

    public static long getPresentInMill(){
        Calendar cal = Calendar.getInstance();
        //System.out.println("calendar = "+cal.getTimeInMillis());
        return cal.getTimeInMillis();
    }

    public String printTime(long milliSec){



        simple.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(milliSec);
        // Formatting Date according to th
        // given format
        return simple.format(result);
//             System.out.println(simple.format(result));

    }

}