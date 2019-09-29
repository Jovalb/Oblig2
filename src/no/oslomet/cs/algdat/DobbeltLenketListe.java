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
        if (a.length < 0) { // tester om a er null
            throw new NullPointerException("Tabellen du har oppgitt er tom!");
        } else if(a.length == 0){
            return;
        }
        // Node front = new Node<>(hode);
        hode = new Node<>(a[0]);  // initialiserer hode og hale
        hale = hode;

        Node p = hode; // Lager ny node som blir satt til første verdi i listen

        p.verdi = a[0];
        if (p.verdi != null){
            antall++;
        }

        for (int i = 1; i < a.length; i++) {
            Node q = new Node<>(a[i]);    // Skaper ny node

            p.neste = q;    // oppdaterer forrige node sin nesteverdi
            q.forrige = p;  // oppdaterer gjeldende node sin forrigeverdi

            p = q;  // oppdaterer forrige node til gjeldende

            q = hale;   // oppdaterer hale

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
        throw new NotImplementedException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new NotImplementedException();
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
        if (antall == 0){
            return "[]";
        }
        Node n = hode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < antall;) {
            if (n.verdi == null){
                n = n.neste;
            } else {
                stringBuilder.append(n.verdi).append(',');
                n = n.neste;
                i++;
            }
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    public String omvendtString() {
        throw new NotImplementedException();
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


