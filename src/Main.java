public class Main {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(5);

        // потоки-производители
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        simpleBlockingQueue.enqueue(j);
                        System.out.println("В очередь добавлено: " + j);
                        Thread.sleep((long) (Math.random() * 1000));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        // потоки-потребители
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 15; j++) {
                        Integer item = simpleBlockingQueue.dequeue();
                        System.out.println("Из очереди получено: " + item);
                        Thread.sleep((long) (Math.random() * 1000));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}