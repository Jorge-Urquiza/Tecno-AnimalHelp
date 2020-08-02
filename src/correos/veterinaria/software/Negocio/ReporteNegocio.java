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
public class ReporteNegocio {

    private Reporte reporte;

    public ReporteNegocio() {
        this.reporte = new Reporte();
    }
    // reporte total de ventas por mes
    public DefaultTableModel ventasMensuales() {
        return reporte.ventasMensuales();
    }
  // los clientes que mas compras han realizado
    public DefaultTableModel top3ClientesCompras() {
        return reporte.top3ClientesCompras();
    }
    // LAS 3 MASCOTAS QUE RECIBIERON ATENCION
    public DefaultTableModel top3MascotasAtendidas() {
        return reporte.top3MascotasAtendidas();
    }

    //los productos que mas vendieron
    public DefaultTableModel top3ProductosVendidos() {
        return reporte.top3ProductosVendidos();
    }

    //ventas total del dia de hoy
    public DefaultTableModel ventasTotalDeHoy() {
        return reporte.ventasTotalDeHoy();
    }
   
}
