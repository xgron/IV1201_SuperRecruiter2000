package standalone;

public class StressTest {
    public static void main(String args[]) {
        Thread T1 = new Thread( "Thread-1");
        T1.start();

        Thread T2 = new Thread( "Thread-2");
        T2.start();

        Thread T3 = new Thread( "Thread-3");
        T3.start();
    }
}