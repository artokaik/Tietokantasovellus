/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Tietokantayhteydet.TKYhteys;
import Tietokantayhteydet.TKYhteysKirjaus;
import Tietokantayhteydet.TKYhteysProjekti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tulostaa raportin, jolla on yhden käyttäjän kaikki työaikakirjaukset.
 *
 * @author Arto
 */
public class RaporttiKaikkiKirjaukset extends SuperServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Kaikki kirjaukset</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                this.raportti(out, request);
            } catch (Exception e) {
                System.out.println(e);
            }
            mainMenuunNappi(out);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void raportti(PrintWriter out, HttpServletRequest request) throws Exception {
        int kayttajaID = Integer.parseInt(request.getParameter("kayttaja"));
        ResultSet r = new TKYhteysKirjaus().kayttajanKaikkiKirjaukset(kayttajaID);

        out.println("<table border='1'>");
        out.println("<tr>");
        out.println(" <td><b>Käyttäjä</b></td>");
        out.println(" <td><b>Päivä</b></td>");
        out.println(" <td><b>Projekti</b></td>");
        out.println(" <td><b>Työlaji</b></td>");
        out.println(" <td><b>Tunnit</b></td>");
        out.println(" <td><b>Selitys</b></td>");
        out.println("</tr>");
        while (r.next()) {
            out.println("<tr>");
            out.println(" <td>" + r.getString("username") + "</td>");
            out.println(" <td>" + r.getString("date") + "</td>");
            out.println(" <td>" + r.getString("project_name") + "</td>");
            out.println(" <td>" + r.getString("work") + "</td>");
            out.println(" <td>" + r.getString("hours") + "</td>");
            out.println(" <td>" + r.getString("description") + "</td>");
            out.println("</tr>");
        }
        r.close();
        out.println("</table>");
    }
}