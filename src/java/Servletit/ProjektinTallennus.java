package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysProjekti;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tallentaa pääkäyttäjän luoman projektin
 * @author Arto
 */
@WebServlet(name = "ProjektinTallennus", urlPatterns = {"/ProjektinTallennus"})
public class ProjektinTallennus extends SuperServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (this.onkoKirjautunut(request, response)) {

            String nimi = request.getParameter("nimi");
            try {
                new TKYhteysProjekti().lisaaProjekti(nimi);
                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                mainMenuun(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")), response);
            }
        }
    }
    

}
