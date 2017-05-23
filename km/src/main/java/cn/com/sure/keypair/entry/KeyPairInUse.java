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

import cn.com.sure.keypair.alg.entry.KeypairAlgorithm;

@Entity
@Table(name = "km_keypair_in_use")
public class KeyPairInUse {
	
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
	private KpgTask kpgTask;
    

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

}
