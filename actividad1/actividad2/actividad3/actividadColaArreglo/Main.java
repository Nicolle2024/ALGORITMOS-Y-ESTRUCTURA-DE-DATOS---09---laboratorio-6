package actividadColaArreglo;

public class Main {
	    public static void main(String[] args) {
	        ColaArreglo cola = new ColaArreglo(5);

	        // 1. Encolar clientes iniciales
	        cola.encolar(101);
	        cola.encolar(102);
	        cola.encolar(103);
	        cola.encolar(104);
	        cola.encolar(105);

	        // 2. Intentar encolar uno más
	        cola.encolar(106);

	        // 3. Desencolar 2 clientes
	        cola.desencolar();
	        cola.desencolar();

	        // 4. Mostrar cliente al frente
	        cola.mostrarFrente();

	        // 5. Encolar 2 clientes más (106 y 107)
	        cola.encolar(106);
	        cola.encolar(107);

	        // 6. Desencolar todos hasta que quede vacía
	        while (!cola.estaVacia()) {
	            cola.desencolar();
	        }

	        // 7. Intentar desencolar uno extra
	        cola.desencolar();
	    }
	}