package arbolDeSufijos;

import java.util.ArrayList;

public class Arbol {

	Nodo root;

	/**
	 * Genera un arbol cuyos nodos son las letras de las palabras añadidas
	 */
	public Arbol() {
		root = new Nodo();
	}

	public void addPalabra(short[] palabra, int clave) {
		Nodo nodo = root;
		for (int i = 0; i < palabra.length; i++) {
			if (nodo.getHijoPorLetra(palabra[i]) == null) {
				nodo.addHijo(new Nodo(palabra[i]));
				nodo = nodo.getHijoPorLetra(palabra[i]);

				if (i == palabra.length - 1)
					nodo.setClave(clave);
			} else
				nodo = nodo.getHijoPorLetra(palabra[i]);
		}
	}

	/**
	 * Busca la palabra dada en el arbol y devuelve su clave
	 * 
	 * @param palabra
	 *            Short[] con la palabra que se desea buscar en el arbol
	 * @return int con la clave de la palabra o -1 si no se ha encontrado
	 */
	public int buscaPalabra(short[] palabra) {
		Nodo nodo = root;

		for (int i = 0; i < palabra.length; i++) {
			if ((nodo = nodo.getHijoPorLetra(palabra[i])) == null)
				return -1; // No encontro la palabra
		}
		return nodo.getClave();
	}

	public void recorreRamas() {
		recorre(root);
	}

	private void recorre(Nodo nodo) {
		if (nodo.getLetra() != -1)
			System.out.println((char) nodo.getLetra());

		if (!nodo.isHoja()) {
			for (int i = 0; i < nodo.getHijos().size(); i++) {
				recorre(nodo.getHijos().get(i));
			}
		}
	}

	public void imprimePalabras() {
		ArrayList<Integer> letras = new ArrayList<Integer>();
		imprime(root, letras);
	}

	@SuppressWarnings("unchecked")
	private void imprime(Nodo nodo, ArrayList<Integer> letras) {
		if (nodo.getLetra() != -1)
			letras.add((int) nodo.getLetra());

		if (nodo.isHoja()) {
			for (int i = 0; i < letras.size(); i++) {
				System.out.print((char) letras.get(i).shortValue());
			}
			System.out.println("\t\tClave: " + nodo.getClave());

		} else {
			for (int i = 0; i < nodo.getHijos().size(); i++) {
				imprime(nodo.getHijos().get(i), (ArrayList<Integer>) letras.clone());
			}
		}
	}

}
