package Servletit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Sovelluslogiikka.Kayttaja;
import Tyokalut.Kirjautuja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Hoitaa sisäänkirjautumisen.
 * @author Arto
 */
@WebServlet(name = "Kirjautuminen", urlPatterns = {"/Kirjautuminen"})
public class Kirjautuminen extends SuperServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            kirjautumisSivulle(response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        try {
            Kayttaja kayttaja = Kirjautuja.doKirjauduSisaan(session, tunnus, salasana);
            mainMenuun(kayttaja, response);
            
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("virheviesti", "Tarkista käyttäjätunnus ja salasana");
        }
    }    
    
}

