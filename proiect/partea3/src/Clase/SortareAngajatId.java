package Clase;

import java.util.Comparator;

public class SortareAngajatId implements Comparator<Angajat> {

    public int compare(Angajat a, Angajat b) {
        return a.getId_angajat().compareTo(b.getId_angajat());
    }
}
