package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {

	private char [][] map;
	private String filename;
	private boolean loaded;
	private int startI, startJ, endI,endJ;
	//private ArrayList>Coordinate>path 
	
	public Maze(){
		this.loaded =false;
	}	
public void loadMaze() throws InterruptedException, IOException {
	
	System.out.println();
	File f = new File(Config.MAZES_PATH);
	String[] pathnames = f.list();
	if(pathnames.length==0 | pathnames == null) {
		System.err.println("No hay laberintos para elegir");
	}else {
		this.chooseMaze(pathnames);
	}
}	
private void chooseMaze(String [] pathnames) throws InterruptedException, IOException {
  
    int num=0;
    System.out.println();
    System.out.print("Los laberintos estan ordenados por tama\u00f1o.\n");
    System.out.println();
    System.out.println("  -LABERINTOS-\n----------------\n");
    
         for(int j=0; j<pathnames.length; j++) {
	    	System.out.println((j+1)+" "+ pathnames[j]);
	   
	        	}
        System.out.println("0 Cancelar");
        System.out.println();
        System.out.print("Elige uno: ");
        num = Interface.getInt();
       	
   
     if(num>0 && num<=pathnames.length) {
    	 
    	 this.filename = Config.MAZES_PATH + pathnames[num-1];
    	 	System.out.println("\nCargando el laberinto en el programa...");
    	    Thread.sleep(900);
    	     
    	     System.out.println();
    	     System.out.println("Laberinto cargado\n");
    	     Thread.sleep(900);
    	     
    	     this.saveMaze(this.filename);
     	}else {
     		if(num==0){
     			System.err.println("\nHas decidido salir sin elegir laberinto");
     			return;
     			}
     		else {
     			System.err.println("\nEl numero introducido no es valido");
     			return;
     		}
     	}
	}
private void saveMaze (String route) {
	
	ArrayList<String> lines =this.readAllLines(route);
	this.map=new char[lines.size()][lines.get(0).length()];
	for(int i=0; i<lines.size(); i++) {
		String currentLine= lines.get(i);
		
		for(int j=0; j<currentLine.length(); j++) {
			/*if(this.map[i][j]=='E' || this.map[i][j]== 'S' ) {
				this.map[i][j]=' ';
			}*/
		this.map[i][j]=currentLine.charAt(j);
		}
	}
	this.loaded=true;
}
private ArrayList<String> readAllLines(String route){
	ArrayList<String> allLines = new ArrayList<String>();
	
	 try {
	      File myObj = new File(route);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        allLines.add(data);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("Ha ocurrido un error.");
	      e.printStackTrace();
	    }
	return allLines;
}
public boolean isLoaded() {
	return this.loaded;
}
public void showMap() throws InterruptedException {
	
	if(isLoaded()) {
	System.out.println("\n");
	System.out.println("Leyendo laberinto para mostrarlo\nEspere, por favor.\n");
	Thread.sleep(900);

	upNumbers();
	leftNumbers();
	
	System.out.println("Laberinto cargado\n");
	}else {
		System.err.println("\nPrimero debe elegir un laberinto para mostrarlo");
		return;
	}
}
private void upNumbers() {
	int count=1;
	System.out.println();
	
	for(int i=0; i<2; i++) {
		for(int j=0; j<map[0].length; j++) {
			if(i==0 && j>10) {
				System.out.print(" "+(j-1)/10);
				}
			else if(i==1 && j>1 ) {
			System.out.print( " "+count);
			count++;
				}else {
					System.out.print("  ");
				}
			if(count==10) {
				count=0;	
			}
		}
		System.out.println();
	}
} 
private void leftNumbers() {
	
	String drawing ="";
	for(int i=0; i<this.map.length; i++) {
		if(i<=9 && i>0) {
		drawing+="0"+(i)+ " ";
			}
		else if(i==0 | i== this.map.length-1) {
			drawing+="   ";
		}
		else {
			drawing+=(i)+" ";
		}
		for(int j=0; j<this.map[0].length; j++) {
			drawing+=""+this.map[i][j]+ " ";
		}
		drawing+="\n";
	}
	System.out.println(drawing);
}
public void setEntranceExit() {
	
	if(isLoaded()) {
		System.out.println("\n\n  -INTRODUCCION DE COORDENADAS-\n---------------------------------\n");
		System.out.print("Introduce fila de inicio: ");
		this.startI=Interface.getInt();
		System.out.print("Introduce columna de inicio: ");
		this.startJ=Interface.getInt();
		System.out.print("Introduce fila de salida: ");
		this.endI=Interface.getInt();
		System.out.print("Introduce columna de salida: ");
		this.endJ=Interface.getInt();
		System.out.println();
		
		if(checkData(this.startI, this.startJ, this.endI, this.endJ)) {
			System.out.println("Coordenadas correctas");
		}else {
			System.err.println("Las coordenadas introducidos no son validas");
			}
	}else {
		System.err.println("\nPrimero debe elegir un laberinto para mostrarlo");
		return;
		}
	}
private boolean checkData(int startOne, int startTwo, int endOne, int endTwo) {
	
	if(startOne>0 && startOne <this.map.length && startTwo>0 && startTwo <this.map[0].length && endOne >0 && 
					endOne<this.map.length && endTwo >0 && endTwo< this.map[0].length && (startOne !=endOne || startTwo !=endTwo) 
					&& map[startOne][startTwo]==' ' && map[endOne][endTwo]==' '){

		for(int i=0; i<map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(this.map[i][j]=='E' || this.map[i][j]== 'S' ) {
					this.map[i][j]=' ';
				}
			}
		}
		map[startOne][startTwo]='E';
		map[endOne][endTwo]='S';
		return true;
	}else return false;
	}
}
