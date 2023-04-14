// @author Juan Camilo
package Modelo;

public class Cliente {

    // Declarar variables
    private String TipoID;
    private String NumeroID;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Contraseña;
    private String NumeroCelular;
    private String CorreoElectronico;

    // Constructor vacio
    public Cliente() {
    
    }

    // Constructor con parametros para actualizar datos
    public Cliente(String TipoID, String NumeroID, String Nombre, String Apellido, String Usuario, String Contraseña, String NumeroCelular, String CorreoElectronico) {
        this.TipoID = TipoID;
        this.NumeroID = NumeroID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.NumeroCelular = NumeroCelular;
        this.CorreoElectronico = CorreoElectronico;
    }

    // Metodos get y set
    public String getTipoID() {
        return TipoID;
    }
    public void setTipoID(String TipoID) {
        this.TipoID = TipoID;
    }

    public String getNumeroID() {
        return NumeroID;
    }
    public void setNumeroID(String NumeroID) {
        this.NumeroID = NumeroID;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }
    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getNumeroCelular() {
        return NumeroCelular;
    }
    public void setNumeroCelular(String NumeroCelular) {
        this.NumeroCelular = NumeroCelular;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }
    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

}
