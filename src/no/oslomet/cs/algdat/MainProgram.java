package no.oslomet.cs.algdat;

public class MainProgram {

    public static void main(String[] args) {
        String [] s1 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);

        System.out.println(l1);
    }

}
