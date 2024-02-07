package store;

import org.apache.commons.validator.EmailValidator;
import store.client.User;
import store.client.UserData;
import store.server.FoodAndAccessories;
import store.server.Pet;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("       Welcome to Pezoo            ");
        ArrayList<Pet> pets = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, Integer> productBought = new HashMap<>();
        Pet pet;
        UserData data;
        User user;
        HashMap<User, ArrayList<Pet>> user_pet = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int c;
        do {
            while (true) {
                try {
                    System.out.println("0) to terminate the program:\n1) to add animals:\n2) to buy animals:\n3) to buy accessories for you animal: \n4) to have a look on the data:");
                    c = scan.nextInt();
                    scan.nextLine();
                    if (c >= 0 && c <= 4) {
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
                    while (true) {
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
                                        user_pet.computeIfAbsent(u, k -> new ArrayList<>());
                                        user_pet.get(u).add(tempPet);
                                        for (Map.Entry<User, ArrayList<Pet>> entry : user_pet.entrySet()) {
                                            System.out.print("User: " + entry.getKey().getName() + " owns: ");
                                            entry.getValue().forEach(o -> System.out.println(o.getPetID() + ") " + o.getType() + ", breed " + o.getBreed()));
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
                    System.out.println("Here you can buy accessories and food for your pet :)");
                    int food_acc = 0;
                    do {
                        System.out.println("1) to check for the food in the catalog.\n2) to check for the accessory in the catalog.\n3) to enter the name of the product you want to buy\n4) to go back on the previous menu:");
                        System.out.println("--------------------------------------------------------");
                        try {
                            food_acc = scan.nextInt();
                            scan.nextLine();
                            if (food_acc == 1) {
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    if (item.getStuff().equals("food")) System.out.println(item.name());
                                }
                                System.out.println("--------------------------------------------------------");
                            }
                            if (food_acc == 2) {
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    if (item.getStuff().equals("accessory")) System.out.println(item.name());
                                }
                                System.out.println("--------------------------------------------------------");
                            }
                            if (food_acc == 3) {
                                System.out.println("Enter the name of the product you want to buy:");
                                String stuffName = scan.nextLine();
                                boolean stuffFound = false;
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    if (stuffName.equalsIgnoreCase(item.name())) {
                                        stuffFound = true;
                                        break;
                                    }
                                }
                                if (!stuffFound) {
                                    System.out.println("Product not found");
                                }
                                int quantity;
                                while (stuffFound) {
                                    System.out.println("Enter the number of products you want to buy:");
                                    try {
                                        quantity = scan.nextInt();
                                        scan.nextLine();
                                        if (quantity > 0) {
                                            stuffFound = false;
                                            if (productBought.containsKey(stuffName)) {
                                                productBought.merge(stuffName, quantity, Integer::sum);
                                            }
                                            productBought.putIfAbsent(stuffName, quantity);
                                            System.out.println("You've successfully bought " + stuffName);
                                            break;
                                        }
                                    } catch (Exception e) {
                                        scan.nextLine();
                                        System.out.println("Invalid input [MUST PROVIDE AN INT]");
                                    }
                                }
                                System.out.println("--------------------------------------------------------");
                            }
                        } catch (Exception e) {
                            scan.nextLine();
                            System.out.println("Invalid input!");
                        }
                    } while (food_acc != 4);
                    break;
                case 4:
                    int f = 0;
                    do {
                        System.out.println("1) to check the animals:\n2) to check the clients:\n3) to check the sells\n4) to go back to the main menu:");
                        try {
                            f = scan.nextInt();
                            scan.nextLine();
                            if (f == 4) break;
                            if (f == 1) {
                                if (pets.isEmpty()) System.out.println("No animals are available");
                                pets.forEach(System.out::println);
                                System.out.println("-----------------------------");
                            } else if (f == 2) {
                                if (users.isEmpty()) System.out.println("No user has registered yet");
                                users.forEach(System.out::println);
                                System.out.println("-----------------------------");
                            } else if (f == 3) {
                                if (productBought.isEmpty()) System.out.println("No product has been bought yet");
                                for (Map.Entry<String, Integer> s : productBought.entrySet()) {
                                    System.out.println("Product: " + s.getKey() + ", Quantity: " + s.getValue());
                                }
                                System.out.println("-----------------------------");
                            }
                        } catch (Exception e) {
                            scan.nextLine();
                        }
                    } while (f > 0 && f <= 4);
                    break;
            }
        } while (c != 0);
    }

    /**
     * Retrieves information about an animal from user input.
     *
     * @return An AnimalInfo object containing age, weight, height, and price.
     */
    private static AnimalInfo getResult(Scanner scan) {
        System.out.println("Enter the age of the animal:(Int)");
        int animalAge;
        while (true) {
            try {
                animalAge = scan.nextInt();
                scan.nextLine();
                if (animalAge > 0) break;
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
                if (animalWeight > 0) break;
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
                if (animalHeight > 0) break;
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
                if (animalPrice > 0) break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Please enter the valid price:(Int-Double)");
            }
        }
        return new AnimalInfo(animalAge, animalWeight, animalHeight, animalPrice);
    }

    /**
     * Represents information about an animal.
     *
     * @param animalAge
     * @param animalWeight
     * @param animalHeight
     * @param animalPrice
     */
    private record AnimalInfo(int animalAge, float animalWeight, int animalHeight, double animalPrice) {
    }
}