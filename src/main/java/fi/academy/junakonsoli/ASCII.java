package fi.academy.junakonsoli;

//tekijä: Kirsi
public class ASCII {

    public void tulostaASCII() {
        String[] ASCIIrivit = new String[7];
        ASCIIrivit[0] = "       _                   _____        __     ";
        ASCIIrivit[1] = "      | |                 |_   _|      / _|     ";
        ASCIIrivit[2] = "      | |_   _ _ __   __ _  | |  _ __ | |_ ___  ";
        ASCIIrivit[3] = "  _   | | | | | '_ \\ / _` | | | | '_ \\|  _/ _ \\ ";
        ASCIIrivit[4] = " | |__| | |_| | | | | (_| |_| |_| | | | || (_) |";
        ASCIIrivit[5] = "  \\____/ \\__,_|_| |_|\\__,_|_____|_| |_|_| \\___/ ";
        ASCIIrivit[6] = "                                                ";

        for (int i = 0; i < ASCIIrivit.length; i++) {
            System.out.println(ASCIIrivit[i]);
        }
    }
}