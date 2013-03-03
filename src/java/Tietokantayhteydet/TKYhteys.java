package Tietokantayhteydet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * TKYhteys on luokka, jonka muut tietokantayhteysluokat perivät. Lisäksi
 * TKYhteys-luokalla on muutama metodi, jotka hakevat tietoa kaikista kaikista
 * tietokannan tauluista.
 *
 * @author Arto
 */
public class TKYhteys {

    DataSource ds;

    /**
     *
     * @throws NamingException
     */
    public TKYhteys() throws NamingException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/arto");
        } catch (NamingException e) {
            System.out.println(e);
        }

    }

    /**
     * Palauttaa tietokantayhteyden.
     *
     * @return
     * @throws SQLException
     */
    protected Connection luoYhteys() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Palauttaa Resultsetin, jossa on parametrina annetun projektin
     * kokonaistunnit työlajin ja käyttäjän mukaan jaoteltuna.
     *
     * @param projectID
     * @return
     * @throws Exception
     */
    public ResultSet haeProjektinTunnit(int projectID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("select works.description as description, username, sum (hours) as hours "
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

    /**
     * Palauttaa ResultSetin, jossa on parametrina annetun käyttäjän kirjaamat
     * tunnit projektin ja työlajin mukaan jaoteltuna.
     *
     * @param userID
     * @return
     * @throws Exception
     */
    public ResultSet haeKayttajanTunnit(int userID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("select works.description as description, project_name, sum (hours) as hours "
                + "from users, timeentry, works, projects, participation "
                + "where timeentry.work_id = works.id "
                + "and participation_id = participation.id "
                + "and participation.project_id = projects.id "
                + "and participation.user_id = users.id "
                + "and users.id = ?"
                + "group by participation_id, work_id, works.description, project_name, username");
        y.setInt(1, userID);
        ResultSet tunnit = y.executeQuery();
        yhteys.close();
        return tunnit;
    }
}
