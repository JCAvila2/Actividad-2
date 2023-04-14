// @author Juan Camilo
package Controlador;

// Importar librerias necesarias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
    // Declarar variable de conexion a la base de datos
    Connection DB;
    
    // Almacenar las credenciales de la base de datos
    String Driver = "com.mysql.cj.jdbc.Driver";
    String NombreDB = "actividad2";
    String url = "jdbc:mysql://localhost:3306/" + NombreDB + "?useSSL=false";
    String Usuario = "root";
    String Contraseña = "";
    
    // Metodo para realizar la conexion con la base de datos y retornarla
    public Connection conexionBaseDeDatos() {
        try {
            Class.forName(Driver);
            DB = DriverManager.getConnection(url, Usuario, Contraseña);
            System.out.println("La conexion con la base de datos se completo con exito");
        } catch (ClassNotFoundException | SQLException error) {
            System.out.println("Error en la conexion con la base de datos: " + error);
        }
        return DB;
    }
}
