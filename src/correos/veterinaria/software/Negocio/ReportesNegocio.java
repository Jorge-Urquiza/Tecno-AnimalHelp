/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Reporte;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class ReportesNegocio {

    private Reporte reporte;

    public ReportesNegocio() {
        this.reporte = new Reporte();
    }
    public DefaultTableModel ventasMensuales() {
        return reporte.ventasMensuales();
    }
}
