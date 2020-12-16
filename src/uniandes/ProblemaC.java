import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * Programa para Resolver el problema 3: Trampolines y tablones
 * @author Julian Oliveros Forero
 * @author Camilo Rozo
 */
public class ProblemaC {

  public static void main(String[] args) throws Exception {
    ProblemaC instancia = new ProblemaC();

    try (
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
    ) {
      String line = br.readLine();
      boolean acabo = false;
      ArrayList<Integer> resp = new ArrayList<>();

      while (line != null && line.length() > 0 && !"0 0".equals(line)) {
        System.out.println(acabo);
        String[] dataStr = line.split(" ");
        int[] numeros = Arrays
          .stream(dataStr)
          .mapToInt(f -> Integer.parseInt(f))
          .toArray();
        System.out.println("linea:" + line);

        int n = numeros[0];
        int m = numeros[1];

        line = br.readLine();
        String[] dataStr2 = line.split(" ");
        int[] numeros2 = Arrays
          .stream(dataStr2)
          .mapToInt(f -> Integer.parseInt(f))
          .toArray();
        System.out.println("linea2:" + line);
        int numPasos = instancia.minNumeroDePasos(n, m, numeros2);

        resp.add(numPasos);

        line = br.readLine();
        System.out.println("linea3" + line);
      }

      for (int i = 0; i < resp.size(); i++) System.out.println(resp.get(i));
    }
  }

  /**
   * Clase que hace referencia a un vertice del grafo de estados
   */
  private class Vertice implements Comparator<Vertice> {

    private Integer minPasos;
    private Integer posicion;
    private Integer tablonRestante;

    public Vertice(int pPosicion, int pPasos, int pTablon) {
     
      this.posicion = pPosicion;
      this.minPasos = pPasos;
      this.tablonRestante = pTablon;
    }

    public int minPasos() {
      return minPasos;
    }

    public int tablonRestante() {
      return tablonRestante;
    }

    @Override
    public int compare(Vertice node1, Vertice node2) {
      if (node1.minPasos < node2.minPasos) return -1;
      if (node1.minPasos > node2.minPasos) return 1;
      return 0;
    }

    public int posicion() {
      return posicion;
    }
  }

  public static final int INFINITO = Integer.MAX_VALUE;

  private ArrayList<Vertice> grafo;

  public ArrayList<Vertice> sucesores(Vertice x, int[] marcas) {
    if (corteRamas(x, marcas)) {
      return null;
    }
    ArrayList<Vertice> sucesores = new ArrayList<>();
    if (marcas[x.posicion()] == 0) {
      return null;
    } else if (marcas[x.posicion()] == -1) {
      sucesores.add(
        new Vertice(
            x.posicion() + 1,
            x.minPasos() + 1,
            x.tablonRestante() - 1
          )
      );
      sucesores.add(
       new Vertice(
            x.posicion() - 1,
            x.minPasos() + 1,
            x.tablonRestante() - 1
          )
      );
    } else {
      int atras = x.posicion() - marcas[x.posicion()];
      int delante = x.posicion() - marcas[x.posicion()];
      if (atras >= 0) {
        sucesores.add(
          new Vertice(atras, x.minPasos() + 1, x.tablonRestante())
        );
      }
      if (delante < marcas.length) {
        sucesores.add(
          new Vertice(delante, x.minPasos() + 1, x.tablonRestante())
        );
      }
    }
    return sucesores;
  }

  public boolean corteRamas(Vertice x, int[] marcas) { //descarto los sucesores de x?
    if (x.tablonRestante() < 0) {
      return true; //descartar sucesores de x;
    } else if (x.tablonRestante() == 0 && marcas[x.posicion()] == -1) {
      return true;
    }
    return false;
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
  public int minNumeroDePasos(int n, int m, int[] marcas) {
    Vertice estadoInicial = new Vertice(marcas[0], 0, m);
    ArrayList<Vertice> soluciones = new ArrayList<>();
    Stack<Vertice> todos = new Stack<>();
    todos.add(estadoInicial);
    for (Vertice v : todos) {
      todos.addAll(sucesores(v, marcas));
      if (marcas[v.posicion()] == 0) {
        soluciones.add(v);
      }
    }

    Vertice verticeOptimo = null;
    int pasos = Integer.MAX_VALUE;
    for (Vertice v : soluciones) {
      if (v.minPasos() < pasos) {
        verticeOptimo = v;
        pasos = v.minPasos();
      }
    }

    // setGrafo(new ArrayList<Vertice>());

    // int[][] matrizadj = new int[n][n];

    // crearEInicializarGrafo(matrizadj, m, marcas);

    // for (int i = 0; i < marcas.length; i++) {}

    return verticeOptimo.minPasos();
  }

  /**
   * Metodo en el cual se crean los vertices del grafo, ademas se inicializan sus valores para poder implementar Dijsktra
   * Le asigna al vertice inicial una distancia de 0
   *
   * @param numeroVertices
   * @param verticeInicial
   */
//  public int[][] crearEInicializarGrafo(
//    int[][] matrizadj,
//    int m,
//    int[] marcas
//  ) {
//    for (int i = 0; i < marcas.length; i++) {
//      for (int j = 0; j < marcas.length; j++) {
//        Vertice actual = new Vertice();
//
//        actual.setID(i);
//        actual.setRutaMasCorta(INFINITO);
//
//        actual.setMarca(marcas[i]);
//
//        if (i == 0) {
//          matrizadj[i][j + 1] = marcas[0];
//        } else if (i == marcas.length) actual.setVerticePredecesor(null);
//        getGrafo().add(actual);
//      }
//    }
//
//    getGrafo().get(0).setRutaMasCorta(0);
//
//    return matrizadj;
//  }

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
