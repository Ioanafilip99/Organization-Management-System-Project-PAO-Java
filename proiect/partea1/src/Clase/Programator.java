package Clase;

public class Programator extends Angajat {
    private String id_manager;
    private double spor;


    public Programator(String id_angajat, String nume, String prenume, String email, String nr_telefon, String id_job, double salariu, String id_departament, int vechime, String id_manager) {
        super(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, id_departament, vechime);
        this.id_manager = id_manager;
        this.spor = calculSpor();
    }



    public String getId_manager() {
        return id_manager;
    }

    public void setId_manager(String id_manager) {
        this.id_manager = id_manager;
    }

    public double getSpor() {
        return spor;
    }

    @Override
    public double calculSpor() {
        return (0.15 * this.getSalariu());
    }

    @Override
    public String toString() {
        return "Programator{" +
                "id_angajat='" + getId_angajat() + '\'' +
                ", nume='" + getNume() + '\'' +
                ", prenume='" + getPrenume() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", nr_telefon='" + getNr_telefon() + '\'' +
                ", id_job='" + getId_job() + '\'' +
                ", salariu=" + getSalariu() +
                ", id_departament='" + getId_departament() + '\'' +
                ", vechime=" + getVechime() +
                ", id_manager='" + id_manager + '\'' +
                ", spor='" + spor + '\'' +
                '}';
    }

}
