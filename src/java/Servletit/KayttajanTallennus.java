/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysKayttaja;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tallentaa requestin parametrina tulevan käyttäjän tiedot tietokantaan.
 * 
 * @author Arto
 */
public class KayttajanTallennus extends SuperServlet {

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (this.onkoKirjautunut(request, response)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                new TKYhteysKayttaja().lisaaKayttaja(username, password, false);
                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                mainMenuun(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")), response);
            }
        }
    }
}
