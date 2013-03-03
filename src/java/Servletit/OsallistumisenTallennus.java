package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysOsallistuminen;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tallentaa osallistumisen
 * @author Arto
 */
@WebServlet(name = "OsallistumisenTallennus", urlPatterns = {"/OsallistumisenTallennus"})
public class OsallistumisenTallennus extends SuperServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (this.onkoKirjautunut(request, response)) {
            
            int projekti_ID = Integer.parseInt(request.getParameter("projekti"));
            int kayttajaID = Integer.parseInt(request.getParameter("kayttaja"));
            boolean isManager = Boolean.parseBoolean(request.getParameter("isManager"));
            try {
                TKYhteysOsallistuminen yhteys = new TKYhteysOsallistuminen();
                yhteys.lisaaOsallistuminen(projekti_ID, kayttajaID, isManager);             
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                mainMenuun(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")), response);
            }
        }
    }

}
