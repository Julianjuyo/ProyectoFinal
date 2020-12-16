package uniandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Programa para Resolver el problema 2: Orden de cortes más barato
 * @author Julian Oliveros Forero
 * @author Camilo Rozo
 */
public class ProblemaB {

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

		ProblemaB instancia = new ProblemaB();

		/**
		 * Lista de casos de prueba
		 */
		ArrayList<Tupla<Integer,int[]>> problemas = new ArrayList<>();


		try ( 
				InputStreamReader is = new InputStreamReader(System.in);

				BufferedReader br = new BufferedReader(is);
				) { 
			String line;
			while((line=br.readLine())!=null && line.length()>0 && !"0".equals(line)) {
				String [] dataStr = line.split(" ");
				int[] nums = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				int rodSize = Integer.parseInt(br.readLine());
				problemas.add(instancia.new Tupla<Integer,int[]>(rodSize,nums));
			}
		}

		for(Tupla<Integer,int[]> problema:problemas)
		{
			int solucion = instancia.costoMinimoCortes(problema.left(),problema.right());
			System.out.println(solucion);
		}
	}

	public int costoMinimoCortes(Integer tamano,int[] puntosCorte)
	{
		int t[][] = new int[tamano+1][tamano+1];

		//T(i,j)=(min⁡ k |i<p[k]<j : T(i,p[k])+(i-j)+T(p[k],j)) si i≠j
		for(int i=0,j=i,z=0;z<t.length;)
		{//z sirve para recorrer por diagonales -- no se me ocurrió otra forma usando sólo i y j
			if(i>=j)//no puedo empezar adelante del final y no puedo cortar si empiezo y acabo en el mismo punto
			{
				t[i][j]=0;
			}
			else
			{
				t[i][j]=-1;
				for(int k=0;k<puntosCorte.length;k++)
				{
					int pk=puntosCorte[k];
					if(pk >i && pk<j)
					{
						t[i][j]=minPositive( t[i][j] , t[i][pk]+(j-i)+t[pk][j]);
					}
				}
				if(t[i][j]==-1) //no se cortó nada en ese rango
				{
					t[i][j]=0;
				}
			}

			//avanzar en la matriz
			if(j==t.length-1)
			{
				i=0;
				z++;
				j=z;
			}
			else
			{
				i++;
				j++;
			}
			
		}

		return t[0][tamano];
	}

	public int minPositive(int a, int b)
	{
		if(a<0) return b;
		else if(b<0) return a;
		else if(b<a) return b;
		return a;
	}
}
