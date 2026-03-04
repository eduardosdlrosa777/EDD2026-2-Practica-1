import java.util.Scanner;
import java.util.InputMismatchException;

public class Interfaz {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int respuesta = 2;
        boolean datoValido = false;
        Tablero tablero = new Tablero(); // Instancia de la lógica del juego
        int opcion = 1;
        
        System.out.println("Bienvenido a Picross!!!\nIngresa el nombre del jugador:");
        String jugador = input.nextLine();

        // --- MENÚ INICIAL ---
        // Bucle para asegurar que el usuario elija una opción válida al arrancar
        while (!datoValido) {
            try {
                System.out.println("Te gustaria empezar a jugar? \n1) Si.\n2) No, salir.");
                respuesta = input.nextInt();
                if (respuesta == 1 || respuesta == 2) {
                    datoValido = true;
                } else {
                    System.out.println("Por favor, elige 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opcion valida");
                input.nextLine(); // Limpia el buffer del scanner
            }
        }
        datoValido = false; // Reseteo para validaciones posteriores
        
        // --- BUCLE PRINCIPAL DE JUEGO ---
        if(respuesta == 1) {
            tablero.reiniciarTablero(); // Limpia la cuadrícula del jugador
            tablero.imprimirTablero();  // Muestra el tablero inicial con pistas
            
            // El juego sigue mientras el tablero actual no sea igual a la solución
            while(!tablero.compararTablero()) {

                // Validación de entrada para la FILA
                while(!datoValido){
                    try {
                        System.out.print("Fila: ");
                        respuesta = input.nextInt();
                        if ( respuesta >= 1 && respuesta <= 5 ) {
                            datoValido = true;
                        } else {
                            System.out.println("Por favor, elige un numero entre 1 y 5");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ingresa una opcion valida");
                        input.nextLine();
                    }
                }
                datoValido = false;
                int x = respuesta;

                // Validación de entrada para la COLUMNA
                while(!datoValido) {
                    try {
                        System.out.print("Columna: ");
                        respuesta = input.nextInt();
                        if ( respuesta >= 1 && respuesta <= 5 ) {
                            datoValido = true;
                        } else {
                            System.out.println("Por favor, elige un numero entre 1 y 5");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ingresa una opcion valida");
                        input.nextLine();
                    }
                }
                datoValido = false;
                int y = respuesta;

                // Validación para el VALOR (1 o 0)
                while(!datoValido) {
                    try {
                        System.out.print("Ingresa el valor que quieres cambiar (1 o 0): ");
                        respuesta = input.nextInt();
                        if (respuesta == 1 || respuesta == 0) {
                            datoValido = true;
                        } else {
                            System.out.println("Por favor, elige 1 o 0");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ingresa una opcion valida");
                        input.nextLine();
                    }
                }
                datoValido = false;
                int valor = respuesta;
                
                // Actualiza la lógica del tablero con los datos recibidos
                tablero.editarTablero(x,y,valor);
                System.out.println("----------------------------");
                tablero.imprimirTablero(); // Muestra el estado actualizado

                // --- MENÚ DE CONTINUACIÓN / RENDICIÓN ---
                if(!tablero.compararTablero()){
                    while(!datoValido) {
                        try {
                            System.out.println(jugador + ", Quieres seguir jugando o te rindes?\n1) Sigo \n0) Me rindo");
                            respuesta = input.nextInt();
                            if (respuesta == 1 || respuesta == 0) {
                                datoValido = true;
                            } else {
                                System.out.println("Por favor, elige 1 o 0");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ingresa una opcion valida");
                            input.nextLine();
                        }
                    }
                    datoValido = false;
                    opcion = respuesta;
                }
                
                // Si el usuario elige 0, sale del bucle de juego
                if (opcion == 0) {
                    break;
                }
            }	
        } else {
            System.out.println("Nos vemos, cuidate!");
        }
        
        // --- MENSAJE FINAL ---
        if(tablero.compararTablero()) {
            System.out.println("Haz ganado estoy re orgullo de ti, " +jugador + " !!!");
        } else {
            System.out.println("Nos vemos, cuidate!!!");
        }
    }
}
