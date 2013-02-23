/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Tietokantayhteydet.TKYhteys;
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
@WebServlet(name = "RaporttiProjekti", urlPatterns = {"/RaporttiProjekti"})
public class RaporttiProjekti extends SuperServlet {



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
        System.out.println("prora1");
        try {
            System.out.println("prora2");
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProjektiRaportti</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjektiRaportti at " + request.getContextPath() + "</h1>");
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
    
    public void raportti(PrintWriter out, HttpServletRequest request) throws Exception {
        int projektiID = Integer.parseInt(request.getParameter("projekti"));
        ResultSet r = new TKYhteys().haeProjektinTunnit(projektiID);
        out.println("<h4>" + new TKYhteysProjekti().haeProjektinNimi(projektiID) + "</h4>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println(" <td><b>Työlaji</b></td>");
        out.println(" <td><b>Käyttäjä</b></td>");
        out.println(" <td><b>Tunnit</b></td>");
        out.println("</tr>");
        while (r.next()) {
            out.println("<tr>");
            out.println(" <td>" + r.getString("description") + "</td>");
            out.println(" <td>" + r.getString("username") + "</td>");
            out.println(" <td>" + r.getString("hours") + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

}