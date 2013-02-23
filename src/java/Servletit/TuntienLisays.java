package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteys;
import Tietokantayhteydet.TKYhteysKayttaja;
import Tietokantayhteydet.TKYhteysOsallistuminen;
import Tietokantayhteydet.TKYhteysProjekti;
import Tietokantayhteydet.TKYhteysTyolaji;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Arto
 */
@WebServlet(name = "TuntienLisays", urlPatterns = {"/TuntienLisays"})
public class TuntienLisays extends SuperServlet {

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
                response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Tuntikirjaus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Tuntikirjaus</h1>");
            tulostaForm(out, request, response);
            out.println("</body>");
            out.println("</html>");

            mainMenuunNappi(out);
        } finally {
            out.close();
        }
    }

    private void tulostaForm(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        out.println("<form action=\"TuntienTallennus\" method=\"POST\">");
        out.println("<fieldset>");
        out.println("<legend>Syötä käytetyt tunnit</legend>");
        try {
            dropDownKayttajanProjektit(out, request);
            dropDownTyolaji(out);
            paivays(out);
        } catch (Exception e) {
            System.out.println(e);
        }
        out.println("<label for=" + "tunnit" + ">Tunnit:</label>");
        out.println("<input type=" + "number" + " name=" + "tunnit" + " id=" + "tunnit" + " />");

        out.println("<label for=" + "selitys" + ">Selitys:</label>");
        out.println("<input type=" + "text" + " name=" + "selitys" + " id=" + "selitys" + " />");
        out.println("<input type=" + "submit" + " value=" + "Lisää" + " />");
        out.println("</fieldset>");
        out.println("</form>");
    }

    private void dropDownKayttajanProjektit(PrintWriter out, HttpServletRequest request) throws Exception {
        ResultSet projektit = new TKYhteysOsallistuminen().haeProjektit(new Kayttaja(request));
        out.println("<select name=" + "projekti" + ">");
        while (projektit.next()) {
            out.println("<option value=" + projektit.getString("participation_id") + " >" + projektit.getString("project_name") + ">");
        }
        out.println("</select>");

    }

    private void paivays(PrintWriter out) {
        Calendar c = new GregorianCalendar();

        out.println("<label for=" + "pv" + ">PV:</label>");
        out.println("<input type=" + "number" + " name=" + "pv" + " id=" + "pv" + " min=1 max=31 value=1" + " />");

        out.println("<label for=" + "kk" + ">KK:</label>");
        out.println("<input type=" + "number" + " name=" + "kk" + " id=" + "kk" + " min=1 max=12 value=1" + " />");

        out.println("<label for=" + "vuosi" + ">VVVV:</label>");
        out.println("<input type=" + "number" + " name=" + "vuosi" + " id=" + "vuosi" + " value=2013" + " />");
    }
}