import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Programaren metodo guztiak (interfazea, sarrerak eta menuaren aukerak).
 * ArrayList-a argumentu gisa jasotzen dute.
 */
public class Metodoak {

    // Data zorrotzak irakurtzeko formatua (31/02/1993 bezalako datak baztertzen ditu)
    private static final DateTimeFormatter DATA_IRAKURRI =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    // Datak erakusteko formatua
    private static final DateTimeFormatter DATA_ERAKUTSI = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Taularen mugak (taula ondo tabulatuta geratzeko)
    private static final int IZEN_MAX = 22;
    private static final int KODE_MAX = 99999;
    private static final int STOCK_MAX = 99999;
    private static final double PREZIO_MAX = 9999.99;

    // Kutxen barne-zabalera
    private static final int ZABALERA = 58;

    // Taularen zutabeen zabalerak: KODEA, IZENA, STOCK, PREZIOA, DATA, EKOIZP.
    private static final int[] ZUTABEAK = {7, 24, 7, 10, 12, 9};

    // =========================================================================
    // HASIERAKO DATUAK
    // =========================================================================

    /** Esleitutako datuekin ArrayList-a sortzen du. */
    public static ArrayList<Artikulua> hasieratu() {
        ArrayList<Artikulua> artikuluak = new ArrayList<>();
        artikuluak.add(new Artikulua(1234, "LATA TOMATE CICA", 234, 0.90, LocalDate.of(1993, 1, 12), false));
        artikuluak.add(new Artikulua(1456, "LATA TOMATE SANCOR", 100, 0.86, LocalDate.of(1993, 1, 14), false));
        artikuluak.add(new Artikulua(1987, "lata palmitos olaff", 200, 3.50, LocalDate.of(1993, 2, 15), true));
        artikuluak.add(new Artikulua(1899, "lata choclo pomar", 120, 1.67, LocalDate.of(1993, 2, 17), false));
        artikuluak.add(new Artikulua(2089, "MAYONESA RI-K", 250, 1.70, LocalDate.of(1993, 3, 14), false));
        artikuluak.add(new Artikulua(1223, "MAYONESA ARCOR", 394, 1.67, LocalDate.of(1993, 2, 15), true));
        artikuluak.add(new Artikulua(1889, "MAYONESA SANCOR 1KG.", 283, 2.20, LocalDate.of(1993, 3, 18), true));
        artikuluak.add(new Artikulua(1884, "VINO BLANCO ETCHART", 305, 2.38, LocalDate.of(1993, 4, 18), true));
        artikuluak.add(new Artikulua(2000, "VINO BLANCO MENEM", 250, 1.90, LocalDate.of(1993, 2, 18), false));
        artikuluak.add(new Artikulua(3000, "VINO TINTO ETCHART", 234, 1.78, LocalDate.of(1993, 3, 10), false));
        artikuluak.add(new Artikulua(2034, "ARROZ INTEGRAL GALLO", 90, 1.80, LocalDate.of(1993, 1, 18), false));
        artikuluak.add(new Artikulua(3442, "ARROZ GALLO", 134, 1.66, LocalDate.of(1993, 4, 16), true));
        artikuluak.add(new Artikulua(5674, "LATAS TOMATE POMARCI", 200, 1.58, LocalDate.of(1993, 4, 12), true));
        artikuluak.add(new Artikulua(1224, "MAYONESA CAMPAGNOLA", 250, 1.85, LocalDate.of(1993, 2, 10), false));
        artikuluak.add(new Artikulua(3454, "ARROZ INTEGRAL CAMPA", 200, 2.50, LocalDate.of(1993, 1, 23), false));
        artikuluak.add(new Artikulua(1922, "lata picadillo cica", 200, 0.36, LocalDate.of(1993, 3, 19), false));
        artikuluak.add(new Artikulua(1990, "CINZANO", 80, 3.08, LocalDate.of(1993, 3, 14), true));
        artikuluak.add(new Artikulua(3004, "GANCIA", 67, 3.17, LocalDate.of(1993, 3, 17), true));
        return artikuluak;
    }

    // =========================================================================
    // INTERFAZEA: MENUA, ATALEN BURUAK, MEZUAK ETA TAULA
    // =========================================================================

    /** Goiko ertza: ┌────┐ */
    private static void kutxaGoia() {
        System.out.println("┌" + "─".repeat(ZABALERA) + "┐");
    }

    /** Banatzailea: ├────┤ */
    private static void kutxaBanatzailea() {
        System.out.println("├" + "─".repeat(ZABALERA) + "┤");
    }

    /** Beheko ertza: └────┘ */
    private static void kutxaBehea() {
        System.out.println("└" + "─".repeat(ZABALERA) + "┘");
    }

    /** Kutxa barruko lerro bat. */
    private static void kutxaLerroa(String testua) {
        System.out.printf("│ %-" + (ZABALERA - 2) + "s │\n", testua);
    }

    /** Menu nagusia marrazten du. */
    public static void marraztuMenua() {
        System.out.println();
        kutxaGoia();
        kutxaLerroa("        ARTIKULUEN KUDEAKETA - MENU NAGUSIA");
        kutxaBanatzailea();
        kutxaLerroa(" [1] Erregistro guztiak zerrendatu");
        kutxaLerroa(" [2] Kodearen bidez kontsultatu");
        kutxaLerroa(" [3] Bi daten arteko erregistroak zerrendatu");
        kutxaLerroa(" [4] Erregistroaren alta");
        kutxaLerroa(" [5] Erregistroaren baja");
        kutxaLerroa(" [6] Erregistroak ordenatu (prezioaren arabera)");
        kutxaLerroa(" [0] Irten");
        kutxaBehea();
    }

    /** Atal bakoitzaren izenburua marrazten du, aukerak ondo bereizteko. */
    public static void marraztuAtala(String izenburua) {
        System.out.println();
        kutxaGoia();
        kutxaLerroa(izenburua);
        kutxaBehea();
    }

    /** Agurra marrazten du. */
    public static void marraztuAgurra() {
        System.out.println();
        kutxaGoia();
        kutxaLerroa("   Eskerrik asko programa erabiltzeagatik. Agur!");
        kutxaBehea();
    }

    // Mezu estandarrak
    public static void errorea(String mezua) {
        System.out.println("❌ Errorea: " + mezua);
    }

    public static void ondo(String mezua) {
        System.out.println("✅ " + mezua);
    }

    public static void informazioa(String mezua) {
        System.out.println("ℹ️  " + mezua);
    }

    /** Taularen lerro horizontal bat (ertzak eta banatzaileak). */
    private static String taulaLerroa(String ezkerra, String erdia, String eskuina) {
        StringBuilder sb = new StringBuilder(ezkerra);
        for (int i = 0; i < ZUTABEAK.length; i++) {
            sb.append("─".repeat(ZUTABEAK[i]));
            sb.append(i < ZUTABEAK.length - 1 ? erdia : eskuina);
        }
        return sb.toString();
    }

    /** Artikuluaren toString() emaitza kutxa batean erakusten du (eremu bat lerro bakoitzean). */
    private static void marraztuToString(Artikulua a) {
        kutxaGoia();
        kutxaLerroa("Ezabatuko den erregistroa:");
        kutxaBanatzailea();
        for (String eremua : a.toString().split(" \\| ")) {
            kutxaLerroa(" " + eremua);
        }
        kutxaBehea();
    }

    /** Ondo tabulatutako taula inprimatzen du. */
    public static void inprimatuTaula(ArrayList<Artikulua> artikuluak) {
        System.out.println(taulaLerroa("┌", "┬", "┐"));
        System.out.printf("│ %-5s │ %-22s │ %-5s │ %-8s │ %-10s │ %-7s │\n",
                "KODEA", "IZENA", "STOCK", "PREZIOA", "AZKEN DATA", "EKOIZP.");
        System.out.println(taulaLerroa("├", "┼", "┤"));
        for (Artikulua a : artikuluak) {
            System.out.printf("│ %-5d │ %-22s │ %5d │ %7.2f€ │ %-10s │ %-7s │\n",
                    a.getKodea(), a.getIzena(), a.getStock(), a.getPrezioa(),
                    a.getData().format(DATA_ERAKUTSI),
                    a.isEkoizpenean() ? "Bai" : "Ez");
        }
        System.out.println(taulaLerroa("└", "┴", "┘"));
        System.out.println("Guztira: " + artikuluak.size() + " erregistro erakutsita.");
    }

    // =========================================================================
    // TEKLATUKO DATUEN IRAKURKETA (erroreen kontrola + salbuespenak)
    // =========================================================================

    /** Zenbaki osoa irakurtzen du baliozkoa izan arte. */
    public static int irakurriEnteroa(Scanner sc, String mezua) {
        while (true) {
            System.out.print(mezua);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                errorea("sartu baliozko zenbaki oso bat.");
            }
        }
    }

    /** Zenbaki osoa irakurtzen du 0 eta gehienezkoa artean. */
    public static int irakurriTartean(Scanner sc, String mezua, int gehienez) {
        while (true) {
            int zenbakia = irakurriEnteroa(sc, mezua);
            if (zenbakia >= 0 && zenbakia <= gehienez) {
                return zenbakia;
            }
            errorea("zenbakiak 0 eta " + gehienez + " artean egon behar du.");
        }
    }

    /** Zenbaki hamartarra irakurtzen du (koma edo puntua onartzen da). */
    public static double irakurriHamartarra(Scanner sc, String mezua, double gehienez) {
        while (true) {
            System.out.print(mezua);
            try {
                double zenbakia = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (zenbakia >= 0 && zenbakia <= gehienez) {
                    return zenbakia;
                }
                errorea("zenbakiak 0 eta " + gehienez + " artean egon behar du.");
            } catch (NumberFormatException e) {
                errorea("sartu baliozko zenbaki hamartar bat.");
            }
        }
    }

    /** Data irakurtzen du (dd/MM/yyyy formatuan) baliozkoa izan arte. */
    public static LocalDate irakurriData(Scanner sc, String mezua) {
        while (true) {
            System.out.print(mezua);
            try {
                return LocalDate.parse(sc.nextLine().trim(), DATA_IRAKURRI);
            } catch (DateTimeParseException e) {
                errorea("data okerra. Formatua: dd/MM/yyyy (adib. 15/02/1993).");
            }
        }
    }

    /** Testua irakurtzen du (hutsik ez eta gehienezko luzerarekin). */
    public static String irakurriTestua(Scanner sc, String mezua) {
        while (true) {
            System.out.print(mezua);
            String testua = sc.nextLine().trim();
            if (testua.isEmpty()) {
                errorea("testua ezin da hutsik egon.");
            } else if (testua.length() > IZEN_MAX) {
                errorea("gehienez " + IZEN_MAX + " karaktere onartzen dira.");
            } else {
                return testua;
            }
        }
    }

    /** Bai/Ez erantzuna irakurtzen du (B edo E). */
    public static boolean irakurriBaiEz(Scanner sc, String mezua) {
        while (true) {
            System.out.print(mezua + " (B/E): ");
            String erantzuna = sc.nextLine().trim();
            if (erantzuna.equalsIgnoreCase("B")) {
                return true;
            }
            if (erantzuna.equalsIgnoreCase("E")) {
                return false;
            }
            errorea("idatzi B (Bai) edo E (Ez).");
        }
    }

    // =========================================================================
    // MENUAREN AUKERAK
    // =========================================================================

    /** 1. aukera: erregistro guztiak zerrendatu. */
    public static void zerrendatuGuztiak(ArrayList<Artikulua> artikuluak) {
        marraztuAtala("1. ERREGISTRO GUZTIAK ZERRENDATU");
        if (artikuluak.isEmpty()) {
            informazioa("Ez dago erregistrorik.");
        } else {
            inprimatuTaula(artikuluak);
        }
    }

    /** Kodea duen artikuluaren posizioa itzultzen du (-1 ez badago). */
    public static int bilatuPosizioa(ArrayList<Artikulua> artikuluak, int kodea) {
        for (int i = 0; i < artikuluak.size(); i++) {
            if (artikuluak.get(i).getKodea() == kodea) {
                return i;
            }
        }
        return -1;
    }

    /** 2. aukera: kodearen bidez kontsultatu. */
    public static void kontsultatuKodea(ArrayList<Artikulua> artikuluak, Scanner sc) {
        marraztuAtala("2. KONTSULTATU KODEAREN BIDEZ");
        int kodea = irakurriTartean(sc, "➤ Sartu bilatu nahi duzun kodea: ", KODE_MAX);
        int pos = bilatuPosizioa(artikuluak, kodea);
        if (pos == -1) {
            errorea("ez da aurkitu '" + kodea + "' kodea duen erregistrorik.");
        } else {
            ArrayList<Artikulua> aurkitua = new ArrayList<>();
            aurkitua.add(artikuluak.get(pos));
            inprimatuTaula(aurkitua);
        }
    }

    /** 3. aukera: bi daten arteko erregistroak zerrendatu. */
    public static void zerrendatuDatenArtean(ArrayList<Artikulua> artikuluak, Scanner sc) {
        marraztuAtala("3. BI DATEN ARTEKO ERREGISTROAK");
        LocalDate hasiera = irakurriData(sc, "➤ Hasierako data (dd/MM/yyyy): ");
        LocalDate bukaera = irakurriData(sc, "➤ Amaierako data (dd/MM/yyyy): ");
        if (bukaera.isBefore(hasiera)) {
            errorea("amaierako data ezin da hasierakoa baino lehenagokoa izan.");
            return;
        }
        ArrayList<Artikulua> tartekoak = new ArrayList<>();
        for (Artikulua a : artikuluak) {
            // Bi datak barne hartzen dira
            if (!a.getData().isBefore(hasiera) && !a.getData().isAfter(bukaera)) {
                tartekoak.add(a);
            }
        }
        if (tartekoak.isEmpty()) {
            informazioa("ez dago erregistrorik bi data horien artean.");
        } else {
            inprimatuTaula(tartekoak);
        }
    }

    /** 4. aukera: alta, gako-eremua (kodea) errepikatu gabe. */
    public static void altaEman(ArrayList<Artikulua> artikuluak, Scanner sc) {
        marraztuAtala("4. ERREGISTROAREN ALTA");
        int kodea = irakurriTartean(sc, "➤ Kode berria: ", KODE_MAX);
        // Egiaztatu: kodea ez dago errepikatuta
        if (bilatuPosizioa(artikuluak, kodea) != -1) {
            errorea("'" + kodea + "' kodea lehendik existitzen da. Alta ez da egin.");
            return;
        }
        String izena = irakurriTestua(sc, "➤ Izena (gehienez " + IZEN_MAX + " karaktere): ");
        int stock = irakurriTartean(sc, "➤ Stock-a: ", STOCK_MAX);
        double prezioa = irakurriHamartarra(sc, "➤ Prezioa (€): ", PREZIO_MAX);
        LocalDate data = irakurriData(sc, "➤ Azken data (dd/MM/yyyy): ");
        boolean ekoizpenean = irakurriBaiEz(sc, "➤ Ekoizpenean dago?");

        Artikulua berria = new Artikulua(kodea, izena, stock, prezioa, data, ekoizpenean);
        artikuluak.add(berria);
        ondo("Alta ondo egin da:");
        ArrayList<Artikulua> gehitua = new ArrayList<>();
        gehitua.add(berria);
        inprimatuTaula(gehitua);
    }

    /** 5. aukera: baja, kodea adieraziz eta berrespena eskatuz. */
    public static void bajaEman(ArrayList<Artikulua> artikuluak, Scanner sc) {
        marraztuAtala("5. ERREGISTROAREN BAJA");
        int kodea = irakurriTartean(sc, "➤ Ezabatu nahi duzun kodea: ", KODE_MAX);
        int pos = bilatuPosizioa(artikuluak, kodea);
        if (pos == -1) {
            errorea("ez da aurkitu '" + kodea + "' kodea duen erregistrorik.");
            return;
        }
        // Erregistroa taulan eta toString bidez erakutsi
        ArrayList<Artikulua> ezabatzekoa = new ArrayList<>();
        ezabatzekoa.add(artikuluak.get(pos));
        inprimatuTaula(ezabatzekoa);
        marraztuToString(artikuluak.get(pos));
        if (irakurriBaiEz(sc, "⚠️  Ziur zaude erregistro hau ezabatu nahi duzula?")) {
            artikuluak.remove(pos);
            ondo("Baja ondo egin da.");
        } else {
            informazioa("Baja bertan behera utzi da.");
        }
    }

    /** 6. aukera: prezioaren arabera ordenatu (txikitik handira). */
    public static void ordenatuPrezioa(ArrayList<Artikulua> artikuluak) {
        marraztuAtala("6. ORDENATU PREZIOAREN ARABERA");
        if (artikuluak.isEmpty()) {
            informazioa("Ez dago erregistrorik ordenatzeko.");
            return;
        }
        artikuluak.sort(Comparator.comparingDouble(Artikulua::getPrezioa));
        ondo("Erregistroak ordenatuta (prezioa, txikitik handira):");
        inprimatuTaula(artikuluak);
    }
}

