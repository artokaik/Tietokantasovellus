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
 * TKYhteysKayttajan metodit käsittelevät users-taulua.
 * @author Arto
 */
public class TKYhteysKayttaja extends TKYhteys {

    /**
     *
     * @throws NamingException
     */
    public TKYhteysKayttaja() throws NamingException {
        super();
    }

    /**
     * Palauttaa Kayttaja-olion, jonka id vastaa parametrina annettua tunnusta ja salasanaa. Jos tunnus ja salasana eivät täsmää, palauttaa null.
     * @param tunnus
     * @param salasana
     * @return
     * @throws Exception
     */
    public Kayttaja haeKayttaja(String tunnus, String salasana) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement s = yhteys.prepareStatement("Select * from users where username = ? and password = ?");
        s.setString(1, tunnus);
        s.setString(2, salasana);
        Kayttaja k = haeKayttaja(s, yhteys);
        s.close();
        return k;
    }

    private Kayttaja haeKayttaja(PreparedStatement s, Connection yhteys) throws Exception {
        Kayttaja kayttaja = null;
        ResultSet kayttajat = s.executeQuery();
        if (kayttajat.next()) {
            kayttaja = new Kayttaja(kayttajat.getInt("id"));
        }
        kayttajat.close();
        yhteys.close();
        return kayttaja;
    }

    /**
     * Mikäli tietokannasta löytyy käyttäjä, jonka id on parametrina annettu id, palauttaa tämä metodi käyttäjä-olion jolla on tuo id. Muuten palauttaa null.
     * @param id
     * @return
     * @throws Exception
     */
    public Kayttaja haeKayttaja(long id) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement s = null;
        s = yhteys.prepareStatement("Select * from users where id = ?");
        s.setLong(1, id); 
        return haeKayttaja(s, yhteys);
    }

    /**
     * Palauttaa ResultSetin, jossa on users-taulu.
     * @return
     * @throws Exception
     */
    public ResultSet haeKayttajat() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from users");
        ResultSet kayttajat = y.executeQuery();
        yhteys.close();
        return kayttajat;
    }
    
    
    
    /**
     * Palauttaa true, jos parametrina annettu käyttäjä on pääkäyttäjä.
     * @param kayttaja
     * @return
     * @throws Exception
     */
    public boolean onkoAdmin(Kayttaja kayttaja) throws Exception{
        Connection yhteys = luoYhteys();
        PreparedStatement y = yhteys.prepareStatement("Select * from users where id = ?");
        y.setLong(1, kayttaja.getId());
        ResultSet kayttajat = y.executeQuery();
        if(!kayttajat.next()){
            return false;
        }
        yhteys.close();
        boolean onko = kayttajat.getBoolean("administrator");
        kayttajat.close();
        y.close();
        return onko;
    }

    /**
     *
     * @return
     */
    public DataSource getDs() {
        return ds;
    }
    
        /**
     * Lisää tietokantaan uuden käyttäjän.
     * @param tunnus
     * @param salasana
     * @param onkoAdmin
     * @throws Exception
     */
    public void lisaaKayttaja(String tunnus, String salasana, boolean onkoAdmin) throws Exception{
        Connection yhteys = this.luoYhteys();
        PreparedStatement y;
        y = yhteys.prepareStatement("INSERT INTO users (username, password, administrator) VALUES"
                + "(?,?,?) RETURNING username, password, administrator");
        y.setString(1, tunnus);
        y.setString(2, salasana);
        y.setBoolean(3, onkoAdmin);
        y.executeQuery().close();
        y.close();
        yhteys.close();
    }
}
