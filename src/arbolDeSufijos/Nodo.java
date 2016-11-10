package arbolDeSufijos;

import java.util.ArrayList;

/**
 * @author Miguel Bayón Sanz
 * @author Alejandro Martínez Andrés
 */
public class Nodo {

	private Nodo padre;
	private ArrayList<Nodo> hijos;
	private short letra;
	private int clave; // La clave la tendran solo las hojas

	/**
	 * Genera un nodo vacio
	 */
	public Nodo() { // Utilizado para el nodo raiz
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = -1; // -1 = No tiene letra
		this.clave = -1; // -1 = No tiene clave
	}

	/**
	 * Genera un nodo con una letra
	 * 
	 * @param letra
	 *            short con el valor numerico de la letra a introducir
	 */
	public Nodo(short letra) {
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = letra;
		this.clave = -1;
	}

	/**
	 * Genera un nodo con una letra y una clave
	 * 
	 * @param letra
	 *            short con el valor numerico de la letra a introducir
	 * @param clave
	 *            int con la clave de la palabra
	 */
	public Nodo(short letra, int clave) { // Utilizado para hojas
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = letra;
		this.clave = clave;
	}

	/**
	 * Añade un nodo hijo
	 * 
	 * @param hijo
	 *            Nodo con los valores del nodo hijo
	 */
	public void addHijo(Nodo hijo) {
		hijos.add(hijo);
	}

	/**
	 * Setter del parametro padre
	 * 
	 * @param padre
	 *            Nodo con los valores del nodo padre
	 */
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	/**
	 * Setter del parametro hijos
	 * 
	 * @param hijos
	 *            ArrayList con los nodos que se estableceran como hijos
	 */
	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}

	/**
	 * Setter del parametro letra
	 * 
	 * @param letra
	 *            short con la letra a establecer en el nodo
	 */
	public void setLetra(short letra) {
		this.letra = letra;
	}

	/**
	 * Setter del parametro clave
	 * 
	 * @param clave
	 *            int con la clave de la palabra
	 */
	public void setClave(int clave) {
		this.clave = clave;
	}

	/**
	 * Getter del parametro clave
	 * 
	 * @return Nodo padre si existe, null si no existe
	 */
	public Nodo getPadre() {
		return padre;
	}

	/**
	 * Getter del parametro hijos
	 * 
	 * @return ArrayList con los nodos hijo
	 */
	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	/**
	 * Getter del parametro letra
	 * 
	 * @return short con el valor numerico de la letra del nodo
	 */
	public short getLetra() {
		return letra;
	}

	/**
	 * Getter del parametro clave
	 * 
	 * @return int con la clave de la palabra
	 */
	public int getClave() {
		return clave;
	}

	/**
	 * Devuelve el Nodo hijo que contenga la letra especificada
	 * 
	 * @param letra
	 *            Short con el valor de la letra buscada
	 * @return Nodo hijo con la letra buscada o null si no existe ese hijo
	 */
	public Nodo getHijoPorLetra(short letra) {
		for (int i = 0; i < hijos.size(); i++) {
			if (hijos.get(i).getLetra() == letra)
				return hijos.get(i);
		}
		return null;
	}

	/**
	 * Indica si el nodo es o no una hoja
	 */
	public boolean isHoja() {
		return hijos.isEmpty();
	}

	/**
	 * Indica si el nodo es o no una raiz
	 */
	public boolean isRoot() {
		return (padre == null);
	}

	/**
	 * Comprueba que los hijos sean iguales buscandolos en caso de estar
	 * desordenados
	 * 
	 * @param nodo
	 *            Nodo cuyos hijos seran comparados
	 * @return true si sus hijos son iguales
	 */
	public boolean compareHijos(Nodo nodo) {
		boolean encontrado;
		if (nodo.getHijos().size() != this.hijos.size())
			return false;

		for (int i = 0; i < this.hijos.size(); i++) {
			encontrado = false;

			for (int j = 0; j < nodo.getHijos().size(); j++) {
				if (nodo.getHijos().get(j).getClave() == this.clave && hijos.get(i).getLetra() == this.letra)
					encontrado = true;
			}

			if (!encontrado)
				return false;
		}

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Nodo))
			return false;

		Nodo nodo = (Nodo) obj;
		if (nodo.getClave() != this.clave)
			return false;
		if (!compareHijos(nodo))
			return false;
		if (nodo.getLetra() != this.letra)
			return false;
		if (!(nodo.isRoot() && this.isRoot()))
			return false;
		if (!nodo.isRoot() && nodo.getPadre().equals(this.padre))
			return false;

		return true;
	}
}