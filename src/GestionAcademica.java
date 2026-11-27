import java.util.ArrayList;

public class GestionAcademica {
    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        this.estudiantes = new ArrayList<>();
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public boolean existeCodigo(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarEstudiante(Estudiante nuevo) {
        if (nuevo == null || existeCodigo(nuevo.getCodigo())) {
            return false;
        }
        estudiantes.add(nuevo);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return e;
            }
        }
        return null;
    }

    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados en el sistema.");
            return;
        }
        System.out.println("\n--- LISTADO DE ESTUDIANTES ---");
        for (Estudiante e : estudiantes) {
            System.out.println(e.toString());
        }
    }

    public void generarReporteAcademico() {
        if (estudiantes.isEmpty()) {
            System.out.println("\n[Aviso] No hay estudiantes registrados. No se puede generar el reporte académico.");
            return;
        }

        int total = estudiantes.size();
        int aprobados = 0;
        int reprobados = 0;
        double sumaPromedios = 0.0;

        Estudiante estMax = estudiantes.get(0);
        Estudiante estMin = estudiantes.get(0);

        for (Estudiante e : estudiantes) {
            double prom = e.calcularPromedio();
            sumaPromedios += prom;

            if (e.obtenerEstado().equals("APROBADO")) {
                aprobados++;
            } else {
                reprobados++;
            }

            if (prom > estMax.calcularPromedio()) {
                estMax = e;
            }
            if (prom < estMin.calcularPromedio()) {
                estMin = e;
            }
        }

        double promedioGeneral = sumaPromedios / total;
        double porcentajeAprobados = ((double) aprobados / total) * 100.0;

        System.out.println("\n==================================================");
        System.out.println("             REPORTE ACADÉMICO DEL CURSO          ");
        System.out.println("==================================================");
        System.out.println("Límite de aprobación (Variante): " + Estudiante.LIMITE_APROBACION);
        System.out.println("Total de estudiantes           : " + total);
        System.out.println("Cantidad de aprobados          : " + aprobados);
        System.out.println("Cantidad de reprobados         : " + reprobados);
        System.out.printf("Promedio general del curso     : %.2f%n", promedioGeneral);
        System.out.printf("Porcentaje de aprobados        : %.2f%%%n", porcentajeAprobados);
        System.out.printf("Mayor promedio                 : %s - %s (%.2f)%n",
                estMax.getCodigo(), estMax.getNombre(), estMax.calcularPromedio());
        System.out.printf("Menor promedio                 : %s - %s (%.2f)%n",
                estMin.getCodigo(), estMin.getNombre(), estMin.calcularPromedio());

        System.out.println("\n--- Estudiantes con promedio inferior al promedio general ---");
        boolean hayInferiores = false;
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < promedioGeneral) {
                System.out.printf(" * %s - %s (Promedio: %.2f)%n",
                        e.getCodigo(), e.getNombre(), e.calcularPromedio());
                hayInferiores = true;
            }
        }
        if (!hayInferiores) {
            System.out.println(" Ningún estudiante está por debajo del promedio general.");
        }

        // Copia de la lista para ordenar de mayor a menor sin alterar el orden de registro original
        ArrayList<Estudiante> ordenados = new ArrayList<>(estudiantes);
        for (int i = 0; i < ordenados.size() - 1; i++) {
            for (int j = 0; j < ordenados.size() - 1 - i; j++) {
                if (ordenados.get(j).calcularPromedio() < ordenados.get(j + 1).calcularPromedio()) {
                    Estudiante temp = ordenados.get(j);
                    ordenados.set(j, ordenados.get(j + 1));
                    ordenados.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n--- Listado ordenado de mayor a menor promedio ---");
        for (int i = 0; i < ordenados.size(); i++) {
            Estudiante e = ordenados.get(i);
            System.out.printf("%d. %-8s | %-20s | Promedio: %.2f | %s%n",
                    (i + 1), e.getCodigo(), e.getNombre(), e.calcularPromedio(), e.obtenerEstado());
        }
        System.out.println("==================================================");
    }
}
