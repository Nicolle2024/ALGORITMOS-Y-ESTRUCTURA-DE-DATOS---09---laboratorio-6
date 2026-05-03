package actividadEjercicio4;
import actividad1.ExceptionIsEmpty;


// 1.  ColaPrioridadHibrida

public class ColaPrioridadHibrida<E, V extends Comparable<V>> {
    private class Entrada {
        E dato;
        V valor;
        Entrada(E dato, V valor) {
            this.dato = dato;
            this.valor = valor;
        }
        @Override
        public String toString() {
            return "(" + dato + "," + valor + ")";
        }
    }
    private class Node {
        Entrada entrada;
        Node siguiente;

        Node(Entrada e) {
            this.entrada = e;
        }
    }

    private class ColaOrdenada {
        Node inicio;
        void enqueue(E dato, V valor) {
            Entrada e = new Entrada(dato, valor);
            Node nuevo = new Node(e);
            if (inicio == null || valor.compareTo(inicio.entrada.valor) < 0) {
                nuevo.siguiente = inicio;
                inicio = nuevo;
                return;
            }

            Node actual = inicio;

            while (actual.siguiente != null &&
                   actual.siguiente.entrada.valor.compareTo(valor) <= 0) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }

        E dequeue() throws ExceptionIsEmpty {
            if (estaVacia())
                throw new ExceptionIsEmpty("Cola vacía");

            E d = inicio.entrada.dato;
            inicio = inicio.siguiente;
            return d;
        }

        boolean estaVacia() {
            return inicio == null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node actual = inicio;
            while (actual != null) {
                sb.append(actual.entrada);
                if (actual.siguiente != null) sb.append(" - ");
                actual = actual.siguiente;
            }
            return sb.length() == 0 ? "vacía" : sb.toString();
        }
    }

    private ColaOrdenada[] niveles;
    private int numNiveles;
    public ColaPrioridadHibrida(int numNiveles) {
        this.numNiveles = numNiveles;
        niveles = new ColaPrioridadHibrida.ColaOrdenada[numNiveles];

        for (int i = 0; i < numNiveles; i++) {
            niveles[i] = new ColaOrdenada();
        }
    }

    public void enqueue(E dato, int prioridad, V valor) {
        if (prioridad < 0 || prioridad >= numNiveles)
            throw new IllegalArgumentException("fuera de rango");

        niveles[prioridad].enqueue(dato, valor);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = numNiveles - 1; i >= 0; i--) {
            if (!niveles[i].estaVacia()) {
                return niveles[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public boolean estaVacia() {
        for (ColaOrdenada cola : niveles) {
            if (!cola.estaVacia()) return false;
        }
        return true;
    }

    public void mostrarTodo() {
        for (int i = numNiveles - 1; i >= 0; i--) {
            System.out.println("Prioridad " + i + ": " + niveles[i]);
        }
    }

    // 2. MAIN

    public static void main(String[] args) throws ExceptionIsEmpty {
        System.out.println("Prioridad Híbrida");
        ColaPrioridadHibrida<String, Integer> cola = new ColaPrioridadHibrida<>(3);

        cola.enqueue("Ana", 2, 5);
        cola.enqueue("Beto", 2, 1);
        cola.enqueue("Carlos", 1, 3);
        cola.enqueue("Flor", 2, 3);

        System.out.println("Salida:");
        cola.mostrarTodo();

        System.out.println("\nOrden de atención:");
        while (!cola.estaVacia()) {
            System.out.print(cola.dequeue() + " ");
        }

        System.out.println();
    }
}
