package cn.com.sure.keypair.entry;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import cn.com.keypair.alg.entry.KeypairAlgorithm;

/*��Կ�鵵*/
public class KeypairArchive {
	/**
	 * ����KIDΨһ��
	 */
	@Id
	@Column(name = "id",length=64) 	
	private String id;
	
	
	/**
	 * ��Կ base64��ʽ
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "pub_key",length=8192) 	
	private String pubKey;
	
	
	/**
	 * ˽Կ base64��ʽ
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "pri_key",length=8192) 	
	private String priKey;
	
	/**
	 * ��Կ�㷨
	 */
    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)   
    @JoinColumn(name = "kpg_algorithm_id")
	private KeypairAlgorithm keypairAlgorithm;
	
	/**
	 * ��������
	 */	
    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)   
    @JoinColumn(name = "km_kpg_task_id") 
	private KpgTask KpgTask;
    
	/**
	 * �鵵ʱ��
	 */
    @Column (name = "archive_time" )
    private Date archiveTime;
    
	/**
	 * ��Կ��ʼʹ��ʱ��
	 */
    @Column (name = "in_use_time" )
    private Date inUseTime;
    
    
	/**
	 * ��Կ����ʱ��
	 */
    @Column (name = "gen_time" )
    private Date genTime;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPubKey() {
		return pubKey;
	}


	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}


	public String getPriKey() {
		return priKey;
	}


	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}


	public KeypairAlgorithm getKeypairAlgorithm() {
		return keypairAlgorithm;
	}


	public void setKeypairAlgorithm(KeypairAlgorithm keypairAlgorithm) {
		this.keypairAlgorithm = keypairAlgorithm;
	}


	public KpgTask getKpgTask() {
		return KpgTask;
	}


	public void setKpgTask(KpgTask kpgTask) {
		KpgTask = kpgTask;
	}


	public Date getArchiveTime() {
		return archiveTime;
	}


	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}


	public Date getInUseTime() {
		return inUseTime;
	}


	public void setInUseTime(Date inUseTime) {
		this.inUseTime = inUseTime;
	}


	public Date getGenTime() {
		return genTime;
	}


	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}
    
    
    

}
