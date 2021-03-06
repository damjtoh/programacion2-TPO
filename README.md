# Programación II – 1er Cuatrimestre 2016
## Trabajo Práctico Obligatorio
### Enunciado del Problema
Modelar una agenda para un consultorio médico donde existen varios profesionales (médicos) que para cada fecha en la que atienden pueden poseer uno a más turnos en distintos horarios cada uno. Un mismo médico no puede tener más de un turno en el mismo horario 
El consultorio atiende de 08:00 a 18:00 todos los días de la semana, así que los médicos pueden tener turnos cualquier día de la semana. Los turnos son de 15 minutos, así que el primer turno del día será a las 08:00, el segundo 08:15 y así sucesivamente hasta el último turno que será a las 17:45. En cada turno se registra además el nombre del paciente.
### La implentación de la agenda del consultorio contemplara los siguientes puntos:

* Obtener todos los médicos. 
* Obtener todas fechas en las que hay turnos.
* Obtener todas fechas en las un médico determinado tiene turnos. 
* Obtener todos los turnos de una fecha determinada.
* Obtener todos los turnos de un médico y una fecha determinada. 
* Agregar un turno para un paciente a un médico determinado en una fecha determinada a una hora determinada.
* Eliminar un turno de un paciente determinado con un médico determinado en una fecha determinada en el horario en que este asignado. 
* Eliminar todos los turnos de un paciente. 
* Eliminar todos los turnos de una fecha determinada a un médico determinado. 

### Resolución del Problema

La resolución del TPO consistirá en la implementación de las interfaces entregadas, de manera para poder obtener los datos solicitados.

Si es necesario, puede utilizar algún otro TDA de los vistos durante la cursada e implementarlo para que acepte el tipo de datos que sea conveniente.

Se proveerá del proyecto conteniendo los TDA a implementar, los nodos necesarios para dicha implementación y los esqueletos de las clases a fin de estandarizar los nombres de las mismas y posibilitar la prueba de las estructuras creadas mediante un test facilitado por la cátedra.

Determinar los costos de cada uno de los métodos implementados.

## Resolución
###Estructura de datos
![alt text](https://raw.githubusercontent.com/damjtoh/programacion2-TPO/master/TPO.png "Estructura de datos")
