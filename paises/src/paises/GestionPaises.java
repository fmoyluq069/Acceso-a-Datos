package paises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionPaises {

    private Connection conexion;

    public GestionPaises() {
        try {
            // Conexi�n a la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/bdejemplo72"; 
            String usuario = "root";
            String contrase�a = "1234";
            
            conexion = DriverManager.getConnection(url, usuario, contrase�a);
            if(conexion!=null)
                System.out.println("Conexion exitosa a la BD");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarPais(String iso3, String nombre, String capital) {
        try {
            String sql = "INSERT INTO paises (ISO3, nombre, Capital) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, iso3);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, capital);
            preparedStatement.executeUpdate();
            System.out.println("Pais a�adido con �xito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPais(int id) {
        try {
            String sql = "DELETE FROM paises WHERE Id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPaises() {
        try {
            String sql = "SELECT * FROM paises";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("Id");
                String iso3 = resultado.getString("ISO3");
                String nombre = resultado.getString("nombre");
                String capital = resultado.getString("Capital");

                System.out.println("ID: " + id + ", ISO3: " + iso3 + ", Nombre: " + nombre + ", Capital: " + capital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GestionPaises gestion = new GestionPaises();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nTRABAJO CON BD MYSQL");
            System.out.println("---------------------------------");
            System.out.println("1. Mostrar Paises");
            System.out.println("2. A�adir Pais.");
            System.out.println("3. Eliminar Pais");
            System.out.println("4. Buscar Pais");            
            System.out.println("5. Salir");
            System.out.print("Seleccione una opci�n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de l�nea

            switch (opcion) {                
                case 1:
                    System.out.println("Lista de pa�ses:");
                    gestion.mostrarPaises();
                    break;
                case 2:
                    gestion.agregarPais("USA", "Estados Unidos", "Washington D.C.");
                    break;   
                case 3:
                    gestion.eliminarPais(14);
                    break;
                case 4:                    
                    break;                 
                case 5:
                    gestion.cerrarConexion(); 
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opci�n no v�lida.");
            }
        }           
    }
}
