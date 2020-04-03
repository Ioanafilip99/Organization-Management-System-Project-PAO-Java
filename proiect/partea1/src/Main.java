import Clase.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ce nume vrei sa dai organizatiei?");
        String denumire  = scanner.next();
        Organizatie organizatie = new Organizatie(denumire);
        System.out.println("Organizatia " + organizatie.getDenumire() + "\n");
        System.out.println("REGULI de folosire: ");
        System.out.println("-> Ordinea de creare a obiectelor este: cladire -> departament -> manager -> programatori");

        System.out.println("Optiuni:\n");
        System.out.println("0 -> EXIT");
        System.out.println("1 -> Adauga angajat");
        System.out.println("2 -> Sterge angajat");
        System.out.println("3 -> Listeaza angajati");
        System.out.println("4 -> Adauga deartament");
        System.out.println("5 -> Sterge departament");
        System.out.println("6 -> Listeaza departamente");
        System.out.println("7 -> Adauga cladire");
        System.out.println("8 -> Sterge cladire");
        System.out.println("9 -> Listeaza cladiri");
        System.out.println("10 -> Sorteaza angajati dupa id");
        System.out.println("11 -> Sorteaza angajati dupa nume");
        System.out.println("12 -> Sorteaza cladirile dupa codul postal");
        System.out.println("\nAlege o optiune: ");

        while(true) {
            String optiune = scanner.next();
            switch (optiune) {

                case "0":
                    System.out.println("EXIT");
                    System.exit(0);
                    break;

                case "1":
                    System.out.println("Ai ales optiunea 1\n");
                    if(organizatie.getDepartamente().size() > 0) {
                        System.out.println("Ce tip de angajat vrei sa adaugi, manager / programator? ");
                        String tip_angajat = scanner.next();
                        if (tip_angajat.equals("manager") || (tip_angajat.equals("programator") && organizatie.getManageri().size() > 0)) {

                            Angajat angajat = null;

                            System.out.println("Introdu urmatoarele date: ");
                            System.out.println("Id Angajat: ");
                            String id_angajat = scanner.next();
                            System.out.println("Nume: ");
                            String nume = scanner.next();
                            System.out.println("Prenume: ");
                            String prenume = scanner.next();
                            System.out.println("Email: ");
                            String email = scanner.next();
                            System.out.println("Numar telefon: ");
                            String nr_telefon = scanner.next();
                            System.out.println("Id Job: ");
                            String id_job = scanner.next();
                            System.out.println("Salariu: ");
                            Double salariu = scanner.nextDouble();

                            // daca este manager ii alegem departamentul
                            if (tip_angajat.equals("manager")) {
                                System.out.println("Alege un departament (dupa index): ");
                                System.out.println(organizatie.getDepartamente());
                                int index_departament = scanner.nextInt();
                                Departament departament = organizatie.alegeDepartament(index_departament);

                                System.out.println("vechime: ");
                                int vechime = scanner.nextInt();

                                angajat = new Manager(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, departament.getId_departament(), vechime);
                                organizatie.adaugaAngajat(angajat);
                                organizatie.setDepartamentManager(index_departament, (Manager) angajat);

                                // daca este programator ii alegem managerul si va prelua departamentul managerului
                            } else if (tip_angajat.equals("programator")) {
                                System.out.println("Alege un manager din lista de manageri (dupa index)");
                                System.out.println(organizatie.getManageri());
                                int index_manager = scanner.nextInt();
                                Manager manager = organizatie.alegeManager(index_manager);

                                System.out.println("vechime: ");
                                int vechime = scanner.nextInt();

                                angajat = new Programator(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, manager.getId_departament(), vechime, manager.getId_angajat());
                                organizatie.adaugaAngajat(angajat);
                                //organizatie.ManagerSubordonat(index_manager, angajat);

                            }

                        } else if (tip_angajat.equals("programator") && organizatie.getManageri().size() == 0) {
                            System.out.println("Trebuie sa existe cel putin un manager in companie pt a putea adauga un programator!\n");
                        } else {
                            System.out.println("Nu exista acest tip de angajat! Alege intre manager si programator data viitoare!\n");
                        }
                    }
                    else {
                        System.out.println("Trebuie sa existe macar un departament in companie pt a putea adauga angajati!");
                    }
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "2":
                    System.out.println("Ai ales optiunea 2\n");
                    System.out.println("Introdu indicele angajatului pe care vrei sa il stergi: ");
                    int index_stergere_angajat = scanner.nextInt();
                    organizatie.stergeAngajat(index_stergere_angajat);
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "3":
                    System.out.println("Ai ales optiunea 3\n");
                    System.out.println("Angajatii din organizatie sunt: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "4":
                    System.out.println("Ai ales optiunea 4\n");
                    if(organizatie.getNumarCladiri() > 0 ) {
                        System.out.println("Introdu urmatoarele date: ");
                        System.out.println("Id Departament: ");
                        String id_departament = scanner.next();

                        Departament departament = null;

                        System.out.println("Alege o cladire (dupa index): ");
                        System.out.println(organizatie.getCladiri());
                        int index_cladire = scanner.nextInt();
                        Cladire cladire = organizatie.alegeCladire(index_cladire);
                        //System.out.println(cladire);

                        departament = new Departament(id_departament, cladire);
                        organizatie.adaugaDepartament(departament);
                        organizatie.setCladireDepartament(index_cladire, departament);

                    }
                    else {
                        System.out.println("Trebuie sa existe macar o cladire in companie pt a crea un departament!");
                    }

                    System.out.println();
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "5":
                    System.out.println("Ai ales optiunea 5\n");
                    System.out.println("Introdu indicele departamentului pe care vrei sa il stergi: ");
                    int index_stergere_departament = scanner.nextInt();
                    organizatie.stergeDepartament(index_stergere_departament);
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "6":
                    System.out.println("Ai ales optiunea 6\n");
                    System.out.println("Departamentele din organizatie sunt: ");
                    System.out.println(organizatie.getDepartamente());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "7":
                    System.out.println("Ai ales optiunea 7\n");
                    System.out.println("Introdu urmatoarele date: ");
                    System.out.println("Id: ");
                    String id_cladire = scanner.next();
                    System.out.println("Adresa: ");
                    String adresa = scanner.next();
                    System.out.println("Cod Postal: ");
                    String cod_postal = scanner.next();

                    Cladire cladire = new Cladire(id_cladire, adresa, cod_postal);
                    organizatie.adaugaCladire(cladire);

                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "8":
                    System.out.println("Ai ales optiunea 8\n");
                    System.out.println("Introdu indicele cladirii pe care vrei sa o stergi: ");
                    int index_stergere_cladire = scanner.nextInt();
                    organizatie.stergeCladire(index_stergere_cladire);
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "9":
                    System.out.println("Ai ales optiunea 9\n");
                    System.out.println("Cladirile din organizatie sunt: ");
                    System.out.println(organizatie.getCladiri());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "10":
                    System.out.println("Ai ales optiunea 10\n");
                    organizatie.sortareAngajatiId();
                    System.out.println("Lista sortata dupa id a angajatilor este: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "11":
                    System.out.println("Ai ales optiunea 11\n");
                    organizatie.sortareAngajatiNume();;
                    System.out.println("Lista sortata dupa nume a angajatilor este: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "12":
                    System.out.println("Ai ales optiunea 12\n");
                    organizatie.sortareCladiri();
                    System.out.println("Lista sortata dupa codul postal a cladirilor este: ");
                    System.out.println(organizatie.getCladiri());
                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                default:
                    System.out.println("\n");
                    System.out.println("Ai ales o optiune incorecta, selectati una din cele de mai sus.");
                    break;
            }
        }
    }
}
