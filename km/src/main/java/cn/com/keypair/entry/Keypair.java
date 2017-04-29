package cn.com.keypair.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "km_kpg_algorithm")
public class Keypair {
	
	public Keypair(){
		
	}
	
	public Keypair(Long id){
		this.id=id;
	}

	@Id
	@Column(name="id", precision=18, scale=0)
    private Long id;
	
	
}
