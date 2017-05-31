package cn.com.sure.log.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class AuditOpLog {
	
	public AuditOpLog() {
	}
	
	public AuditOpLog(String id) {
		this.id = id;
	}
	/**
	 * ID
	 */
	@Id  @Column(name="id")             	private String id;
	
	@Column (name = "op_type") 				private long type;   //表示操作的类型。
	@Column (name = "op_action")        	private String action; //表示执行的操作是什么
	
	@Column (name = "op_action_ext1")  		private String actionExt1; //表示执行的操作扩展
	@Column (name = "op_action_ext2")  		private String actionExt2; //表示执行的操作扩展
	@Column (name = "op_action_ext3")  		private String actionExt3; //表示执行的操作扩展
	@Column (name = "op_action_ext4")  		private String actionExt4; //表示执行的操作扩展

	@Column (name = "op_message")           private String  message;   //本地化消息
	@Column (name = "op_timestamp")         private Date    timestamp; //执行上述操作的日期和时间。以 GMT 时间存储此值
	@Column (name = "op_ip")                private String  ip;        //对其执行操作的IP
	@Column (name = "op_operator")          private String  operator;  //对其执行操作的帐户
	@Column (name = "is_op_succ")			private Integer isOpSucc;  //表示已执行操作的结果
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionExt1() {
		return actionExt1;
	}
	public void setActionExt1(String actionExt1) {
		this.actionExt1 = actionExt1;
	}
	public String getActionExt2() {
		return actionExt2;
	}
	public void setActionExt2(String actionExt2) {
		this.actionExt2 = actionExt2;
	}
	public String getActionExt3() {
		return actionExt3;
	}
	public void setActionExt3(String actionExt3) {
		this.actionExt3 = actionExt3;
	}
	public String getActionExt4() {
		return actionExt4;
	}
	public void setActionExt4(String actionExt4) {
		this.actionExt4 = actionExt4;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getIsOpSucc() {
		return isOpSucc;
	}
	public void setIsOpSucc(Integer isOpSucc) {
		this.isOpSucc = isOpSucc;
	}
	
	
	

}
