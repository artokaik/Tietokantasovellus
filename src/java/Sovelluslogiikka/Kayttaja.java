/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sovelluslogiikka;

import Tietokantayhteydet.TKYhteysKayttaja;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Olio, joka kuvaa järjestelmän käyttäjää.
 * @author Arto
 */
public class Kayttaja {

    private int id;

    /**
     *
     * @param id
     */
    public Kayttaja(int id) {
        this.id = id;
    }

    /**
     * Luo uuden käyttäjä-olion, joka vastaa kirjautunutta käyttäjää.
     * @param request
     */
    public Kayttaja(HttpServletRequest request) {
        this((Integer) request.getSession().getAttribute("kayttaja_id"));
    }

    /**
     * Palauttaa true jos käyttäjällä on pääkäyttäjän oikeudet, muuten false
     * @return
     */
    public boolean onkoAdmin() {
        try {
            return new TKYhteysKayttaja().onkoAdmin(this);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Palauttaa true jos käyttäjä on parametrina annetun projektin projektimanageri.
     * @param projektiID
     * @return
     */
    public boolean onkoManageri(int projektiID) {
        try {
            return new TKYhteysKayttaja().onkoAdmin(this);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }
}
