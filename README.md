# Hotel Alura
## Formacion Backend Oracle Next Education
## Descripcion
Este proyecto hace parte de la formacion de desarrollador Backend que imparte Alura junto a Oracle, siendo asi que este es un Challenge que consiste en la creacion de una app de escritorio creada a partir del lenguaje de Java junto a las librerias de JPA y Swing, en donde se deben de cumplir ciertos requisitos de funcionamiento. Entre estos requisitos encontramos:
1. Permitir iniciar Sesion unicamente a los usuario que esten en el sistema
2. Realizar operaciones CRUD como crear reservas y huespedes, poder editarlos, eliminarlos y finalmente poder visualizarlos en una tabla
3. Aplicar reglas de negocio como el calculo automatico de precios de acuerdo a la cantidad de dias de reserva
4. Manejar una base de datos relacional para la gestion de datos
### `Organizacion del Proyecto`
Este proyecto esta conformado por 5 packages en donde se almacenan multiples archivos que permiten su funcionaminto, estos packages son:
1. `Controller:` Almacena los controladores los cuales estan encargados de administrar los metodos que se usan para los procesos de CRUD
2. `dao:` Almacena los archivos Dao los cuales contienen la logica que se utilizo para los metodos de cada modelo, como por ejemplo los metodos de `guardar()`, `editar()`, etc.
3. `modelo:` El package de modelo se encarga de albergar las clases que luego se mapean en la base de datos como tablas, entre estos modelos encontramos 3: `usuario`, `huesped`, `reserva`.
4. `utils:` Guarda el archivo de `JPAUtils` el cual tiene el `EntityManagerFactory`
5. `views:` Por ultimo esta el package de views en donde estan las clases o pantallas que se usan para la interfaz grafica.
6. `resources`: En esta carpeta almacenamos 2 subcarpetas las cuales son las imagenes y la carpeta de META-INF esta ultima es donde guardamos nuestro archivo persistence.xml el cual se encarga de la conexion a la base de datos y contiene algunas configuraciones como las credenciales de la base de datos, parametros de creacion, etc.

## `Dependencias Pom.xml`
Dentro del archivo `pom.xml` escribimos las dependencias que fueron necesarias para la creacion del proyecto y su debido funcionamiento, estas dependencias son las siguientes y con sus respectivas versiones:
### c3p0
<dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.4</version>
        </dependency>
        
### jcalendar
<dependency>
            <groupId>com.toedter</groupId>
            <artifactId>jcalendar</artifactId>
            <version>1.4</version>
        </dependency>
        
### mchange-conmmons-java
<dependency>
            <groupId>com.mchange</groupId>
            <artifactId>mchange-commons-java</artifactId>
            <version>0.2.16</version>
        </dependency>
        
### mysql-connector-java
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
