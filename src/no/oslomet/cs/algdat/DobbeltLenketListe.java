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
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    private Node finnNode(int indeks){
        indeks = Objects.requireNonNull(indeks,"Indeks du har oppgitt er ugyldig!");
        if (indeks <= antall/2){
            Node startNode = hode;      // lager hjelpenode som starter på hode
            Node funnetNode = startNode;        // lager noden vi skal retunere
            for (int i = 0; i < indeks ; i++) {
                funnetNode = startNode.neste; // Oppdater noden vi skal returnere
                startNode = funnetNode;     // Oppdaterer hjelpenoden
            }
            return funnetNode;
        } else if( indeks > antall/2){
            Node startNode = hale;      // lager hjelpenode som starter på hale
            Node funnetNode = startNode;        // lager noden vi skal returnere
            for (int i = antall; i > indeks ; i--) {
                funnetNode = startNode.forrige; // Oppdater noden vi skal returnere
                startNode = funnetNode;     // Oppdaterer hjelpenoden
            }
            return funnetNode;
        }


        return null;    // hvis indeksen ikke stemmer returnerer vi null
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);

        Node hentetNode = new Node<>(finnNode(indeks));
        return (T) hentetNode;  // her bruker vi finnNode metoden og returnerer en node lik den på indeks
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        nyverdi = Objects.requireNonNull(nyverdi,"verdi kan ikke være null!");
        Node eksisterendeNode = (Node) hent(indeks);
        finnNode(indeks).verdi = nyverdi;
        endringer++;

        return (T) eksisterendeNode;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
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


