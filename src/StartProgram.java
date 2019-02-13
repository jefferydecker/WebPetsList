

import java.util.List;
import java.util.Scanner;

import controller.PetsListHelper;
import model.Pet;

/**
 * @author Kelly Kleindorfer and Jeffery Decker
 *  - based on Kelly Kleindorfer's ConsoleShoppingList project:
 *  - 						https://github.com/kjkleindorfer/ConsoleShoppingList
 *  
 * Demo Video: https://screencast.com/t/NOAEo8oGib
 */
public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static PetsListHelper plh = new PetsListHelper();

		private static void addAPet() {
			System.out.print("Enter type of pet: ");
			String type = in.nextLine();
			System.out.print("Enter pet's name: ");
			String name = in.nextLine();
			System.out.print("Enter pet's owner: ");
			String owner = in.nextLine();
			
			Pet toAdd = new Pet(type, name, owner);
			plh.insertItem(toAdd);
		}

		private static void deleteAPet() {
			System.out.print("Enter the pet type to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the pet name to delete: ");
			String name = in.nextLine();
			System.out.print("Enter the pet owner to delete: ");
			String owner = in.nextLine();
			Pet	toDelete = new Pet(type, name, owner);
			plh.deleteItem(toDelete);
		}

		private static void editAPet() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Type");
			System.out.println("2 : Search by Name");
			System.out.println("3 : Search by Owner");
			int searchBy = in.nextInt();
			in.nextLine();
///*			
			List<Pet> foundPets;
			if (searchBy == 1) {
				System.out.print("Enter the pet type: ");
				String type = in.nextLine();
				foundPets = plh.searchForPetByType(type);
				
			} else if (searchBy == 2) {
				System.out.print("Enter the pet name: ");
				String name = in.nextLine();
				foundPets = plh.searchForPetByName(name);

			} else {
				System.out.print("Enter pet owner: ");
				String owner = in.nextLine();
				foundPets = plh.searchForPetByOwner(owner);
			}

			if (!foundPets.isEmpty()) {
				System.out.println("Found Results.");
				for (Pet l : foundPets) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Pet toEdit = plh.searchForPetById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName() + " a " 
					+ toEdit.getType() + " friend of " + toEdit.getOwner());
				System.out.println("1 : Update pet type");
				System.out.println("2 : Update pet name");
				System.out.println("3 : Update pet owner");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New pet type: ");
					String newType = in.nextLine();
					toEdit.setType(newType);
				} else if (update == 2) {
					System.out.print("New pet name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else {
					System.out.print("New pet owner: ");
					String newOwner = in.nextLine();
					toEdit.setOwner(newOwner);
				}

				plh.updatePet(toEdit);

			} else {
				System.out.println("---- No results found");
			}
//*/
		}

		public static void main(String[] args) {
			runMenu();
		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome Pets list! ---");
			while (goAgain) {
				System.out.println("\n*  Select from the following:");
				System.out.println("*  1 -- Add an pet");
				System.out.println("*  2 -- Edit an pet");
				System.out.println("*  3 -- Delete an pet");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();
				System.out.println();

				if (selection == 1) {
					addAPet();
				} else if (selection == 2) {
					editAPet();
				} else if (selection == 3) {
					deleteAPet();
				} else if (selection == 4) {
					viewTheList();
				} else {
					plh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<Pet> allPets = plh.showAllPets();
			for(Pet singlePet : allPets) {
				System.out.println(singlePet.returnPetDetails());
			}

		}

	}
