/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria;

import com.itextpdf.text.DocumentException;
import correos.veterinaria.software.Negocio.*;
import javax.swing.table.DefaultTableModel;
import correos.veterinaria.utils.*;
import correos.veterinaria.software.Datos.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, DocumentException {
        //cu1
 /*
         CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
         categoriaNegocio.registrar("Frutas", "Todas las frutas");
         categoriaNegocio.registrar("Lacteos", "Derivados Leche");
         categoriaNegocio.eliminar(3);
     
         DefaultTableModel tabla = categoriaNegocio.getCategorias();
         System.out.println(tabla.getColumnCount());
         System.out.println(tabla.getDataVector());
         */
       //(System.out.println(tabla.getValueAt(1, 1));
        //CU2
   /*   
        
         veterinario.registrar("Evans", "Balcazar", 5555, "75055455", "Calle Uruguay #20");
         veterinario.modificar(1, "Juanito", "Perez Gallardo", 111, "78036436", "Calle paraguay #20");
         veterinario.eliminar(2);
         */
        //CU3
         /*
         ClienteNegocio cliente = new ClienteNegocio();
         cliente.registrar("Ernesto ","Zambrana","2030542", "71036584")
         VeterinarioNegocio veterinario = new VeterinarioNegocio();
         DefaultTableModel tabla = veterinario.getVeterinarios();
         System.out.println(tabla.getDataVector());
         ;
         cliente.eliminar(3);
         */
         //CU4
/*
         ProductosNegocio producto = new ProductosNegocio();
         //producto.registrar("Biopet", 15, 1);
         VeterinarioNegocio veterinario = new VeterinarioNegocio();
         DefaultTableModel tabla = veterinario.getVeterinarios();
         System.out.println(tabla.getDataVector());
         /* producto.eliminar(2);
         */
        //CU5
        /*
         MascotaNegocio mascota = new MascotaNegocio();
         //mascota.registrar("Sony", "Pitbull", "Negro", 1);
         mascota.eliminar(2);
         */

        //CU6
        //AtencionNegocio atencion = new AtencionNegocio();
        //atencion.modificar(1,"15-04-2020", "El perro vino con dolores", "Darle pastillas", 1, 1, 1);
        //  atencion.registrar("05-04-2020", "El perro vino con diarrea", "Darle paracetamol", 1, 1, 1);
        //DefaultTableModel tabla = atencion.getAtenciones();
        //System.out.println(tabla.getDataVector());
        //PROBANDO
        // REPORTE
        /*
        
        
         String outputFile = "prueba.pdf";
         // String outputFile = "C:/Users/users/Desktop/test.pdf";
         CategoriaNegocio categoria = new CategoriaNegocio();
         DefaultTableModel model = categoria.getCategorias();

         String Head[] = {"ID", "NOMBRE", "PRECIO", "CATEGORIA_ID"};
         String Cabecera = "ANIMALHELP - LISTA DE PRODUCTOS";
         Mensaje mensaje = Utils.dibujarTablaHtml(model, Head, Cabecera);
         String url= mensaje.getData();
         categoria.generatePDF(url);

         System.out.println("Done!");
        
         */
       /* 
        VentasNegocio venta = new VentasNegocio();
        LinkedList<DetalleVenta> lista = new LinkedList<>();
        DetalleVenta detalle1 = new DetalleVenta();
        DetalleVenta detalle2 = new DetalleVenta();
        detalle1.setDetalleVenta(3, 3);
        //product_id y cantidad
        detalle2.setDetalleVenta(4, 2);
        
        
        lista.add(detalle1);
        lista.add(detalle2);
        venta.registrar('', 1, 1, lista);
       */

    }

}
