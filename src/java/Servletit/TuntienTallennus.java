package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysKirjaus;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arto
 */
@WebServlet(name = "TuntienTallennus", urlPatterns = {"/TuntienTallennus"})
public class TuntienTallennus extends SuperServlet {

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

        try {
            int pv = Integer.parseInt(request.getParameter("pv"));
            int kk = Integer.parseInt(request.getParameter("kk"));
            int vuosi = Integer.parseInt(request.getParameter("vuosi"));

            Date paivays = new Date(vuosi - 1900, kk - 1, pv);

            PrintWriter out = response.getWriter();
            int participationID = Integer.parseInt(request.getParameter("projekti"));
            int tyolajiID = Integer.parseInt(request.getParameter("tyolaji"));
            double tunnit = Double.parseDouble(request.getParameter("tunnit"));
            String selitys = request.getParameter("selitys");
            new TKYhteysKirjaus().lisaaKirjaus(participationID, tyolajiID, tunnit, selitys, paivays);
            mainMenuun(new Kayttaja(request), response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

