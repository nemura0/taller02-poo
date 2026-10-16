# Taller 02 POO - La fonda del Papu

Este taller consiste en un programa de Java que registra los choripanes y terremotos consumidos en una fonda. Se pueden revisar los registros, agregar nuevos, editar o eliminar los existentes y calcular las estadísticas de cada participante. Los datos se cargan desde archivos de texto y los cambios quedan guardados.

## Integrantes

| Nombre | RUT | Carrera | GitHub |
|---|---|---|---|
| Vicente Guerra | 21.855.415-6 | ITI | nemura0 |
| Diego Meneses | 22.087.010-3 | ICCI | DiegoMenesesPrime |

## Organización del proyecto

El programa está dividido en dos paquetes:

**dominio**

- `Consumo.java`: clase abstracta con los datos y métodos comunes a los consumos.
- `Choripan.java`: guarda el largo y ancho del choripán y calcula sus kcal.
- `Terremoto.java`: guarda los litros consumidos y calcula sus kcal.
- `Participante.java`: representa a cada participante.
- `Formato.java`: ayuda a mostrar los datos ordenados en consola.

**logica**

- `Fonda.java`: carga y guarda los archivos, administra los registros y calcula las métricas.
- `Main.java`: contiene el menú y las opciones que utiliza el usuario.

Los archivos `Participantes.txt` y `Consumido.txt` se encuentran en la carpeta principal del proyecto. El diagrama UML está en `DIAGRAMA_CLASES_TALLER02.pdf`.

## Cómo ejecutar el programa

Se necesita Java 21 y Eclipse.

1. Clonar el repositorio desde CMD:

   ```bat
   git clone https://github.com/nemura0/taller02-poo.git
   ```

2. En Eclipse, entrar a **File > Import > General > Existing Projects into Workspace**.
3. Seleccionar la carpeta clonada, sin marcar **Copy projects into workspace**, y presionar **Finish**.
4. Abrir `src/logica/Main.java` y ejecutarlo con **Run As > Java Application**.

Es importante mantener `Participantes.txt` y `Consumido.txt` en la carpeta principal para que el programa pueda leerlos.

## Opciones del programa

El menú principal permite:

1. Ver los registros de choripanes y terremotos.
2. Agregar, editar o eliminar registros.
3. Consultar cantidades, litros, kcal consumidas y días ebrio por participante.
4. Salir del programa.

Para comprobar que funciona, se pueden agregar registros, modificar sus datos y eliminarlos. Después hay que cerrar y volver a ejecutar el programa para verificar que los cambios sigan guardados en `Consumido.txt`. También se pueden probar letras en los menús, campos vacíos, valores negativos e índices que no existen, para comprobar el manejo de errores.

## Consideraciones del código

Se usó programación orientada a objetos, con `Consumo` como clase abstracta y `Choripan` y `Terremoto` como sus clases hijas. Así, ambos tipos de consumo se guardan en un mismo vector y cada uno calcula sus kcal con su propia fórmula.

Para los registros se usan vectores de tamaño fijo, con un máximo de 200 participantes y 200 consumos. No se utilizaron colecciones dinámicas. Las librerías utilizadas son `Scanner`, `File`, `FileWriter`, `BufferedWriter` e `IOException`.

Al iniciar, primero se leen los participantes y luego los consumos. Si una línea está mal escrita o corresponde a alguien que no está registrado, se omite sin detener el programa. Cuando se agrega, edita o elimina un consumo, se actualiza `Consumido.txt` con el formato correspondiente.

Las kcal se calculan con `(largo * ancho) / 3` para los choripanes y `(litros * 10) / 3` para los terremotos. Un día ebrio se cuenta cuando un participante acumula al menos 2 litros de terremoto en una misma fecha.
