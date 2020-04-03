package Interfete;

import Clase.*;

import java.util.List;

public interface OrganizatieInterfata {

    void adaugaAngajat(Angajat angajat);
    void stergeAngajat(int index);
    Angajat alegeAngajat(int index);
    Manager alegeManager(int index);
    Programator alegeProgramator(int index);
    void sortareAngajatiId();
    void sortareAngajatiNume();
    List<Manager> getManageri();
    List<Programator> getProgramatori();

    void adaugaDepartament(Departament departament);
    void stergeDepartament(int index);
    Departament alegeDepartament(int index);

    void adaugaCladire(Cladire cladire);
    void stergeCladire(int index);
    Cladire alegeCladire(int index);
    void sortareCladiri();
}
