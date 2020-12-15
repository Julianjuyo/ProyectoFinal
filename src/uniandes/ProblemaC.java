package uniandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


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
	 * Calcula el minimo numero de pasos que el protagonista debe realizar para ir del origen al final del camino
	 * @param n Longitud en metros del camino. 
	 * @param m Longitud rn metros del tablon.
	 * @param int [] arreglo con las marcas. Arreglo con las marcas donde se puede tener trampolines, marca final, abismos y avance de un paso. 
	 * @return int numero minimo de pasos que el personaje debe hacer para llegar 
	 * 		       al final del camino, retorna -1 si no se puede llegar al final.
	 * 
	 */
	public int minNumeroDePasos(int n, int m ,int[] marcas) {

		System.out.println("\n"+"Entro");
		System.out.println(n);
		System.out.println(m);

		for (int i : marcas) {
			System.out.println("it"+i);
		}

		return 1;
	}

}
