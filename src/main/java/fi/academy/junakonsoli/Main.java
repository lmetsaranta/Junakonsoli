package fi.academy.junakonsoli;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) {
        ASCII otsikko = new ASCII();
        otsikko.tulostaASCII();
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(scanner);
        ui.kaynnista();
    }
}
