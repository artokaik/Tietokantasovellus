/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantayhteydet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.NamingException;

/**
 * Works-taulua käyttävä luokka.
 *
 * @author Arto
 */
public class TKYhteysTyolaji extends TKYhteys {

    /**
     *
     * @throws NamingException
     */
    public TKYhteysTyolaji() throws NamingException {
        super();
    }

    /**
     * Palauttaa ResultSettinä kaikki työlajit.
     *
     * @return
     * @throws Exception
     */
    public ResultSet haeTyolajit() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from works");
        ResultSet tyolajit = y.executeQuery();
        yhteys.close();
        return tyolajit;
    }

    /**
     * Lisää työlajin tietokantaan.
     *
     * @param nimi
     * @throws Exception
     */
    public void lisaaTyolaji(String nimi) throws Exception {
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = yhteys.prepareStatement("INSERT INTO works (description) VALUES"
                + "(?) RETURNING description");
        y.setString(1, nimi);
        y.executeQuery().close();
        y.close();
        yhteys.close();
    }
}
