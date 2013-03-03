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
import javax.servlet.http.HttpSession;

/**
 * Kirjaa käyttäjän ulos ja ohjaa tämän kirjautumissivulle.
 * @author Arto
 */
@WebServlet(name = "Uloskirjautuminen", urlPatterns = {"/Uloskirjautuminen"})
public class Uloskirjautuminen extends SuperServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("kayttaja_id");
        ohjaaSivulle("index.jsp", response);
    }



}
