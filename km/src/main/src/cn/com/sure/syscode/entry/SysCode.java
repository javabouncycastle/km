package cn.com.sure.syscode.entry;

import javax.persistence.Column;
import javax.persistence.Id;

public class SysCode {

	@Id
	@Column(name="ID")
    private Long id;
	
	/**
	 * 参数名称
	 */
	@Column(name="para_code")
	private String paraCode;
    
	/**
	 * 参数值(显示值)
	 */
    @Column (name = "para_value")
    private String paraValue;
	/**
	 * 是否有效
	 */
    @Column (name = "is_valid")
    private Integer isValid;
        
	/**
	 * 备注
	 */
    @Column (name ="notes")
    private String notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
}
