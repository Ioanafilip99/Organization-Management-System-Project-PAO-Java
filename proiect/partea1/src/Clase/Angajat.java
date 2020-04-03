package Clase;

public abstract class Angajat implements Comparable<Angajat>{
    private String id_angajat;
    private String nume;
    private String prenume;
    private String email;
    private String nr_telefon;
    private String id_job;
    private double salariu;
    private String id_departament;
    private int vechime;

    public Angajat(String id_angajat, String nume, String prenume, String email, String nr_telefon, String id_job, double salariu, String id_departament, int vechime) {
        this.id_angajat = id_angajat;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.nr_telefon = nr_telefon;
        this.id_job = id_job;
        this.salariu = salariu;
        this.id_departament = id_departament;
        this.vechime = vechime;
    }

/*    public Angajat copy() {
        return new Angajat(this.id_angajat,
                this.nume,
                this.prenume,
                this.email,
                this.nr_telefon,
                this.id_job,
                this.salariu,
                this.id_departament,
                this.vechime);
    }*/

    public String getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(String id_angajat) {
        this.id_angajat = id_angajat;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public String getId_job() {
        return id_job;
    }

    public void setId_job(String id_job) {
        this.id_job = id_job;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getId_departament() {
        return id_departament;
    }

    public void setId_departament(String id_departament) {
        this.id_departament = id_departament;
    }

    public int getVechime() {
        return vechime;
    }

    public void setVechime(int vechime) {
        this.vechime = vechime;
    }

    @Override
    public int compareTo(Angajat angajat) {

        return this.nume.compareTo(angajat.getNume());
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id_angajat='" + id_angajat + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", id_job='" + id_job + '\'' +
                ", salariu=" + salariu +
                ", id_departament='" + id_departament + '\'' +
                ", vechime=" + vechime +
                '}';
    }



    public abstract double calculSpor();

}
