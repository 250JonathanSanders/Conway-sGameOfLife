//imports//
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	//Variables
	public static ArrayList<ArrayList<ArrayList<Boolean>>> arrGrid = new ArrayList<>(); //3D grid, x and y are dimensions, z0 is current status, z1 is next status, z2 is # of neighbors
	public static Scanner scrInput = new Scanner(System.in); //Scanner object
	
	public static int intWidth = 0, intHeight = 0; //Width and height
	public static String strFlush = "", strInput = "";
	
	public static void main(String[] args){
		//Define width and height
		while (intHeight <= 0) { //Define width
			System.out.print("Enter grid width: ");
			try{
				intHeight = scrInput.nextInt();
			}catch(Exception e){
				System.out.println("Invalid width.");
			}
			strFlush = scrInput.nextLine(); //Flush input stream
		}
		
		while (intWidth <= 0) { //Define height
			System.out.print("Enter grid height: ");
			try{
				intWidth = scrInput.nextInt();
			}catch(Exception e){
				System.out.println("Invalid height.");
			}
			strFlush = scrInput.nextLine(); //Flush input stream
		}
		
		//Set width, height, and status of arraylist
		for (int i = 0; i < intWidth; i++){//set Width
			arrGrid.add(new ArrayList<ArrayList<Boolean>>(intHeight));
			for (int j = 0; j < intHeight; j++){//set Height
				arrGrid.get(i).add(new ArrayList<Boolean>());
				//set status of each cell
				arrGrid.get(i).get(j).add(false);
				arrGrid.get(i).get(j).add(false);
			}
		}
		
		System.out.print("(R)andomize, or (P)lot  points? ");
		strInput = scrInput.nextLine();
		while (!strInput.equalsIgnoreCase("r") && !strInput.toLowerCase().contains("random") && !strInput.equalsIgnoreCase("p") && !strInput.toLowerCase().contains("plot")){
			System.out.print("Invalid input.\n(R)andomize, or (P)lot custom points? ");
			strInput = scrInput.nextLine();
		}
		
		if (strInput.toLowerCase().contains("random") || strInput.equalsIgnoreCase("r")){
			Randomize();
		} else if (strInput.toLowerCase().contains("plot") || strInput.equalsIgnoreCase("p")){
			Plot();
		}
		
		Next();
		do{
			System.out.println("Press enter for the next iteration, or type \"exit\" to stop the program");
			strInput = scrInput.nextLine();
			if (!strInput.equalsIgnoreCase("exit")){
				Next();
			}
		}while (!strInput.equalsIgnoreCase("exit"));
	}
	
	public static void Randomize(){
		int intRand, intInput = -1;
		do {
			System.out.print("Enter chance of cell to be alive, as a numerical value between 0 and 100, such as \"5\": ");
			try{
				intInput = scrInput.nextInt();
				
			}catch(Exception e){
				System.out.println("Invalid input.");
			}
			strFlush = scrInput.nextLine();
		}while (intInput > 100 || intInput < 0);
		
		//Process through every cell
		for (int i = 0; i < intWidth; i++){
			for (int j = 0; j < intHeight; j++){
				intRand = (int) (100 * Math.random() + 1); //generates number between 1 and 100
				if (intRand <= intInput){
					arrGrid.get(i).get(j).set(0, true);
					arrGrid.get(i).get(j).set(1, true);
				}
			}
		}
	}
	
	public static void Plot(){
		int intPointX, intPointY;
		System.out.println("To plot a point on the grid, enter its coordinates as such: \"x,y\".\nTo escape the plotter, enter \"exit\"");
		
		do{
			System.out.print("Enter your next point: ");
			strInput = scrInput.nextLine();
			
			if (!strInput.equalsIgnoreCase("exit")) {
				try {
					intPointX = Integer.parseInt(strInput.split(",")[0]) - 1;
					intPointY = Integer.parseInt(strInput.split(",")[1]) - 1;
					
					arrGrid.get(intPointY).get(intPointX).set(0, true); //Have to switch X and Y, no clue why
					arrGrid.get(intPointY).get(intPointX).set(1, true);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Out of bounds.");
				} catch (Exception e) {
					System.out.println("Invalid format.");
				}
			}
		} while (!strInput.equalsIgnoreCase("exit"));
	}
	public static void Next(){
		int intCounter;
		//Print current iteration
		for (int i = 0; i < intWidth; i++) {
			for (int j = 0; j < intHeight; j++) {
				arrGrid.get(i).get(j).set(0, (arrGrid.get(i).get(j).get(1)));
				if (arrGrid.get(i).get(j).get(0)) {
					System.out.print("o ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.print("\n");
		}
		
		//Set up next iteration
		for (int i = 0; i < intWidth; i++){
			for (int j = 0; j < intHeight; j++){
				//Check neighbors
				intCounter = 0; //reset neighbor counter
				for (int x = i-1; x <= i+1; x++){
					for (int y = j-1; y <= j+1; y++){
						try {
							if (arrGrid.get(x).get(y).get(0) && (x != i || y != j)) {
								intCounter++;
							}
						} catch (IndexOutOfBoundsException e){
								System.out.print("");
						}
					}
				}

				//Set status for next iteration
				if ((intCounter == 2 && arrGrid.get(i).get(j).get(0))|| intCounter == 3){
					arrGrid.get(i).get(j).set(1, true);
				} else{
					arrGrid.get(i).get(j).set(1, false);
				}
			}
		}
	}
}
