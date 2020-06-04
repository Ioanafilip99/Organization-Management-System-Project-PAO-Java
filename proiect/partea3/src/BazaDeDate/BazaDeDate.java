package BazaDeDate;

import Clase.Cladire;
import Clase.Programator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BazaDeDate {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/proiect";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";

    private static final String GEL_ALL_CLADIRI = "SELECT * FROM cladire";
    private static final String CREATE_NEW_CLADIRE = "INSERT INTO cladire(id_cladire,adresa,cod_postal,id_departament)"
            + "values(?,?,?,?)";
    private static final String DELETE_CLADIRE = "DELETE FROM cladire WHERE id_cladire = ?";
    private static final String DELETE_CLADIRI  ="DELETE FROM cladire";
    private static final String UPDATE_CLADIRE_DEPARTAMENT = "UPDATE cladire SET id_departament = ? WHERE id_cladire = ?";

    private static final String GEL_ALL_PROGRAMATORI = "SELECT * FROM programator";
    private static final String CREATE_NEW_PROGRAMATOR = "INSERT INTO programator(id_angajat,nume,prenume,email,nr_telefon,id_job,salariu,id_departament,vechime,id_manager,spor)"
            + "values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_PROGRAMATOR = "DELETE FROM programator WHERE id_angajat = ?";
    private static final String DELETE_PROGRAMATORI  ="DELETE FROM programator";
    private static final String UPDATE_PROGRAMATOR_MANAGER = "UPDATE programator SET id_manager = ? WHERE id_angajat = ?";


    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public List<Cladire> getCladiri() throws SQLException {
        List<Cladire> cladiri = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GEL_ALL_CLADIRI);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Cladire c = new Cladire(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            cladiri.add(c);
        }

        return cladiri;
    }

    public boolean addCladire (Cladire c) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CREATE_NEW_CLADIRE);
        preparedStatement.setString(1, c.getId_cladire());
        preparedStatement.setString(2, c.getAdresa());
        preparedStatement.setString(3, c.getCod_postal());
        preparedStatement.setString(4, c.getId_departament());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteCladire (int id_cladire) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_CLADIRE);
        preparedStatement.setInt(1, id_cladire);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteCladiri () throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_CLADIRI);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateCladire (int id_cladire, String id_departament) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_CLADIRE_DEPARTAMENT);
        preparedStatement.setString(1, id_departament);
        preparedStatement.setInt(2, id_cladire);
        return preparedStatement.executeUpdate() > 0;
    }

    public List<Programator> getProgramatori() throws SQLException {
        List<Programator> programatori = new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GEL_ALL_PROGRAMATORI);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Programator p = new Programator(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7),
                    rs.getString(8), rs.getInt(9), rs.getString(10));
            programatori.add(p);
        }

        return programatori;
    }


    public boolean addProgramator (Programator p) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CREATE_NEW_PROGRAMATOR);
        preparedStatement.setString(1, p.getId_angajat());
        preparedStatement.setString(2, p.getNume());
        preparedStatement.setString(3, p.getPrenume());
        preparedStatement.setString(4, p.getEmail());
        preparedStatement.setString(5, p.getNr_telefon());
        preparedStatement.setString(6, p.getId_job());
        preparedStatement.setDouble(7, p.getSalariu());
        preparedStatement.setString(8, p.getId_departament());
        preparedStatement.setInt(9, p.getVechime());
        preparedStatement.setString(10, p.getId_manager());
        preparedStatement.setDouble(11, p.getSpor());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteProgramator (int id_angajat) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PROGRAMATOR);
        preparedStatement.setInt(1, id_angajat);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteProgramatori () throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PROGRAMATORI);
        return preparedStatement.executeUpdate() > 0;
    }

//    public boolean updateProgramator (int id_departament, String id_manager) throws SQLException {
//        PreparedStatement preparedStatement = getDbConnection().prepareStatement(UPDATE_PROGRAMATOR_MANAGER);
//        preparedStatement.setString(1, id_manager);
//        preparedStatement.setInt(2, id_departament);
//        return preparedStatement.executeUpdate() > 0;
//    }

}
