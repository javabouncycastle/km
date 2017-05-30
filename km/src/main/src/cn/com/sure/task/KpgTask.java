package cn.com.sure.task;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.sure.keypair.entry.KeypairAlgorithm;
import cn.com.sure.syscode.entry.SysCode;

@Entity
@Table(name = "kpg_task")
public class KpgTask {
	/**
	 * ����
	 */
	@Id
	@Column(name = "id",length=32) 	
	private Long id;
	
	/**
	 * ����
	 */
	@Column(name = "name",length=128) 	
	private String name;

	/**
	 * ������Կ����Կ�㷨+����
	 */
	@Column(name = "kpg_algorithm_id") 
	private KeypairAlgorithm kpgAlgorithm;
    
	/**
	 * ������Կ����Կ����
	 */
	@Column(name = "kpg_key_amount",precision=12, scale=0) 	
	private Integer kpgKeyAmount;
	
    /**
     * ����״̬  1 standby׼��״̬��executing����ִ�У�finished������ɣ�exception�쳣���� ��interrupted�˹��ж�
     */
    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)   
    @JoinColumn(name = "task_status") 
	private SysCode taskStatus;
    
    /**
	 * �趨����ʼʱ��
	 */
    @Column (name = "task_start_time" )
    private Date taskStartTime;
    

	/**
	 * ����˵��
	 */
	@Column(name = "task_notes",length=256) 	
	private String taskNotes;
	
	/**
	 * ����ִ�п�ʼʱ��
	 */
    @Column (name = "exe_task_start_time" )
    private Date exeTaskStartTime;
    
    
	/**
	 * ����ִ�н���ʱ��
	 */
    @Column (name = "exe_task_end_time" )
    private Date exeTaskEndTime;
    
	/**
	 * ��Կ�Ѿ��洢�����¼����
	 */
    @Column (name = "dbcommit_bufsize",precision=12, scale=0)
	private Integer dbCommitBufsize; 
	
	
	
	/**
	 * ��Կ�Ѿ��洢����
	 */
    @Column (name = "generated_key_amount",precision=12, scale=0)
	private Integer generatedKeyAmount; 
	
	/**
	 * ����ִ�н��˵��
	 */
	@Column(name = "task_exe_result",length=256) 	
	private String taskExeResult;

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

	public KeypairAlgorithm getKpgAlgorithm() {
		return kpgAlgorithm;
	}

	public void setKpgAlgorithm(KeypairAlgorithm kpgAlgorithm) {
		this.kpgAlgorithm = kpgAlgorithm;
	}

	public Integer getKpgKeyAmount() {
		return kpgKeyAmount;
	}

	public void setKpgKeyAmount(Integer kpgKeyAmount) {
		this.kpgKeyAmount = kpgKeyAmount;
	}

	public SysCode getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(SysCode taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskNotes(String taskNotes) {
		this.taskNotes = taskNotes;
	}

	public Date getExeTaskStartTime() {
		return exeTaskStartTime;
	}

	public void setExeTaskStartTime(Date exeTaskStartTime) {
		this.exeTaskStartTime = exeTaskStartTime;
	}

	public Date getExeTaskEndTime() {
		return exeTaskEndTime;
	}

	public void setExeTaskEndTime(Date exeTaskEndTime) {
		this.exeTaskEndTime = exeTaskEndTime;
	}

	public Integer getDbCommitBufsize() {
		return dbCommitBufsize;
	}

	public void setDbCommitBufsize(Integer dbCommitBufsize) {
		this.dbCommitBufsize = dbCommitBufsize;
	}

	public Integer getGeneratedKeyAmount() {
		return generatedKeyAmount;
	}

	public void setGeneratedKeyAmount(Integer generatedKeyAmount) {
		this.generatedKeyAmount = generatedKeyAmount;
	}

	public String getTaskExeResult() {
		return taskExeResult;
	}

	public void setTaskExeResult(String taskExeResult) {
		this.taskExeResult = taskExeResult;
	}
	
	
	
}
