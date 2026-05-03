
// 1. INTERFAZ QUEUE
package actividad1;

public interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    boolean isEmpty();
}



// 2. EXCEPCIÓN ExceptionIsEmpty
package actividad1;

public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}



// 3. IMPLEMENTACIÓN QueueArray
package actividad1;

public class QueueArray<E> implements Queue<E> {

    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    // índice circular
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("La cola está llena, no se puede agregar: " + x);
            return;
        }
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola está vacía, no se puede eliminar");

        E elem = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return elem;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola está vacía");
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public String toString() {
        if (isEmpty()) return "Cola vacía []";

        StringBuilder sb = new StringBuilder("Cola [");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % array.length;
            sb.append(array[idx]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}



// 4. CLASE DE PRUEBA TestQueue
package actividad1;

public class TestQueue {

    public static void main(String[] args) {
        System.out.println(" Prueba con Integer ");
        testConEnteros();

        System.out.println("\n Prueba con String ");
        testConStrings();
    }

    static void testConEnteros() {
        QueueArray<Integer> cola = new QueueArray<>(5);

        cola.enqueue(33);
        cola.enqueue(48);
        cola.enqueue(75);

        System.out.println("Cola con los elementos agregados: " + cola);

        try {
            System.out.println("Primer elemento: " + cola.front());

            System.out.println("Eliminar: " + cola.dequeue());

            System.out.println("Como queda la cola: " + cola);

            cola.enqueue(49);
            cola.enqueue(3);
            cola.enqueue(11);

            System.out.println("Elementos nuevos agregados: " + cola);

            cola.enqueue(99);

            while (!cola.isEmpty()) {
                System.out.println(" " + cola.dequeue());
            }

            cola.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción la cola esta vacia no se puede eliminar " + e.getMessage());
        }
    }

    static void testConStrings() {
        QueueArray<String> cola = new QueueArray<>(4);

        cola.enqueue("Max");
        cola.enqueue("Pedro");
        cola.enqueue("Flor");

        System.out.println("Inicio: " + cola);

        try {
            System.out.println("Primer valor: " + cola.front());

            cola.dequeue();
            cola.enqueue("Carlos");

            System.out.println("Salida: " + cola);

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción la cola esta vacia no se puede eliminar " + e.getMessage());
        }
    }
}


