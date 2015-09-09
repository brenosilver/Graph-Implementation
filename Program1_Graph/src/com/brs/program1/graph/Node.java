package com.brs.program1.graph;

import java.util.List;

/**
 * 
 * @author Breno
 *
 * @param <T>
 */
public class Node<T> {

	private T vertex;
	private List<Node<T>> adjacencyList;
	
	
	public Node(T vertex) {
		this.vertex = vertex;
		this.adjacencyList = null;
	}

	public Node(T vertex, List<Node<T>> adjancentList) {
		this.vertex = vertex;
		this.adjacencyList = adjancentList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		return true;
	}





	public T getVertex() {
		return vertex;
	}
	public void setVertex(T vertex) {
		this.vertex = vertex;
	}
	@Override
	public String toString() {
		return this.vertex.toString();
	}
	public List<Node<T>> getAdjacencyList() {
		return adjacencyList;
	}
	public void setAdjacencyList(List<Node<T>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	
}
