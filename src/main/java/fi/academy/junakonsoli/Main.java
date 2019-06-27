package fi.academy.junakonsoli;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(scanner);
        ui.kaynnista();

    }
}
