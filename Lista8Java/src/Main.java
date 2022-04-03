public class Main {
    public static void main(String[] args) throws FullException, EmptyException {

        MyQueue<String> queue = new CyclicArrayQueue<>(3);
        // System.out.println(queue.first()); // EMPTY ERROR
        queue.enqueue("aa");
        queue.enqueue("bb");
        queue.enqueue("cc");
        System.out.println(queue.isFull());
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.isFull());
        System.out.println(queue.first());
        queue.enqueue("dd");
        System.out.println(queue.isFull());
        System.out.println(queue.first());
        // queue.enqueue("ee"); // FULL ERROR
        queue.dequeue();
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.isEmpty());



        MyQueue<Integer> queue2 = new CyclicArrayQueue<>(3);
        // queue2.first(); // EMPTY ERROR
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.isFull();
        queue2.first();
        queue2.dequeue();
        queue2.isFull();
        queue2.first();
        queue2.enqueue(4);
        queue2.isFull();
        queue2.first();
        // queue2.enqueue("ee"); // FULL ERROR
        queue2.dequeue();
        queue2.first();
        queue2.dequeue();
        queue2.first();
        queue2.dequeue();
        queue2.isEmpty();

    }
}
