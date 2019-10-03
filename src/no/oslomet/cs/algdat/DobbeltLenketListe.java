package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////

// Elever på gruppe: Jo Vetle s329329


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
        a = Objects.requireNonNull(a, "Tabellen a er null!");
        if (a.length == 0) {
            return;
        }
        // Node front = new Node<>(hode);
        hode = new Node<>(a[0]);  // initialiserer hode og hale
        hale = new Node<>(a[a.length - 1]);

        Node p = hode; // Lager ny node som blir satt til første verdi i listen

        p.verdi = a[0];
        if (p.verdi != null) {
            antall++;
        }

        for (int i = 1; i < a.length; i++) {
            Node q = new Node<>(a[i]);    // Skaper ny node

            p.neste = q;    // oppdaterer forrige node sin nesteverdi
            q.forrige = p;  // oppdaterer gjeldende node sin forrigeverdi

            p = q;  // oppdaterer forrige node til gjeldende

            hale = q; // oppdaterer hale

            if (p.verdi != null) {
                antall++;
            }

        }


    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe subliste = new DobbeltLenketListe();

        for (int i = fra; i < til; i++) {
            subliste.leggInn(hent(i));
        }
        subliste.endringer = 0;
        return subliste;
    }

    // fra-til-kontroll hentet fra kompendium
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (antall() == 0) {
            return true;    // Hvis antallet er lik 0 vil det tilsvare at listen er tom
        } else {
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        verdi = Objects.requireNonNull(verdi, "verdi kan ikke være null!");

        if (tom()) { // tilfelle 1 at listen er på forhånd tom
            hode = null;
            hale = null;
            antall = 0;

            Node p = new Node<>(verdi);
            p.neste = null;
            p.forrige = null;

            hode = p;
            hale = p;
            antall++;
            endringer++;
            return true;

        } else {        // tilfelle 2 at listen eksisterer og ikke er tom
            Node p = new Node<>(verdi);

            p.forrige = hale;   // setter forrige verdi til å være halen
            p.neste = null;     // setter neste til null
            hale.neste = p;     // setter forrige hale sin neste verdi til p

            hale = p;           // oppdaterer hale til nåværende node
            antall++;
            endringer++;
            return true;
        }
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        Node nyNode = new Node<>(verdi);

        if (hode == null || indeks == antall) {     //sjekker om noden skal settes bakerst
            leggInn(verdi);
        } else if (indeks == 0) {       //sjekker om noden skal settes fremmers

            nyNode.forrige = null;
            nyNode.neste = hode;     // setter neste til forrige hodet
            hode.forrige = nyNode;     // oppdaterer hodet sin forrige verdi til nynode

            hode = nyNode;           // oppdaterer hodet til nåværende node
            antall++;
            endringer++;
        } else {
            Node forrigeNode = hode;    // her setter vi opp noden som skal være noden før den vi setter inn
            int teller = 0;
            while (teller < antall) {    // teller som går gjennom hele listen
                if (teller == indeks - 1) {    // når vi når punktet før der vi skal plassere noden starter vi prosessen
                    Node current = forrigeNode.neste;   // vi setter nåværende node til å være den forrige sin neste
                    nyNode.neste = current;     // Vi setter den nye noden sin neste til å være den nåværende
                    forrigeNode.neste = nyNode;     // vi oppdaterer den forrige noden så den peker på den nye
                    nyNode.forrige = forrigeNode;   // oppdaterer den nye så den peker på den forrige
                    current.forrige = nyNode;       // oppdaterer noden etter den nye så den peker på den nye
                }
                forrigeNode = forrigeNode.neste;        // her blar vi gjennom alle nodene

                teller++;
            }
            antall++;
            endringer++;

        }
    }

    private Node finnNode(int indeks) {
        indeks = Objects.requireNonNull(indeks, "Indeks du har oppgitt er ugyldig!");
        if (indeks <= antall / 2) {
            Node startNode = hode;      // lager hjelpenode som starter på hode
            Node funnetNode = startNode;        // lager noden vi skal retunere
            for (int i = 0; i < indeks; i++) {
                funnetNode = startNode.neste; // Oppdater noden vi skal returnere
                startNode = funnetNode;     // Oppdaterer hjelpenoden
            }
            return funnetNode;
        } else if (indeks > antall / 2) {
            Node startNode = hale;      // lager hjelpenode som starter på hale
            Node funnetNode = startNode;        // lager noden vi skal returnere
            for (int i = antall - 1; i > indeks; i--) {
                funnetNode = startNode.forrige; // Oppdater noden vi skal returnere
                startNode = funnetNode;     // Oppdaterer hjelpenoden
            }
            return funnetNode;
        }


        return null;    // hvis indeksen ikke stemmer returnerer vi null
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        Node hentetNode = finnNode(indeks);
        return (T) hentetNode.verdi;  // her bruker vi finnNode metoden og returnerer en node sin verdi lik den på indeks
    }

    @Override
    public boolean inneholder(T verdi) {

        if (indeksTil(verdi) != -1) {
            return true;
        }
        return false;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) { // sjekker om verdi er null
            return -1;
        }
        int funnetIndex = 0;    // initialiserer funnnet index
        Node sammenLigning = new Node<>(verdi); // setter opp node til verdi for sammenligning

        for (int i = 0; i < antall; i++) {      //for loop som går gjennom listen og sammenligner
            Node current = finnNode(i);
            if (current.verdi.equals(sammenLigning.verdi)) {
                funnetIndex = i;
                return funnetIndex;
            }

        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        nyverdi = Objects.requireNonNull(nyverdi, "verdi kan ikke være null!");  // sjekker for null
        indeksKontroll(indeks, false);   // glemte å legge inn indekskontroll
        Node eksisterendeNode = finnNode(indeks);    // lager ny node for eksisterende node
        T gammelVerdi = (T) eksisterendeNode.verdi; // her beholder vi den gamle verdien til noden
        eksisterendeNode.verdi = nyverdi;   // her bytter vi verdien til den eksisterende noden
        endringer++;    // oppdaterer endringer

        return gammelVerdi;    // returnerer eksisterende node sin forrige verdi
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null){
            return false;
        }

        if (antall == 1 && hode.verdi.equals(verdi)) {
            hode = null;
            hale = null;
            antall--;
            endringer++;

            return true;
        }
        Node leteNode = hode;

        for (int i = 0; i < antall; i++) {
            if (leteNode.verdi.equals(verdi)) {
                if (i == 0) {
                    hode = leteNode.neste;
                    hode.forrige = null;
                    leteNode.forrige = null;

                    antall--;
                    endringer++;
                    return true;
                } else if (i == antall - 1) {
                    hale = leteNode.forrige;
                    hale.neste = null;
                    leteNode.neste = null;

                    antall--;
                    endringer++;
                    return true;
                } else {
                    leteNode = leteNode.forrige;        // her måtte jeg gå et hakk tilbake ellers ville den slette neste node
                    Node nesteNode = leteNode.neste;
                    Node nesteNesteNode = nesteNode.neste;

                    leteNode.neste = nesteNesteNode;
                    nesteNesteNode.forrige = leteNode;

                    antall--;
                    endringer++;
                    return true;
                }
            }
            leteNode = leteNode.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node currentNode = hode;     // starter å lete fra hodet
        Node tempNode = currentNode;

        if (antall == 1) {
            hode = null;
            hale = null;

            antall--;
            endringer++;
            return (T) currentNode.verdi;
        }

        if (indeks == 0) {
            hode = currentNode.neste;
            hode.forrige = null;
            currentNode.neste = null;

            antall--;
            endringer++;

            return (T) currentNode.verdi;
        }

        for (int i = 0; i < indeks; i++) {
            currentNode = currentNode.neste;
            tempNode = currentNode;
        }

        if (indeks == antall - 1) {
            hale = currentNode.forrige;
            hale.neste = null;
            currentNode.neste = null;

            antall--;
            endringer++;

            return (T) tempNode.verdi;
        }

        currentNode = currentNode.forrige;      // går et hakk tilbake i listen
        Node nesteNode = currentNode.neste;
        Node nesteNesteNode = nesteNode.neste;

        currentNode.neste = nesteNesteNode;
        nesteNesteNode.forrige = currentNode;

        antall--;
        endringer++;

        return (T) tempNode.verdi;
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        if (antall == 0) {
            return "[]";
        }
        Node n = hode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < antall; ) {
            if (n.verdi == null) {
                n = n.neste;
            } else {
                stringBuilder.append(n.verdi).append(", ");
                n = n.neste;
                i++;
            }
        }

        stringBuilder.setLength(stringBuilder.length() - 2);    //Fjerner siste kommaet

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    public String omvendtString() {
        if (antall == 0) {
            return "[]";
        }
        Node n = hale;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < antall; ) {
            if (n.verdi == null) {
                n = n.forrige;
            } else {
                stringBuilder.append(n.verdi).append(", ");
                n = n.forrige;
                i++;
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);    //Fjerner siste kommaet

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext() {
            throw new NotImplementedException();
        }

        @Override
        public T next() {
            throw new NotImplementedException();
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


