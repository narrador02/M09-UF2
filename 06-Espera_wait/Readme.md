Per què s’atura l’execució al cap d’un temps?

La ejecución se detiene porque los asistentes están en un bucle infinito (while (true)), pero con un pequeño descanso entre cada acción. Cada vez que un asistente intenta hacer una reserva o cancelarla, el programa espera un tiempo aleatorio entre 0 y 1 segundo (Thread.sleep((long) (Math.random() * 1000))). Esto hace que el programa no se ejecute de manera frenética, sino que vaya "tomándose su tiempo" entre cada acción. Es como si los asistentes estuvieran pensando qué hacer antes de actuar. 

Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%(ferReserva)-30%(cancel·lar)? I si foren al revés les probabilitats?

Si fuera 70%-30%:
Aquí los asistentes estarían más "ansiosos" por hacer reservas que por cancelarlas. Las plazas disponibles se agotarían más rápido, y los asistentes tendrían que esperar más tiempo para poder reservar. Las cancelaciones serían menos frecuentes, así que las plazas disponibles no aumentarían tan a menudo.

Si fuera 30%-70%:
En este caso, los asistentes estarían más "indecisos" y cancelarían más reservas de las que hacen. Las plazas disponibles aumentarían con más frecuencia, y los asistentes tendrían menos problemas para reservar.

Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?

La lista es necesaria porque nos permite saber quiénes han hecho reservas, no solo cuántas reservas hay. Si solo usáramos una variable entera para contar las plazas disponibles, no tendríamos forma de saber qué asistente ha reservado y cuál no. Imagina que un asistente quiere cancelar su reserva: sin la lista, no podríamos identificar si ese asistente tenía una reserva o no. Sería como intentar devolver una entrada al cine sin saber si la compraste en primer lugar.