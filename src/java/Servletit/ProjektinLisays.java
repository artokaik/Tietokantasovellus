/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tulostaa sivun, jolla pääkäyttäjä voi luoda uuden projektin.
 * @author Arto
 */
@WebServlet(name = "ProjektinLisays", urlPatterns = {"/ProjektinLisays"})
public class ProjektinLisays extends SuperServlet {


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
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("<title>Projektin lisays</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Projektin lisäys</h1>");
                tulostaForm(out, request, response);
                mainMenuunNappi(out);
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    private void tulostaForm(PrintWriter out, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (onkoAdmin(request,response)) {
            out.println("<form action=\"ProjektinTallennus\" method=\"POST\">");
            out.println("<fieldset>");
            out.println("<legend>Kirjoita projektin nimi</legend>");
            out.println("<label for=" + "nimi" + ">Projektin nimi:</label>");
            out.println("<input type=" + "text" + " name=" + "nimi" + " id=" + "nimi" + " />");
            out.println("<input type=" + "submit" + " value=" + "Lisää" + " />");
            out.println("</fieldset>");
            out.println("</form>");
        }
    }

}