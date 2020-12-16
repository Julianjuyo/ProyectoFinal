package uniandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Programa para Resolver el problema 1: Inversa Matricial en Zp
 * @author Julian Oliveros Forero
 * @author Camilo Rozo
 */
public class ProblemaA {

	public class Tupla<A,B>{
		public A a;
		public B b;

		Tupla(A left,B right)
		{
			a=left;
			b=right;
		}

		public A left()
		{
			return a;
		}

		public B right()
		{
			return b;
		}

	}

	public static void main(String[] args) throws Exception {

		ProblemaA instancia = new ProblemaA();

		/**
		 * Lista de casos de prueba
		 */
		ArrayList<Tupla<Integer,Integer[][]>> problemas = new ArrayList<>();


		try ( 
				InputStreamReader is = new InputStreamReader(System.in);

				BufferedReader br = new BufferedReader(is);
				) { 
			String line;

			System.out.println("1");
			int currentMatrixSize=-1;
			Integer[][] currentMatrix = null;
			int currentIndex=0;
			Integer currentPrime=0;

			System.out.println("2");
			while((line=br.readLine())!=null && line.length()>0 && !"0 0".equals(line)) {
				String [] dataStr = line.split(" ");
				int[] nums = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();

				if(currentMatrixSize!=-1)
				{
					System.out.println("3");
					if(currentIndex==nums[0])
					{
						currentMatrixSize =-1;
						problemas.add(instancia.new Tupla<Integer,Integer[][]>(currentPrime,currentMatrix));
					}
					else {
						for(int i=0;i<nums.length;i++)
						{
							System.out.println("4");
							currentMatrix[currentIndex][i]=nums[i];
						}
						currentIndex++;
					}
				}
				else {
					System.out.println("5");
					currentMatrix=new Integer[nums[0]][nums[0]];
					currentPrime = nums[1];
				}
			}
		}

		System.out.println("6");

		for(Tupla<Integer,Integer[][]> problema:problemas)
		{
			System.out.println("7");

			Integer[][] solucion = instancia.inversaMatricial(problema.left(),problema.right());
			if(solucion ==null)
			{
				System.out.println("X");
			}
			else
			{
				for(Integer[] fila:solucion)
				{
					for(Integer filasubi:fila)
					{
						System.out.append(filasubi+" ");
					}
					System.out.append("\n");
				}
			}
			System.out.println("*");
		}
	}



	/**
	 * Metodo que calcula la inversa multiplicativa
	 * @param primo
	 * @param matriz
	 * @return
	 */
	public Integer[][] inversaMatricial(Integer primo,Integer[][] matriz)
	{
		System.out.println("primo"+primo);
		System.out.println("tamano"+matriz.length);


		Integer n = matriz.length;
		Integer[][] matrizInversa = new Integer[n][n];


		// Este metodo inicializa la matriz Inversa creando una matriz identidad
		for (int i = 0; i < matrizInversa.length; i++) {
			for (int j = 0; j < matrizInversa.length; j++) {
				if(i==j)
					matrizInversa[i][j]=1;
				else
					matrizInversa[i][j]=0;
			}
		}


		int i=0;
		for (int j = 0; j < n; j++) {

//			System.out.println("------------------------------");
//			System.out.println("1:"+matriz[j][j]);
			if(matriz[j][j]!=1){
				
				int FiM = matriz[i][j];
				int FiMI = matrizInversa[i][j];
//				System.out.println("2.0:"+matriz[i][j]);
//				System.out.println("2.1:"+matrizInversa[i][j]);

				int a = encontrarDivisorPor1(primo, FiM);
//				System.out.println("2.3:"+a);


				// Cambia los valores para la fila, el caso en que se tenga que convertir en 1
				for (int k = 0; k < n; k++) {
					FiM = matriz[j][k];
					FiMI = matrizInversa[j][k];
//					System.out.println("3.1A:"+matriz[j][k]);
//					System.out.println("3.2A:"+matrizInversa[j][k]);
					matriz[j][k]= ((FiM*a)%primo);
					matrizInversa[j][k]= ((FiMI*a)%primo);
//					System.out.println("3.1D:"+matriz[j][k]);
//					System.out.println("3.2D:"+matrizInversa[j][k]);
				}

			}
			
			//Recorrer Por la filas de una columna
			for (int h = 0; h < n; h++) {
				int valorCasilla =  matriz[h][j];

				if(valorCasilla!=0 && h!=j ) {
					int constante = valorCasilla*-1;
					
					//Cambia los valores para la matriz. 
					for (int k = j; k < n; k++) {
						int RFilaActual = matriz[j][k];	
						int RFilaACambiar = matriz[h][k];
//						System.out.println("6.1:"+RFilaActual);
//						System.out.println("6.2:"+RFilaACambiar);
						int a =(RFilaActual*constante);
						a = (a+RFilaACambiar);
						matriz[h][k] = (((a% primo) + primo) % primo);
//						System.out.println("6.6:"+matriz[h][k]);
					}

					//Cambia los valores para la matriz Inversa
					for (int k = 0; k < n; k++) {
						int RFilaActual = matrizInversa[j][k];	
						int RFilaACambiar = matrizInversa[h][k];
//						System.out.println("7.1:"+RFilaActual);
//						System.out.println("7.2:"+RFilaACambiar);
						int a =(RFilaActual*constante);
						a = (a+RFilaACambiar);
						matrizInversa[h][k] = (((a% primo) + primo) % primo);
						//System.out.println("7.5:"+matrizInversa[h][k]);
					}			
				}
			}
			i++;
		}
		return matrizInversa;
	}


	public void imprimir(Integer[][] a,Integer[][] b ) {

		System.out.println("----------------------");

		for (int i = 0; i <  a.length; i++) {
			for (int j = 0; j <  a.length; j++) {

				System.out.println(a[i][j]+ "  ");
			}
			System.out.println();
		}

		System.out.println("---");

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {

				System.out.println(b[i][j]+ "  ");
			}
			System.out.println();
		}
		System.out.println("----------------------");

	}




	/**
	 * Metodo para encontrar que x satisface la formula R1*x mod primo = 1
	 * @param primo
	 * @param R1
	 * @return
	 */
	public int encontrarDivisorPor1(Integer primo, Integer R1) {
		boolean termino=false;
		int resp=0;

		for (int i = 1; i < 100000000 & !termino; i++) {	
			resp=(R1*i)%primo;

			if(resp==1) {
				termino=true;
				resp=i;
			}
		}
		return resp;
	}


}