package Tyokalut;


import Sovelluslogiikka.Kayttaja;
import Tietokantayhteydet.TKYhteys;
import Tietokantayhteydet.TKYhteysKayttaja;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;


/**
 * Hoitaa kirjautumisen.
 * @author Arto
 */
public class Kirjautuja {

    /**
     *
     * @param session
     * @param tunnus
     * @param salasana
     * @return
     * @throws Exception
     */
    public static Kayttaja doKirjauduSisaan(HttpSession session, String tunnus, String salasana) throws Exception {
        TKYhteysKayttaja yhteys = new TKYhteysKayttaja();
        Kayttaja kayttaja = yhteys.haeKayttaja(tunnus, salasana);
        session.setAttribute(kayttajaSessioAvain(), kayttaja.getId());
        return kayttaja;
    }

    private static String kayttajaSessioAvain() {
        return "kayttaja_id";
    }

    /**
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static Kayttaja getKirjautunutKayttaja(HttpSession session) throws Exception{

        int kayttajaId = ((Integer) session.getAttribute(kayttajaSessioAvain())).intValue();
        TKYhteysKayttaja yhteys = new TKYhteysKayttaja();
        Kayttaja kayttaja = yhteys.haeKayttaja(kayttajaId);
        return kayttaja;

    }
}