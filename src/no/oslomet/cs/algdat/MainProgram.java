package no.oslomet.cs.algdat;

public class MainProgram {

    public static void main(String[] args) {
        String[] s = {"Ole",null,"Per","Kari​",null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + "   "+liste.tom());

        Liste<Integer> liste2 = new DobbeltLenketListe<>(new Integer[]{null});

        System.out.println(liste2.antall());

    }

}
