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
 * TKYhteysOsallistuminen on luokka joka käyttää participation-taulua.
 * @author Arto
 */
public class TKYhteysOsallistuminen extends TKYhteys {

    /**
     *
     * @throws NamingException
     */
    public TKYhteysOsallistuminen() throws NamingException {
        super();
    }

    /**
     * Hakee kaikki projektit, joihin parametrina annettu käyttäjä osallistuu.
     * @param kayttaja
     * @return
     * @throws Exception
     */
    public ResultSet haeProjektit(Kayttaja kayttaja) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select participation.id as participation_id, project_name from participation, projects where user_id = ? and projects.id = participation.project_id");
        y.setInt(1, kayttaja.getId());
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        return projektit;
    }

    /**
     * Palauttaa true jos parametrina annettu käyttäjäID on parametrina annetun projektiID:n projektimanageri. Muuten palauttaa false.
     * @param userID
     * @param projectID
     * @return
     * @throws Exception
     */
    public boolean onkoManageri(int userID, int projectID) throws Exception{
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = yhteys.prepareStatement("SELECT * from PARTICIPATION where user_id = ? and project_id = ?");
        y.setInt(1, userID);
        y.setInt(2, projectID);   
        y.executeQuery();
        ResultSet r = y.getResultSet();
        yhteys.close();
        r.close();
        y.close();
        while(r.next()){
            if(r.getBoolean("manager")){               
                return true;
            }
        }
        return false;
    }

    /**
     * Lisää osallistumisen tietokantaan.
     * @param projectID
     * @param userID
     * @param isManager
     * @throws Exception
     */
    public void lisaaOsallistuminen(int projectID, int userID, boolean isManager) throws Exception {
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = yhteys.prepareStatement("INSERT INTO PARTICIPATION (project_id, user_id, manager) VALUES"
                + "(?,?, ?) RETURNING project_id, user_id, manager");
        y.setInt(1, projectID);
        y.setInt(2, userID);
        y.setBoolean(3, isManager);

        y.executeQuery().close();
        y.close();
        yhteys.close();
    }

    /**
     * Palauttaa ResultSetin projekteista, joihin parametrina annettu käyttäjä ei osallistu.
     * @param kayttaja
     * @return
     * @throws Exception
     */
    public ResultSet haeUudetProjektit(Kayttaja kayttaja) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from projects where id not in (select project_id from participation where user_ID = ?) order by project_name");
        y.setInt(1, kayttaja.getId());
        ResultSet projektit = y.executeQuery();
        yhteys.close();
        y.closeOnCompletion();
        return projektit;
    }

    /**
     * Palauttaa resultsetin käyttäjistä, jotka eivät osallistu parametrina annettuun projektiin.
     * @param projektiID
     * @return
     * @throws Exception
     */
    public ResultSet haeUudetKayttajat(int projektiID) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from users where id not in (select user_id from participation where project_ID = ?) order by username");
        y.setInt(1, projektiID);
        ResultSet kayttajat = y.executeQuery();
        yhteys.close();
        return kayttajat;
    }
}
