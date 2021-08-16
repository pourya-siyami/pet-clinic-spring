import config.Config;
import database.DBManager;
import model.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String petType;
    static String petName;
    static int petAge;
    static String petOwner;
    static DBManager bean;

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        bean = (DBManager) context.getBean("myDb");
        getDataFromUser();
    }

    public static void getDataFromUser() {
        boolean label = true;
        while (label) {
            System.out.println("Press 1 to register a pet \n" +
                    "Press 2 to show pets \n" +
                    "press 3 to exit");
            Scanner s = new Scanner(System.in);
            int userInput = s.nextInt();
            switch (userInput) {
                case 1:
                    Pet pet = getPet();
                    saveToDatabase(pet);
                    break;
                case 2:
                    showPets();
                    break;
                case 3:
                    label = false;
                    break;
            }
        }
    }

    private static Pet getPet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register your pet and its owner");
        System.out.println("Enter your pet type[Cat, Dog, Bird...]:");
        petType = scanner.next();
        System.out.println("Enter your pet name:");
        petName = scanner.next();
        System.out.println("Enter your pet age:");
        petAge = scanner.nextInt();
        System.out.println("Enter the name of the pet owner:");
        petOwner = scanner.next();
        return new Pet(petType, petName, petAge, petOwner);
    }

    private static void saveToDatabase(Pet pet) {
        boolean success = bean.savePet(pet);
        if (success)
            System.out.println("saved to DB");
        else
            System.out.println("failed to save");
    }

    private static void showPets() {
        ArrayList<Pet> pets = bean.loadPets();
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }
}