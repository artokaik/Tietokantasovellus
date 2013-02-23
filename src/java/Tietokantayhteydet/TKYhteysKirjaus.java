/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantayhteydet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.naming.NamingException;

/**
 *
 * @author Arto
 */
public class TKYhteysKirjaus extends TKYhteys {

    public TKYhteysKirjaus() throws NamingException {
        super();
    }

    public void lisaaKirjaus(int participationID, int tyolajiID, double tunnit, String selitys, Date paivays) {
        try {
            Connection yhteys = this.luoYhteys();
            PreparedStatement y;
            y = ds.getConnection().prepareStatement("INSERT INTO timeentry (participation_id,work_id,hours,description, workday) VALUES"
                    + "(?,?,?,?,?) RETURNING participation_id,work_id,hours,description, workday");
            y.setInt(1, participationID);
            y.setInt(2, tyolajiID);
            y.setDouble(3, tunnit);
            y.setString(4, selitys);
            y.setDate(5, paivays);
            y.executeQuery();
            yhteys.close();
        } catch (SQLException e) {
            
        }
    }
}
