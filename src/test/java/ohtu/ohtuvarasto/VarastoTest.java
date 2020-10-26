package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoEiVoiOllaTilavuudeltaNegatiivinen() {
        assertEquals(0, new Varasto(-1).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudelleVarastolleAnnettuAlkusaldoOnOikein() {
        assertEquals(10, new Varasto(10,10).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudelleVarastolleAnnettuAlkusaldoTilavuusEiVoiOllaNegatiivinen() {
        assertEquals(0, new Varasto(-1, 0).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void annettuAlkusaldoEiVoiOllaNegatiivinen() {
        assertEquals(0, new Varasto(10, -1).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldonMahtuessaLisataanAlkusaldo() {
        assertEquals(10, new Varasto(10, 10).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianPaljonTayttaessaTaytetaanMaksimiin() {
        assertEquals(10, new Varasto(10, 11).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void paljonkoMahtuuToimii() {
        assertEquals(5, new Varasto(10, 5).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaEiVoiLisata() {
        varasto.lisaaVarastoon(-3);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliEiVoiTayttaa() {
        varasto.lisaaVarastoon(11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivista() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(10), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }



}