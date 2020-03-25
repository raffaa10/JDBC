package fr.diginamic.jdbc.entites;

/**
 * @author antoinethebault
 *Fournisseur : represente un fournisseur avec son id unique et son nom
 */
public class Fournisseur {
	/**id : int*/
	private int id;
	/**nom : String*/
	private String nom;
	
	/**Constructor
	 * @param id
	 * @param nom
	 */
	public Fournisseur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	/**Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.id+" "+this.nom;
	}
}
