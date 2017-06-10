/**
 * 
 */
package cn.com.sure.keypair.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Limin
 *
 */
public class KeypairStandby {
	
	/**
	 * 主键KID唯一的
	 */
	@Id
	private String id;
	
	
	/**
	 * 公钥 base64格式
	 */
	@Lob
	@Column(name = "pub_key",length=8192) 	
	private String pubKey;
	
	
	/**
	 * 私钥 base64格式
	 */
	@Lob
	@Column(name = "pri_key",length=8192) 	
	private String priKey;
	
	/**
	 * 密钥算法
	 */
	private KeypairAlgorithm keypairAlgorithm;
	
	/**
	 * 所属任务
	 */	
	private KpgTask KpgTask;

	/**
	 * 密钥生成时间
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


	public Date getGenTime() {
		return genTime;
	}


	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}


}
