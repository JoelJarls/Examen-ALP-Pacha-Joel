import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos gestorArchivos = new GestorArchivos();

        // Recuperación automática al iniciar el programa (RF05)
        gestion.setEstudiantes(gestorArchivos.cargarRegistros());

        int opcion = 0;
        do {
            System.out.println("\n===================");
            System.out.println("SISTEMA AULALOGIC");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("===================");
            System.out.println("4. Generar reporte academico");
            System.out.println("5. Guardar registros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            String entrada = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    registrarNuevoEstudiante(sc, gestion);
                    break;
                case 2:
                    gestion.listarEstudiantes();
                    break;
                case 3:
                    buscarEstudiante(sc, gestion);
                    break;
                case 4:
                    gestion.generarReporteAcademico();
                    break;
                case 5:
                    gestorArchivos.guardarRegistros(gestion.getEstudiantes());
                    break;
                case 6:
                    // Antes de salir, guardar los registros (Sección 5)
                    gestorArchivos.guardarRegistros(gestion.getEstudiantes());
                    System.out.println("Saliendo del sistema AulaLogic. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("[Error] Opción inválida. Ingrese un número entre 1 y 6.");
            }
        } while (opcion != 6);

        sc.close();
    }

    private static void registrarNuevoEstudiante(Scanner sc, GestionAcademica gestion) {
        System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");

        String codigo;
        while (true) {
            System.out.print("Ingrese código del estudiante (ej. E001): ");
            codigo = sc.nextLine().trim();
            if (codigo.isEmpty() || codigo.contains(";")) {
                System.out.println("[Error] El código no puede estar vacío ni contener punto y coma (;).");
            } else if (gestion.existeCodigo(codigo)) {
                System.out.println("[Error] El código '" + codigo + "' ya se encuentra registrado.");
            } else {
                break;
            }
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese nombre completo: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty() || nombre.contains(";")) {
                System.out.println("[Error] El nombre no puede estar vacío ni contener punto y coma (;).");
            } else {
                break;
            }
        }

        int[] notas = new int[3];
        for (int i = 0; i < 3; i++) {
            notas[i] = leerNotaValida(sc, i + 1);
        }

        Estudiante nuevo = new Estudiante(codigo, nombre, notas);
        if (gestion.registrarEstudiante(nuevo)) {
            System.out.println("[OK] Estudiante registrado con éxito. Estado: " + nuevo.obtenerEstado());
        }
    }

    private static int leerNotaValida(Scanner sc, int numeroNota) {
        while (true) {
            System.out.print("Ingrese calificación " + numeroNota + " (0 a 20): ");
            String entrada = sc.nextLine().trim();
            try {
                int nota = Integer.parseInt(entrada);
                if (nota >= 0 && nota <= 20) {
                    return nota;
                } else {
                    System.out.println("[Error] La calificación debe estar entre 0 y 20.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Debe ingresar un número entero válido.");
            }
        }
    }

    private static void buscarEstudiante(Scanner sc, GestionAcademica gestion) {
        System.out.print("\nIngrese el código del estudiante a buscar: ");
        String codigo = sc.nextLine().trim();
        Estudiante encontrado = gestion.buscarPorCodigo(codigo);

        if (encontrado != null) {
            System.out.println("\n--- RESULTADO DE BÚSQUEDA ---");
            System.out.println("Código        : " + encontrado.getCodigo());
            System.out.println("Nombre        : " + encontrado.getNombre());
            int[] n = encontrado.getNotas();
            System.out.println("Calificaciones: " + n[0] + ", " + n[1] + ", " + n[2]);
            System.out.printf("Promedio      : %.2f%n", encontrado.calcularPromedio());
            System.out.println("Nota máxima   : " + encontrado.obtenerNotaMaxima());
            System.out.println("Nota mínima   : " + encontrado.obtenerNotaMinima());
            System.out.println("Estado        : " + encontrado.obtenerEstado());
        } else {
            System.out.println("[Info] No existe ningún estudiante registrado con el código '" + codigo + "'.");
        }
    }
}
