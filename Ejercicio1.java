package actividadPilaLista;
import actividad1.ExceptionIsEmpty;

// 1. INTERFAZ STACK
public interface Stack<E> {
    void push(E x);
    E pop() throws ExceptionIsEmpty;
    E top() throws ExceptionIsEmpty;
    boolean isEmpty();
}

// 2. CLASE NODE
package actividadPilaLista;
public class Node<E> {
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

// 3. STACK LINK
package actividadPilaLista;
import actividad1.ExceptionIsEmpty;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;
    public StackLink() {
        this.top = null;
    }
    public void push(E x) {
        Node<E> nuevo = new Node<>(x);
        nuevo.setNext(top);
        top = nuevo;
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La pila está vacía no se puede hacer eliminar");

        E dato = top.getData();
        top = top.getNext();
        return dato;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La pila está vacía, no hay tope");

        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String toString() {
        if (isEmpty()) return "Pila vacía []";

        StringBuilder sb = new StringBuilder("Final: [");
        Node<E> actual = top;

        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(", ");
            actual = actual.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}

// 4. CLASE DE PRUEBA
package actividadPilaLista;
import actividad1.ExceptionIsEmpty;
public class TestStackLink {
    public static void main(String[] args) {
        System.out.println("Prueba");
        StackLink<Integer> pila = new StackLink<>();
        pila.push(33);
        pila.push(11);
        pila.push(55);
        pila.push(18);
        System.out.println("elementos agregados: " + pila);

        try {
            System.out.println("elemento que esta arriba de la lista: " + pila.top());
            System.out.println("elimina el elemento que esta en el tope: " + pila.pop());
            System.out.println("elimina el elemento que esta en el tope: " + pila.pop());
            System.out.println("final: " + pila);

            pila.push(99);
            System.out.println("Se agrego un nuevo valor: " + pila);

            while (!pila.isEmpty()) {
                System.out.println("eliminar: " + pila.pop());
            }

            pila.pop();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
