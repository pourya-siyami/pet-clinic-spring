package database;

import model.Pet;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    Connection connection;

    public DBManager() {
        try {
            String url = "jdbc:sqlite:petclinic.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            System.out.println("error");
        }
    }

    public boolean savePet(Pet pet) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO \"Pet\" (PetType,PetName,PetAge,PetOwner)" +
                    " VALUES( '%s', '%s', '%s', '%s');", pet.petType, pet.petName, pet.petAge, pet.petOwner);
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Pet> loadPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM \"Pet\" ;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String petType = resultSet.getString("PetType");
                String petName = resultSet.getString("PetName");
                int petAge = resultSet.getInt("PetAge");
                String petOwner = resultSet.getString("PetOwner");
                Pet pet = new Pet(petType, petName, petAge, petOwner);
                pets.add(pet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pets;
    }
}