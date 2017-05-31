package cn.com.sure.keypair.entry;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "km_keypair_in_use")
public class KeyPairInUse {
	
	/**
	 * 主键KID唯一的
	 */
	@Id
	@Column(name = "id",length=64) 	
	private String id;
	
	
	/**
	 * 公钥 base64格式
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "pub_key",length=8192) 	
	private String pubKey;
	
	
	/**
	 * 私钥 base64格式
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "pri_key",length=8192) 	
	private String priKey;
	
	/**
	 * 密钥算法
	 */
    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)   
    @JoinColumn(name = "kpg_algorithm_id")
	private KeypairAlgorithm keypairAlgorithm;
	
	/**
	 * 所属任务
	 */	
    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)   
    @JoinColumn(name = "km_kpg_task_id") 
	private KpgTask kpgTask;
    

	/**
	 * 密钥开始使用时间
	 */
    @Column (name = "in_use_time" )
    private Date inUseTime;
    
    
	/**
	 * 密钥生成时间
	 */
    @Column (name = "gen_time" )
    private Date genTime;

}
