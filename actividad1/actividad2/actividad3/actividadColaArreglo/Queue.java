package actividadColaArreglo;

import actividad1.ExceptionIsEmpty;

// Reutilizamos la interfaz Queue del paquete actividad1
// pero la redefinimos aqui para no tener isFull
public interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    boolean isEmpty();
}
