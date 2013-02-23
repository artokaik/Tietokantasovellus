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
 *
 * @author Arto
 */
public class TKYhteysProjekti extends TKYhteys {

    public TKYhteysProjekti() throws NamingException {
        super();
    }

    public ResultSet haeProjektit() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from projects");
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        return projektit;
    }

    public void lisaaProjekti(String nimi) throws Exception{
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = ds.getConnection().prepareStatement("INSERT INTO projects (project_name) VALUES"
                + "(?) RETURNING project_name");
        y.setString(1, nimi);
        y.executeQuery();
        yhteys.close();
    }
    
        public String haeProjektinNimi(int projectID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select project_name from projects where id = ?");
        y.setInt(1, projectID);
        ResultSet projekti = y.executeQuery();
        projekti.next();
        yhteys.close();
        return projekti.getString("project_name");
    }
}
