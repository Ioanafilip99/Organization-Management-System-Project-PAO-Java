package Service;

import Clase.Cladire;
import Clase.Departament;
import Clase.Manager;
import Clase.Programator;

import java.io.*;

public class MainService {

    private String raportCladiri = "D:\\facultate\\AN 2\\SEM 2\\PAO\\LABORATOR\\PROIECT\\src\\Rapoarte\\RaportCladiri.csv";
    private String raportDepartamente = "D:\\facultate\\AN 2\\SEM 2\\PAO\\LABORATOR\\PROIECT\\src\\Rapoarte\\RaportDepartamente.csv";
    private String raportManageri = "D:\\facultate\\AN 2\\SEM 2\\PAO\\LABORATOR\\PROIECT\\src\\Rapoarte\\RaportManageri.csv";
    private String raportProgramatori = "D:\\facultate\\AN 2\\SEM 2\\PAO\\LABORATOR\\PROIECT\\src\\Rapoarte\\RaportProgramatori.csv";

    public void scriereRaportCladiri (Cladire cladire) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportCladiri, true))) {
            bufferedWriter.append(cladire.getId_cladire());
            bufferedWriter.append(",");
            bufferedWriter.append(cladire.getAdresa());
            bufferedWriter.append(",");
            bufferedWriter.append(cladire.getCod_postal());
            bufferedWriter.append(",");
            if(cladire.getId_departament() != null) {
                bufferedWriter.append(cladire.getId_departament());
            } else {
                bufferedWriter.append(" - ");
            }
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void headerRaportCladiri () {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportCladiri))) {
            bufferedWriter.append("ID CLADIRE");
            bufferedWriter.append(",");
            bufferedWriter.append("ADRESA");
            bufferedWriter.append(",");
            bufferedWriter.append("COD POSTAL");
            bufferedWriter.append(",");
            bufferedWriter.append("ID DEPARTAMENT");
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void citireRaportCladiri () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(raportCladiri))) {
            String inregistrare;
            while ((inregistrare = bufferedReader.readLine()) != null) {
                String [] dateInregistrare = inregistrare.split(",");
                System.out.println(dateInregistrare[0] + " " + dateInregistrare[1] + " " + dateInregistrare[2] + " " + dateInregistrare[3]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void scriereRaportDepartamente (Departament departament) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportDepartamente, true))) {
            bufferedWriter.append(departament.getId_departament());
            bufferedWriter.append(",");
            if(departament.getManager() != null) {
                bufferedWriter.append(departament.getManager().getId_angajat());
            } else {
                bufferedWriter.append(" - ");
            }
            bufferedWriter.append(",");
            bufferedWriter.append(departament.getCladire().getId_cladire());
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void headerRaportDepartamente () {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportDepartamente))) {
            bufferedWriter.append("ID DEPARTAMENT");
            bufferedWriter.append(",");
            bufferedWriter.append("ID MANAGER");
            bufferedWriter.append(",");
            bufferedWriter.append("ID CLADIRE");
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void citireRaportDepartamente () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(raportDepartamente))) {
            String inregistrare;
            while ((inregistrare = bufferedReader.readLine()) != null) {
                String [] dateInregistrare = inregistrare.split(",");
                System.out.println(dateInregistrare[0] + " " + dateInregistrare[1] + " " + dateInregistrare[2]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void scriereRaportManageri (Manager manager) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportManageri, true))) {
            bufferedWriter.append(manager.getId_angajat());
            bufferedWriter.append(",");
            bufferedWriter.append(manager.getNume() + " " + manager.getPrenume());
            bufferedWriter.append(",");
            bufferedWriter.append(manager.getId_departament());
            bufferedWriter.append(",");
            bufferedWriter.append(manager.getId_job());
            bufferedWriter.append(",");
            bufferedWriter.append(manager.getEmail());
            bufferedWriter.append(",");
            bufferedWriter.append(manager.getNr_telefon());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(manager.getSalariu()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(manager.getSpor()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(manager.getVechime()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(manager.getSubordonati().size()));
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void headerRaporManageri () {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportManageri))) {
            bufferedWriter.append("ID MANAGER");
            bufferedWriter.append(",");
            bufferedWriter.append("NUME");
            bufferedWriter.append(",");
            bufferedWriter.append("ID DEPARTAMENT");
            bufferedWriter.append(",");
            bufferedWriter.append("ID JOB");
            bufferedWriter.append(",");
            bufferedWriter.append("EMAIL");
            bufferedWriter.append(",");
            bufferedWriter.append("NR TELEFON");
            bufferedWriter.append(",");
            bufferedWriter.append("SALARIU");
            bufferedWriter.append(",");
            bufferedWriter.append("SPOR");
            bufferedWriter.append(",");
            bufferedWriter.append("VECHIME");
            bufferedWriter.append(",");
            bufferedWriter.append("NR SUBORDONATI");
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void citireRaporManageri () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(raportManageri))) {
            String inregistrare;
            while ((inregistrare = bufferedReader.readLine()) != null) {
                String [] dateInregistrare = inregistrare.split(",");
                System.out.println(dateInregistrare[0] + " " + dateInregistrare[1] + " " + dateInregistrare[2]
                        + " " + dateInregistrare[3] + " " + dateInregistrare[4] + " " + dateInregistrare[5]
                        + " " + dateInregistrare[6] + " " + dateInregistrare[7] + " " + dateInregistrare[8]
                        + " " + dateInregistrare[9]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void scriereRaportProgramatori (Programator programator) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportProgramatori, true))) {
            bufferedWriter.append(programator.getId_angajat());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getNume() + " " + programator.getPrenume());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getId_manager());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getId_departament());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getId_job());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getEmail());
            bufferedWriter.append(",");
            bufferedWriter.append(programator.getNr_telefon());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(programator.getSalariu()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(programator.getSpor()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(programator.getVechime()));
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void headerRaporProgramatori () {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(raportProgramatori))) {
            bufferedWriter.append("ID PROGRAMATOR");
            bufferedWriter.append(",");
            bufferedWriter.append("NUME");
            bufferedWriter.append(",");
            bufferedWriter.append("ID MANAGER");
            bufferedWriter.append(",");
            bufferedWriter.append("ID DEPARTAMENT");
            bufferedWriter.append(",");
            bufferedWriter.append("ID JOB");
            bufferedWriter.append(",");
            bufferedWriter.append("EMAIL");
            bufferedWriter.append(",");
            bufferedWriter.append("NR TELEFON");
            bufferedWriter.append(",");
            bufferedWriter.append("SALARIU");
            bufferedWriter.append(",");
            bufferedWriter.append("SPOR");
            bufferedWriter.append(",");
            bufferedWriter.append("VECHIME");
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void citireRaportProgramatori () {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(raportProgramatori))) {
            String inregistrare;
            while ((inregistrare = bufferedReader.readLine()) != null) {
                String [] dateInregistrare = inregistrare.split(",");
                System.out.println(dateInregistrare[0] + " " + dateInregistrare[1] + " " + dateInregistrare[2]
                        + " " + dateInregistrare[3] + " " + dateInregistrare[4] + " " + dateInregistrare[5]
                        + " " + dateInregistrare[6] + " " + dateInregistrare[7] + " " + dateInregistrare[8]
                        + " " + dateInregistrare[9]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
