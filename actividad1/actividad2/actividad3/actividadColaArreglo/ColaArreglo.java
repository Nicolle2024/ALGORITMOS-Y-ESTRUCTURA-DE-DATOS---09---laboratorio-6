package actividadColaArreglo;

class ColaArreglo {
    private int[] items;
    private int frente;
    private int finalCola;
    private int tamanoActual;
    private int capacidad;

    public ColaArreglo(int capacidad) {
        this.capacidad = capacidad;
        this.items = new int[capacidad];
        this.frente = 0;
        this.finalCola = -1;
        this.tamanoActual = 0;
    }

    public void encolar(int cliente) {
        if (tamanoActual == capacidad) {
            System.out.println("Cola llena");
            return;
        }
        // Incremento circular del puntero final
        finalCola = (finalCola + 1) % capacidad;
        items[finalCola] = cliente;
        tamanoActual++;
    }

    public int desencolar() {
        if (tamanoActual == 0) {
            System.out.println("Cola vacía");
            return -1;
        }
        int cliente = items[frente];
        // Incremento circular del puntero frente
        frente = (frente + 1) % capacidad;
        tamanoActual--;
        System.out.println("Atendiendo cliente: " + cliente);
        return cliente;
    }

    public void mostrarFrente() {
        if (tamanoActual > 0) {
            System.out.println("Cliente en frente: " + items[frente]);
        }
    }

    public boolean estaVacia() {
        return tamanoActual == 0;
    }
}

