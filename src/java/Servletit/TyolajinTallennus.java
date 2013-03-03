/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysProjekti;
import Tietokantayhteydet.TKYhteysTyolaji;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tallentaa työlajin.
 * @author Arto
 */
public class TyolajinTallennus extends SuperServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (this.onkoKirjautunut(request, response)) {

            String nimi = request.getParameter("nimi");
            try {
                new TKYhteysTyolaji().lisaaTyolaji(nimi);

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                mainMenuun(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")), response);
            }
        }
    }
}
