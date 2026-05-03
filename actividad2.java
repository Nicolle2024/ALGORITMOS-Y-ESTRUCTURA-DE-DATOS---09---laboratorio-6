// 1. INTERFAZ DEQUE
package actividad2;
import actividad1.ExceptionIsEmpty;

public interface Deque<E> {
    void addFirst(E x);
    void addLast(E x);
    E removeFirst() throws ExceptionIsEmpty;
    E removeLast() throws ExceptionIsEmpty;
    E getFirst() throws ExceptionIsEmpty;
    E getLast() throws ExceptionIsEmpty;
    boolean isEmpty();
}


// 2. CLASE NODE
package actividad2;
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

// 3. IMPLEMENTACIÓN DEQUE LINK
package actividad2; 
import actividad1.ExceptionIsEmpty;
class DequeLink<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;
    public DequeLink() {
        first = null;
        last = null;
    }
    public void addFirst(E x) {
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            first = nuevo;
            last = nuevo;
        } else {
            nuevo.setNext(first);
            first = nuevo;
        }
    }

    public void addLast(E x) {
        Node<E> nuevo = new Node<>(x);

        if (isEmpty()) {
            first = nuevo;
            last = nuevo;
        } else {
            last.setNext(nuevo);
            last = nuevo;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La estructura está vacía no se puede eliminar el primer elemento");
        E dato = first.getData();
        first = first.getNext();
        if (first == null)
            last = null;
        return dato;
    }

    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La estructura está vacía no se puede eliminar el último elemento");
        E dato = last.getData();
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node<E> actual = first;

            while (actual.getNext() != last) {
                actual = actual.getNext();
            }

            actual.setNext(null);
            last = actual;
        }

        return dato;
    }

    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La lista esta vacia al inicio");
        return first.getData();
    }

    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La lista esta vacia al final");
        return last.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        if (isEmpty()) return "Estructura vacía []";

        StringBuilder sb = new StringBuilder("[");
        Node<E> actual = first;

        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" - ");
            actual = actual.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}

// 4. CLASE MAIN (TEST DEQUE)
package actividad2; 
import actividad1.ExceptionIsEmpty;
public class TestDeque {
    public static void main(String[] args) {
        System.out.println("Prueba");
        DequeLink<String> deque = new DequeLink<>();
        deque.addLast("N");
        deque.addFirst("A");
        deque.addLast("J");
        deque.addLast("D");
        System.out.println("Después de agregar A,N,J,D: " + deque);
        try {
            System.out.println("Primer valor: " + deque.getFirst());
            System.out.println("Ultimo valor: " + deque.getLast());
            System.out.println("quitar el primer valor: " + deque.removeFirst());
            System.out.println("quitar el ultimo valor: " + deque.removeLast());
            System.out.println("salida: " + deque);
            deque.removeFirst();
            deque.removeFirst();
            System.out.println("Lista vacia: " + deque.isEmpty());
            deque.removeFirst();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
