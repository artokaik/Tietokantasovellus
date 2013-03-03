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
 * Tulostaa sivun, jolta voi valita tulostettavan raportin.
 *
 * @author Arto
 */
@WebServlet(name = "Raportit", urlPatterns = {"/Raportit"})
public class Raportit extends SuperServlet {

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
                out.println("<title>Raportit</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Raportit:</h1>");

                projektinYhteenvetoRaportti(out);

                kayttajanYhteenvetoRaportti(out, request);

                this.kaikkiKirjaukset(out, request);

                mainMenuunNappi(out);

                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    private void projektinYhteenvetoRaportti(PrintWriter out) {
        try {
            out.println("<form action=\"RaporttiProjekti\" method=\"POST\">");
            out.println("<label for=" + "projekti" + ">Valitse projekti</label>");
            super.dropDownKaikkiProjektit(out);
            out.println("<input type=" + "submit" + " value=" + "'Näytä yhteenveto'" + " />");
            out.println("</form>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void kaikkiKirjaukset(PrintWriter out, HttpServletRequest request) {
        try {
            out.println("<form action=\"RaporttiKaikkiKirjaukset\" method=\"POST\">");
            if (new Kayttaja(request).onkoAdmin()) {
                out.println("<label for=" + "kayttaja" + ">Valitse käyttäjä</label>");
                super.dropDownKaikkiKayttajat(out);
            } else {
                out.println("<label for=" + "kayttaja" + ">Listaus työaikakirjauksista:</label>");
                out.println("<input type='hidden' name='kayttaja' value='" + ((Integer) request.getSession().getAttribute("kayttaja_id")) + "'>");
            }
            out.println("<input type=" + "submit" + " value=" + "'Näytä kirjaukset'" + " />");
            out.println("</form>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void kayttajanYhteenvetoRaportti(PrintWriter out, HttpServletRequest request) {
        try {
            out.println("<form action=\"RaporttiKayttaja\" method=\"POST\">");
            if (new Kayttaja(request).onkoAdmin()) {
                out.println("<label for=" + "kayttaja" + ">Valitse käyttäjä</label>");
                super.dropDownKaikkiKayttajat(out);
            } else {
                out.println("<label for=" + "kayttaja" + ">Yhteenveto omista työtunneista</label>");
                out.println("<input type='hidden' name='kayttaja' value='" + ((Integer) request.getSession().getAttribute("kayttaja_id")) + "'>");
            }
            out.println("<input type=" + "submit" + " value=" + "'Näytä yhteenveto'" + " />");
            out.println("</form>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
