# Taller 02 POO — La fonda del Papu

Programa en Java que carga los consumos (choripanes y terremotos) de la fonda desde archivos de texto, permite verlos, agregarlos, editarlos y eliminarlos guardando los cambios en `Consumido.txt`, y calcula métricas por participante.

## Integrantes

| Nombre | RUT | Carrera | Usuario GitHub |
|---|---|---|---|
| Vicente Guerra | 21.855.415-6 | ITI | nemura0 |
| Diego Meneses | 22.087.010-3 | ICCI | DiegoMenesesPrime |

## Estructura del proyecto

- `src/dominio/`: clases del dominio (`Consumo`, `Choripan`, `Terremoto`, `Participante`).
- `src/logica/`: `Main` (punto de entrada y menús).
- `Participantes.txt` y `Consumido.txt`: archivos de datos, en la raíz del proyecto.

## Instrucciones de ejecución

1. Clonar el repositorio: `git clone https://github.com/nemura0/taller02-poo.git`
2. En Eclipse: **File → Import → General → Existing Projects into Workspace** y seleccionar la carpeta clonada.
3. Abrir `src/logica/Main.java` y ejecutar con **Run As → Java Application**.
4. Los archivos `.txt` deben quedar en la raíz del proyecto (junto a `src/`).
