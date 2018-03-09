package standalone;

public class StressTest {
    public static void main(String args[]) {
        StressThread T1 = new StressThread( "Thread_1");
        T1.start();

        StressThread T2 = new StressThread( "Thread_2");
        T2.start();

        StressThread T3 = new StressThread( "Thread_3");
        T3.start();
    }
}