/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import correos.veterinaria.software.Datos.*;
import static java.awt.PageAttributes.MediaType.C;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import javax.swing.table.DefaultTableModel;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class CategoriaNegocio {

    private Categoria categoria;

    public CategoriaNegocio() {
        this.categoria = new Categoria();
    }

    public DefaultTableModel getCategoria(int id) {
        return categoria.getCategoria(id);
    }

    public DefaultTableModel getCategorias() {
        return categoria.getCategorias();
    }

    public void modificar(int id, String nombre, String descripcion) {
        //id de la categoria a modificar
        categoria.setCategoria(id, nombre, descripcion);
        categoria.modificar();
    }

    public void eliminar(int id) {
        //id de la categoria a modificar
        categoria.setId(id);
        categoria.eliminar();
    }
    public void registrar(String nombre, String descripcion) {
        categoria.setCategoria(nombre, descripcion);
        categoria.registrar();
    }


    public void generatePDF(String url) throws FileNotFoundException, DocumentException, IOException {

        String html2 = "<h1>iText</h1>\n"
                + "<ul>\n"
                + "	<li>The Platform</li>\n"
                + "	<li>The Library</li>\n"
                + "	<li>The Company</li>\n"
                + "</ul>\n"
                + "<h1>The Library</h1>\n"
                + "<p>iText is a software developer toolkit that allows users to integrate PDF functionalities within their applications, processes or products.</p>\n"
                + "<h2 class=\"ql-align-center\">What?</h2>\n"
                + "<p class=\"ql-align-center\">iText is a software developer toolkit that allows users to integrate PDF functionalities within their applications</p>\n"
                + "<h2 color =\"blue\" class=\"ql-align-center\">How?</h2>\n"
                + "<p class=\"ql-align-center\">By providing you with the tools to create and manipulate PDF in your source code</p>\n"
                + "<h2 class=\"ql-align-center\">Really?</h2>\n"
                + "<p class=\"ql-align-center\">Yes really!</p>";
        String writePath = "D:\\test.pdf";

        try (OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"))) {
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(html2));
            document.close();
        }
    }
}
