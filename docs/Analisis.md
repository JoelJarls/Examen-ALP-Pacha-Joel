# Análisis del Problema - Sistema AulaLogic

## 1. Descripción del Problema
La Universidad Técnica de Ambato requiere una aplicación de consola en Java para administrar las calificaciones de los estudiantes de una asignatura, permitiendo registrar datos validados, consultar registros individuales, generar indicadores estadísticos del curso y conservar la información de forma persistente en un archivo de texto (`datos/estudiantes.txt`).

## 2. Análisis Entrada - Proceso - Salida (EPS)

| Fase | Descripción Detallada |
| :--- | :--- |
| **Entradas** | • Opción del menú principal (`1` a `6`).<br>• Código del estudiante (`String` único, no vacío, sin `;`).<br>• Nombre completo del estudiante (`String` no vacío, sin `;`).<br>• Tres calificaciones parciales (`int` comprendidas entre `0` y `20`).<br>• Registros almacenados previamente en `datos/estudiantes.txt`. |
| **Proceso** | • Validación de datos de entrada y control de excepciones (`NumberFormatException`, `IOException`).<br>• Cálculo del promedio real de las tres calificaciones (`(n1 + n2 + n3) / 3.0`).<br>• Obtención de la calificación máxima y mínima mediante recorrido del arreglo `int[3]`.<br>• Determinación del estado académico (`APROBADO` o `REPROBADO`) según el límite mínimo de aprobación (`14.0`).<br>• Cálculo de indicadores del curso (total, aprobados, reprobados, promedio general, porcentaje de aprobados, extremos y lista inferior al promedio) y ordenamiento descendente por promedio.<br>• Lectura y escritura en archivo plano con formato `codigo; nombre; n1;n2;n3`. |
| **Salidas** | • Mensajes de confirmación y advertencias de validación en consola.<br>• Ficha individual de consulta por código.<br>• Reporte académico completo del curso.<br>• Archivo actualizado `datos/estudiantes.txt`. |

## 3. Diseño de la Arquitectura (4 Clases)
* **`Estudiante`:** Representa al estudiante (`codigo`, `nombre`, `notas[]`) y calcula su promedio, nota máxima, nota mínima y estado académico.
* **`GestionAcademica`:** Administra la colección (`ArrayList<Estudiante>`), valida duplicados, busca por código, ordena y genera el reporte general.
* **`GestorArchivos`:** Gestiona la lectura (`FileReader`/`BufferedReader`) y escritura (`FileWriter`/`BufferedWriter`) en `datos/estudiantes.txt`.
* **`Main`:** Controla el menú interactivo en consola y coordina la interacción entre las clases.
