import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
    private static final String CARPETA = "datos";
    private static final String RUTA_ARCHIVO = CARPETA + File.separator + "estudiantes.txt";

    public void guardarRegistros(ArrayList<Estudiante> estudiantes) {
        File directorio = new File(CARPETA);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false))) {
            for (Estudiante e : estudiantes) {
                int[] notas = e.getNotas();
                // Formato obligatorio: E001; Ana Torres; 15;18;12
                String linea = e.getCodigo() + "; " + e.getNombre() + "; "
                        + notas[0] + ";" + notas[1] + ";" + notas[2];
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("[OK] Registros guardados correctamente en " + RUTA_ARCHIVO);
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo guardar el archivo: " + ex.getMessage());
        }
    }

    public ArrayList<Estudiante> cargarRegistros() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("[Info] Archivo " + RUTA_ARCHIVO + " no encontrado. Se iniciará con lista vacía.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    try {
                        String codigo = partes[0].trim();
                        String nombre = partes[1].trim();
                        int n1 = Integer.parseInt(partes[2].trim());
                        int n2 = Integer.parseInt(partes[3].trim());
                        int n3 = Integer.parseInt(partes[4].trim());

                        int[] notas = {n1, n2, n3};
                        lista.add(new Estudiante(codigo, nombre, notas));
                    } catch (NumberFormatException nfe) {
                        System.out.println("[Advertencia] Formato numérico inválido en la línea " + numLinea);
                    }
                } else {
                    System.out.println("[Advertencia] Estructura incorrecta en la línea " + numLinea);
                }
            }
            System.out.println("[OK] Se recuperaron " + lista.size() + " registro(s) desde " + RUTA_ARCHIVO);
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo leer el archivo: " + ex.getMessage());
        }

        return lista;
    }
}
