import java.util.ArrayList;
import java.util.Scanner;

/**
 * Programa nagusia: menua kudeatzen du.
 * Objektu guztiak ArrayList batean daude eta metodoei argumentu gisa pasatzen zaizkie.
 */
public class Menua {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Objektu guztiak biltzen dituen ArrayList-a
        ArrayList<Artikulua> artikuluak = Metodoak.hasieratu();

        int aukera;
        do {
            // Menua marraztu eta aukera irakurri (errore-kontrolarekin)
            Metodoak.marraztuMenua();
            aukera = Metodoak.irakurriEnteroa(sc, "➤ Aukeratu aukera bat [0-6]: ");

            // Aukeraren arabera dagokion metodoa deitu
            switch (aukera) {
                case 1:
                    Metodoak.zerrendatuGuztiak(artikuluak);
                    break;
                case 2:
                    Metodoak.kontsultatuKodea(artikuluak, sc);
                    break;
                case 3:
                    Metodoak.zerrendatuDatenArtean(artikuluak, sc);
                    break;
                case 4:
                    Metodoak.altaEman(artikuluak, sc);
                    break;
                case 5:
                    Metodoak.bajaEman(artikuluak, sc);
                    break;
                case 6:
                    Metodoak.ordenatuPrezioa(artikuluak);
                    break;
                case 0:
                    Metodoak.marraztuAgurra();
                    break;
                default:
                    Metodoak.errorea("aukera okerra. Sartu 0 eta 6 arteko zenbaki bat.");
            }
        } while (aukera != 0);

        sc.close();
    }
}
