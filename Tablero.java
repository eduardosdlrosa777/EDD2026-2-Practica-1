import java.util.Random;
public class Tablero {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AMARILLO = "\u001B[33m";

    Random rand = new Random();
    int filas;
    int columnas;
    int[][] tablero;
    int[][] solucionTablero;

    public Tablero() {
	this.filas = 5;
	this.columnas = 5;
	this.tablero = new int[filas][columnas];
	this.solucionTablero = createSolution();
    }

    // Tablero solución fijo
    public static int[][] createSolution() {
	
        return new int[][] {
            {1,0,1,1,0},
            {1,1,1,0,0},
            {1,1,0,0,1},
            {0,1,0,1,1},
            {1,0,0,1,0}
        };
    }
    //Agrega los métodos que creas que faltan en esta clase

    public void reiniciarTablero() {
	for(int i = 0; i < tablero.length; i++) {
	    for(int j = 0; j < tablero[0].length; j++) {
		tablero[i][j] = 0;
	    }
	}
    }
    
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
    
    public void editarTablero(int x, int y, int valor) {
	tablero[x-1][y-1] = valor;
    }
    
    public int[][] obtenerTablero() {
	return tablero;
    }
    
    public void imprimirTablero() {
	
	String[][] pistasFilas = pistasFilas();
	String[][] pistasColumnas = pistasColumnas();
	
	System.out.println(ANSI_VERDE + "  1 2 3 4 5" + ANSI_RESET);
       	for(int i = 0; i < tablero.length; i++) {
	    System.out.print(ANSI_VERDE + (i + 1) + " " + ANSI_RESET);
	    for(int j = 0; j < tablero[0].length; j++) {
		System.out.print(tablero[i][j] + " ");
	    }
	    for(String pista : pistasFilas[i]){
		System.out.print(ANSI_AMARILLO + pista + " ");
	    }
	    System.out.println(" " + ANSI_RESET);
	}
	
	int maxLongRenglon = 0;
	for(String[] pista : pistasColumnas){
	    String renglon = "";
	    for(String a : pista){
		renglon += a;
	    }
	    if (renglon.length() > maxLongRenglon){
		maxLongRenglon = renglon.length();
	    }
	}
	
	int[][] pistasDeColumnasConCeros = new int[5][maxLongRenglon];
	for(int i = 0; i < pistasDeColumnasConCeros.length; i++){
		for(int j = 0; j < maxLongRenglon; j++){
		    pistasDeColumnasConCeros[i][j] = 0;
		}
	}
	
	for(int i = 0; i < pistasColumnas.length; i++){
	    for(int j = 0; j < pistasColumnas[i].length; j++){
		if(pistasColumnas[i][j] != ""){
		    pistasDeColumnasConCeros[i][j] = Integer.parseInt(pistasColumnas[i][j]);
		}
	    }
	}
	
	System.out.print(ANSI_AMARILLO);
	
	for(int i = 0; i < maxLongRenglon; i++){
		System.out.print("  ");
		for(int j = 0; j < pistasDeColumnasConCeros.length; j++){
		    if(pistasDeColumnasConCeros[j][i] == 0){
			System.out.print("  ");
		    }else{
				System.out.print(pistasDeColumnasConCeros[j][i] + " ");
		    }
		}
		System.out.println();
	}

	System.out.print(ANSI_RESET);
	
    }
    
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
				}
				else{
					if (contador != 0){
					    pista = pista + String.valueOf(contador) + " ";
					    contador = 0;
					}
				}
				index++;
		    }
		    if (contador != 0){
				pista = pista + String.valueOf(contador) + " ";
		    }
			pistas[i] = pista;
		}
		String[][] retorno = new String[5][];
		for(int i = 0; i < 5; i++){
			retorno[i] = pistas[i].split(" ");
		}
		return retorno;
	}
    
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
			    }
			    else{
					if (contador != 0){
					    pista = pista + String.valueOf(contador) + " ";
					    contador = 0;
					}
				}
			    index++;
			}
			if (contador != 0){
			    pista = pista + String.valueOf(contador) + " ";
			}
			pistas[i] = pista;
	}
	String[][] retorno = new String[5][];
	for(int i = 0; i < 5; i++){
	    retorno[i] = pistas[i].split(" ");
	}
		return retorno;
    }
    
}
