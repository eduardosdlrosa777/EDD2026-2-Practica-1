Eduardo Sanchez de la Rosa
426008938
eduardosdlrosa@gmail.com

EL METODO MAIN ESTA EN INTERFAZ, ENTONCES EL JAVAC HACERLO EN INTERFAZ POR FAVOR.

BREVE DESCRIPCION

El código se divide en dos clases principales que separan la lógica de la interacción con el usuario:

    Interfaz.java (El Controlador): Maneja la entrada de datos por teclado, el flujo de turnos y las validaciones (usando try-catch para evitar que el programa truene si escribes una letra en vez de un número).

    Tablero.java (La Lógica): Se encarga de crear la solución aleatoria, calcular las pistas y gestionar el estado de la cuadrícula (el tablero del jugador vs. la solución).

Al iniciar, el programa genera una solución llenando una matriz con 0s y 1s. Lo más interesante es cómo genera las pistas:

    Pistas de Filas y Columnas: El método pistasFilas() y pistasColumnas() recorren la solución. Si encuentran varios "1" seguidos, los cuentan y guardan ese número. Si hay un "0", cierran ese grupo y empiezan a contar el siguiente.

    Visualización: El método imprimirTablero() muestra tu progreso actual y coloca las pistas calculadas a los lados y debajo de la cuadrícula para que sepas cuántos "1" debes colocar.

nicio: Te pide tu nombre y genera un tablero nuevo donde todo empieza en 0.

Ciclo de Juego: * Te pide coordenadas (Fila y Columna) del 1 al 5.

    Te pregunta qué valor quieres poner (1 para marcar, 0 para limpiar).

    Actualiza el tablero y lo vuelve a imprimir.

Condición de Victoria: Después de cada movimiento, llama a compararTablero(). Si tu matriz es idéntica a la solución oculta, ¡ganas!

Rendirse: En cada turno tienes la opción de seguir o retirarte.

