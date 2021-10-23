package univ.lille.gl.sra1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dock {

	@Id
    @GeneratedValue
    int id;
	
	int numDocker;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumDocker() {
		return numDocker;
	}

	public void setNumDocker(int numDocker) {
		this.numDocker = numDocker;
	}
	
	
	
}