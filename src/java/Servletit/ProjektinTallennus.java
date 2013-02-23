package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysKirjaus;
import Tietokantayhteydet.TKYhteysOsallistuminen;
import Tietokantayhteydet.TKYhteysProjekti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 *
 * @author Arto
 */
@WebServlet(name = "ProjektinTallennus", urlPatterns = {"/ProjektinTallennus"})
public class ProjektinTallennus extends SuperServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (this.onkoKirjautunut(request, response)) {

            String nimi = request.getParameter("nimi");
            try {
                new TKYhteysProjekti().lisaaProjekti(nimi);
                mainMenuun(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")), response);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
