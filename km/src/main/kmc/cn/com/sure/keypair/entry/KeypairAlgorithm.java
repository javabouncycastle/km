package cn.com.sure.keypair.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keypair_algorithm")
public class KeypairAlgorithm {
	
	public KeypairAlgorithm(){
		
	}
	
	public KeypairAlgorithm(Long id){
		this.id=id;
	}
	
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id",length=32) 	
	private Long id;
	
	/**
	 * 别名
	 */
	@Column(name = "name",length=64) 	
	private String name;

	
	@Column(name = "alg_oid",length=256)                  private String algorithmOid;//算法OID
	
	@Column(name = "alg_name",length=128)                 private String algorithmName;//算法英文缩写
	/**
	 * 密钥长度
	 */
	@Column(name = "keysize",precision=12, scale=0) 		private Integer keysize;
	
	@Column(name = "notes",length=256)                      private String notes;//

    @Column (name = "is_valid",precision=1, scale=0)        private Integer isValid;//是否有效,0无效，1有效

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlgorithmOid() {
		return algorithmOid;
	}

	public void setAlgorithmOid(String algorithmOid) {
		this.algorithmOid = algorithmOid;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public Integer getKeysize() {
		return keysize;
	}

	public void setKeysize(Integer keysize) {
		this.keysize = keysize;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

    

}
