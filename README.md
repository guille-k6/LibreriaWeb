# Librería web
Trabajo práctico integrador de la cátedra de Java (cursado año 2022).
## Integrantes: 
- Casey, Guillermo. Legajo: 47026.
- Dominguez, Bernardo. Legajo: 43213.

### Especificaciones
Sistema web para la administración de una biblioteca. 
#### Entidades
1. **Socio**. Usuario del sistema.

1. **Ejemplar**. Representa la existencia física de un determinado *libro*.
2. **Libro**. Libro con su nombre *autor* y editorial. Cada libro individualmente tiene una cántidad máxima de días permitidas de préstamo.
3. **Autor**. Autor de *libros*.
4. **Política de préstamo**. Representa la política de préstamo actual, es decir, la cántidad máxima de *libros* pendientes de devolución que puede tener un socio.
5. **Cuota**. Cuotas que un *socio* deberá pagar mensualmente para poder tomar libros *prestados*.
6. **Préstamo**. Entidad que representa un préstamo, un *socio* podrá realizar un préstamo por uno o varios *libros* y devolverlos todos juntos o separados.
7. **Línea de préstamo**. Entidad que representa el préstamo de un *libro*, en un *préstamo* hay una o más líneas de préstamo.

#### Caso de uso complejo
`Préstamo de un libro`. Cuando un usuario se acerca a la biblioteca y elige los libros que tomará como préstamo, el usuario administrador generará un préstamo para ese usuario. En el proceso se validará que el usuario no incumpla la política de préstamo y que tenga la cuota del mes paga.

#### Requerimientos adicionales
- Logeo de errores. Cada vez que ocurre una excepción, se logea el stacktrace y el mensaje de excepción en un archivo de texto plano.

- Manejo global de excepciones.

#### Listado complejo
 - Listado de cuotas con su estado por un socio entre dos fechas.

#### Niveles de acceso
- Usuario: Usuario final que podrá ver sus *préstamos* y *libros*.
- Administrador: Administrador del sistema que podrá modificar todas las entidades y dar de alta los *préstamos*.  

#### Users para el sistema
- juanperez : juanperez1 ADMIN
- johndoe : johndoe1  
- gcasey : gcasey
- bdominguez : bdominguez ADMIN



