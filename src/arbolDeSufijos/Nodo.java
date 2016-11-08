package arbolDeSufijos;

import java.util.ArrayList;

public class Nodo {

	Nodo padre;
	ArrayList<Nodo> hijos;
	short letra;
	int clave; // La clave la tendran solo las hojas

	public Nodo() { // Utilizado para el nodo raiz
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = -1; // -1 = No tiene letra
		this.clave = -1; // -1 = No tiene clave
	}

	public Nodo(short letra) {
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = letra;
		this.clave = -1;
	}

	public Nodo(short letra, int clave) { // Utilizado para hojas
		this.padre = null;
		hijos = new ArrayList<Nodo>();
		this.letra = letra;
		this.clave = clave;
	}

	public void addHijo(Nodo hijo) {
		hijos.add(hijo);
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}

	public void setLetra(short letra) {
		this.letra = letra;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public Nodo getPadre() {
		return padre;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public short getLetra() {
		return letra;
	}

	public int getClave() {
		return clave;
	}
	
	public Nodo getHijoPorLetra(short letra){
		for (int i = 0; i < hijos.size(); i++){
			if (hijos.get(i).getLetra()==letra)
				return hijos.get(i);
		}
		return null;
	}

	public boolean isHoja() {
		return hijos.isEmpty();
	}

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}