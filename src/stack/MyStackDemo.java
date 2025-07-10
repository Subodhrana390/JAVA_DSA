package stack;

public class MyStackDemo {

    static int top;
    int[] arr;
    int size;

    public MyStackDemo(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    public static void main(String[] args) {
        MyStackDemo stackDemo = new MyStackDemo(5);
        stackDemo.push(10);
        stackDemo.push(20);
        stackDemo.push(30);
        stackDemo.push(40);
        stackDemo.push(50);
        System.out.println("After push");
        stackDemo.display();
        stackDemo.pop();
        System.out.println("After pop");
        stackDemo.display();
        System.out.println(stackDemo.peak());
    }

    void push(int data) {
        if (top == size - 1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            arr[top] = data;
        }
    }

    void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
        } else {
            top--;
        }
    }

    void display() {
        for (int i = 0; i <= top; i++) {
            System.out.println(arr[i]);
        }
    }

    int peak() {
        if (top == -1) {
            return -1;
        } else {
            return arr[top];
        }
    }

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == size - 1;
    }

}
