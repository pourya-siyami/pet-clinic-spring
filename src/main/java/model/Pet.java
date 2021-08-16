package model;

public class Pet {
    public String petType;
    public String petName;
    public int petAge;
    public String petOwner;

    public Pet(String petType, String petName, int petAge, String petOwner) {
        this.petType = petType;
        this.petName = petName;
        this.petAge = petAge;
        this.petOwner = petOwner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petType='" + petType + '\'' +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petOwner='" + petOwner + '\'' +
                '}';
    }
}