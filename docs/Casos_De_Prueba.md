# Matriz de Casos de Prueba - AulaLogic

* **Variante aplicada:** [Ej. D - Límite de aprobación: 14.0]

| ID | Caso de Prueba | Entrada | Resultado Esperado | Resultado Obtenido | Estado |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **CP01** | Registro correcto de un estudiante | Código: `E001`, Nombre: `Ana Torres`, Notas: `15, 18, 12` | Se registra en memoria, promedio `15.00`, estado `APROBADO`. | Estudiante registrado correctamente con promedio `15.00` y estado `APROBADO`. | APROBADO |
| **CP02** | Intento de registrar código duplicado | Código: `E001` (ya existente) | Mensaje de error indicando código repetido y solicita nuevamente. | Muestra `[Error] El código 'E001' ya se encuentra registrado.` y vuelve a pedirlo. | APROBADO |
| **CP03** | Calificación fuera del rango permitido | Nota 1: `25`, luego `-3`, luego `16` | Rechaza `25` y `-3`, solicita nuevamente hasta ingresar `16`. | Muestra `[Error] La calificación debe estar entre 0 y 20.` y acepta `16`. | APROBADO |
| **CP04** | Promedio igual al límite de aprobación | Código: `E002`, Nombre: `Carlos Vela`, Notas: `14, 14, 14`  | Promedio exacto `14.00` y estado `APROBADO`. | Promedio `14.00` y estado `APROBADO`. | APROBADO |
| **CP05** | Búsqueda de estudiante existente | Código: `E001` | Muestra datos, notas, promedio, máxima, mínima y estado. | Presenta ficha completa de `Ana Torres`. | APROBADO |
| **CP06** | Búsqueda de estudiante inexistente | Código: `E999` | Mensaje informativo indicando que no existe. | Muestra `[Info] No existe ningún estudiante registrado con el código 'E999'.` | APROBADO |
| **CP07** | Reporte sin estudiantes registrados | Opción `4` con lista vacía | Mensaje informativo sin lanzar excepción ni dividir entre cero. | Muestra `[Aviso] No hay estudiantes registrados. No se puede generar el reporte académico.` | APROBADO |
| **CP08** | Guardado y recuperación al reiniciar | Opción `6` (Salir) y volver a ejecutar `Main` | Guarda en `datos/estudiantes.txt` y recupera los registros al iniciar. | Archivo generado con formato `E001; Ana Torres; 15;18;12` y cargado al reiniciar. | APROBADO |
