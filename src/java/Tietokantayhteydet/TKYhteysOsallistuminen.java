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

/**
 *
 * @author Arto
 */
public class TKYhteysOsallistuminen extends TKYhteys {

    public TKYhteysOsallistuminen() throws NamingException {
        super();
    }

    public ResultSet haeProjektit(Kayttaja kayttaja) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select participation.id as participation_id, project_name from participation, projects where user_id = ? and projects.id = participation.project_id");
        y.setInt(1, kayttaja.getId());
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        return projektit;
    }

    public boolean onkoManageri(int userID, int projectID) throws Exception{
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = ds.getConnection().prepareStatement("SELECT * from PARTICIPATION where user_id = ? and project_id = ?");
        y.setInt(1, userID);
        y.setInt(2, projectID);   
        y.executeQuery();
        ResultSet r = y.getResultSet();
        yhteys.close();
        while(r.next()){
            if(r.getBoolean("manager")){               
                return true;
            }
        }
        return false;
    }

    public void lisaaOsallistuminen(int projectID, int userID, boolean isManager) throws Exception {
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = ds.getConnection().prepareStatement("INSERT INTO PARTICIPATION (project_id, user_id, manager) VALUES"
                + "(?,?, ?) RETURNING project_id, user_id, manager");
        y.setInt(1, projectID);
        y.setInt(2, userID);
        y.setBoolean(3, isManager);

        y.executeQuery();
        yhteys.close();
    }

    public ResultSet haeUudetProjektit(Kayttaja kayttaja) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from projects where id not in (select project_id from participation where user_ID = ?) order by project_name");
        y.setInt(1, kayttaja.getId());
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        return projektit;
    }

    public ResultSet haeUudetKayttajat(int projektiID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from users where id not in (select user_id from participation where project_ID = ?) order by username");
        y.setInt(1, projektiID);
        ResultSet kayttajat = y.executeQuery();
        yhteys.close();
        return kayttajat;
    }
}
