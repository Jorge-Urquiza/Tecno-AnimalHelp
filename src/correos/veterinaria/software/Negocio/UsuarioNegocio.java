package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Usuario;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:29 PM
 */
public class UsuarioNegocio {

    public Usuario m_Usuario;

    public UsuarioNegocio() {
        this.m_Usuario = new Usuario();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerUsuario(int id) {
        return this.m_Usuario.getUsuario(id);
    }

    public DefaultTableModel obtenerUsuarios() {
        return this.m_Usuario.getUsuarios();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param tipo
     * @param estado
     * @return 
     */
    public int registrarUsuario(String nombres, String apellidos, int telefono, Date fecha_nacimiento, int tipo, boolean estado) {
        // No olvidar primero settear los datos
        this.m_Usuario.setUsuario(nombres, apellidos, telefono, fecha_nacimiento, tipo, estado);
        return this.m_Usuario.registrarUsuario();
    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param tipo
     * @param estado
     */
    public void modificarUsuario(int id, String nombres, String apellidos, int telefono, Date fecha_nacimiento, int tipo, boolean estado) {
        // No olvidar primero settear los datos
        this.m_Usuario.setUsuario(id, nombres, apellidos, telefono, fecha_nacimiento, tipo, estado);
        this.m_Usuario.modificarUsuario();
    }

}
