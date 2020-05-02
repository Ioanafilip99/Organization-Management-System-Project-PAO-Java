package Clase;

import Interfete.OrganizatieInterfata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Organizatie implements OrganizatieInterfata {

    private String denumire;
    private List<Angajat> angajati = new ArrayList<>();
    private List<Departament> departamente = new ArrayList<>();
    private Cladire [] cladiri = new Cladire[0];

    public Organizatie(String denumire) {
        this.denumire = denumire;
    }

    public Organizatie(String denumire, List<Angajat> angajati, List<Departament> departamente, Cladire[] cladiri) {
        this.denumire = denumire;
        this.angajati = angajati;
        this.departamente = departamente;
        this.cladiri = cladiri;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(List<Angajat> angajati) {
        this.angajati = angajati;
    }

    public List<Departament> getDepartamente() {
        return departamente;
    }

    public void setDepartamente(List<Departament> departamente) {
        this.departamente = departamente;
    }

    public Cladire[] getCladiri() {
        return cladiri;
    }

    public String afisareCladiri() {
        return Arrays.toString(cladiri);
    }

    public int getNumarCladiri() {
        return cladiri.length;
    }

    public void setCladiri(Cladire[] cladiri) {
        this.cladiri = cladiri;
    }

    public void setCladireDepartament (int index, Departament departament) {
        cladiri[index].setId_departament(departament.getId_departament());
    }

    public void setDepartamentManager (int index, Manager manager) {
        departamente.get(index).setManager(manager);
    }

    @Override
    public void adaugaAngajat(Angajat angajat) {
        this.angajati.add(angajat);
        System.out.println("S-a adaugat angajatul: ");
        System.out.println(angajat);
    }

    @Override
    public void stergeAngajat(int index) {
        if(angajati != null && index >= 0 && index < angajati.size()) {
            this.angajati.remove(index);
            System.out.println("S-a sters angajatul cu indexul : " + index);
        }
        else {
            System.out.println("Nu exista angajat cu indexul " + index);
        }
    }

    @Override
    public Angajat alegeAngajat(int index) {
        return angajati.get(index);
    }

    @Override
    public Manager alegeManager(int index) {
        List<Manager> manageri = this.getManageri();
        if(manageri != null && index >= 0 && index < manageri.size()) {
            return manageri.get(index);
        }
        else {
            System.out.println("Nu exista managerul cu indexul " + index);
            return null;
        }
    }

    @Override
    public Programator alegeProgramator(int index) {
        List<Programator> programatori = this.getProgramatori();
        if(programatori != null && index >= 0 && index < programatori.size()) {
            return programatori.get(index);
        }
        else {
            System.out.println("Nu exista programatorul cu indexul " + index);
            return null;
        }
    }

    @Override
    public void sortareAngajatiId() {
        angajati.sort(new SortareAngajatId());
    }

    @Override
    public void sortareAngajatiNume() {
        Collections.sort(angajati);
    }

    @Override
    public List<Manager> getManageri() {
        List<Manager> manageri = new ArrayList<>();
        for(Angajat angajat : angajati) {
            if(angajat instanceof Manager){
                manageri.add((Manager) angajat);
            }
        }
        return manageri;
    }

    @Override
    public List<Programator> getProgramatori() {
        List<Programator> programatori = new ArrayList<>();
        for(Angajat angajat : angajati) {
            if(angajat instanceof Programator) {
                programatori.add((Programator) angajat);
            }
        }
        return programatori;
    }

    @Override
    public void adaugaDepartament(Departament departament) {
        this.departamente.add(departament);
        System.out.println("S-a adaugat departamentul: ");
        System.out.println(departament);
    }

    @Override
    public void stergeDepartament(int index) {
        if(departamente != null && index >= 0 && index < departamente.size()) {
            this.departamente.remove(index);
            System.out.println("S-a sters departamentul cu indexul : " + index );
        }
        else {
            System.out.println("Nu exista departament cu indexul " + index);
        }
    }

    @Override
    public Departament alegeDepartament(int index) {
        if(departamente != null && index >= 0 && index < departamente.size()) {
            return departamente.get(index);
        }
        else {
            System.out.println("Nu exista departament cu indexul " + index);
            return null;
        }
    }

    @Override
    public void adaugaCladire(Cladire cladire) {
        Cladire [] tmp = new Cladire[cladiri.length + 1];
        System.arraycopy(cladiri,0, tmp,0, cladiri.length);
        tmp[tmp.length - 1] = cladire;
        cladiri = tmp;
        System.out.println("S-a adaugat cladirea: ");
        System.out.println(cladire);
    }

    @Override
    public void stergeCladire(int index) {
        if(cladiri != null && index >= 0 && index < cladiri.length) {
            Cladire [] tmp = new Cladire[cladiri.length - 1];
            for (int i = 0, k = 0; i < cladiri.length; i++) {
                if (i == index) {
                    continue;
                }
                tmp[k++] = cladiri[i];
            }
            cladiri = tmp;
            System.out.println("S-a sters cladirea cu indexul : "+ index);
        }
        else {
            System.out.println("Nu exista programator la indexul " + index);
        }
    }

    @Override
    public Cladire alegeCladire(int index) {
        if(cladiri != null && index >= 0 && index < cladiri.length) {
            return cladiri[index];
        }
        else {
            System.out.println("Nu exista cladire cu indexul " + index);
            return null;
        }
    }

    @Override
    public void sortareCladiri() {
        Arrays.sort(cladiri, new SortareCladiri());
    }
}

