# Taller 5

Aplicación que recibe una cadena cualquiera, 
esta es recibida y es guardada en una base de datos Mongo, 
esta acción puede ser realizada por 3 instancias diferentes, 
presentes en los puertos 34000, 34001 y 34002. Una vez esta es recibida 
se muestra un json con las ultimas 10 cadenas enviadas junto con la fecha y 
hora en que se enviaron.

## Getting Started

### Prerequisites

Git: permite el control de versiones del proyecto

Java: lenguaje en el cual esta desarrollado la totalidad del proyecto

Maven: Software que gestiona proyectos java


### Arquitectura

Para la realización de este laboratorio se empleo la herramienta de 
Docker la cual permite crear imágenes y contenedores de diferentes 
aplicaciones. En este caso primero tenemos una imagen de Mongo, seguido de esto 
tenemos 3 instancias diferentes las cuales consultan dicha base de datos además 
de realizar ciertas operaciones con la cadena que llega, finalmente un balanceador 
de cargas que se encarga de determinar la instancia a la cual enviar la cadena recibida. 
Una vez tenemos las imágenes de todo lo descrito con anterioridad estas pasan a estar en 
diferentes contenedores que son capaces de interactuar entre sí. Por último, estos 
contenedores se encuentran corriendo en un servicio de EC2 de AWS, herramienta que
permite configurar servidores virtuales.

## Diseño de clases

Para las 3 instancias se emplea la clase de SparkWebServer el cual 
por medio del framework de Spark es capaz de hacer peticiones 
a la base de datos, para la conexión en si se emplea la clase 
de MongoServiceImpl el cual es un singleton que permite consultar 
la información almacenada.
Para el balanceador de cargas se emplea la clase 
App que también usa el framework de Spark para hacer peticiones 
a alguno de las 3 instancias mencionadas. 


### Creación imagenes

Lo primero es la creación De las diferentes imágenes que se van a emplear, para ello podemos usar el comando 

```
docker build --tag "nombre imagen" .
```

Este comando genera una unica imagen del main establecido en el archivo Dockerfile. Para crear varias
imagenes se puede usar el comando

```
docker-compose up -d
```

![img_1.png](img_1.png)

una vez tenemos las diferentes imagenes estas se deben subir a Docker Hub para ello empleamos los siguientes
2 comandos

```
docker tag "nombreImagen" "nombreUsuario"/"nombreRepositorioDocker"
docker push "nombreUsuario"/"nombreRepositorioDocker":latest 
```

Una vez tengamos esto en Docker Hub creamos una instacia nueva en AWS, donde configuramos los puertos

![img_3.png](img_3.png)

Accedemos a la terminal donde podremos emplear dichas imagenes para crear contenedores que corran
nuestos servicios

![img_2.png](img_2.png)

### Pruebas

En este caso accedemos al siguiente link: http://ec2-34-207-236-237.compute-1.amazonaws.com:45000
Ingresamos una cadena y veremos lo siguiente: 

![img.png](img.png)

![img_4.png](img_4.png)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Versioning

version 1.0

## Authors

Sergio Andres Rozo Pulido

