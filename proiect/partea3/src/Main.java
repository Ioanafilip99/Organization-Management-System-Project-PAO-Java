import BazaDeDate.BazaDeDate;
import Clase.*;
import Service.MainService;
import Thread.MyCallable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static Organizatie organizatie = new Organizatie();
    public static BazaDeDate bazaDeDate = new BazaDeDate();

    public static void main(String[] args) throws InterruptedException {

        MainService mainService = new MainService();
//        BazaDeDate bazaDeDate = new BazaDeDate();
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Ce nume vrei sa dai organizatiei?");
//        String denumire  = scanner.next();
//        Organizatie organizatie = new Organizatie(denumire);

        System.out.println("Vrei sa pastrezi datele din baza de date? (da/nu): ");
        String opt_baza = scanner.next();
        if(opt_baza.equals("da")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                List<Cladire> cladiri = bazaDeDate.getCladiri();
                for(Cladire c : cladiri) {
                    organizatie.adaugaCladire(c);
                }

                List<Programator> programatori = bazaDeDate.getProgramatori();
                for(Programator p : programatori) {
                    organizatie.adaugaAngajat(p);
                }

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else if (opt_baza.equals("nu")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                bazaDeDate.deleteCladiri();
                bazaDeDate.deleteProgramatori();

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Raspuns gresit, datele se vor sterge");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                bazaDeDate.deleteCladiri();
                bazaDeDate.deleteProgramatori();

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }


//        System.out.println("Organizatia " + organizatie.getDenumire() + "\n");
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

        System.out.println("13 -> Generare raport programatori, sortat crescator dupa nume");
        System.out.println("14 -> Afisare raport programatpori, sortat crescator dupa nume");

        System.out.println("15 -> Generare raport manageri");
        System.out.println("16 -> Afisare raport manageri");

        System.out.println("17 -> Generare raport departamente");
        System.out.println("18 -> Afisare raport departamente");

        System.out.println("19 -> Generare raport cladiri");
        System.out.println("20 -> Afisare raport cladiri");

        System.out.println("21 -> Stergere baze de date cladiri");
        System.out.println("22 -> Stergere baze de date programatori");
        System.out.println("23 -> Afiseaza programatori din baza de date");

        System.out.println("24 -> Interfata cladiri si departamente");

        System.out.println("25 -> Calcul medii salarii si medie totala");



        System.out.println("\nAlege o optiune: ");

        while(true) {
            String optiune = scanner.next();
            switch (optiune) {

                case "0":
                    System.out.println("EXIT");
                    System.exit(0);
                    break;

                case "1":
                    System.out.println("\nAi ales optiunea 1\n");
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
                                int index_departament = 0;
                                Departament departament = null;
                                while(departament == null) {
                                    System.out.println("Alege un departament (dupa index): ");
                                    System.out.println(organizatie.getDepartamente());
                                    index_departament = scanner.nextInt();
                                    departament = organizatie.alegeDepartament(index_departament);
                                }

                                System.out.println("vechime: ");
                                int vechime = scanner.nextInt();

                                angajat = new Manager(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, departament.getId_departament(), vechime);
                                organizatie.adaugaAngajat(angajat);
                                organizatie.setDepartamentManager(index_departament, (Manager) angajat);

                                // daca este programator ii alegem managerul si va prelua departamentul managerului
                            } else if (tip_angajat.equals("programator")) {
                                int index_manager = 0;
                                Manager manager = null;
                                while(manager == null) {
                                    System.out.println("Alege un manager din lista de manageri (dupa index)");
                                    System.out.println(organizatie.getManageri());
                                    index_manager = scanner.nextInt();
                                    manager = organizatie.alegeManager(index_manager);
                                }

                                System.out.println("vechime: ");
                                int vechime = scanner.nextInt();

                                angajat = new Programator(id_angajat, nume, prenume, email, nr_telefon, id_job, salariu, manager.getId_departament(), vechime, manager.getId_angajat());
                                //adaugare la baza de date
                                try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    bazaDeDate.addProgramator((Programator)angajat);
                                }
                                catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                organizatie.adaugaAngajat(angajat);
                                manager.adaugaSubordonat((Programator) angajat);


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
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "2":
                    System.out.println("\nAi ales optiunea 2\n");

                    System.out.println("Programatori din organizatie: ");
                    System.out.println(organizatie.getAngajati());

                    System.out.println("Introdu indicele angajatului pe care vrei sa il stergi: ");
                    int index_stergere_angajat = scanner.nextInt();

                    if(organizatie.alegeAngajat(index_stergere_angajat) instanceof Programator) {
                        //strgere din baza de date
                        try {
                            Class.forName("com.mysql.jdbc.Driver");

                            int id = Integer.parseInt(organizatie.alegeAngajat(index_stergere_angajat).getId_angajat());
                            bazaDeDate.deleteProgramator(id);

                        }
                        catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    organizatie.stergeAngajat(index_stergere_angajat);
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "3":
                    System.out.println("\nAi ales optiunea 3\n");
                    System.out.println("Angajatii din organizatie sunt: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "4":
                    System.out.println("\nAi ales optiunea 4\n");
                    if(organizatie.getNumarCladiri() > 0 ) {
                        System.out.println("Introdu urmatoarele date: ");
                        System.out.println("Id Departament: ");
                        String id_departament = scanner.next();

                        Departament departament = null;
                        int index_cladire = 0;
                        Cladire cladire = null;

                        while(cladire == null) {
                            System.out.println("Alege o cladire (dupa index): ");
                            System.out.println(organizatie.afisareCladiri());
                            index_cladire = scanner.nextInt();
                            cladire = organizatie.alegeCladire(index_cladire);
                        }

                        departament = new Departament(id_departament, cladire);
                        organizatie.adaugaDepartament(departament);
                        organizatie.setCladireDepartament(index_cladire, departament);


                        // modific campul id_departament in cladire din baza de date
                        try {
                            Class.forName("com.mysql.jdbc.Driver");

                            int id_c = Integer.parseInt(cladire.getId_cladire());
                            bazaDeDate.updateCladire(id_c, id_departament);

                        }
                        catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        System.out.println("Trebuie sa existe macar o cladire in companie pt a crea un departament!");
                    }

                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "5":
                    System.out.println("\nAi ales optiunea 5\n");
                    System.out.println("Introdu indicele departamentului pe care vrei sa il stergi: ");
                    int index_stergere_departament = scanner.nextInt();
                    organizatie.stergeDepartament(index_stergere_departament);
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "6":
                    System.out.println("\nAi ales optiunea 6\n");
                    System.out.println("Departamentele din organizatie sunt: ");
                    System.out.println(organizatie.getDepartamente());
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "7":
                    System.out.println("\nAi ales optiunea 7\n");

                    System.out.println("Introdu urmatoarele date: ");
                    System.out.println("Id: ");
                    String id_cladire = scanner.next();
                    System.out.println("Adresa: ");
                    String adresa = scanner.next();
                    System.out.println("Cod Postal: ");
                    String cod_postal = scanner.next();

                    Cladire cladire = new Cladire(id_cladire, adresa, cod_postal);
                    organizatie.adaugaCladire(cladire);

                    //adaugare la baza de date
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        bazaDeDate.addCladire(cladire);
                    }
                    catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "8":
                    System.out.println("\nAi ales optiunea 8\n");

                    System.out.println("Cladiri din organizatie: ");
                    System.out.println(organizatie.afisareCladiri());

                    System.out.println("Introdu indicele cladirii pe care vrei sa o stergi: ");
                    int index_stergere_cladire = scanner.nextInt();

                    Cladire cladire_de_sters = organizatie.alegeCladire(index_stergere_cladire);
                    organizatie.stergeCladire(index_stergere_cladire);

                    //stergere din baza de date
                    try {
                        Class.forName("com.mysql.jdbc.Driver");

                        int id = Integer.parseInt(cladire_de_sters.getId_cladire());
                        bazaDeDate.deleteCladire(id);

                    }
                    catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "9":
                    System.out.println("\nAi ales optiunea 9\n");
                    System.out.println("Cladirile din organizatie sunt: ");
                    System.out.println(organizatie.afisareCladiri());

                    // afisare baza de date
                    try {
                        Class.forName("com.mysql.jdbc.Driver");

                        System.out.println("Cladiri din baza de date: ");
                        System.out.println(bazaDeDate.getCladiri());
                    }
                    catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Alege urmatoarea optiune: \n");
                    break;

                case "10":
                    System.out.println("Ai ales optiunea 10\n");
                    organizatie.sortareAngajatiId();
                    System.out.println("Lista sortata dupa id a angajatilor este: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "11":
                    System.out.println("\nAi ales optiunea 11\n");
                    organizatie.sortareAngajatiNume();;
                    System.out.println("Lista sortata dupa nume a angajatilor este: ");
                    System.out.println(organizatie.getAngajati());
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "12":
                    System.out.println("\nAi ales optiunea 12\n");
                    organizatie.sortareCladiri();
                    System.out.println("Lista sortata dupa codul postal a cladirilor este: ");
                    System.out.println(organizatie.afisareCladiri());
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "13":
                    System.out.println("\nAi ales optiunea 13\n");
                    mainService.headerRaporProgramatori();
                    List<Programator> programatori = organizatie.getProgramatori();
                    Collections.sort(programatori, Comparator.comparing(Programator::getNume));
                    programatori.forEach(programator -> mainService.scriereRaportProgramatori(programator));
                    System.out.println("Raportul a fost creat cu succes!");
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "14":
                    System.out.println("\nAi ales optiunea 14\n");
                    mainService.citireRaportProgramatori();
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "15":
                    System.out.println("\nAi ales optiunea 15\n");
                    mainService.headerRaporManageri();
                    List<Manager> manageri = organizatie.getManageri();
                    manageri.forEach(manager -> mainService.scriereRaportManageri(manager));
                    System.out.println("Raportul a fost creat cu succes!");
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "16":
                    System.out.println("\nAi ales optiunea 16\n");
                    mainService.citireRaporManageri();
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "17":
                    System.out.println("\nAi ales optiunea 17\n");
                    mainService.headerRaportDepartamente();
                    List<Departament> departamente = organizatie.getDepartamente();
                    departamente.forEach(departament -> mainService.scriereRaportDepartamente(departament));
                    System.out.println("Raportul a fost creat cu succes!");
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "18":
                    System.out.println("\nAi ales optiunea 18\n");
                    mainService.citireRaportDepartamente();
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "19":
                    System.out.println("\nAi ales optiunea 19\n");
                    mainService.headerRaportCladiri();
                    Cladire [] cladiri = organizatie.getCladiri();
                    for (Cladire c : cladiri) {
                        mainService.scriereRaportCladiri(c);
                    }
                    System.out.println("Raportul a fost creat cu succes!");
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "20":
                    System.out.println("\nAi ales optiunea 20\n");
                    mainService.citireRaportCladiri();
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "21":
                    System.out.println("\nAi ales optiunea 21\n");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        bazaDeDate.deleteCladiri();

                    }catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "22":
                    System.out.println("\nAi ales optiunea 22\n");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        bazaDeDate.deleteProgramatori();

                    }catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "23":
                    System.out.println("\nAi ales optiunea 23\n");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        System.out.println("Programatori din baza de date: ");
                        System.out.println(bazaDeDate.getProgramatori());

                    }catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "24":
                    System.out.println("\nAi ales optiunea 24\n");

                    JFrame jFrame2 = new JFrame("DEPARTAMENTE");

                    JLabel idLabel2 = new JLabel("ID: ");
                    JTextField idTextField2 = new JTextField();
                    idLabel2.setBounds(50, 50, 200, 30);
                    idTextField2.setBounds(150, 50, 200, 30);

                    JList<Cladire> jList2 = new JList<>();
                    jList2.setBounds(50, 200, 400, 200);

                    JList<Departament> jList3 = new JList<>();
                    jList3.setBounds(50, 500, 400, 200);
                    jList3.setListData(getDepartamenteAsArray());

                    JButton jButtonAdd2 = new JButton("SALVEAZA");
                    jButtonAdd2.setBounds(500, 100, 150 ,30);
                    jButtonAdd2.addActionListener(addActionListener2(idTextField2, jList2, jList3));

                    JButton jButtonDelete2 = new JButton("STERGE DEPARTAMENT");
                    jButtonDelete2.setBounds(500, 500, 200, 30);
                    jButtonDelete2.addActionListener(deleteActionListener2(jList3));


                    jFrame2.add(idLabel2);
                    jFrame2.add(idTextField2);
                    jFrame2.add(jList2);
                    jFrame2.add(jButtonAdd2);
                    jFrame2.add(jList3);
                    jFrame2.add(jButtonDelete2);
                    jFrame2.setSize(800, 800);
                    jFrame2.setLayout(null);

                    JFrame jFrame1 = new JFrame("CLADIRi");

                    JLabel idLabel = new JLabel("ID: ");
                    JTextField idTextField = new JTextField();
                    idLabel.setBounds(50, 50, 200, 30);
                    idTextField.setBounds(150, 50, 200, 30);

                    JLabel adresaLabel = new JLabel("ADRESA: ");
                    JTextField adresaTextField = new JTextField();
                    adresaLabel.setBounds(50, 100, 200, 30);
                    adresaTextField.setBounds(150, 100, 200, 30);

                    JLabel codLabel = new JLabel("COD POSTAL: ");
                    JTextField codTextField = new JTextField();
                    codLabel.setBounds(50, 150, 200, 30);
                    codTextField.setBounds(150, 150, 200, 30);

                    JList<Cladire> jList = new JList<>();
                    jList.setBounds(50, 300, 400, 200);
                    jList.setListData(getCladiriAsArray());

                    JButton jButtonAdd = new JButton("SALVEAZA");
                    jButtonAdd.setBounds(500, 100, 150 ,30);
                    jButtonAdd.addActionListener(addActionListener(idTextField, adresaTextField, codTextField, jList));

                    JButton jButtonDelete = new JButton("STERGE CLADIRE");
                    jButtonDelete.setBounds(500, 300, 200, 30);
                    jButtonDelete.addActionListener(deleteActionListener(jList));

                    JButton jButtonNextPage = new JButton("DEPARTAMENTE");
                    jButtonNextPage.setBounds(500, 400, 200, 30);
                    jButtonNextPage.addActionListener(actionEvent -> {
                        jList2.setListData(getCladiriAsArray());
                        jFrame2.add(jList2);
                        jFrame2.setVisible(true);

                    });

                    jFrame1.add(idLabel);
                    jFrame1.add(idTextField);
                    jFrame1.add(adresaLabel);
                    jFrame1.add(adresaTextField);
                    jFrame1.add(codLabel);
                    jFrame1.add(codTextField);
                    jFrame1.add(jButtonAdd);
                    jFrame1.add(jButtonDelete);
                    jFrame1.add(jList);
                    jFrame1.add(jButtonNextPage);
                    jFrame1.setSize(800, 800);
                    jFrame1.setLayout(null);
                    jFrame1.setVisible(true);

                    System.out.println("Alege urmatoarea optiune: ");
                    break;

                case "25":
                    System.out.println("\nAi ales optiunea 25\n");

                    int sizeManageri = organizatie.getManageri().size();
                    int sizeProgramatori = organizatie.getProgramatori().size();

                    double [] salariiManageri = new double[sizeManageri];
                    double [] salariiProgramatori = new double[sizeProgramatori];

                    int im = 0;
                    int ip = 0;

                    for(Angajat a : organizatie.getAngajati()) {
                        if(a instanceof Manager) {
                            salariiManageri[im] = a.getSalariu();
                            im = im + 1;
                        }
                        else {
                            salariiProgramatori[ip] = a.getSalariu();
                            ip = ip + 1;
                        }
                    }

                    ExecutorService executorService = Executors.newFixedThreadPool(5);

                    MyCallable m1 = new MyCallable(salariiManageri);
                    MyCallable m2 = new MyCallable(salariiProgramatori);
                    List<Future<Double>> ftResult = executorService.invokeAll(Arrays.asList(m1,m2));
                    double callableSum = ftResult.stream().mapToDouble(result -> {
                        try {
                            return result.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        throw  new RuntimeException("ERROR ON CALCULATION!!");
                    }).sum();

                    System.out.println("Media salarii: " + String.format("%.2f", callableSum/2));
                    System.out.println("Alege urmatoarea optiune: ");
                    break;


                default:
                    System.out.println("\n");
                    System.out.println("Ai ales o optiune incorecta, selectati una din cele de mai sus.");
                    break;
            }
        }
    }

    private static Cladire [] getCladiriAsArray() {
        return organizatie.getCladiri();
    }

    private static Departament [] getDepartamenteAsArray() {
        return organizatie.getDepartamente().toArray(new Departament[0]);
    }

    private static ActionListener addActionListener(JTextField idTextField, JTextField adresaTextField,
                                                    JTextField codTextField, JList jList) {
        ActionListener actionListener = actionEvent -> {
            String id = idTextField.getText();
            String adresa = adresaTextField.getText();
            String cod_postal = codTextField.getText();

            Cladire cladire = new Cladire(id, adresa, cod_postal);
            organizatie.adaugaCladire(cladire);
            jList.setListData(getCladiriAsArray());
            try {
                Class.forName("com.mysql.jdbc.Driver");
                bazaDeDate.addCladire(cladire);

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Cladirea a fost adaugata cu succes!");
        };
        return actionListener;
    }

    private static ActionListener addActionListener2(JTextField idTextField, JList jList2, JList jList3) {

        ActionListener actionListener = actionEvent -> {
            String id = idTextField.getText();
            int index_cladire = jList2.getSelectedIndex();
            Cladire cladire = organizatie.alegeCladire(index_cladire);
            cladire.setId_departament(id);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                bazaDeDate.updateCladire(Integer.parseInt(cladire.getId_cladire()), id);

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            Departament departament = new Departament(id, cladire);
            organizatie.adaugaDepartament(departament);
            jList3.setListData(getDepartamenteAsArray());

            JOptionPane.showMessageDialog(null, "Departamentul a fost adaugat cu succes!");
        };
        return actionListener;
    }


    private static ActionListener deleteActionListener(JList<Cladire> jList) {
        ActionListener actionListener = actionEvent -> {
            int index = jList.getSelectedIndex();
            Cladire cladire = organizatie.alegeCladire(index);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                int id = Integer.parseInt(cladire.getId_cladire());
                bazaDeDate.deleteCladire(id);

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            organizatie.stergeCladire(index);
            jList.setListData(getCladiriAsArray());
            JOptionPane.showMessageDialog(null, "Cladirea a fost stearsa cu succes!");
        };
        return actionListener;
    }

    private static ActionListener deleteActionListener2(JList<Departament> jList3) {
        ActionListener actionListener = actionEvent -> {
          int index = jList3.getSelectedIndex();
          organizatie.stergeDepartament(index);
          jList3.setListData(getDepartamenteAsArray());
            JOptionPane.showMessageDialog(null, "Departamentul a fost sters cu succes!");
        };
        return actionListener;
    }
}
