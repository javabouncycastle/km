package cn.com.sure.syscode.entry;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

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
	 * 参数类别 - 引用km_sys_code_type表
	 */
    private SysCodeType paraType;
    
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
    

	public SysCodeType getParaType() {
		return paraType;
	}

	public void setParaType(SysCodeType paraType) {
		this.paraType = paraType;
	}

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
