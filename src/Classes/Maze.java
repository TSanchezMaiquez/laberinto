package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {

	private char [][] map;
	private boolean loaded;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;
	private ArrayList<Coordinate>path = new ArrayList <>();
	private boolean cheekingES;
	
	
	public Maze(){
		this.loaded =false;
		this.cheekingES=false;
		
	}

public void loadMaze() throws InterruptedException {
	
	System.out.println();
	File f = new File(Config.MAZES_PATH);
	String[] pathnames = f.list();
	if(pathnames.length==0 | pathnames == null) {
		System.err.println("No hay laberintos para elegir");
		
	}else {
		this.chooseMaze(pathnames);
		
	}
}	
private void chooseMaze(String [] pathnames) throws InterruptedException {
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
    	 	System.out.println("\nCargando el laberinto en el programa...");
    	    Thread.sleep(900);
    	     
    	     
    	     this.saveMaze(Config.MAZES_PATH + pathnames[num-1]);
    	     System.out.println();
    	     System.out.println("Laberinto cargado\n");
    	     Thread.sleep(900);
    	     Log.write("Laberinto cargado", pathnames[num-1]);
     	}else {
     		if(num==0){
     			System.err.println("\nHas decidido salir sin elegir laberinto");
     			}
     		else {
     			System.err.println("\nEl numero introducido no es valido");
     			
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
	ArrayList<String> allLines = new ArrayList<>();
	
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
	      Log.write("Error guardando laberinto seleccionado ", "Sistema");
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
	
	   StringBuilder drawing = new StringBuilder();
	    for (int i = 0; i < this.map.length; i++) {
	        if (i <= 9 && i > 0) {
	            drawing.append("0").append(i).append(" ");
	        } else if (i == 0 || i == this.map.length - 1) {
	            drawing.append("   ");
	        } else {
	            drawing.append(i).append(" ");
	        }
	        for (int j = 0; j < this.map[0].length; j++) {
	            drawing.append(this.map[i][j]).append(" ");
	        }
	        drawing.append("\n");
	    }
	    System.out.println(drawing.toString());
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
		
		String coordinates= "("+this.startI +", "+ this.startJ+")"+"("+this.endI+", "+this.endJ+")";
		
		if(checkData(this.startI, this.startJ, this.endI, this.endJ)) {
			Log.write("Coordenadas correctas", coordinates);
			System.out.println("Coordenadas correctas");
		}else {
			System.err.println("Las coordenadas introducidos no son validas");
			Log.write("Coordenadas incorrectas", coordinates);
			}
	}else {
		System.err.println("\nPrimero debe elegir un laberinto para mostrarlo");
		
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
	Log.write("Acceso a seekingWays()", "");
	
	if(this.cheekingES) {
		
		System.out.print(Config.SEEKING_A_PATH);
		int option = Interface.getInt();
		System.out.println();
		if(option==1) {
			if(this.firstWay()) {
				Log.write("Primero camino encontrado", "Ha dado "+this.path.size()+ " pasos");
				System.out.println("Camino encontrado\nMostrando el camino:\n");
				 Thread.sleep(900);
				showTheWay();
			}else {
				System.out.println("Camino no encontrado");
				Log.write("error buscando camino en el laberinto", "Sistema");
			}
		}else if(option==2) {
			System.err.println("Ups, me gustaría pero va a ser que hoy no.\n");
			
		}
	}
	
	else {
		System.err.println("\n\nAntes debes seleccionar laberinto e introducir coordenadas de entrada y salida");
		
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
	for (int i = path.size()-2; i >= 0; i--) {
		
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
    
    
	
	 return dfs(startI, startJ, path, visited);
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