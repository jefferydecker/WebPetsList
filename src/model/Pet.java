package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pets")
public class Pet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private	int	id;
	@Column(name="TYPE")
	private	String	type;
	@Column(name="NAME")
	private	String	name;
	@Column(name="OWNER")
	private	String	owner;

	// Constructors
	public	Pet() {
	}
	
	public Pet(String type, String name, String owner) {
		this.type = type;
		this.name = name;
		this.owner = owner;
	}
	
	// Getters and setters
	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	// helper methods
	public String returnPetDetails() {
		return name + " the " + type + ", friend of " + owner;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", type=" + type + ", name=" + name + ", owner=" + owner + "]";
	}

}
