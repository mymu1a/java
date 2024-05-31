package artur.maschenko.dateconverter.component;

public class RequestCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}