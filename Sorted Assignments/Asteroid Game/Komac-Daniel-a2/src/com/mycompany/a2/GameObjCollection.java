package com.mycompany.a2;

import java.util.LinkedList;

public class GameObjCollection implements ICollection{

	private LinkedList<Object> gameObjects;
	
	GameObjCollection(){
		gameObjects = new LinkedList<Object>();
	}
	
	public void add(Object newObj) {
		gameObjects.add(newObj);
		
	}

	public IIterator getIterator() {
		return new GameObjIterator();
	}

	
	private class GameObjIterator implements IIterator{
		
		/*private class Node{
			private Object data;
			private Node next;
			
			Node(Object data, Node next){
				this.data = data;
				this.next = next;
			}
			
		}*/
		
		private int pos;
		
		GameObjIterator(){
			pos = -1;
		}
		
		public boolean hasNext(){
			if(gameObjects.size() < 1)
				return false;
			if(gameObjects.size()-1 == pos)
			return false;
			
			return true;
		}
		
		
		public Object getNext(){
			pos++;
			return (gameObjects.get(pos)); 
		}
		
		public Object previous(){
			
			pos--;
			return gameObjects.get(pos);
		}

		public void remove() {
			
			gameObjects.remove(pos);
			pos--;
			
		}
		
	}
	
}
