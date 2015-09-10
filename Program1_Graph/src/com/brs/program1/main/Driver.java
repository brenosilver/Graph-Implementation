package com.brs.program1.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.brs.program1.graph.Graph;
import com.brs.program1.main.Menu.State;

/**
 * 
 * @author Breno
 *
 */
public class Driver {
	private static final String ADJ_LIST_PATH = "src/adjacencyList";
	
	private static Map< Integer, List<Integer>> adjacencyList;
	private static Graph graph = null;

	public static void main(String[] args) {
		adjacencyList = new LinkedHashMap<Integer, List<Integer>>();
		Scanner scan = new Scanner(System.in);
		Menu menu = new Menu();
		init(menu, scan);
		scan.close();
	}
	
	
	
	/**
	 * Initializes the program displaying the menu.
	 * @param menu
	 */
	public static void init(Menu menu, Scanner scan){
	
		State state = menu.displayMenu(scan);
		System.out.println(state.getValue());
		
		switch(state){
			case READ:		adjacencyList = readFile();
							graph = new Graph(adjacencyList);
							init(menu, scan);
							break;
						
			case DISPLAY:	if(adjacencyList.isEmpty()) init(menu, scan);
							System.out.println(graph.toString());
							init(menu, scan);
							break;
							
			case EXIT:		return;
				
			case DFS:		if(adjacencyList.isEmpty()) init(menu, scan);
							System.out.println("Please enter root node...");
							graph.depthFirstSearch(graph.getNode(scan.nextInt()));
							init(menu, scan);
							break;
			
			case BFS:		if(adjacencyList.isEmpty()) init(menu, scan);
							System.out.println("Please enter root node...");
							graph.breathFirstSearch(graph.getNode(scan.nextInt()));
							init(menu, scan);
							break;
			
			default : 		break;

		}
		
	}
	
	
	/**
	 * Read adjacency list from file.
	 * @return The map of adjacency values.
	 */
	private static Map<Integer, List<Integer>> readFile(){
		
		Scanner scan = null;
		try {	
			scan = new Scanner(new File(ADJ_LIST_PATH));
		} catch (FileNotFoundException e) {
			System.err.println("File not found! " + e.getMessage());
		}
		Map< Integer, List<Integer>> result = new LinkedHashMap<Integer, List<Integer>>();
		
		
		int key = 0;
		while(scan.hasNextLine()){
			
			String line = scan.nextLine().trim();
			
			// jump empty lines
			if(line.length() == 0){
				continue;
			}
			
			//create StringTokenizer, parsing with space
			StringTokenizer st1 = new StringTokenizer(line, " ");
			
			List<Integer> list = new ArrayList<Integer>();
			
			if(st1.hasMoreTokens()){
				key++;
				
				while(st1.hasMoreTokens()){
					
					int vertexVal = Integer.valueOf(st1.nextToken());
					
					// Vertex cannot be less or equal to 0.
					// In this case we don't add it to the adjacency list.
					if(vertexVal <= 0){
						continue;
					}
					
					list.add(vertexVal);
				}
			}
			result.put(key, list);
			
		}

		scan.close();
		return result;
	}

	

}
