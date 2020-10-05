package busqueda_profundidad;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String args[]){

		Scanner leer = new Scanner (System.in);
		System.out.println("Ingrese el número de pancakes");
		int cantidad = leer.nextInt();
		String ordenado = "abcdefghijklmnopqrstuvwxyz";	
		ordenado = ordenado.substring(0, cantidad);
		System.out.println( "" );
		System.out.println("Cadena Original: " + ordenado);	
		String cadena = revolver(ordenado);
		System.out.println("Cadena Revuelta: " + cadena);	
		pancake pancake = new pancake(cadena, id, 0, 0);
		cadenasProducidas.add(cadena);
		yaRevisados.add(pancake);
		busqueda(ordenado, pancake, 1, 0);
		if(idFin>1) {
			ArrayList<pancake>camino = new ArrayList<pancake>();
			while (yaRevisados.get(idFin-1).getIdPadre() > 0) {
				camino.add(yaRevisados.get(idFin-1));
				idFin = yaRevisados.get(idFin-1).getIdPadre();
			}
			for(int i = camino.size()-1; i >= 0; i --) {
				System.out.println("Se movió desde la pos " + camino.get(i).getDesplazados() + ": " + camino.get(i).getTexto());
			}
		}else {
			System.out.println("La cadena no requiere ningun cambio: " + cadena);
		}
	}
	
	public static int id = 1;
	public static int idFin = 0;
	public static int tamanio = 1000;
	public static ArrayList<pancake> yaRevisados = new ArrayList<pancake>();
	public static ArrayList<String> cadenasProducidas = new ArrayList<String>();
	public static boolean orden = false;
	
	public static void busqueda(String ordenado, pancake pancake, int longitud, int posMovimiento) {
		String cadena = pancake.getTexto();
		for(int i = 2; i <= cadena.length(); i ++) {
			if(!orden) {
				if(!cadena.equals(ordenado)) {
					String aux = mover(cadena,i);
					if(i != posMovimiento && !cadenasProducidas.contains(aux) && longitud < tamanio) {
						id ++;
						pancake temp = new pancake(aux, id, pancake.getId(), i);
						yaRevisados.add(temp);
						cadenasProducidas.add(temp.getTexto());
						busqueda(ordenado, temp, longitud+1, i);
					}
				}else {
					i = cadena.length()+1;
					idFin = id;
					orden = true;
				}
			}else {
				if(longitud == 1) {
					tamanio = tamanioSolucion()-1;
					orden = false;
					i = 2;
					cadenasProducidas.clear();
					cadenasProducidas.add(cadena);
				}
			}
		}
	}
	
	public static int tamanioSolucion() {
		int cont = 0;
		int aux = idFin;
		while (yaRevisados.get(aux-1).getIdPadre() > 0) {
			cont++;
			aux = yaRevisados.get(aux-1).getIdPadre();
		}
		return cont;
	}
	
	public static String revolver(String cadena) {
		char texto[] = new char [cadena.length()];
		texto = cadena.toCharArray();
		for(int i = 0; i < texto.length; i ++) {
			char temp = texto[i];
			int rand = (int)(Math.random() * texto.length);
			texto[i] = texto[rand];
			texto[rand] = temp;
		}
		String revuelto = "";
		for(int i = 0; i < texto.length; i ++) {
			revuelto += texto[i];
		}
		return revuelto;
	}
	
	public static String mover (String cadena, int posicion) {
		char texto[] = new char [posicion + 1];
		char cadenaOrig[] = new char [cadena.length()];
		cadenaOrig = cadena.toCharArray();
		String newText = "";
		int e = 0;
		for(int i = posicion-1; i >= 0; i --) {
			texto[e] = cadena.charAt(i);
			e ++;
		}
		for(int i = 0; i < posicion; i ++) {
			cadenaOrig[i] = texto[i];
		}
		for(int i = 0; i < cadena.length(); i ++) {
			newText += cadenaOrig[i];
		}
		return newText;
	}
	
}
