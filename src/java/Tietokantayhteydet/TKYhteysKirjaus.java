/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantayhteydet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.naming.NamingException;

/**
 *
 * @author Arto
 */
public class TKYhteysKirjaus extends TKYhteys {

    /**
     * TKYhteysKirjaus-luokan metodit käyttävät timeEntry-taulua.
     * @throws NamingException
     */
    public TKYhteysKirjaus() throws NamingException {
        super();
    }

    /**
     * Lisää tuntikirjauksen tietokantaan.
     * @param participationID
     * @param tyolajiID
     * @param tunnit
     * @param selitys
     * @param paivays
     */
    public void lisaaKirjaus(int participationID, int tyolajiID, double tunnit, String selitys, Date paivays) {
        try {
            Connection yhteys = this.luoYhteys();
            PreparedStatement y;
            y = yhteys.prepareStatement("INSERT INTO timeentry (participation_id,work_id,hours,description, workday) VALUES"
                    + "(?,?,?,?,?) RETURNING participation_id,work_id,hours,description, workday");
            y.setInt(1, participationID);
            y.setInt(2, tyolajiID);
            y.setDouble(3, tunnit);
            y.setString(4, selitys);
            y.setDate(5, paivays);
            y.executeQuery().close();
            yhteys.close();
            y.close();
        } catch (SQLException e) {
        }
    }

    /**
     * Palauttaa käyttäjän kaikki kirjaukset resultsettinä. Kenttien nimet ovat username, date, project_name, work, hours ja description.
     * @param userID
     * @return
     */
    public ResultSet kayttajanKaikkiKirjaukset(int userID) {
        try {
            Connection yhteys = this.luoYhteys();
            PreparedStatement y = yhteys.prepareStatement("select username, workday as date, project_name, works.description as work, hours, timeentry.description as description "
                    + "from users, timeentry, works, projects, participation "
                    + "where timeentry.work_id = works.id "
                    + "and participation_id = participation.id "
                    + "and participation.project_id = projects.id "
                    + "and participation.user_id = users.id "
                    + "and users.id = ?"
                    + "order by username, date");
            y.setInt(1, userID);
            ResultSet resultset = y.executeQuery();
            yhteys.close();
            return resultset;
        } catch (SQLException e) {
            System.out.println("kayttajanKaikkiKirjaukset");
            System.out.println(e);
        }
        return null;
    }
}
