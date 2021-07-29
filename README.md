# FuegoQuasar  
  
Fuego de Quasar es un API que soluciona el siguiente problema:   
  
>Han Solo ha sido recientemente nombrado General de la Alianza Rebelde y busca dar un gran golpe contra el Imperio Galáctico para reavivar la llama de la resistencia. El servicio de inteligencia rebelde ha detectado un llamado de auxilio de una nave portacarga imperial a la deriva en un campo de asteroides. El manifiesto de la nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legión entera.    
>
> **Desafío** 
> Como jefe de comunicaciones rebelde, tu misión es crear un programa en Golang que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave.    
>    
>**Posición de los satélites actualmente en servicio**  
> * Kenobi: [-500, -200]  
> * Skywalker: [100, -100]  
> * Sato: [500, 100]   
  

  
## Planteamiento de la solución :memo:  
  
### 1. Obtener la posición del emisor por Trilateración 🚀  
  
Se implementó la librería _Trilateration_ que resuelve un problema de trilateración espacial n-D utilizando un optimizador de mínimos cuadrados no lineal, la documentación de la librería es:   
  
> https://github.com/lemmingapex/Trilateration  
  
### 2. Obtener el contenido del mensaje de auxilio 📄  
  
Se recorren los mensajes interceptados, eliminando los que vengan vacíos o nulos, luego se realiza una iteración por cada mensaje para armar una lista con los palabras de los mensajes interceptados por cada satélite.   
 
 ## Implementación de la solución :bookmark_tabs:

Para la creación del proyecto `fuegoQuasar` se usó el generador [Sprint Initializr](https://start.spring.io/) con la versión 2.5.3 de Spring Boot que es un framework que facilita la creación y desarrollo de Servicios y Micro servicios, Java 11 y adicionalmente para generar la documentación se hizo uso de las anotaciones [Swagger](https://swagger.io/). 

### Operaciones

**POST:**
*  **`/topsecret`** 
Procesa la información interceptada por los satélites y retorna la posición y mensaje enviado por la nave.
	##### Entrada:
	```JSON
	{"satelliteList":[
		{
			"name": "string",
			 "distance": 0,  
			 "message": ["string"]
	    },
	    {
		    "name": "string",
		    "distance": 0,
		    "message": ["string"]
		},
		{
			"name": "string",
			"distance": 0,
			"message": ["string"]
		}
	  	  ]
	  	}
	``` 

		
	##### Salida exitosa:
	```JSON
	{
		"position":{
			"x":0,
			"y":0
		},
		"message":"string"
	}
	```
	##### Salida Fallida:	
	```JSON
	{
		"timestamp":"2021-07-29T01:28:21.839+00:00",
		"status":404,
		"error":"Not Found",
		"path":"/topsecret"
	}
	```
	Esta salida se presenta en los siguientes casos: 
	* Si el nombre de alguno de los satélites que reportan no coincide con los satélites que se encuentran actualmente en servicio.
	* Si no es posible obtener la posición y/o mensaje de auxilio a partir del reporte de los satélites.
		
* **`topsecret_split/{satellite_name}`**
Permite recibir el reporte de cada uno de los satélites de manera separada y los almacena.
	
	`{satellite_name}` : debe ser el nombre de alguno de los satélites que se encuentran en servicio.
	
	##### Entrada: 
	 http://[ip_server]/topsecret_split/KENOBI
	```JSON
	{
	"distance": 0,  
	"message": ["string"]
	}
	```
	> *Nota: Si se envía más de un reporte por satélite, solo se guardará la información del ultimo reporte* 
	
	##### Ejemplo de salida:

	##### Salida exitosa:
	```JSON
	Transmisión recibida con éxito
	```
	
	   
**GET:**
* **`/topsecret_split`**
Procesa la información de los reportes almacenados de cada satélite y retorna la posición y mensaje enviado por la nave.
	#### Salida:
	
	##### Salida exitosa:
	```JSON
	{
		"position":{
			"x":0,
			"y":0
		},
		"message":"string"
	}
	```
	##### Salida Fallida:	
	```JSON
	{
		"timestamp":"2021-07-29T01:28:21.839+00:00",
		"status":404,
		"error":"Not Found",
		"path":"/topsecret"
	}
	```
	Esta salida se presenta en el siguiente caso: 
	* Si no es posible obtener la posición y/o mensaje de auxilio a partir del reporte de los satélites.
		

### Estructura del código fuente  :package:
Todos los paquetes y clases se han definido siguiendo principios *SOLID*.  
  
Lista de paquetes en ``com.meli.fuegoQuasar``  
  
 - :file_folder:**config** Clase para el manejo de documentación Swagger  
 - :file_folder:**constant** Posición de los satélites actualmente en servicio  
 - :file_folder:**controller** Clases que reciben las peticiones a los end-point definidos  
 - :file_folder:**exceptions** Clases para el manejo de excepciones  
 - :file_folder:**model** Clases que representan el negocio  
 - :file_folder:**services** Clases con la logica para obtener la localización y el mensaje

### Despligue de la API en AWS

Se realizó despliegue de fuegoQuasar.jar en una instancia EC2 (Linux) de AWS mediante [Elastic Beanstalk](https://console.aws.amazon.com/elasticbeanstalk/home?region=us-east-1#/welcome). 

El servicio puede ser consumido a usando la URL: http://appfuegoquasar-env.eba-vrteugxy.us-east-1.elasticbeanstalk.com/[operacion] 
  
## Ejecución del servicio  :computer:

### Ambiente local
#### Pre-requisitos 📋 
* JDK 11    
* Maven  
#### Instalación 🔧  
Una vez hayas clonado el repositorio: https://github.com/TatianaTarazona/FuegoQuasar.git, puedes instalarlo con el comando:  
```bash  
mvn install 
```  
#### Pruebas Unitarias ⚙️ 
Esta API tiene pruebas unitarias construidas con JUnit 5. Las pruebas unitarias se pueden ejecutar mediante el comando  
```bash  
mvn test 
```
### Consumo del servicio (Local-AWS)

##### Operaciones

##### POST

*  `/topsecret` 
 
	* http://localhost:8080/topSecret
	* http://appfuegoquasar-env.eba-vrteugxy.us-east-1.elasticbeanstalk.com/topsecret
	##### Ejemplo entrada:
	```JSON
	{"satelliteList":[
		{
			"name": "KENOBI",
			"distance": 100.1,  
			"message": ["este", "", "", "mensaje", ""]
		 },
		 {
			"name": "SKYWALKER",
			"distance": 100.1,
			"message": ["este", "", "", "mensaje", ""]
		 },
		 {
			"name": "SATO",
			"distance": 100.1,
			"message": ["este", "", "", "mensaje", ""]
		 }
    	]
    }
	``` 
		
	##### Ejemplos de salida:
			
	##### Salida exitosa:

	```JSON
	{
		"position":{
			"x":0.21678650315821996,
			"y":-50.30019522344838
		},
		"message":"este es un mensaje secreto "
	}
	```

	##### Salida Fallida:	

	```JSON
	{
		"timestamp":"2021-07-29T01:28:21.839+00:00",
		"status":404,
		"error":"Not Found",
		"path":"/topsecret"
	}
	```
	
			
	Esta salida se presenta en los siguientes casos: 
	* Si el nombre de alguno de los satélites que reportan no coincide con los satélites que se encuentran actualmente en servicio.
	* Si no es posible obtener la posición y/o mensaje de auxilio a partir del reporte de los satélites.
		
* `topsecret_split/{satellite_name}`
	
	`{satellite_name}` : debe ser el nombre de alguno de los satélites que se encuentran en servicio.
	
	* http://localhost:8080/topsecret_split/KENOBI
	* http://appfuegoquasar-env.eba-vrteugxy.us-east-1.elasticbeanstalk.com/topsecret_split/KENOBI
	
	#### Ejemplo entrada: 
	 
	 
	```JSON
	{
		"distance": 100.1,  
		"message": ["este", "", "", "mensaje", ""]
	}
   ```
   
	> *Nota: Si se envía más de un reporte por satélite, solo se guardará la información del ultimo reporte* 
	
	#### Ejemplo de salida:
		
	##### Salida exitosa:
	
	```JSON
	Transmisión recibida con éxito
	```
	
##### GET:
* `/topsecret_split`

	* http://localhost:8080/topsecret_split
	* http://appfuegoquasar-env.eba-vrteugxy.us-east-1.elasticbeanstalk.com/topsecret_split
	
	#### Ejemplos de salida:
		
	##### Salida exitosa:
	```JSON
		{
			"position":{
				"x":0.21678650315821996,
				"y":-50.30019522344838
			},
			"message":"este es un mensaje secreto "
		}
	```
	##### Salida Fallida:	
	```JSON
		{
			"timestamp":"2021-07-29T01:28:21.839+00:00",
			"status":404,
			"error":"Not Found",
			"path":"/topsecret"
		}
	```
	Esta salida se presenta en el siguiente caso: 
		* Si no es posible obtener la posición y/o mensaje de auxilio a partir del reporte de los satélites.
		
#### Documentación Swagger  

Una vez hayas instalado la API localmente (ver paso Instalación), puedes ingresar a la siguiente URL:
 http://localhost:8080/swagger-ui.html
 
 O en la URL del servicio expuesto en AWS: http://appfuegoquasar-env.eba-vrteugxy.us-east-1.elasticbeanstalk.com/swagger-ui.html
   
## Herramientas utilizadas 🛠️  
  
* Java - Lenguaje Progamación  
* Sprint Boot - Framework  
* Maven - Herramienta para la gestión y construcción de proyectos Java  
* AWS - Servicios de computación en la nube  
* IntelliJ - IDE  
* Swagger - Documentación de servicios Web