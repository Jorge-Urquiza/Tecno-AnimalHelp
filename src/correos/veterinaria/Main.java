/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria;

import com.idrsolutions.image.pdf.PdfEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TimerTask;
import org.jfree.chart.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException {
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
         VentaNegocio venta = new VentaNegocio();
         System.out.println(venta.getVentas().getDataVector());
   
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
        // CU8 
      /*  ReportesNegocio reporte = new ReportesNegocio();
         System.out.println(reporte.top3ProductosVendidos().getDataVector());
         */
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("PERROS" + " ( 20 %)", new Double(20));
        dataset.setValue("GATOS" + " ( 40 %)", new Double(40));
        dataset.setValue("CONEJOS" + " ( 10 %)", new Double(10));
        JFreeChart chart = ChartFactory.createPieChart(// char t

                "ATENCIONES",// title                                                                     
                dataset, // data
                true, // include legend
                true, false);

        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file = new File("chart2.jpg");
            // ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);

            File pdfFile = new File("examplePDF.pdf");
            pdfFile.createNewFile();

            //write the image to the pdf
            PdfEncoder encoder = new PdfEncoder();
            encoder.write(file, pdfFile);

        } catch (Exception e) {

        }
        // creo la imagen
    }

}
