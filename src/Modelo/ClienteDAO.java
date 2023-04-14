// @author Juan Camilo
package Modelo;

// Importar librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Importar otras clases de la aplicacion
import Controlador.conexionDB;


public class ClienteDAO {
    conexionDB con = new conexionDB(); // Instancia de la clase conexionDB
    Connection BaseDeDatos;
    PreparedStatement ps;
    ResultSet rs;

    // Metodo para consultar y almacenar los datos de la tabla 'Clientes' en una lista
    public List ListarClientes() {
        String sqlQuery = "SELECT * FROM clientes";
        List<Cliente> listaDeClientes = new ArrayList<>();
        
        try { 
            BaseDeDatos = con.conexionBaseDeDatos();
            ps = BaseDeDatos.prepareStatement(sqlQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setTipoID(rs.getString(1));
                cliente.setNumeroID(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setApellido(rs.getString(4));
                cliente.setUsuario(rs.getString(5));
                cliente.setContraseña(rs.getString(6));
                cliente.setNumeroCelular(rs.getString(7));
                cliente.setCorreoElectronico(rs.getString(8));
                listaDeClientes.add(cliente);
            }
            System.out.println("Consulta de la tabla clientes realizada correctamente");
        } catch (SQLException error) {
            System.out.println("Error en la consulta de la tabla clientes: " + error);
        }
        return listaDeClientes;
    }


    // Metodo para agregar un nuevo cliente a la tabla 'clientes' en la base de datos
    public boolean AgregarCliente(Cliente cliente) {
        String sqlQuery = "INSERT INTO clientes (TipoID, NumeroID, Nombre, Apellido, Usuario, Contraseña, NumeroCelular, CorreoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            BaseDeDatos = con.conexionBaseDeDatos();
            ps = BaseDeDatos.prepareStatement(sqlQuery);
            ps.setString(1, cliente.getTipoID());
            ps.setString(2, cliente.getNumeroID());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getUsuario());
            ps.setString(6, cliente.getContraseña());
            ps.setString(7, cliente.getNumeroCelular());
            ps.setString(8, cliente.getCorreoElectronico());
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Error en la insercion de datos en la tabla Clientes: " + error);
            return false;
        }
        return true;
    }


    // Metodo para actualizar los datos de un cliente en la tabla 'Clientes' en la base de datos
    public boolean ActualizarCliente(Cliente cliente) {
        String sqlQuery = "UPDATE clientes SET TipoID = ?, NumeroID = ?, Nombre = ?, Apellido = ?, Usuario = ?, Contraseña = ?, NumeroCelular = ?, CorreoElectronico = ? WHERE NumeroID = ?";
        try {
            BaseDeDatos = con.conexionBaseDeDatos();
            ps = BaseDeDatos.prepareStatement(sqlQuery);
            ps.setString(1, cliente.getTipoID());
            ps.setString(2, cliente.getNumeroID());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getUsuario());
            ps.setString(6, cliente.getContraseña());
            ps.setString(7, cliente.getNumeroCelular());
            ps.setString(8, cliente.getCorreoElectronico());
            ps.setString(9, cliente.getNumeroID());
            int filasModificadas = ps.executeUpdate();
            if (filasModificadas == 0) {
                return false;
            }
        } catch (SQLException error) {
            System.out.println("Error en la actualizacion de datos en la tabla Clientes: " + error);
            return false;
        }
        return true;
    }


    // Metodo para eliminar un cliente de la tabla 'clientes' en la base de datos
    public boolean EliminarCliente(Cliente cliente) {
        String sqlQuery = "DELETE FROM clientes WHERE NumeroID = ?";
        try {
            BaseDeDatos = con.conexionBaseDeDatos();
            ps = BaseDeDatos.prepareStatement(sqlQuery);
            ps.setString(1, cliente.getNumeroID());
            int filasModificadas = ps.executeUpdate();
            if (filasModificadas == 0) {
                return false;
            }
        } catch (SQLException error) {
            System.out.println("Error en la eliminacion de datos en la tabla Clientes: " + error);
        }
        return true;
    }


}
