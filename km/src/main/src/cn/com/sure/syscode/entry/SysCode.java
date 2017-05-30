package cn.com.sure.syscode.entry;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

public class SysCode {

	@Id
	@Column(name="ID")
    private Long id;
	
	/**
	 * ��������
	 */
	@Column(name="para_code")
	private String paraCode;
    
	/**
	 * ����ֵ(��ʾֵ)
	 */
    @Column (name = "para_value")
    private String paraValue;
    
	/**
	 * ������� - ����km_sys_code_type��
	 */
    private SysCodeType paraType;
    
	/**
	 * �Ƿ���Ч
	 */
    @Column (name = "is_valid")
    private Integer isValid;
        
	/**
	 * ��ע
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
