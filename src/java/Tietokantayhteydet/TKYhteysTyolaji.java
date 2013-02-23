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
 *
 * @author Arto
 */
public class TKYhteysTyolaji extends TKYhteys {

    public TKYhteysTyolaji() throws NamingException {
        super();
    }
    
    public ResultSet haeTyolajit() throws Exception {
        Connection yhteys = luoYhteys();
        PreparedStatement y = this.ds.getConnection().prepareStatement("Select * from works");
        ResultSet tyolajit = y.executeQuery();
        yhteys.close();
        return tyolajit;
    }
}
