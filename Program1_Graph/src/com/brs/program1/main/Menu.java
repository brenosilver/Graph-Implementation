package com.brs.program1.main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Breno
 *
 */
public class Menu {
	
	private int selected;
		
	public enum State{
		
		READ(1),
		DFS(2),
		BFS(3),
		DISPLAY(4),
		EXIT(5);
		
		private final String READ_FROM_FILE_VAL = "READ FROM FILE";
		private final String DFS_VAL = "DFS";
		private final String BFS_VAL = "BFS";
		private final String DISPLAY_VAL = "DISPLAY";
		private final String EXIT_VAL = "EXIT";
		
		private int index;

		
		private State(int index) {
			this.index = index;
		}
		
		public String getValue(){
			
			switch(this){
				case READ: return READ_FROM_FILE_VAL; 
				case DFS: return DFS_VAL; 
				case BFS: return BFS_VAL; 
				case DISPLAY: return DISPLAY_VAL;
				case EXIT: return EXIT_VAL;
				default : return "";
				
			}
			
		}
		
		public int getIndex(){
			return index;
		}
		
	}



	/**
	 * display the menu while the option is not selected
	 * @param scan
	 * @return
	 */
	public State displayMenu(Scanner scan){
		

		// while the option selected is not in the state, prompt again.
		do{
			try{
				System.out.println("Please choose an option...");	
				displayOptions();
				selected = scan.nextInt()-1;
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input. Please enter a number between 1 and " + State.values().length + ".");
				selected = -1;
				scan.nextLine(); // clear the buffer
				continue;
			}
		}
		while(selected < 0 || selected > State.values().length-1);
						
		return State.values()[selected];
	}
	
	
	
	private void displayOptions(){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(State st : State.values()){
			i++;
			sb.append(i);
			sb.append("- ");
			sb.append(st.getValue());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	

	
	public int getSelected() {
		return selected;
	}
	public void setSelected(int option) {
		this.selected = option;
	}
	
}
