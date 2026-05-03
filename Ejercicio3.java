package actividadEjercicio3;
import actividad1.ExceptionIsEmpty;

// 1. MULTILEVELPRIORITY QUEUE
public class MultiLevelPriorityQueue<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private static class SimpleQueue<E> {
        Node<E> front, rear;
        void enqueue(E x) {
            Node<E> n = new Node<>(x);
            if (rear == null) {
                front = rear = n;
            } else {
                rear.next = n;
                rear = n;
            }
        }

        E dequeue() throws ExceptionIsEmpty {
            if (isEmpty())
                throw new ExceptionIsEmpty("Cola vacía");
            E d = front.data;
            front = front.next;
            if (front == null) rear = null;
            return d;
        }

        boolean isEmpty() {
            return front == null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<E> cur = front;

            while (cur != null) {
                sb.append(cur.data);
                if (cur.next != null) sb.append(" - ");
                cur = cur.next;
            }
            return sb.length() == 0 ? "vacía" : sb.toString();
        }
    }
    private SimpleQueue<E>[] queues;
    private int levels;
    public MultiLevelPriorityQueue(int levels) {
        this.levels = levels;
        queues = new SimpleQueue[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new SimpleQueue<>();
        }
    }

    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels)
            throw new IllegalArgumentException("fuera de rango: " + priority);

        queues[priority].enqueue(x);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty())
                return queues[i].dequeue();
        }

        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public boolean isEmpty() {
        for (SimpleQueue<E> q : queues)
            if (!q.isEmpty())
                return false;

        return true;
    }

    public void printAll() {
        for (int i = levels - 1; i >= 0; i--) {
            System.out.println("Prioridad " + i + ": " + queues[i]);
        }
    }

    // MAIN
    public static void main(String[] args) throws ExceptionIsEmpty {
        System.out.println("Prioridad");
        MultiLevelPriorityQueue<String> pq = new MultiLevelPriorityQueue<>(3);
        pq.enqueue("flor", 0);
        pq.enqueue("ana", 2);
        pq.enqueue("Carlos", 1);
        pq.enqueue("Liam", 2);
        System.out.println("salida:");
        pq.printAll();
        System.out.println("\nOrden de salida:");
        while (!pq.isEmpty()) {
            System.out.print(pq.dequeue() + " ");
        }

        System.out.println();
    }
}
