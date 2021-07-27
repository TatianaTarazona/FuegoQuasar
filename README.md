# FuegoQuasar

Fuego de Quasar es un API que soluciona el siguiente problema: 

>Han Solo ha sido recientemente nombrado General de la Alianza Rebelde y busca dar un gran golpe contra el Imperio Galáctico para reavivar la llama de la resistencia. 

>El servicio de inteligencia rebelde ha detectado un llamado de auxilio de una nave portacarga imperial a la deriva en un campo de asteroides. 

>El manifiesto de la nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legión entera.

**Desafío** 

Como jefe de comunicaciones rebelde, tu misión es crear un programa en Golang que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave. 

**Posición de los satélites actualmente en servicio**
* Kenobi: [-500, -200] 
* Skywalker: [100, -100] 
* Sato: [500, 100] 


## Requerimientos 📋  
* JDK 11  
* Maven 3.6.3

## Pasos para la ejecución :computer:

###Insalación 🔧 ###
Una vez hayas clonado el repositorio: https://github.com/TatianaTarazona/FuegoQuasar.git, puedes instalarlo con el comando:
```bash
mvn install  
```

##Test unitario ⚙️ ##
Esta API tiene pruebas unitarias construidas con JUnit 5. Las pruebas unitarias se pueden ejecutar mediante el comando
```bash
mvn test  
```

##Planteamiento de la solución:memo:

### Obtener la posición del emisor por Triangulación 🚀

Se implemento la librería _Trilateration que resuelve un problema de trilateración espacial n-D utilizando un optimizador de mínimos cuadrados no lineal, la librería es: 

>Librería: https://github.com/lemmingapex/Trilateration
