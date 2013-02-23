/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arto
 */
@WebServlet(name = "Raportit", urlPatterns = {"/Raportit"})
public class Raportit extends SuperServlet {

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

        if (super.onkoKirjautunut(request, response)) {
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
                try {
                    out.println("<form action=\"RaporttiProjekti\" method=\"POST\">");
                    super.dropDownKaikkiProjektit(out);
                    out.println("<input type=" + "submit" + " value=" + "'Näytä tunnit'" + " />");
                    out.println("</form>");
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
    }
}
