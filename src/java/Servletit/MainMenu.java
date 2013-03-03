package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Tulostaa MainMenun
 * 
 * @author Arto
 */
@WebServlet(name = "MainMenu", urlPatterns = {"/MainMenu"})
public class MainMenu extends SuperServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (onkoKirjautunut(request, response)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<html>");
                out.println("<head>");
                out.println("<title>MainMenu</title>");
                out.println("</head>");
                out.println("<body>");
                this.ulosKirjautuminen(out);
                this.tuntienLisays(out);
                this.raportit(out);
                out.println("<br>");
                this.kayttajanLisays(out, request);
                this.osallistumisenLisays(out, request);
                this.projektinLisays(out, request);
                this.tyolajinLisays(out, request);


                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

 
    private void ulosKirjautuminen(PrintWriter out) {
        out.println("<form action=\"Uloskirjautuminen\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Kirjaudu ulos'" + " />");
        out.println("</form>");
    }

  
    private void tuntienLisays(PrintWriter out) {
        out.println("<form action=\"TuntienLisays\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Lisää tuntikirjaus'" + " />");
        out.println("</form>");
    }


    private void kayttajanLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            try {
                out.println("<form action=\"KayttajanLisays\" method=\"POST\">");
                out.println("<input type=" + "submit" + " value=" + "'Lisää Käyttäjä'" + " />");
                out.println("</form>");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private void tyolajinLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            try {
                out.println("<form action=\"TyolajinLisays\" method=\"POST\">");
                out.println("<input type=" + "submit" + " value=" + "'Lisää työlaji'" + " />");
                out.println("</form>");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private void osallistumisenLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            try {
                out.println("<form action=\"OsallistumisenLisays\" method=\"POST\">");
                dropDownKaikkiProjektit(out);
                out.println("<input type=" + "submit" + " value=" + "'Lisää osallistuminen'" + " />");
                out.println("</form>");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private void projektinLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            out.println("<form action=\"ProjektinLisays\" method=\"POST\">");
            out.println("<input type=" + "submit" + " value=" + "'Lisää uusi projekti'" + " />");
            out.println("</form>");
        }
    }


    private void raportit(PrintWriter out) {
        out.println("<form action=\"Raportit\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Raportit'" + " />");
        out.println("</form>");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
