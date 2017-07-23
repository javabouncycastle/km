/**
 * 
 */
package cn.com.sure.keypair.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.kpgtask.entry.KpgTask;

/**
 * @author Limin
 *
 */
public class KeyPairStandby {
	
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
	private KeyPairAlgorithm keyPairAlgorithm;
	
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

	public KeyPairAlgorithm getKeyPairAlgorithm() {
		return keyPairAlgorithm;
	}


	public void setKeyPairAlgorithm(KeyPairAlgorithm keyPairAlgorithm) {
		this.keyPairAlgorithm = keyPairAlgorithm;
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
