package no.oslomet.cs.algdat;

public class MainProgram {

    public static void main(String[] args) {
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());
    }

}
