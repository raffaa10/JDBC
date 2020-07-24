package exemple.jdbc.entity;

public class Article {
	
	private String designation; 
	private int id;
	private double prix; 
	private String ref;
	private int id_fou;
	/**
	 * @param designation
	 * @param id
	 * @param prix
	 * @param ref
	 */
	public Article(String designation, int id, double prix, String ref, int id_fou) {
		super();
		this.designation = designation;
		this.id = id;
		this.prix = prix;
		this.ref = ref;
		this.id_fou = id_fou;
	}
	@Override
	public String toString() {
		return "designation : " + designation + ", id : " + id + ", prix : " + prix + ", ref : " + ref + ", id_fou : " + id_fou;
	}
		/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @return the id_fou
	 */
	public int getId_fou() {
		return id_fou;
	}
	
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @param id_fou the id_fou to set
	 */
	public void setId_fou(int id_fou) {
		this.id_fou = id_fou;
	}
	
	

}
