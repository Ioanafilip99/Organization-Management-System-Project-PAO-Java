package Clase;

public class Departament {

    private String id_departament;
    private Manager manager;
    private Cladire cladire;

    public Departament(String id_departament, Cladire cladire) {
        this.id_departament = id_departament;
        this.cladire = cladire;
    }

    public Departament(String id_departament, Manager manager, Cladire cladire) {
        this.id_departament = id_departament;
        this.manager = new Manager(manager);
        this.cladire = cladire;
    }

    public String getId_departament () {
        return id_departament;
    }

    public void setId_departament (String id_departament){
        this.id_departament = id_departament;
    }

    public Manager getManager () {
        return manager;
    }

    public void setManager (Manager manager){
        this.manager = manager;
    }

    public Cladire getCladire () {
        return cladire;
    }

    public void setCladire (Cladire cladire){
        this.cladire = cladire;
    }

    @Override
    public String toString () {
        return "Departament{" +
                "id_departament='" + id_departament + '\'' +
                ", manager=" + manager +
                ", cladire=" + cladire +
                '}';
    }

}
