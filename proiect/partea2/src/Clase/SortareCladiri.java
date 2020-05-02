package Clase;

import java.util.Comparator;

public class SortareCladiri implements Comparator<Cladire> {

    public int compare(Cladire a, Cladire b) {
        return a.getCod_postal().compareTo(b.getCod_postal());
    }
}

