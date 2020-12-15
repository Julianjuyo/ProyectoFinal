package uniandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import ejemplo.ProblemaZ;

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

			int currentMatrixSize=-1;
			Integer[][] currentMatrix = null;
			int currentIndex=0;
			Integer currentPrime=0;
			while((line=br.readLine())!=null && line.length()>0 && !"0 0".equals(line)) {
				String [] dataStr = line.split(" ");
				int[] nums = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				if(currentMatrixSize!=-1)
				{
					if(currentIndex==nums[0])
					{
						currentMatrixSize =-1;
						problemas.add(instancia.new Tupla<Integer,Integer[][]>(currentPrime,currentMatrix));
					}
					else {
						for(int i=0;i<nums.length;i++)
						{
							currentMatrix[currentIndex][i]=nums[i];
						}
						currentIndex++;
					}
				}
				else {
					currentMatrix=new Integer[nums[0]][nums[0]];
					currentPrime = nums[1];
				}
			}
		}
		
		for(Tupla<Integer,Integer[][]> problema:problemas)
		{
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
	
	public Integer[][] inversaMatricial(Integer primo,Integer[][] matriz)
	{
		
		
		
		//no hay solución al problema
		return null;
	}
}
