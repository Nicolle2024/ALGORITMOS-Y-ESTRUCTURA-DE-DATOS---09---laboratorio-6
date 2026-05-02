package actividad3;

import actividad1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    // Nodo interno que guarda el dato y su prioridad
    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }

        public String toString() {
            return "(" + data + ", p=" + priority + ")";
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    // Inserta manteniendo el orden: mayor prioridad al inicio
    public void enqueue(E x, N pr) {
        EntryNode nuevo = new EntryNode(x, pr);
        Node<EntryNode> nodoNuevo = new Node<>(nuevo);

        if (isEmpty()) {
            // lista vacía, simplemente agregamos
            first = nodoNuevo;
            last = nodoNuevo;
            return;
        }

        // Si la prioridad es mayor que el primero, va al inicio
        if (pr.compareTo(first.getData().priority) > 0) {
            nodoNuevo.setNext(first);
            first = nodoNuevo;
            return;
        }

        // Buscamos la posición donde insertar
        Node<EntryNode> actual = first;
        while (actual.getNext() != null &&
               actual.getNext().getData().priority.compareTo(pr) >= 0) {
            actual = actual.getNext();
        }

        // Insertamos después de 'actual'
        nodoNuevo.setNext(actual.getNext());
        actual.setNext(nodoNuevo);

        // Si quedó al final, actualizamos last
        if (nodoNuevo.getNext() == null) {
            last = nodoNuevo;
        }
    }

    // Elimina el de mayor prioridad (siempre el primero)
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola de prioridad está vacía");
        E aux = this.first.getData().data;
        this.first = this.first.getNext();
        if (this.first == null)
            this.last = null;
        return aux;
    }

    // Retorna el dato con mayor prioridad
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola de prioridad está vacía");
        return first.getData().data;
    }

    // Retorna el dato con menor prioridad (último)
    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola de prioridad está vacía");
        return last.getData().data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        if (isEmpty()) return "PriorityQueue vacía []";
        StringBuilder sb = new StringBuilder("PriorityQueue [");
        Node<EntryNode> actual = first;
        while (actual != null) {
            sb.append(actual.getData().toString());
            if (actual.getNext() != null) sb.append(" -> ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
