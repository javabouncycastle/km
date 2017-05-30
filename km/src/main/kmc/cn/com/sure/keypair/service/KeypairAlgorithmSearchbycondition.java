package cn.com.sure.keypair.service;

import cn.com.sure.framework.PaginatedParam;

public class KeypairAlgorithmSearchbycondition extends PaginatedParam{
	
	private Long id;
	
	private String name;
	
	private String algorithmOid;
	
	private String algorithmName;
	
	private Integer keysize;
	
	private String notes;
	
	private Integer isValid;
	
	
	
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
