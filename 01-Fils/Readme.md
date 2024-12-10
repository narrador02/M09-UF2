Actividad 01: Programación de Hilos en Java

Introducción
Esta actividad es una introducción a la programación de hilos en Java, implementando tres comportamientos para los hilos Juan y Pepe.

Comportamientos:

· Intercalado Equitativo: Los hilos se alternan de manera aproximada.
 - Ejecución: 
 === Comportamiento 1: Intercalado ===
    Pepe 1
    Juan 1
    Pepe 2
    Juan 2
    Pepe 3
    Juan 3
    Juan 4
    Pepe 4
    Juan 5
    Pepe 5
    Pepe 6
    Juan 6
    Pepe 7
    Juan 7
    Pepe 8
    Juan 8
    Pepe 9
    Juan 9
    Termina el hilo Pepe
    Termina el hilo Juan

· Pepe Primero: Pepe se ejecuta completamente antes que Juan.
 - Ejecución: 
 === Comportamiento 2: Pepe Primero ===
    Pepe 1
    Juan 1
    Pepe 2
    Pepe 3
    Pepe 4
    Pepe 5
    Pepe 6
    Pepe 7
    Pepe 8
    Pepe 9
    Termina el hilo Pepe
    Juan 2
    Juan 3
    Juan 4
    Juan 5
    Juan 6
    Juan 7
    Juan 8
    Juan 9
    Termina el hilo Juan

· Alternancia Estricta: Los hilos se alternan en un orden estricto.
 - Ejecución: 
 === Comportamiento 3: Alternancia Estricta ===
    Juan 1
    Pepe 1
    Juan 2
    Pepe 2
    Juan 3
    Pepe 3
    Juan 4
    Pepe 4
    Juan 5
    Pepe 5
    Juan 6
    Pepe 6
    Juan 7
    Pepe 7
    Juan 8
    Pepe 8
    Juan 9
    Pepe 9
    Termina el hilo Pepe
    Termina el hilo Juan