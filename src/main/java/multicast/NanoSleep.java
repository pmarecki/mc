package multicast;

import static java.lang.System.nanoTime;

public class NanoSleep {
    public static void waitMicroseconds(long delay) {
        delay *= 1000;
        long ss = nanoTime();
        while(ss + delay >= nanoTime());
    }
    public static void main(String[] args) {
        long st = nanoTime();
        int REP = 10000;
        int WAIT_MICROS = 2;
        for (int i = 0; i < REP; i++) {
            waitMicroseconds(WAIT_MICROS);
        }
        System.out.println((nanoTime()-st)/REP/1000);  //how many nanoseconds slept (should be WAIT_MICROS)
    }
}