package store;

import org.apache.commons.validator.EmailValidator;
import store.client.User;
import store.client.UserData;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("       Welcome to Pezoo            ");
        ArrayList<Pet> pets = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Pet pet;
        UserData data;
        User user;
        HashMap<User, ArrayList<Pet>> user_pet = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int c;
        do {
            while (true) {
                try {
                    System.out.println("0) to terminate the program:\n1) to add animals:\n2) to buy animals:\n3) to buy some food for your pet:");
                    c = scan.nextInt();
                    scan.nextLine();
                    if (c >= 0 && c <= 3) {
                        break;
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
                    if (pets.isEmpty()) {
                        System.out.println("You must add some animals first.");
                        break;
                    }
                    System.out.println("--------------------------------");
                    System.out.println("Animals:");
                    pets.forEach(p -> System.out.println(p.getType()));
                    System.out.println("Select an animal you're interested in:");
                    String selectedAnimal;
                    boolean found = false;
                    while (true) {
                        selectedAnimal = scan.nextLine();
                        if (selectedAnimal.matches(".*\\d.*")) {
                            System.out.println("Please select an animal from the list given up there:");
                            continue;
                        }
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
                    Pet tempPet = null;
                    ArrayList<Pet> tempList = new ArrayList<>();
                    int id;
                    do {
                        System.out.println("Enter the ID of the animal you want you buy or enter 0 to exit:");
                        while (true) {
                            try {
                                id = scan.nextInt();
                                scan.nextLine();
                                break;
                            } catch (Exception e) {
                                scan.nextLine();
                                System.out.println("Enter a valid number: ");
                            }
                        }
                        found = false;
                        if (id == 0) break;
                        for (Pet animal : pets) {
                            if (id == animal.getPetID()) {
                                System.out.println(animal);
                                try {
                                    tempPet = (Pet) animal.clone();
                                    found = true;
                                    break;
                                } catch (CloneNotSupportedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        if (!found) System.out.println("Please enter the valid ID:");
                    } while (!found);

                    int r;
                    while (id != 0) {
                        System.out.println("You must login to go further\n1)Login\n2)Register\n3)Exit");
                        while (true) {
                            try {
                                r = scan.nextInt();
                                scan.nextLine();
                                break;
                            } catch (Exception e) {
                                scan.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                        if (r == 3) {
                            System.out.println("Goodbye!");
                            break;
                        }
                        if (r == 1) {
                            if (users.isEmpty()) {
                                System.out.println("No account has been registered yet!");
                            } else {
                                System.out.println("Enter your email:");
                                String email = scan.nextLine();
                                System.out.println("Enter your account's unique code:");
                                String password = scan.nextLine();
                                found = false;
                                for (User u : users) {
                                    if (u.getUserData().getEmail().equals(email) && u.getUniqueUserCode().equals(password)) {
                                        System.out.println("Welcome " + u.getName() + "!");
                                        found = true;
                                        tempList.add(tempPet);
                                        user_pet.put(u, tempList);
                                        for (Map.Entry<User, ArrayList<Pet>> entry : user_pet.entrySet()) {
                                            System.out.println("User: " + entry.getKey().getName() + " --> Pet: ");
                                            entry.getValue().forEach(o -> {
                                                System.out.println(o.getPetID() + " - " + o.getType() + " - " + o.getBreed());
                                            });
                                            if (tempPet != null) {
                                                tempPet.pet_num_manager(pets, tempPet.getPetID());
                                            }
                                        }
                                        break;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Invalid email or code!!!");
                                } else break;
                            }
                        } else if (r == 2) {
                            System.out.println("Enter your name:");
                            String name;
                            while (true) {
                                name = scan.nextLine();
                                if (name.matches(".*\\d.*")) {
                                    System.out.println("Invalid input!\nA name must contain only alphabets");
                                } else break;
                            }
                            System.out.println("Enter your phone number:");
                            int num;
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
                            System.out.println("Enter your email address:");
                            String email;
                            while (true) {
                                try {
                                    email = scan.nextLine();
                                    EmailValidator validator = EmailValidator.getInstance();
                                    if (validator.isValid(email)) {
                                        System.out.println(email);
                                        break;
                                    } else {
                                        throw new InputMismatchException();
                                    }
                                } catch (Exception e) {
                                    scan.nextLine();
                                    System.out.println("Invalid email!\nPlease insert a valid email;");
                                }
                            }
                            try {
                                data = new UserData(num, email);
                                user = new User(name, data);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("\nEnter Y if you would like to add a description about yourself\nPress any other key/keys to continue");
                            if (scan.nextLine().equalsIgnoreCase("y")) {
                                System.out.println("Enter a description:");
                                user.setDescription(scan.nextLine());
                            }
                            System.out.println("Your account has been registered successfully:\n" + user + "\nN.B. Use the unique code as your password to login :)");

                            users.add(user);
                        } else System.out.println("Invalid input!");
                    }
                    break;
                case 3:
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