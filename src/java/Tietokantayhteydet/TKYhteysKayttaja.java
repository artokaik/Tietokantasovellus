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
public class TKYhteysKayttaja extends TKYhteys {

    public TKYhteysKayttaja() throws NamingException {
        super();
    }

    public Kayttaja haeKayttaja(String tunnus, String salasana) throws Exception {
        Connection yhteys = luoYhteys();
        System.out.println("kkkhaeKayttaja1");
        PreparedStatement s = this.ds.getConnection().prepareStatement("Select * from users where username = ? and password = ?");
        s.setString(1, tunnus);
        s.setString(2, salasana);
        return haeKayttaja(s, yhteys);
    }

    public Kayttaja haeKayttaja(PreparedStatement s, Connection yhteys) throws Exception {
        System.out.println("kkkhaeKayttaja2");

        Kayttaja kayttaja = null;
        ResultSet kayttajat = s.executeQuery();
        if (kayttajat.next()) {
            kayttaja = new Kayttaja(kayttajat.getInt("id"));
        }
        yhteys.close();
        return kayttaja;
    }

    public Kayttaja haeKayttaja(long id) throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement s = null;
        s = this.ds.getConnection().prepareStatement("Select * from users where id = ?");
        s.setLong(1, id);
        return haeKayttaja(s, yhteys);

    }

    public ResultSet haeKayttajat() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from users");
        ResultSet kayttajat = y.executeQuery();
        yhteys.close();
        return kayttajat;
    }
    
    
    
    public boolean onkoAdmin(Kayttaja kayttaja) throws Exception{
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from users where id = ?");
        y.setLong(1, kayttaja.getId());
        ResultSet kayttajat = y.executeQuery();
        if(!kayttajat.next()){
            return false;
        }
        yhteys.close();
        return kayttajat.getBoolean("administrator");
    }

    public DataSource getDs() {
        return ds;
    }
}
