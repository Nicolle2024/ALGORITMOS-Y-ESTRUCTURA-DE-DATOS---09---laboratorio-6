package actividadColaArreglo;

import actividad1.ExceptionIsEmpty;

@SuppressWarnings("unchecked")
public class QueueArray<E> implements Queue<E> {

    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int capacidad) {
        array = (E[]) new Object[capacidad];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(E x) {
        // si el arreglo está lleno, avisamos pero no lanzamos excepción
        if (size == array.length) {
            System.out.println("Cola llena, no se pudo agregar: " + x);
            return;
        }
        // usamos índice circular para aprovechar el espacio liberado por dequeue
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("No se puede desencolar, la cola está vacía");
        E elemento = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return elemento;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola está vacía, no hay elemento al frente");
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) return "Cola []";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int indice = (front + i) % array.length;
            sb.append(array[indice]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]  (front=" + front + ", rear=" + rear + ", size=" + size + ")");
        return sb.toString();
    }
}
