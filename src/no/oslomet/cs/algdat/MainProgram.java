package no.oslomet.cs.algdat;

public class MainProgram {

    public static void main(String[] args) {
        String[] s = {"Ole",null,"Per","Kari​",null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + "   "+liste.tom());

        Liste<Integer> liste2 = new DobbeltLenketListe<>(new Integer[]{1,2,3});

        System.out.println(liste2);

    }

}
