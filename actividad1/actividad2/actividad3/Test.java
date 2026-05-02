package actividad3;

import actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        System.out.println(" Prueba Cola de Prioridad ");

        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        // Insertamos con distintas prioridades
        pq.enqueue("Paciente C", 2);
        pq.enqueue("Paciente A", 5);
        pq.enqueue("Paciente D", 1);
        pq.enqueue("Paciente B", 5); // misma prioridad que A, va después
        pq.enqueue("Paciente E", 3);

        System.out.println("salida:");
        System.out.println(pq);

        try {
            System.out.println("\n mayor prioridad: " + pq.front());
            System.out.println(" menor prioridad: " + pq.back());

            System.out.println("orden de prioridad:");
            while (!pq.isEmpty()) {
                System.out.println(" ): " + pq.dequeue());
            }

            // intentar sacar de cola vacía
            pq.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
