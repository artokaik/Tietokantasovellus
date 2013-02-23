package Tietokantayhteydet;

import Sovelluslogiikka.Kayttaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.tomcat.jdbc.pool.DataSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Arto
 */
public class TKYhteys {

    DataSource ds;

    public TKYhteys() throws NamingException {
        try {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/arto");
        } catch (NamingException e){
            System.out.println(e);
        }

    }

    protected Connection luoYhteys() throws SQLException {
        return ds.getConnection();
    }

    public ResultSet haeProjektinTunnit(int projectID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("select works.description as description, username, sum (hours) as hours " 
                + "from users, timeentry, works, projects, participation "
                + "where timeentry.work_id = works.id "
                + "and participation_id = participation.id "
                + "and participation.project_id = projects.id "
                + "and participation.user_id = users.id "
                + "and projects.id = ?"
                + "group by participation_id, work_id, works.description, project_name, username");
        y.setInt(1, projectID);
        ResultSet tunnit = y.executeQuery();
        yhteys.close();
        return tunnit;
    }
    
        public ResultSet haeKayttajanTunnit(Kayttaja kayttaja) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("select works.description as description, project_name, sum (hours) as hours " 
                + "from users, timeentry, works, projects, participation "
                + "where timeentry.work_id = works.id "
                + "and participation_id = participation.id "
                + "and participation.project_id = projects.id "
                + "and participation.user_id = users.id "
                + "and users.id = ?"
                + "group by participation_id, work_id, works.description, project_name, username");
        y.setInt(1, kayttaja.getId());
        ResultSet tunnit = y.executeQuery();
        yhteys.close();
        return tunnit;
    }
}
