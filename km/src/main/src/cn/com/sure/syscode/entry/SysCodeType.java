/**
 * 
 */
package cn.com.sure.syscode.entry;

import javax.persistence.Id;

/**
 * @author Limin
 *  ϵͳ����������� - ��ʶ��� �����Ա�ְ�Ƶ�
 *  
 */
public class SysCodeType {
	
	@Id
    private Long id;
   
	/**
	 * ����ֵ(��ʾֵ)
	 */
    private String paraType;
    
    
	/**
	 * ��ע
	 */
    private String notes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getParaType() {
		return paraType;
	}


	public void setParaType(String paraType) {
		this.paraType = paraType;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

}
