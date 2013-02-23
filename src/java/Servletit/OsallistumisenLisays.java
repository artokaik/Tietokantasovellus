/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysKayttaja;
import Tietokantayhteydet.TKYhteysOsallistuminen;
import Tietokantayhteydet.TKYhteysProjekti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arto
 */
@WebServlet(name = "OsallistumisenLisays", urlPatterns = {"/OsallistumisenLisays"})
public class OsallistumisenLisays extends SuperServlet {

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
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Osallistumisen lisays</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Osallistumisen Lisays</h1>");
                tulostaForm(out, request, response);
                mainMenuunNappi(out);
                out.println("</body>");
                out.println("</html>");

            } finally {
                out.close();
            }
        }
    }

    private void tulostaForm(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (new Kayttaja(request).onkoAdmin()) {
            out.println("<form action=\"OsallistumisenTallennus\" method=\"POST\">");
            out.println("<fieldset>");
            out.println("<legend>Valitse henkilö ja projekti</legend>");
            out.println("<input type='hidden' name='projekti' value=" + request.getParameter("projekti") + ">");
            out.println("<input type='hidden' name='isManager' value=" + request.getParameter("true") + ">");
            try {
//                dropDownProjekti(out, request, response);
                dropDownKayttaja(out, request, response);
            } catch (Exception e) {
                System.out.println(e);
            }

            out.println("<input type=" + "submit" + " value=" + "Lisää" + " />");
            out.println("</fieldset>");
            out.println("</form>");
        } else {
            out.println("<p> Sinulla ei ole oikeuksia tähän </p>");
        }
    }

    private void dropDownProjekti(PrintWriter out, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultSet projektit = new TKYhteysOsallistuminen().haeUudetProjektit(new Kayttaja((Integer) request.getSession().getAttribute("kayttaja_id")));
        out.println("<select name=" + "projekti" + ">");
        while (projektit.next()) {
            out.println("<option value=" + projektit.getString("id") + " >" + projektit.getString("project_name") + ">");
        }
        out.println("</select>");

    }

    private void dropDownKayttaja(PrintWriter out, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultSet kayttajat = new TKYhteysOsallistuminen().haeUudetKayttajat(Integer.parseInt(request.getParameter("projekti")));
        out.println("<select name=" + "kayttaja" + ">");
        while (kayttajat.next()) {
            out.println("<option value=" + kayttajat.getString("id") + " >" + kayttajat.getString("username") + ">");
        }
        out.println("</select>");
    }
}
