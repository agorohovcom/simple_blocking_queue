import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    private final Queue<T> queue = new LinkedList<>();

    private final int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private int count = 0;

    public SimpleBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
    }

    public SimpleBlockingQueue() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public synchronized int size() {
        return count;
    }

    // добавление элемента в очередь
    public synchronized void enqueue(T item) throws InterruptedException {
        while (count == capacity) {
            wait();
        }
        queue.add(item);
        count++;
        notifyAll();
    }

    // извлечение элемента из очереди
    public synchronized T dequeue() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        T item = queue.poll();
        count--;
        notifyAll();
        return item;
    }
}
