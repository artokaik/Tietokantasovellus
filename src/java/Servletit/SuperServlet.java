/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysProjekti;
import Tietokantayhteydet.TKYhteysTyolaji;
import Tyokalut.Kirjautuja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Arto
 */
@WebServlet(name = "SuperServlet", urlPatterns = {"/SuperServlet"})
public class SuperServlet extends HttpServlet {

    public void mainMenuun(Kayttaja kayttaja, HttpServletResponse response) throws IOException {
        ohjaaSivulle("MainMenu", response);
    }

    public void kirjautumisSivulle(HttpServletResponse response) throws IOException {
        ohjaaSivulle("index.jsp", response);
    }

    protected void ohjaaSivulle(String sivu, HttpServletResponse response) throws IOException {
        response.sendRedirect(sivu);
    }

    protected void dropDownKaikkiProjektit(PrintWriter out) throws Exception {
        ResultSet projektit = new TKYhteysProjekti().haeProjektit();
        out.println("<select name=" + "projekti" + ">");
        while (projektit.next()) {
            out.println("<option value=" + projektit.getString("id") + " >" + projektit.getString("project_name") + ">");
        }
        out.println("</select>");
    }

    protected void dropDownKaikkiKayttajat(PrintWriter out) throws Exception {
        ResultSet projektit = new TKYhteysProjekti().haeProjektit();
        out.println("<select name=" + "projekti" + ">");
        while (projektit.next()) {
            out.println("<option value=" + projektit.getString("id") + " >" + projektit.getString("project_name") + ">");
        }
        out.println("</select>");
    }

    protected void dropDownTyolaji(PrintWriter out) throws Exception {
        ResultSet tyolajit = new TKYhteysTyolaji().haeTyolajit();
        out.println("<select name=" + "tyolaji" + ">");
        while (tyolajit.next()) {
            out.println("<option value=" + tyolajit.getString("id") + " >" + tyolajit.getString("description"));
        }
        out.println("</select>");

    }

    protected void mainMenuunNappi(PrintWriter out) {
        out.println("<form action=\"MainMenu\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Palaa'" + " />");
        out.println("</form>");
    }

    protected boolean onkoKirjautunut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            request.setAttribute("kayttaja", Kirjautuja.getKirjautunutKayttaja(session));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SuperServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        ohjaaSivulle("index.jsp", response);
        return false;
    }

    protected boolean onkoAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
        HttpSession session = request.getSession(true);
        if (Kirjautuja.getKirjautunutKayttaja(session).onkoAdmin()) {
            return this.onkoKirjautunut(request, response);
        }
        } catch (Exception e){
        }
        return false;
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}