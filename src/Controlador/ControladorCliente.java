// @ author Juan Camilo
package Controlador;


// Importar librerias necesarias
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

// Importar clases necesarias
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.Interfaz;


public class ControladorCliente implements ActionListener {
    
    // Instancias de cada objeto
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    Interfaz vista = new Interfaz();
    DefaultTableModel modeloTabla = new DefaultTableModel();
 
    
    // Declarar variables
    private String TipoID;
    private String NumeroID;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Contraseña;
    private String NumeroCelular;
    private String CorreoElectronico;


    // Implementar vista
    public ControladorCliente (Interfaz vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        llenarTabla();
    }


    // Metodo para agregar eventos
    private void agregarEventos() {
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);

        vista.getTblTabla().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                llenarCampos(e);
            }
        });
    }


    // Metodo para llenar la tabla de datos
    private void llenarTabla() {
        String [] columnas = {"Tipo de ID", "Número ID", "Nombre", "Apellido", "Usuario", "Contraseña", "Numero de Celular", "Correo Electrónico"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        List<Cliente> listaClientes = clienteDAO.ListarClientes();
        for (Cliente cliente : listaClientes) {
            modeloTabla.addRow(new Object[] {
                cliente.getTipoID(), 
                cliente.getNumeroID(), 
                cliente.getNombre(), 
                cliente.getApellido(), 
                cliente.getUsuario(), 
                cliente.getContraseña(), 
                cliente.getNumeroCelular(), 
                cliente.getCorreoElectronico()
            });
        }
        vista.getTblTabla().setModel(modeloTabla);
        vista.getTblTabla().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));
    }


    // Metodo para llenar los campos de texto con los datos del cliente seleccionado
    private void llenarCampos(MouseEvent event) {
        JTable clienteSeleccionado = (JTable) event.getSource();
        
        vista.getInputTipoID().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 0).toString());
        vista.getInputNumeroID().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 1).toString());
        vista.getInputNombre().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 2).toString());
        vista.getInputApellido().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 3).toString());
        vista.getInputUsuario().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 4).toString());
        vista.getInputContraseña().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 5).toString());
        vista.getInputNumeroCelular().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 6).toString());
        vista.getInputCorreoElectronico().setText(vista.getTblTabla().getModel().getValueAt(clienteSeleccionado.getSelectedRow(), 7).toString());
    }


    // Validar input del usuario
    private boolean inputValido() {
        if ((vista.getInputTipoID().getText()).equals("") || (vista.getInputNumeroID().getText()).equals("") || 
            (vista.getInputNombre().getText()).equals("") || (vista.getInputApellido().getText()).equals("") || 
            (vista.getInputUsuario().getText()).equals("") || (vista.getInputContraseña().getText()).equals("") || 
            (vista.getInputNumeroCelular().getText()).equals("") || (vista.getInputCorreoElectronico().getText()).equals("")
        ) {
            JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    // Metodo para limpiar los campos de texto
    private void limpiarCampos() {
        // Cambiar el valor de las variables
        TipoID = "";
        NumeroID = "";
        Nombre = "";
        Apellido = "";
        Usuario = "";
        Contraseña = "";
        NumeroCelular = "";
        CorreoElectronico = "";

        // Actualizar el valor en la vista
        vista.getInputTipoID().setText(TipoID);
        vista.getInputNumeroID().setText(NumeroID);
        vista.getInputNombre().setText(Nombre);
        vista.getInputApellido().setText(Apellido);
        vista.getInputUsuario().setText(Usuario);
        vista.getInputContraseña().setText(Contraseña);
        vista.getInputNumeroCelular().setText(NumeroCelular);
        vista.getInputCorreoElectronico().setText(CorreoElectronico);
    }

    // Metodo para actualizar las variables
    private boolean actualizarVariables() {
        try {
            TipoID = vista.getInputTipoID().getText();
            NumeroID = vista.getInputNumeroID().getText();
            Nombre = vista.getInputNombre().getText();
            Apellido = vista.getInputApellido().getText();
            Usuario = vista.getInputUsuario().getText();
            Contraseña = vista.getInputContraseña().getText();
            NumeroCelular = vista.getInputNumeroCelular().getText();
            CorreoElectronico = vista.getInputCorreoElectronico().getText();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos invalidos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    // Metodo para agregar un cliente 
    private void agregarCliente() {
        try {
            if (inputValido()) {
                if (actualizarVariables()) {
                    Cliente cliente = new Cliente(TipoID, NumeroID, Nombre, Apellido, Usuario, Contraseña, NumeroCelular, CorreoElectronico);
                    boolean respuesta = clienteDAO.AgregarCliente(cliente);
                    llenarTabla();
                    if (respuesta) {
                        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Cliente agregado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese Numero ID", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Ya existe un cliente con ese Numero ID");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            llenarTabla();
        }
    }


    // Metodo para actualizar un cliente
    private void actualizarCliente() {
        try {
            if (inputValido()) {
                if (actualizarVariables()) {
                    Cliente cliente = new Cliente(TipoID, NumeroID, Nombre, Apellido, Usuario, Contraseña, NumeroCelular, CorreoElectronico);
                    boolean respuesta = clienteDAO.ActualizarCliente(cliente);
                    llenarTabla();
                    if (respuesta) {
                        System.out.println("Cliente actualizado correctamente");
                        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ningun cliente con ese Número de ID, cree uno nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("No existe ningun cliente con ese Numero de ID, cree uno nuevo");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            llenarTabla();
        }
    }


    // Metodo para eliminar un cliente
    private void eliminarCliente() {
        try {
            if (inputValido()) {
                if (actualizarVariables()) {
                    Cliente cliente = new Cliente(TipoID, NumeroID, Nombre, Apellido, Usuario, Contraseña, NumeroCelular, CorreoElectronico);
                    boolean respuesta = clienteDAO.EliminarCliente(cliente);
                    limpiarCampos();
                    llenarTabla();
                    if (respuesta) {
                        System.out.println("Cliente eliminado correctamente");
                        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ningun cliente con ese Número de ID", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("No existe ningun cliente con ese Numero de ID");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            llenarTabla();
        }
    }


    // Metodo para dar acciones a los botones
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == vista.getBtnAgregar()) {
            agregarCliente();
        }

        if (evento.getSource() == vista.getBtnActualizar()) {
            actualizarCliente();
        }

        if (evento.getSource() == vista.getBtnEliminar()) {
            eliminarCliente();
        }

        if (evento.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }

}
