package actividadEjercicio2;
import actividad1.ExceptionIsEmpty;

// CLASE COLA ARREGLO

class QueueArray {
    private int[] items;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.items = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int client) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % capacity;
        items[rear] = client;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int client = items[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Serving client: " + client);
        return client;
    }

    public void front() {
        if (size > 0) {
            System.out.println("Front client: " + items[front]);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


// CLASE MAIN (PRUEBA)

public class Main {
    public static void main(String[] args) {
        QueueArray queue = new QueueArray(5);
        queue.enqueue(101);
        queue.enqueue(102);
        queue.enqueue(103);
        queue.enqueue(104);
        queue.enqueue(105);
        queue.enqueue(106);
        queue.dequeue();
        queue.dequeue();
        queue.front();
        queue.enqueue(106);
        queue.enqueue(107);

        while (!queue.isEmpty()) {
            queue.dequeue();
        }

        queue.dequeue();
    }
}
