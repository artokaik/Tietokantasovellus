package Tyokalut;


import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteys;
import Tietokantayhteydet.TKYhteysKayttaja;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arto
 */
public class Kirjautuja {

    public static Kayttaja doKirjauduSisaan(HttpSession session, String tunnus, String salasana) throws Exception {
        TKYhteysKayttaja yhteys = new TKYhteysKayttaja();
        Kayttaja kayttaja = yhteys.haeKayttaja(tunnus, salasana);
        session.setAttribute(kayttajaSessioAvain(), kayttaja.getId());
        return kayttaja;
    }

    private static String kayttajaSessioAvain() {
        return "kayttaja_id";
    }

    public static Kayttaja getKirjautunutKayttaja(HttpSession session) throws Exception{

        int kayttajaId = ((Integer) session.getAttribute(kayttajaSessioAvain())).intValue();
        TKYhteysKayttaja yhteys = new TKYhteysKayttaja();
        Kayttaja kayttaja = yhteys.haeKayttaja(kayttajaId);
        return kayttaja;

    }
}