import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pet> pets = new ArrayList<>();
//        ArrayList<Store.Interfaces.User> users = new ArrayList<>();
        Pet pet;
        UserData data;
        Scanner scan = new Scanner(System.in);
        int c;
        do {
            while (true) {
                try {
                    System.out.println("1) to add animals:\n2) to buy animals:\n3) to terminate the program:");
                    c = scan.nextInt();
                    scan.nextLine();
                    if (c >= 0 && c <= 3) {
                        if (c == 2 && pets.isEmpty()) {
                            System.out.println("You must add some animals first.");
                        } else break;
                    } else System.out.println("Invalid input! Please enter a number that's given down there:");
                } catch (Exception e) {
                    scan.nextLine();
                    System.out.println("Please enter a valid input.\nInvalid input! Please enter a number that's given down there:");
                }
            }
            switch (c) {
                case 1:
                    String m = "";
                    do {
                        if (!m.equalsIgnoreCase("n")) {
                            try {
                                System.out.println("Enter what type of animal you want to add(Example: cat, dog, etc):");
                                String animalType;
                                animalType = scan.nextLine();
                                if (animalType.matches(".*\\d.*")) {
                                    System.out.println("Enter a valid animal.");
                                    continue;
                                }
                                System.out.println("Enter the animal's breed(Example: cat: ragdoll):");
                                String animalBreed;
                                while (true) {
                                    animalBreed = scan.nextLine();
                                    if (animalBreed.matches(".*\\d.*")) {
                                        System.out.println("Please enter a valid breed:");
                                    } else break;
                                }
                                AnimalInfo animalInfo = getResult(scan);
                                pet = new Pet(animalType, animalBreed, animalInfo.animalAge(), animalInfo.animalWeight(), animalInfo.animalHeight(), animalInfo.animalPrice());
                                System.out.println("\nEnter Y if you would like to add extra info about the animal such as it's favorite food, description, etc.\nPress any other key/keys to continue");
                                if (scan.nextLine().equalsIgnoreCase("Y")) {
                                    System.out.println("Enter the animal's favorite food:");
                                    pet.setFavoriteFood(scan.nextLine());
                                    System.out.println("Enter the animal's description:");
                                    pet.setDescription(scan.nextLine());
                                    System.out.println("Can this animal swim? Reply with Yes or No only:");
                                    while (true) {
                                        String canSwim = scan.nextLine();
                                        if (canSwim.equalsIgnoreCase("Yes") || canSwim.equalsIgnoreCase("no")) {
                                            pet.setSwim(canSwim);
                                            break;
                                        } else System.out.println("Please enter yes or no only.");
                                    }
                                }
                                System.out.println("Here's the pet\n" + pet + "\nEnter Yes to confirm the pet or press any other key/keys to discard the animal insertion:");
                                String addPet = scan.nextLine();
                                if (addPet.equalsIgnoreCase("Yes") || addPet.equalsIgnoreCase("ye")) {
                                    pets.add(pet);
                                    System.out.println("You've added an animal in the list successfully!");
                                }
                                System.out.println("Press N to stop adding animals\nPress any other key/keys to add more animals");
                                m = scan.nextLine();
                            } catch (Exception e) {
                                scan.nextLine();
                                System.out.println("Invalid input!\nPlease provide valid input");
                            }
                        }
                    } while (!m.equalsIgnoreCase("n"));
                    break;
                case 2:
                    System.out.println("--------------------------------");
                    System.out.println("Animals:");
                    pets.forEach(p -> System.out.println(p.getType()));
                    System.out.println("Select an animal you're interested in:");
                    String selectedAnimal;
                    while (true) {
                        selectedAnimal = scan.nextLine();

                        if (selectedAnimal.matches(".*\\d.*")) {
                            System.out.println("Please select an animal from the list given up there:");
                            continue;
                        }
                        boolean found = false;
                        for (Pet animal : pets) {
                            if (selectedAnimal.equalsIgnoreCase(animal.getType())) {
                                System.out.println(animal);
                                found = true;
                            }
                        }
                        if (found) {
                            break;
                        } else System.out.println("Please select an animal from the list given up there:");
                    }
                    System.out.println("You must have an account to go further\n        Register Now!\n1) Register.\n2E)Exit");
                    int r;
                    while (true) {
                        try {
                            r = scan.nextInt();
                            scan.nextLine();
                            break;
                        } catch (Exception e) {
                            scan.nextLine();
                            System.out.println("Invalid input!\n1) Register.\n2)Exit");
                        }
                    }
                    if (r == 2) {
                        System.out.println("Goodbye!");
                        break;
                    }
                    System.out.println("Enter your name:");
                    String name;
                    while (true) {
                        name = scan.nextLine();
                        if (name.matches(".*\\d.*")) {
                            System.out.println("Invalid input!\nA name must contain only alphabets");
                        } else break;
                    }
                    break;
                case 3:
                    int num;
                    try {
                        while (true) {
                            try {
                                num = scan.nextInt();
                                scan.nextLine();
                                if (Integer.toString(num).length() != 10)
                                    System.out.println("Invalid phone number!\nPlease insert a valid phone number");
                                else break;
                            } catch (Exception e) {
                                scan.nextLine();
                                System.out.println("Invalid phone number!\nPlease insert a valid phone number");
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    String email;
                    try {

                    }catch (Exception e) {
                        scan.nextLine();
                    }
                    break;
            }
        } while (c != 0);
    }



    private static AnimalInfo getResult(Scanner scan) {
        System.out.println("Enter the age of the animal:(Int)");
        int animalAge;
        while (true) {
            try {
                animalAge = scan.nextInt();
                scan.nextLine();
                break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Please enter the valid age:(Int)");
            }
        }
        System.out.println("Enter the animal's weight:(Int-Float)");
        float animalWeight;
        while (true) {
            try {
                animalWeight = scan.nextFloat();
                scan.nextLine();
                break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Please enter weight:(Int-Float)");
            }
        }
        System.out.println("Enter its height(in cms):(Int)");
        int animalHeight;
        while (true) {
            try {
                animalHeight = scan.nextInt();
                scan.nextLine();
                break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Please enter the valid height:(Int)");
            }
        }
        System.out.println("Enter the price of the animal:");
        double animalPrice;
        while (true) {
            try {
                animalPrice = scan.nextDouble();
                scan.nextLine();
                break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Please enter the valid price:(Int-Double)");
            }
        }
        return new AnimalInfo(animalAge, animalWeight, animalHeight, animalPrice);
    }

    private record AnimalInfo(int animalAge, float animalWeight, int animalHeight, double animalPrice) {
    }
}