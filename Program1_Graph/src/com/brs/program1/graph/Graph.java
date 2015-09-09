package com.brs.program1.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author Breno
 *
 */
public class Graph {
	
	private Map<Node<Integer>, List<Node<Integer>>> adjacencyList;
	private Map<Integer, Node<Integer>> nodePool;
	private Set<Node<Integer>> visited = new HashSet<Node<Integer>>();
	
	
	
	public Graph(Map<Integer, List<Integer>> adjacencyList) {
		this.nodePool = new HashMap<Integer, Node<Integer>>();
		this.adjacencyList = processList(adjacencyList);
		
		System.out.println(this.adjacencyList.toString());
	}
	
	
	
	/**
	 * Translate the lists of integers into actual nodes with linked adjacency lists.
	 * @param map
	 * @return the map of adjacency lists.
	 */
	private Map<Node<Integer>, List<Node<Integer>>> processList(Map<Integer, List<Integer>> map){
		
		Map<Node<Integer>, List<Node<Integer>>> result = new HashMap<Node<Integer>, List<Node<Integer>>>();
		Iterator<Map.Entry<Integer, List<Integer>>> parentIt = map.entrySet().iterator();
			

		// create a node for each adjacency root and put into the map as a key.
		while(parentIt.hasNext()){
			
			Entry<Integer, List<Integer>> entry = parentIt.next();
			
			Node<Integer> parent = new Node<Integer>(entry.getKey());
			nodePool.put(entry.getKey(), parent);
			
			result.put(nodePool.get(entry.getKey()), new ArrayList<Node<Integer>>());
		}
		

		// create a node for each neighbor and put it into the the result map.
		Iterator<Map.Entry<Integer, List<Integer>>> parentIt2 = map.entrySet().iterator();
		while(parentIt2.hasNext()){
			Entry<Integer, List<Integer>> entry = parentIt2.next();
			
			
			List<Node<Integer>> rootNeighbors = new ArrayList<Node<Integer>>();
			
			// set the adjacency links of all the neighbor nodes
			for(Integer val : entry.getValue()){
				Node<Integer> currentNeighbor = new Node<Integer>(val);
				rootNeighbors.add(nodePool.get(val));
				
				if(!nodePool.containsKey(val)){
					nodePool.put(val, currentNeighbor);
				}
				
				Node<Integer> poolNode = nodePool.get(val);
				List<Node<Integer>> resultNeighbors = result.get(currentNeighbor);
				
				poolNode.setAdjacencyList(resultNeighbors);
				result.get(new Node<Integer>(entry.getKey())).add(poolNode);			

			}
			
			// set the adjacency list of all the root nodes in case it's a disconnected graph 
			// and they were not set above.
			getNodeFromList(entry.getKey(), result).setAdjacencyList(rootNeighbors);
		}
		
				
		return result;
				
	}
	
	
	/**
	 * DFS
	 * @param root
	 * @return
	 */
	private String dfs(Node<Integer> root){
		StringBuilder sb = new StringBuilder();
		this.visited.add(root);
		
		sb.append(root);
		for( Node<Integer> adj :  root.getAdjacencyList()){
			sb.append(" -> ");
			sb.append(adj);
			
			if(!(this.visited.contains(adj))){
				dfs(adj);
			}
		}

		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * Initializes the depth first search and clear the visited nodes after.
	 * @param root
	 * @return String representation of the search.
	 */
	public String depthFirstSearch(Node<Integer> root){
		String result = dfs(root);
		this.visited.clear();
		
		return result;
	}
	
	
	
	/**
	 * BFS
	 * @param root
	 * @return
	 */
	private String bfs(Node<Integer> root){
		Queue<Node<Integer>> queue = new ArrayDeque<Node<Integer>>();
		StringBuilder sb = new StringBuilder();
		
		this.visited.add(root);
		queue.add(root);
		
		while( !(queue.isEmpty()) ){
			
			root = queue.poll();
			sb.append(root);
			
			for( Node<Integer> adj :  root.getAdjacencyList()){
				sb.append(" -> ");
				sb.append(adj);
				
				if(!(this.visited.contains(adj))){
					this.visited.add(adj);
					queue.add(adj);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Initializes the breath first search and clear the visited nodes after.
	 * @param root
	 * @return String representation of the search.
	 */
	public String breathFirstSearch(Node<Integer> root){
		String result = bfs(root);
		this.visited.clear();
		
		return result;
	}
	
	
	public String DepthFirstSearch(Integer root){
		return depthFirstSearch(new Node<Integer>(root));
	}
	
	
	/**
	 * get the adjacency list for the passed key
	 * @param key
	 * @return
	 */
	public List<Node<Integer>> getAdjacents(int key){
		return this.adjacencyList.get(new Node<Integer>(key));
	}
	
	/**
	 * get the key node for the passed value
	 * @param val
	 * @return
	 */
	public Node<Integer> getNode(int val){
		for ( Node<Integer> key : adjacencyList.keySet() ) {
		    if(key.getVertex() == val){
		    	return key;		    	
		    }
		}
		return null;
	}
	
	private Node<Integer> getNodeFromList(int val,  Map<Node<Integer>, List<Node<Integer>>> list){
		for ( Node<Integer> key : list.keySet() ) {
		    if(key.getVertex().equals(val)){
		    	return key;		    	
		    }
		}
		return null;
	}
	
	public boolean contains(Node<Integer> node){
		return this.adjacencyList.containsKey(node);
	}
	
	public boolean contains(Integer key){
		return this.adjacencyList.containsKey(new Node<Integer>(key));
	}
	
	
	@Override
	public String toString() {
		String separator = " -> ";
		
		StringBuilder sb = new StringBuilder();
		for ( Node<Integer> key : adjacencyList.keySet() ) {
		   sb.append(key);
		   
		   if(key.getAdjacencyList() != null){
			   for(Node<Integer> adjs : key.getAdjacencyList()){
				   sb.append(separator);
				   sb.append(adjs);
			   }
		   }
		   sb.append("\n");
		}
		
		return sb.toString();
	}

}
