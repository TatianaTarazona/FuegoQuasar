# FuegoQuasar

Fuego de Quasar es un API que soluciona el siguiente problema: 

>Han Solo ha sido recientemente nombrado General de la Alianza Rebelde y busca dar un gran golpe contra el Imperio Galáctico para reavivar la llama de la resistencia.  
El servicio de inteligencia rebelde ha detectado un llamado de auxilio de una nave portacarga imperial a la deriva en un campo de asteroides.  
El manifiesto de la nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legión entera.  
**Desafío**  
Como jefe de comunicaciones rebelde, tu misión es crear un programa en Golang que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave.  
**Posición de los satélites actualmente en servicio**  
Kenobi: [-500, -200]  
Skywalker: [100, -100]  
Sato: [500, 100]  


## Requerimientos 📋  
* JDK 11  
* Maven

## Pasos para la ejecución :computer:

### Insalación 🔧
Una vez hayas clonado el repositorio: https://github.com/TatianaTarazona/FuegoQuasar.git, puedes instalarlo con el comando:
```bash
mvn install  
```

## Test unitario ⚙️ ##
Esta API tiene pruebas unitarias construidas con JUnit 5. Las pruebas unitarias se pueden ejecutar mediante el comando
```bash
mvn test  
```


## Planteamiento de la solución:memo:

### Obtener la posición del emisor por Triangulación 🚀

Se implementó la librería _Trilateration_ que resuelve un problema de trilateración espacial n-D utilizando un optimizador de mínimos cuadrados no lineal, la librería es: 

> https://github.com/lemmingapex/Trilateration

### Obtener el contenido del mensaje de auxilio 📄

Se recorren los mensajes interceptados, eliminando los que vengan vacíos o nulos, luego se realiza una iteración por cada mensaje para armar una lista con los palabras de los mensajes interceptados por cada satélite. 


## Estructura del código fuente  :package:

Todos los paquetes y clases se han definido siguiendo principios *SOLID*.

Lista de paquetes en ``com.meli.fuegoQuasar``

 - :file_folder:**config** Clase para el manejo de documentación Swagger
 - :file_folder:**constant** Posición de los satélites actualmente en servicio
 - :file_folder:**controller** Clases que reciben las peticiones a los end-point definidos
 - :file_folder:**exceptions** Clases para el manejo de excepciones
 - :file_folder:**model** Clases que representan el negocio
 - :file_folder:**services** Clases con la logica para obtener la localización y el mensaje

## Documentación Swagger

[fuegoQuasar](http://localhost:8080/swagger-ui.html)


## Herramientas utilizadas 🛠️

* Java - Lenguaje Progamación
* Sprint Boot - Framework
* Maven - Herramienta para la gestión y construcción de proyectos Java
* Amazon - Servicios de computación en la nube
* IntelliJ - IDE
* Swagger - Documentación de servicios Web

