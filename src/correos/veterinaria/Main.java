/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria;

import correos.veterinaria.software.Negocio.*;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //cu1
        /*
         CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
         categoriaNegocio.registrar("Frutas", "Todas las frutas");
         categoriaNegocio.registrar("Lacteos", "Derivados Leche");
         categoriaNegocio.eliminar(3);
         */

        //CU2
        /*
         VeterinarioNegocio veterinario = new VeterinarioNegocio();
         veterinario.registrar("Evans", "Balcazar", 5555, "75055455", "Calle Uruguay #20");
         veterinario.modificar(1, "Juanito", "Perez Gallardo", 111, "78036436", "Calle paraguay #20");
         veterinario.eliminar(2);
         */
         //CU3
        ClienteNegocio cliente = new ClienteNegocio();
        cliente.registrar("Ernesto ","Zambrana","2030542", "71036584");
        cliente.eliminar(3);
    }

}
