import java.util.Random;

public class Tablero {

    // Códigos ANSI para dar color a la terminal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AMARILLO = "\u001B[33m";

    Random rand = new Random();
    int filas;
    int columnas;
    int[][] tablero;           // Lo que el jugador ve y edita
    int[][] solucionTablero;   // La respuesta correcta

    /**
     * Constructor: Inicializa las dimensiones y crea el tablero de juego y su solución.
     */
    public Tablero() {
        this.filas = 5;
        this.columnas = 5;
        this.tablero = new int[filas][columnas];
        this.solucionTablero = createSolution();
    }

    /**
     * Define la matriz de 5x5 que representa el dibujo oculto.
     * @return Matriz de enteros con la solución.
     */
    public static int[][] createSolution() {
        return new int[][] {
            {1,0,1,1,0},
            {1,1,1,0,0},
            {1,1,0,0,1},
            {0,1,0,1,1},
            {1,0,0,1,0}
        };
    }

    /**
     * Llena el tablero del jugador con ceros para empezar una partida limpia.
     */
    public void reiniciarTablero() {
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 0;
            }
        }
    }
    
    /**
     * Verifica si el progreso del jugador coincide exactamente con la solución.
     * @return true si ganó, false si hay diferencias.
     */
    public boolean compararTablero() {
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] != solucionTablero[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Cambia el valor de una celda específica. 
     * Resta 1 a las coordenadas porque el usuario usa rango 1-5 y el array usa 0-4.
     */
    public void editarTablero(int x, int y, int valor) {
        tablero[x-1][y-1] = valor;
    }
    
    /**
     * Retorna la matriz actual del jugador.
     */
    public int[][] obtenerTablero() {
        return tablero;
    }
    
    /**
     * El método más complejo: Imprime el tablero con colores y coloca las pistas 
     * calculadas a la derecha (filas) y abajo (columnas).
     */
    public void imprimirTablero() {
        String[][] pistasFilas = pistasFilas();
        String[][] pistasColumnas = pistasColumnas();
        
        // Imprime encabezado de números de columna
        System.out.println(ANSI_VERDE + "  1 2 3 4 5" + ANSI_RESET);
        
        // Imprime cuerpo del tablero y pistas de filas laterales
        for(int i = 0; i < tablero.length; i++) {
            System.out.print(ANSI_VERDE + (i + 1) + " " + ANSI_RESET); // Número de fila
            for(int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            // Pistas de las filas (al final de cada renglón)
            for(String pista : pistasFilas[i]){
                System.out.print(ANSI_AMARILLO + pista + " ");
            }
            System.out.println(" " + ANSI_RESET);
        }
        
        // Lógica para alinear verticalmente las pistas de las columnas abajo
        int maxLongRenglon = 0; // Para saber qué tan alto será el bloque de pistas inferiores
        for(String[] pista : pistasColumnas){
            String renglon = "";
            for(String a : pista){ renglon += a; }
            if (renglon.length() > maxLongRenglon){ maxLongRenglon = renglon.length(); }
        }
        
        // Conversión de pistas de columnas a una matriz para impresión vertical
        int[][] pistasDeColumnasConCeros = new int[5][maxLongRenglon];
        for(int i = 0; i < pistasColumnas.length; i++){
            for(int j = 0; j < pistasColumnas[i].length; j++){
                if(!pistasColumnas[i][j].equals("")){
                    pistasDeColumnasConCeros[i][j] = Integer.parseInt(pistasColumnas[i][j]);
                }
            }
        }
        
        // Imprime las pistas de las columnas en formato vertical
        System.out.print(ANSI_AMARILLO);
        for(int i = 0; i < maxLongRenglon; i++){
            System.out.print("  ");
            for(int j = 0; j < pistasDeColumnasConCeros.length; j++){
                if(pistasDeColumnasConCeros[j][i] == 0){
                    System.out.print("  ");
                } else {
                    System.out.print(pistasDeColumnasConCeros[j][i] + " ");
                }
            }
            System.out.println();
        }
        System.out.print(ANSI_RESET);
    }
    
    /**
     * Calcula los grupos de '1' consecutivos en cada fila de la solución.
     * Ejemplo: {1,1,0,1,1} devuelve "2 2".
     */
    public String[][] pistasFilas(){
        String[] pistas = new String[] {"", "", "", "", ""};
        for(int i = 0; i < 5; i++){
            int[] filaActual = this.solucionTablero[i];
            int index = 0;
            int contador = 0;
            String pista = "";
            while (index < filaActual.length){
                if (filaActual[index] == 1) {
                    contador++;
                } else {
                    if (contador != 0){
                        pista = pista + String.valueOf(contador) + " ";
                        contador = 0;
                    }
                }
                index++;
            }
            if (contador != 0) pista = pista + String.valueOf(contador) + " ";
            pistas[i] = pista;
        }
        // Convierte el String de pistas en un array para facilitar la impresión
        String[][] retorno = new String[5][];
        for(int i = 0; i < 5; i++){
            retorno[i] = pistas[i].split(" ");
        }
        return retorno;
    }
    
    /**
     * Calcula los grupos de '1' consecutivos en cada columna de la solución.
     * Funciona igual que pistasFilas pero transponiendo la búsqueda.
     */
    public String[][] pistasColumnas(){
        String[] pistas = new String[] {"", "", "", "", ""};
        for(int i = 0; i < 5; i++){
            int[] columnaActual = {0, 0, 0, 0, 0};
            for(int j = 0; j < 5; j++){
                columnaActual[j] = this.solucionTablero[j][i];
            }
            int index = 0;
            int contador = 0;
            String pista = "";
            while (index < columnaActual.length){
                if (columnaActual[index] == 1) {
                    contador++;
                } else {
                    if (contador != 0){
                        pista = pista + String.valueOf(contador) + " ";
                        contador = 0;
                    }
                }
                index++;
            }
            if (contador != 0) pista = pista + String.valueOf(contador) + " ";
            pistas[i] = pista;
        }
        String[][] retorno = new String[5][];
        for(int i = 0; i < 5; i++){
            retorno[i] = pistas[i].split(" ");
        }
        return retorno;
    }
}
