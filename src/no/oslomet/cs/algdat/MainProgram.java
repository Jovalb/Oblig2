package no.oslomet.cs.algdat;

public class MainProgram {

    public static void main(String[] args) {
        /*String [] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " "
                + l2.omvendtString() + " " + l3.omvendtString());*/

        //DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
       // System.out.println(liste.toString() + " " + liste.omvendtString());

       /* for (int i = 1; i <= 4; i++) {
            liste.leggInn(i);
            //System.out.println(liste.toString() + " " + liste.omvendtString());

        }*/

        //System.out.println(liste.hent(3));
       // System.out.println(liste.antall());




       // System.out.println(liste.subliste(0,4));

        //System.out.println(liste.oppdater(4,5));
        /*if (liste.oppdater(3,5) != 4){
            System.out.println("hei");
        }*/

        //System.out.println(liste);
        //System.out.println(liste.subliste(0,4));

        //System.out.println(liste.indeksTil(5));

       // System.out.println(liste.inneholder(3));

        //System.out.println(liste.antall());


        /*liste.leggInn(0,7);
        System.out.println(liste);
        System.out.println(liste.antall());

        System.out.println(liste.toString() + " " + liste.omvendtString());*/

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        liste.leggInn(0, 4);  // ny verdi i tom liste
        liste.leggInn(0, 2);  // ny verdi legges forrest
        liste.leggInn(2, 6);  // ny verdi legges bakerst
        liste.leggInn(1, 3);  // ny verdi nest forrest
        liste.leggInn(3, 5);  // ny verdi nest bakerst
        liste.leggInn(0, 1);  // ny verdi forrest
        liste.leggInn(6, 7);  // ny verdi legges bakerst

        System.out.println(liste.toString());

        System.out.println(liste.omvendtString());

        liste.fjern(1);

        System.out.println(liste);
        System.out.println(liste.omvendtString());


        String [] a = {"A","B","C","D","E","F","G"};

        DobbeltLenketListe<String> liste1 = new DobbeltLenketListe<>(a);

        System.out.println(liste1 + " " + liste1.omvendtString());

        if (!liste1.fjern(3).equals("D")){
            System.out.println("hei");
        }

        System.out.println(liste1 + " " + liste1.omvendtString());

        liste1.fjern(null);


        /*Character[] c = {'A','B','C','D','E','F','G','H','I','J',};

        DobbeltLenketListe<Character> liste1 = new DobbeltLenketListe<>(c);

        System.out.println(liste1 + " " + liste1.omvendtString());

        System.out.println(liste1.subliste(3,8));
        System.out.println(liste1.subliste(5,5));
        System.out.println(liste1.subliste(8,liste1.antall()));*/





    }

}
