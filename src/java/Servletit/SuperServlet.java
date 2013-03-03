/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteysKayttaja;
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

    /**
     *
     * Ohjaa käyttäjän mainMenuun.
     * 
     * @param kayttaja
     * @param response
     * @throws IOException
     */
    public void mainMenuun(Kayttaja kayttaja, HttpServletResponse response) throws IOException {
        ohjaaSivulle("MainMenu", response);
    }

    /**
     *
     * ohjaa käyttäjän kirjautumissivulle.
     * 
     * @param response
     * @throws IOException
     */
    public void kirjautumisSivulle(HttpServletResponse response) throws IOException {
        ohjaaSivulle("index.jsp", response);
    }

    /**
     * Ohjaa käyttäjän parametrina annetulle sivulle.
     * @param sivu
     * @param response
     * @throws IOException
     */
    protected void ohjaaSivulle(String sivu, HttpServletResponse response) throws IOException {
        response.sendRedirect(sivu);
    }

    /**
     * Tulostaa dropdown-valikon kaikista projekteista html-koodina.
     * @param out
     * @throws Exception
     */
    protected void dropDownKaikkiProjektit(PrintWriter out) throws Exception {
        ResultSet projektit = new TKYhteysProjekti().haeProjektit();
        out.println("<select name=" + "projekti" + ">");
        while (projektit.next()) {
            out.println("<option value=" + projektit.getString("id") + " >" + projektit.getString("project_name") + ">");
        }
        out.println("</select>");
        projektit.close();
    }

    /**
     * Tulostaa dropdown-valikon kaikista käyttäjistä html-koodina.
     * @param out
     * @throws Exception
     */
    protected void dropDownKaikkiKayttajat(PrintWriter out) throws Exception {
        ResultSet kayttajat = new TKYhteysKayttaja().haeKayttajat();
        out.println("<select name=" + "kayttaja" + ">");
        while (kayttajat.next()) {
            out.println("<option value=" + kayttajat.getString("id") + " >" + kayttajat.getString("username") + ">");
        }
        kayttajat.close();
        out.println("</select>");
    }

    /**
     * Tulostaa dropdown-valikon kaikista työlajeista html-koodina.
     * @param out
     * @throws Exception
     */
    protected void dropDownTyolaji(PrintWriter out) throws Exception {
        ResultSet tyolajit = new TKYhteysTyolaji().haeTyolajit();
        out.println("<select name=" + "tyolaji" + ">");
        while (tyolajit.next()) {
            out.println("<option value=" + tyolajit.getString("id") + " >" + tyolajit.getString("description"));
        }
        tyolajit.close();
        out.println("</select>");

    }

    /**
     * Tulostaa html-koodina napin, jolla käyttäjä pääsee takaisin päävalikkoon.
     * @param out
     */
    protected void mainMenuunNappi(PrintWriter out) {
        out.println("<form action=\"MainMenu\" method=\"POST\">");
        out.println("<input type=" + "submit" + " value=" + "'Päävalikkoon'" + " />");
        out.println("</form>");
    }

    /**
     * Palauttaa true jos käyttäjä on kirjautunut. Muussa tapauksessa ohjaa käyttäjän takaisin kirjautumissivulle ja palauttaa false.
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    protected boolean onkoKirjautunut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            request.setAttribute("kayttaja", Kirjautuja.getKirjautunutKayttaja(session));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SuperServlet.class.getName()).log(Level.SEVERE, null, ex);
            kirjautumisSivulle(response);
        }
        return false;

    }

    /**
     * Palauttaa true jos kirjautunut käyttäjä on pääkäyttäjä ja false jos ei ole. Virheen sattuessa (esim. käyttäjä ei ole kirjautunut) ohjaa käyttäjän kirjautumissivulle.
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    protected boolean onkoAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(true);
            if (Kirjautuja.getKirjautunutKayttaja(session).onkoAdmin()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            ohjaaSivulle("index.jsp", response);

        }
        return false;

    }

    /**
     * Suorittaa servletin doPost-metodin mikäli käyttäjä on kirjautunut. Muuten palauttaa kirjautumissivulle.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (onkoKirjautunut(request, response)) {
            this.doPost(request, response);
        } else {
            kirjautumisSivulle(response);
        }
    }
}