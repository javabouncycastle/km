/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface KpgTaskService {

	/**
	 * @return 
	 * 
	 */
	List<KpgTask> selectAll();

	/**
	 * @param kpgTask
	 * @return 
	 */
	int insert(KpgTask kpgTask)throws KmApplicationexception;

	/**
	 * @param kpgTask
	 * @return 
	 */
	int update(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void delete(Long id);

	/**
	 * @param id 
	 * @return 
	 * 
	 */
	KpgTask selectById(Long id);

    /**
     * 
     *  功能描述：查询所有需要执行的任务
     *  @param taskStatus
     *  @return
     */
    public List<KpgTask> findAllUnExecutedTask();

	/**
	 * @param codeIdTaskStatusWaitingForExecuting
	 * @return
	 */
	List<KpgTask> findByTaskStatus(Long codeId);

	/**
	 * @param taskId
	 * @param sliceSize
	 */
	void updateGeneratedKeyAmount(Long taskId, int sliceSize);

	/**
	 * @param kpgTask
	 * @return
	 */
	List<KpgTask> searchByCondition(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void start(Long id);

}
