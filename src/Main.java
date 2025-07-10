public class Main {

    public static void main(String[] args) {
//        innerDeque deque = new innerDeque(10);
//        deque.pushFront(10);
//        deque.pushFront(20);
//        deque.pushFront(30);
//        deque.pushFront(40);
//        deque.pushFront(50);
//        deque.pushRear(60);
//        deque.pushRear(70);
//        deque.pushRear(80);
//        deque.pushRear(90);
//        deque.display();
//        deque.popFront();
//        deque.popRear();
//        deque.popFront();
//        deque.popRear();
//        deque.display();

        innerCircularQueue queue = new innerCircularQueue(10);
        queue.enque(10);
        queue.enque(20);
        queue.enque(40);
        queue.enque(30);
        queue.enque(50);
        queue.enque(60);
        queue.enque(70);
        queue.enque(80);
        queue.enque(90);
        queue.enque(100);
        queue.enque(110);


    }

    static class innerDeque {
        int[] arr;
        int front;
        int rear;
        int size;
        int count; // to track the number of elements in the deque

        public innerDeque(int n) {
            size = n;
            arr = new int[size];
            front = -1;
            rear = -1;
            count = 0;
        }

        void pushFront(int data) {
            if (count == size) { // Deque is full
                System.out.println("Queue is full");
                return;
            }
            if (front == -1) { // First element added
                front = rear = 0;
            } else if (front == 0) { // Wrap around to the end
                front = size - 1;
            } else {
                front--;
            }
            arr[front] = data;
            count++;
        }

        void pushRear(int data) {
            if (count == size) { // Deque is full
                System.out.println("Queue is full");
                return;
            }
            if (rear == -1) { // First element added
                front = rear = 0;
            } else if (rear == size - 1) { // Wrap around to the start
                rear = 0;
            } else {
                rear++;
            }
            arr[rear] = data;
            count++;
        }

        void popFront() {
            if (count == 0) { // Deque is empty
                System.out.println("Queue is empty");
                return;
            }
            if (front == rear) { // Single element being removed
                front = rear = -1;
            } else if (front == size - 1) { // Wrap around to the start
                front = 0;
            } else {
                front++;
            }
            count--;
        }

        void popRear() {
            if (count == 0) { // Deque is empty
                System.out.println("Queue is empty");
                return;
            }
            if (front == rear) { // Single element being removed
                front = rear = -1;
            } else if (rear == 0) {
                rear = size - 1;
            } else {
                rear--;
            }
            count--;
        }

        void display() {
            if (count == 0) { // Deque is empty
                System.out.println("Queue is empty");
                return;
            }
            System.out.println("Deque elements:");
            int i = front;
            for (int c = 0; c < count; c++) {
                System.out.println(arr[i]);
                i = (i + 1) % size;
            }
        }
    }

    static class innerCircularQueue {
        int[] arr;
        int front;
        int rear;
        int size;

        public innerCircularQueue(int n) {
            size = n;
            arr = new int[size];
            front = -1;
            rear = -1;
        }

        void enque(int data) {
            if ((front == 0 && rear == size - 1) && (rear == (front - 1 + size) % size)) {
                System.out.println("Queue is full");
            } else if (front == -1) {
                front = rear = 0;
                arr[rear] = data;
            } else if (rear == size - 1 && front != 0) {
                rear = 0;
                arr[rear] = data;
            } else
                rear++;
            arr[rear] = data;
        }

        void deque() {
            if (front == -1) {
                System.out.println("Queue is empty");
            } else if (front == rear) {
                front = rear = -1;
            } else if (front == (rear + 1) % size) {
                front = 0;
            } else
                front++;
        }

    }
}