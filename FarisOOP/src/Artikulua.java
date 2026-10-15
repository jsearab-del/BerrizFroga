import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Artikulu bat adierazten duen klasea (objektua).
 * Eremuak: kodea (gakoa), izena, stock-a, prezioa, azken data eta
 * ekoizpenean dagoen ala ez (eremu logikoa).
 */
public class Artikulua {

    // Datak erakusteko formatua
    private static final DateTimeFormatter FORMATUA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Atributuak
    private int kodea;
    private String izena;
    private int stock;
    private double prezioa;
    private LocalDate data;
    private boolean ekoizpenean;

    // Eraikitzailea
    public Artikulua(int kodea, String izena, int stock, double prezioa, LocalDate data, boolean ekoizpenean) {
        this.kodea = kodea;
        this.izena = izena;
        this.stock = stock;
        this.prezioa = prezioa;
        this.data = data;
        this.ekoizpenean = ekoizpenean;
    }

    // Getter-ak
    public int getKodea() {
        return kodea;
    }

    public String getIzena() {
        return izena;
    }

    public int getStock() {
        return stock;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isEkoizpenean() {
        return ekoizpenean;
    }

    // Setter-ak
    public void setKodea(int kodea) {
        this.kodea = kodea;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEkoizpenean(boolean ekoizpenean) {
        this.ekoizpenean = ekoizpenean;
    }

    // Eremu guztien informazioa kate batean
    @Override
    public String toString() {
        return "Kodea: " + kodea
                + " | Izena: " + izena
                + " | Stock: " + stock
                + " | Prezioa: " + String.format("%.2f", prezioa) + " €"
                + " | Azken data: " + data.format(FORMATUA)
                + " | Ekoizpenean: " + (ekoizpenean ? "Bai" : "Ez");
    }
}
