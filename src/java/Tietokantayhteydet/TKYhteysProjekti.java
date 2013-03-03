/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantayhteydet;

import Sovelluslogiikka.Kayttaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.NamingException;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Luokka, jonka metodit käyttävät projects-taulua.
 *
 * @author Arto
 */
public class TKYhteysProjekti extends TKYhteys {

    /**
     *
     * @throws NamingException
     */
    public TKYhteysProjekti() throws NamingException {
        super();
    }

    /**
     * Palauttaa ResultSetissä kaikki projektit.
     *
     * @return
     * @throws Exception
     */
    public ResultSet haeProjektit() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from projects");
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        return projektit;
    }

    /**
     * Lisää projekin tietokantaan.
     *
     * @param nimi
     * @throws Exception
     */
    public void lisaaProjekti(String nimi) throws Exception {
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = yhteys.prepareStatement("INSERT INTO projects (project_name) VALUES"
                + "(?) RETURNING project_name");
        y.setString(1, nimi);
        y.executeQuery().close();
        y.close();
        yhteys.close();
    }

    /**
     * hakee parametrina annettua projektiID:tä vastaavan projektin nimen.
     *
     * @param projectID
     * @return
     * @throws Exception
     */
    public String haeProjektinNimi(int projectID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select project_name from projects where id = ?");
        y.setInt(1, projectID);
        ResultSet projekti = y.executeQuery();
        projekti.next();
        String nimi = projekti.getString("project_name");
        y.close();
        yhteys.close();
        projekti.close();
        return nimi;
    }
}
