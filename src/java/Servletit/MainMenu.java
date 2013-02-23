package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
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
@WebServlet(name = "MainMenu", urlPatterns = {"/MainMenu"})
public class MainMenu extends SuperServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (onkoKirjautunut(request, response)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet MainMenu</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p><a href=index.jsp >Kirjaudu ulos</a></p>");

                this.ulosKirjautuminen(out);
                this.tuntienLisays(out, request);
                this.osallistumisenLisays(out, request);
                this.projektinLisays(out, request);
                this.raportit(out);

                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    public void ulosKirjautuminen(PrintWriter out) {
        out.println("<form action=\"Uloskirjautuminen\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Kirjaudu ulos'" + " />");
        out.println("</form>");
    }

    public void tuntienLisays(PrintWriter out, HttpServletRequest request) {
        out.println("<form action=\"TuntienLisays\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Lisää tuntikirjaus'" + " />");
        out.println("</form>");
    }

    public void osallistumisenLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            try {
                out.println("<form action=\"OsallistumisenLisays\" method=\"POST\">");
                dropDownKaikkiProjektit(out);
                out.println("<input type=" + "submit" + " value=" + "'Lisää osallistuminen'" + " />");
                out.println("</form>");
            } catch (Exception e) {
                System.out.println("dd");
                System.out.println(e);
            }
        }
    }

    public void projektinLisays(PrintWriter out, HttpServletRequest request) {
        if (new Kayttaja(request).onkoAdmin()) {
            out.println("<form action=\"ProjektinLisays\" method=\"POST\">");
            out.println("<input type=" + "submit" + " value=" + "'Lisää uusi projekti'" + " />");
            out.println("</form>");
        }
    }

    public void raportit(PrintWriter out) {
        out.println("<form action=\"Raportit\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Raportit'" + " />");
        out.println("</form>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }
}
