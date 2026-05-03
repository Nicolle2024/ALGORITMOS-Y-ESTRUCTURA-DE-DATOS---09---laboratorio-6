package actividad3;
import actividad1.ExceptionIsEmpty;

// 1. INTERFAZ PRIORITY QUEUE
public interface PriorityQueue<E, N> {
    void enqueue(E x, N pr);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
}


// 2. CLASE NODE
class Node<E> {
    private E data;
    private Node<E> next;
    public Node(E data) {
        this.data = data;
        this.next = null;
    }
    public E getData() {
        return data;
    }
    public Node<E> getNext() {
        return next;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
}

// 3. PRIORITY QUEUE LINK SORT
class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
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

    public void enqueue(E x, N pr) {
        EntryNode nuevo = new EntryNode(x, pr);
        Node<EntryNode> nodoNuevo = new Node<>(nuevo);

        if (isEmpty()) {
            first = nodoNuevo;
            last = nodoNuevo;
            return;
        }

        if (pr.compareTo(first.getData().priority) > 0) {
            nodoNuevo.setNext(first);
            first = nodoNuevo;
            return;
        }

        Node<EntryNode> actual = first;

        while (actual.getNext() != null &&
               actual.getNext().getData().priority.compareTo(pr) >= 0) {
            actual = actual.getNext();
        }

        nodoNuevo.setNext(actual.getNext());
        actual.setNext(nodoNuevo);

        if (nodoNuevo.getNext() == null) {
            last = nodoNuevo;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola de prioridad está vacía");

        E aux = this.first.getData().data;
        this.first = this.first.getNext();

        if (this.first == null)
            this.last = null;

        return aux;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola de prioridad está vacía");

        return first.getData().data;
    }

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

// 4. CLASE DE PRUEBA TEST
public class Test {

    public static void main(String[] args) {
        System.out.println("Prueba con Prioridad");

        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        pq.enqueue("Paciente Carlos", 2);
        pq.enqueue("Paciente Ana", 5);
        pq.enqueue("Paciente David", 1);
        pq.enqueue("Paciente liam", 5);
        pq.enqueue("Paciente Esteban", 3);

        System.out.println("Como queda la cola:");
        System.out.println(pq);

        try {
            System.out.println("\nPrimer valor ingresado tiene mayor prioridad: " + pq.front());
            System.out.println("ultimo valor ingresado tiene menor prioridad: " + pq.back());

            System.out.println("\norden de prioridad:");
            while (!pq.isEmpty()) {
                System.out.println("Eliminar el paciente atendido: " + pq.dequeue());
            }

            pq.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
