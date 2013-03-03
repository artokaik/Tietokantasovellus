/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servletit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Tulostaa html-sivun, jolla voi lisätä uuden käyttäjän järjestelmään.
 * 
 * @author Arto
 */
public class KayttajanLisays extends SuperServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Käyttäjän lisäys</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Käyttäjän lisäys</h1>");
            this.tulostaForm(out, request);
            super.mainMenuunNappi(out);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void tulostaForm(PrintWriter out, HttpServletRequest request) {
        out.println("<form action=\"KayttajanTallennus\" method=\"POST\">");
        out.println("<fieldset>");
        out.println("<label for=" + "nimi" + ">Käyttäjätunnus:</label>");
        out.println("<input type=" + "text" + " name=" + "username" + " id=" + "username" + " />");
        out.println("<label for=" + "nimi" + ">Salasana:</label>");
        out.println("<input type=" + "text" + " name=" + "password" + " id=" + "password" + " />");
        out.println("<input type=" + "submit" + " value=" + "Lisää" + " />");
        out.println("</fieldset>");
        out.println("</form>");
    }
}