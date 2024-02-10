package main;

import org.apache.commons.validator.EmailValidator;
import store.client.User;
import store.client.UserData;
import store.server.FoodAndAccessories;
import store.server.Pet;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Welcome message
        System.out.println("       Welcome to Pezoos            ");

        // Initialize lists and maps
        ArrayList<Pet> pets = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, Integer> productBought = new HashMap<>();
        Pet pet;
        UserData data;
        User user;
        HashMap<User, ArrayList<Pet>> user_pet = new HashMap<>();

        // Create a scanner for user input
        Scanner scan = new Scanner(System.in);

        // Regex pattern that to check whether a string contains characters such as / * - etc.
        String regex = "^[a-zA-Z0-9 ]*$";
        int c;

        // Main menu loop
        do {
            while (true) {
                try {
                    // Display menu options
                    System.out.println("0) to terminate the program:\n1) to add animals:\n2) to buy animals:\n3) to buy accessories for you animal: \n4) to have a look on the data:");
                    // Read user input
                    c = scan.nextInt();
                    scan.nextLine();
                    // Validate input
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
                                // Prompt the user for the type of animal
                                System.out.println("Enter what type of animal you want to add(Example: cat, dog, etc):");
                                String animalType;
                                animalType = scan.nextLine();

                                // Validate that the input doesn't contain digits and characters such as # / * etc...
                                if (animalType.matches(".*\\d.*") || !animalType.matches(regex)) {
                                    System.out.println("Enter a valid animal.");
                                    continue;
                                }

                                // Prompt the user for the animal's breed
                                System.out.println("Enter the animal's breed(Example: cat: ragdoll):");
                                String animalBreed;

                                // Validate that the input doesn't contain digits and characters such as # / * etc...
                                while (true) {
                                    animalBreed = scan.nextLine();
                                    if (animalBreed.matches(".*\\d.*")  || !animalBreed.matches(regex)) {
                                        System.out.println("Please enter a valid breed:");
                                    } else break;
                                }
                                AnimalInfo animalInfo = getResult(scan);
                                pet = new Pet(animalType, animalBreed, animalInfo.animalAge(), animalInfo.animalWeight(), animalInfo.animalHeight(), animalInfo.animalPrice());

                                // Ask the user if they want to add extra information about the animal
                                System.out.println("\nEnter Y if you would like to add extra info about the animal such as it's favorite food, description, etc.\nPress any other key/keys to continue");
                                if (scan.nextLine().equalsIgnoreCase("Y")) {

                                    // Prompt for the animal's favorite food
                                    System.out.println("Enter the animal's favorite food:");
                                    pet.setFavoriteFood(scan.nextLine());

                                    // Prompt for the animal's description
                                    System.out.println("Enter the animal's description:");
                                    pet.setDescription(scan.nextLine());

                                    // Prompt for whether the animal can swim
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

                                    // Add the pet to the list
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

                    // Display the types of animals in the list
                    pets.forEach(p -> System.out.println(p.getType()));
                    System.out.println("Select an animal you're interested in:");
                    String selectedAnimal;
                    boolean found = false;

                    // Loops until a valid animal is selected
                    while (true) {
                        selectedAnimal = scan.nextLine();

                        // Validate that the input doesn't contain digits
                        if (selectedAnimal.matches(".*\\d.*")) {
                            System.out.println("Please select an animal from the list given up there:");
                            continue;
                        }

                        // Check if the selected animal exists in the list
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

                    // Initialize a Pet object to null
                    Pet tempPet = null;
                    int id;

                    // Start a loop
                    do {
                        System.out.println("Enter the ID of the animal you want you buy or enter 0 to exit:");

                        // Start a loop to validate the input
                        while (true) {
                            try {
                                id = scan.nextInt();
                                scan.nextLine();
                                break;
                            } catch (Exception e) {

                                // If the input is not a valid integer, prompt the user to enter a valid number
                                scan.nextLine();
                                System.out.println("Enter a valid number: ");
                            }
                        }
                        found = false;
                        if (id == 0) break;

                        // Iterate over the list of pets
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

                        // If no pet was found with the entered ID, prompt the user to enter a valid ID
                        if (!found) System.out.println("Please enter the valid ID:");
                    } while (!found);

                    int r;
                    while (true) {
                        System.out.println("You must login to go further\n1)Login\n2)Register\n3)Exit");

                        // Start a loop to validate the input
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

                        // If the user entered 3 (to exit), print a goodbye message and break the loop
                        if (r == 3) {
                            System.out.println("Goodbye!");
                            break;
                        }
                        if (r == 1) {

                            // If there are no registered users
                            if (users.isEmpty()) {
                                System.out.println("No account has been registered yet!");
                            } else {
                                System.out.println("Enter your email:");
                                String email = scan.nextLine();
                                System.out.println("Enter your account's unique code:");
                                String password = scan.nextLine();
                                found = false;

                                // Iterate over the list of users
                                for (User u : users) {

                                    // If the entered email and unique code match a user's email and unique code
                                    if (u.getUserData().getEmail().equals(email) && u.getUniqueUserCode().equals(password)) {
                                        System.out.println("Welcome " + u.getName() + "!");

                                        // Set 'found' to true
                                        found = true;
                                        user_pet.computeIfAbsent(u, k -> new ArrayList<>());
                                        user_pet.get(u).add(tempPet);

                                        // Print all the pets owned by each user
                                        for (Map.Entry<User, ArrayList<Pet>> entry : user_pet.entrySet()) {
                                            System.out.println("User: " + entry.getKey().getName() + " owns: ");
                                            entry.getValue().forEach(o -> System.out.println("Pet) " + o.getType() + ", Breed: " + o.getBreed()));
                                            if (tempPet != null) {
                                                tempPet.pet_num_manager(pets, tempPet.getPetID());
                                            }
                                        }
                                        break;
                                    }
                                }

                                // If no user was found with the entered email and unique code, print an error message
                                if (!found) {
                                    System.out.println("Invalid email or code!!!");
                                } else break;
                            }
                        } else if (r == 2) {
                            System.out.println("Enter your name:");
                            String name;

                            // Start a loop to validate the input
                            while (true) {
                                name = scan.nextLine();

                                // If the name contains any digits, print an error message
                                if (name.matches(".*\\d.*") || !name.matches(regex)) {
                                    System.out.println("Invalid input!\nA name must contain only alphabets");
                                } else break;
                            }
                            System.out.println("Enter your phone number:");
                            int num;

                            // Start a loop to validate the input
                            while (true) {
                                try {
                                    num = scan.nextInt();
                                    scan.nextLine();

                                    // If the number is not exactly 10 digits long, print an error message
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

                            // Start a loop to validate the input
                            while (true) {
                                try {
                                    email = scan.nextLine();

                                    // Create an instance of EmailValidator
                                    // The EmailValidator is a class in the Apache Commons Validator library used to perform email validations
                                    EmailValidator validator = EmailValidator.getInstance();
                                    // The isValid(String email) method checks if a given string is a valid email address
                                    if (validator.isValid(email)) {
                                        System.out.println(email);
                                        break;
                                    } else {
                                        // If the email is valid, print the email and break the loop
                                        throw new InputMismatchException();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid email!\nPlease insert a valid email;");
                                }
                            }
                            try {
                                // Create a new UserData object with the entered phone number and email, to create a new User object with the entered name and the created UserData object
                                data = new UserData(num, email);
                                user = new User(name, data);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            // Ask the user if they would like to add a description about themselves
                            System.out.println("\nEnter Y if you would like to add a description about yourself\nPress any other key/keys to continue");
                            if (scan.nextLine().equalsIgnoreCase("y")) {
                                System.out.println("Enter a description:");
                                user.setDescription(scan.nextLine());
                            }

                            // Print a success message with the user's details and add the new user to the list of users
                            System.out.println("Your account has been registered successfully:\n" + user + "\nN.B. Use the unique code as your password to login :)");

                            users.add(user);
                        } else System.out.println("Invalid input!");
                    }
                    break;
                case 3:
                    System.out.println("Here you can buy accessories and food for your pet :)");
                    int food_acc = 0;

                    // Start a loop
                    do {
                        System.out.println("1) to check for the food in the catalog.\n2) to check for the accessory in the catalog.\n3) to enter the name of the product you want to buy\n4) to go back on the previous menu:");
                        System.out.println("--------------------------------------------------------");
                        try {
                            food_acc = scan.nextInt();
                            scan.nextLine();

                            // If the user chose to check the food in the catalog (food_acc == 1)
                            if (food_acc == 1) {
                                // Iterate over the values of the FoodAndAccessories enum
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    if (item.getStuff().equals("food")) System.out.println(item.name());
                                }
                                System.out.println("--------------------------------------------------------");
                            }

                            // If the user chose to check the accessories in the catalog (food_acc == 2)
                            if (food_acc == 2) {
                                // Iterate over the values of the FoodAndAccessories enum
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    if (item.getStuff().equals("accessory")) System.out.println(item.name());
                                }
                                System.out.println("--------------------------------------------------------");
                            }

                            // If the user chose to enter the name of the product they want to buy (food_acc == 3)
                            if (food_acc == 3) {
                                System.out.println("Enter the name of the product you want to buy:");
                                String stuffName = scan.nextLine();
                                boolean stuffFound = false;
                                // Iterate over the values of the FoodAndAccessories enum
                                for (FoodAndAccessories item : FoodAndAccessories.values()) {
                                    // If the entered name matches the name of an item (case-insensitive), set 'stuffFound' to true and break the loop
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
                                            // If the product is already in the 'productBought' map, add the entered quantity to the existing quantity
                                            if (productBought.containsKey(stuffName)) {
                                                productBought.merge(stuffName, quantity, Integer::sum);
                                            }
                                            // If the product is not already in the 'productBought' map, add it with the entered quantity
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

                    // Start a loop
                    do {
                        // Print the options for the user
                        System.out.println("1) to check the animals:\n2) to check the clients:\n3) to check the sells\n4) to go back to the main menu:");
                        try {
                            f = scan.nextInt();
                            scan.nextLine();

                            // If the user chose to go back to the main menu (f == 4), break the loop
                            if (f == 4) break;

                            // If the user chose to check the animals (f == 1)
                            if (f == 1) {
                                // Print each animal in the 'pets' list if there are any
                                if (pets.isEmpty()) System.out.println("No animals are available");
                                pets.forEach(System.out::println);
                                System.out.println("-----------------------------");
                            }

                            // If the user chose to check the clients (f == 2)
                            else if (f == 2) {
                                // Print each user in the 'users' list if there are any
                                if (users.isEmpty()) System.out.println("No user has registered yet");
                                users.forEach(System.out::println);
                                System.out.println("-----------------------------");
                            }

                            // If the user chose to check the sells (f == 3)
                            else if (f == 3) {
                                // Print each product and its quantity in the 'productBought' HashMap if there are any
                                if (productBought.isEmpty()) System.out.println("No product has been bought yet");
                                for (Map.Entry<String, Integer> s : productBought.entrySet()) {
                                    System.out.println("Product: " + s.getKey() + ", Quantity: " + s.getValue());
                                }
                                System.out.println("-----------------------------");
                            }
                        } catch (Exception e) {
                            // If the user's input was not a valid integer, clear the input
                            scan.nextLine();
                        }
                    } while (f > 0 && f <= 4); // Continue the loop until the user chooses to go back to the main menu
                    break;
            }
        } while (c != 0); // Terminate when user selects option 0
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