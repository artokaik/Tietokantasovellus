/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sovelluslogiikka;

import Tietokantayhteydet.TKYhteysKayttaja;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Arto
 */
public class Kayttaja {

    private int id;

    public Kayttaja(int id) {
        this.id = id;
    }

    public Kayttaja(HttpServletRequest request) {
        this((Integer) request.getSession().getAttribute("kayttaja_id"));

    }

    public boolean onkoAdmin() {
        try {
            return new TKYhteysKayttaja().onkoAdmin(this);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean onkoManageri(int projektiID) {
        try {
            return new TKYhteysKayttaja().onkoAdmin(this);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public int getId() {
        return id;
    }
}
