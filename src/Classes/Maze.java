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
	private ArrayList<Coordinate>path = new ArrayList <Coordinate>();
	private boolean cheekingES;
	
	public Maze(){
		this.loaded =false;
		this.cheekingES=false;
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
	this.cheekingES=false;
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
					&& map[startOne][startTwo]!='#' && map[endOne][endTwo]!='#'){

		for(int i=0; i<map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(this.map[i][j]=='E' || this.map[i][j]== 'S' || map[i][j]=='^' || map[i][j]=='V' || map[i][j]=='<'  || map[i][j]=='>' ) {
					this.map[i][j]=' ';
				}
			}
		}
		map[startOne][startTwo]='E';
		map[endOne][endTwo]='S';
		this.cheekingES=true;
		return true;
	}else return false;
	}
public void seekingWays() throws InterruptedException {

	
	if(this.cheekingES) {
		
		System.out.print(Config.SEEKING_A_PATH);
		int option = Interface.getInt();
		System.out.println();
		if(option==1) {
			if(this.firstWay()) {
				System.out.println("Camino encontrado\nMostrando el camino:\n");
				 Thread.sleep(900);
				showTheWay();
			}else {
				System.out.println("Camino no encontrado");
			}
		}else if(option==2) {
			System.err.println("Ups, me gustaría pero va a ser que hoy no.\n");
			return;
		}else {
			return;
		}
	}
	
	else {
		System.err.println("\n\nAntes debes seleccionar laberinto e introducir coordenadas de entrada y salida");
		return;
		}
	}
private void showTheWay() throws InterruptedException {
	for (int i = 0; i < map.length; i++) {
		for (int j = 0; j < map.length; j++) {
			if(map[i][j]=='^' ||map[i][j]=='V' || map[i][j]=='<' || map[i][j]=='>' ) {
				map[i][j]=' ';
			}
		}
	}
	for (int i = path.size()-1; i >= 0; i--) {
		if(i==path.size()-1) {
			continue;
		}else {
		if(path.get(i).direction==1) {
			this.map[path.get(i).i][path.get(i).j]='V';
		}else if(path.get(i).direction==2) {
			this.map[path.get(i).i][path.get(i).j]='^';
		}else if(path.get(i).direction==3) {
			this.map[path.get(i).i][path.get(i).j]='>';
		}else {
			this.map[path.get(i).i][path.get(i).j]='<';
			}
		}
	}
	showMap();
	
	
	for (int i = path.size()-1; i >= 0; i--) {
		if(i==path.size()-1) {
			System.out.println("("+ startI+ ", "+ startJ + ") Entrada");
		}else {
		if(path.get(i).direction==1) {
			System.out.println("("+ path.get(i).i+ ", "+ path.get(i).j + ") Abajo");
		}else if(path.get(i).direction==2) {
			System.out.println("("+ path.get(i).i+ ", "+ path.get(i).j + ") Arriba");
		}else if(path.get(i).direction==3) {
			System.out.println("("+ path.get(i).i+ ", "+ path.get(i).j + ") Derecha");
		}else {
			System.out.println("("+ path.get(i).i+ ", "+ path.get(i).j + ") Izquierda");
			}
		}
	}
	System.out.println("("+ endI+ ", "+ endJ + ") Salida");
	System.out.println("\nDesplazamientos totales: "+ path.size());

       
}
private boolean firstWay() {
	
	 this.path = new ArrayList<>();
     boolean[][] visited = new boolean[map.length][map[0].length];
     boolean found =dfs(startI, startJ, path, visited);
    
	
	return found;
}
private boolean dfs(int i, int j, ArrayList<Coordinate> path, boolean[][] visited) {
    // Si llegamos al final, encontramos una solución
    if (i == endI && j == endJ) {
        return true;
    	}
    
    // Si estamos en una pared o ya visitamos esta casilla, no continuamos
    if (map[i][j] == '#' || visited[i][j]) {
        return false;
    	}

    // Marcamos la casilla como visitada
    visited[i][j] = true;

    // Probamos con cada dirección
    if (dfs(i + 1, j, path, visited)) {
        path.add(new Coordinate(i, j, 1)); //abajo
        return true;
    	}

    if (dfs(i - 1, j, path, visited)) {
        path.add(new Coordinate(i, j, 2)); //arriba
        return true;
    	}

    if (dfs(i, j + 1, path, visited)) {
        path.add(new Coordinate(i, j, 3)); //derecha
        return true;
    	}

    if (dfs(i, j - 1, path, visited)) {
        path.add(new Coordinate(i, j, 4)); //izquierda
        return true;
    	}

    // Si no encontramos una solución en esta casilla, retrocedemos
    return false;
	}
}
