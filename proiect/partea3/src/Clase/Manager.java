package Clase;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Angajat {

    private List<Programator> subordonati = new ArrayList<>();
    private double spor;

    public Manager(String id_angajat, String nume, String prenume, String email, String nr_telefon, String id_job, double salariu, String id_departament, int vechime) {
        super(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, id_departament, vechime);
        this.spor = calculSpor();
    }

    public Manager(Manager manager) {
        super(manager.getId_angajat(),
                manager.getNume(),
                manager.getPrenume(),
                manager.getEmail(),
                manager.getNr_telefon(),
                manager.getId_job(),
                manager.getSalariu(),
                manager.getId_departament(),
                manager.getVechime());
        this.subordonati = manager.subordonati;
        this.spor = manager.spor;
    }

    public List<Programator> getSubordonati() {
        return subordonati;
    }

    public void setSubordonati(List<Programator> subordonati) {
        this.subordonati = subordonati;
    }

    public double getSpor() {
        return spor;
    }

    @Override
    public double calculSpor() {
        return (0.10 * this.getSalariu());
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id_angajat='" + getId_angajat() + '\'' +
                ", nume='" + getNume() + '\'' +
                ", prenume='" + getPrenume() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", nr_telefon='" + getNr_telefon() + '\'' +
                ", id_job='" + getId_job() + '\'' +
                ", salariu=" + getSalariu() +
                ", id_departament='" + getId_departament() + '\'' +
                ", vechime=" + getVechime() +
                ", subordonati=" + subordonati + '\'' +
                ", spor=" + spor +
                '}';
    }

    public void adaugaSubordonat(Programator subordonat) {
        this.subordonati.add(subordonat);
    }

    public void stergeSubordonatDupaIndex(int index){
        this.subordonati.remove(index);
    }

}
