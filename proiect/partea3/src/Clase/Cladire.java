package Clase;

public class Cladire {
    private String id_cladire;
    private String adresa;
    private String cod_postal;
    private String  id_departament;

    public Cladire(String id_cladire, String adresa, String cod_postal) {
        this.id_cladire = id_cladire;
        this.adresa = adresa;
        this.cod_postal = cod_postal;
    }

    public Cladire(String id_cladire, String adresa, String cod_postal, String id_departament) {
        this.id_cladire = id_cladire;
        this.adresa = adresa;
        this.cod_postal = cod_postal;
        this.id_departament = id_departament;
    }

    public String getId_cladire() {
        return id_cladire;
    }

    public void setId_cladire(String id_cladire) {
        this.id_cladire = id_cladire;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getId_departament() {
        return id_departament;
    }

    public void setId_departament(String id_departament) {
        this.id_departament = id_departament;
    }


    @Override
    public String toString() {
        return "Cladire{" +
                "id_cladire='" + id_cladire + '\'' +
                ", adresa='" + adresa + '\'' +
                ", cod_postal='" + cod_postal + '\'' +
                ", departament=" + id_departament +
                '}';
    }
}
