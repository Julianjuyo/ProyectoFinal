package uniandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;




/**
 * Programa para Resolver el problema 3: Trampolines y tablones
 * @author Julian Oliveros Forero
 * @author Camilo Rozo
 */
public class ProblemaC {

	public static void main(String[] args) throws Exception {

		ProblemaC instancia = new ProblemaC();

		try ( 
				InputStreamReader is= new InputStreamReader(System.in);

				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();
			boolean acabo = false;			
			ArrayList<Integer> resp = new ArrayList<>();


			while(line!=null && line.length()>0 && !"0 0".equals(line)) {

				System.out.println(acabo);
				String [] dataStr = line.split(" ");
				int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				System.out.println("linea:"+ line);


				int m = numeros[0];
				int n = numeros[1];

				line = br.readLine();
				String [] dataStr2 = line.split(" ");
				int []numeros2 = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
				System.out.println("linea2:"+ line);
				int numPasos =instancia.minNumeroDePasos(n, m, numeros2);

				resp.add(numPasos);


				line = br.readLine();
				System.out.println("linea3"+ line);
			}

			for (int i = 0; i < resp.size(); i++) 
				System.out.println(resp.get(i));

		}
	}

	/**
	 * METODO que hace referencia a un vertice del grafo
	 * @author julianoliveros
	 *
	 */
	class Vertice implements Comparator<Vertice> {

		private int ID;
		private Integer MinPasos;
		private Integer Marca;
		private Vertice verticePredecesor;

		public  Vertice() {
			this.setID(0);
			this.MinPasos = 0;
			this.verticePredecesor = null;

		}

		public int getMinPasos() {
			return MinPasos;
		}
		public void setRutaMasCorta(int rutaMasCorta) {
			this.MinPasos = rutaMasCorta;
		}

		public Vertice getVerticePredecesor() {
			return verticePredecesor;
		}
		public void setVerticePredecesor(Vertice verticePredecesor) {
			this.verticePredecesor = verticePredecesor;
		}
		private int getID() {
			return ID;
		}

		private void setID(int iD) {
			ID = iD;
		}
		@Override
		public int compare(Vertice node1, Vertice node2) 
		{ 
			if (node1.MinPasos < node2.MinPasos) 
				return -1; 
			if (node1.MinPasos > node2.MinPasos) 
				return 1; 
			return 0; 
		}

		public Integer getMarca() {
			return Marca;
		}

		public void setMarca(Integer marca) {
			Marca = marca;
		}
	}
	
	
	
	public static final int INFINITO = Integer.MAX_VALUE;

	private ArrayList<Vertice> grafo ;



	/**
	 * Calcula el minimo numero de pasos que el protagonista debe realizar para ir del origen al final del camino
	 * @param n Longitud en metros del camino. 
	 * @param m Longitud rn metros del tablon.
	 * @param int [] arreglo con las marcas. Arreglo con las marcas donde se puede tener trampolines, marca final, abismos y avance de un paso. 
	 * @return int numero minimo de pasos que el personaje debe hacer para llegar 
	 * 		       al final del camino, retorna -1 si no se puede llegar al final.
	 * 
	 */
	public int minNumeroDePasos(int n, int m ,int[] marcas) {

		System.out.println("Entro al algoritmo" );
		setGrafo(new ArrayList<Vertice>());

		int[][] matrizadj = new int[n][n];

		crearEInicializarGrafo(m, marcas);



		for (int i = 0; i < marcas.length; i++) {





		}


		System.out.println("\n"+"Entro");
		System.out.println(n);
		System.out.println(m);

		for (int i : marcas) {
			System.out.println("it"+i);
		}





		return 1;
	}


	/**
	 * Metodo en el cual se crean los vertices del grafo, ademas se inicializan sus valores para poder implementar Dijsktra
	 * Le asigna al vertice inicial una distancia de 0
	 * 
	 * @param numeroVertices
	 * @param verticeInicial
	 */
	public int[][] crearEInicializarGrafo(int[][] matrizadj, int m, int[] marcas) {



		for (int i = 0; i < marcas.length; i++) {

			for (int j = 0; j < marcas.length; j++) {
				
				
				Vertice actual = new Vertice();
				
				actual.setID(i);
				actual.setRutaMasCorta(INFINITO);
				
				actual.setMarca(marcas[i]);
				
				if(i==0) {
					matrizadj[i][j+1]= marcas[0];
				}
				else if(i==marcas.length)
				
				
				actual.setVerticePredecesor(null);
				getGrafo().add(actual);
\nzdbe d'andjke  xdm x wajksd cdJSHf wq z	zkx.jsdn sdjfbdhc fd;fdsznsd
fdq
Wad 
			}



		}
		
		getGrafo().get(0).setRutaMasCorta(0);

		return matrizadj;
	}


	/**
	 * Metodo que retorna el grafo
	 * @return retorna el grafo
	 */
	public ArrayList<Vertice> getGrafo() {
		return grafo;
	}

	/**
	 * Metodo que asigna un grafo
	 * @param grafo
	 */
	public void setGrafo(ArrayList<Vertice> grafo) {
		this.grafo = grafo;
	}


}
