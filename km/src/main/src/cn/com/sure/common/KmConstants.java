package cn.com.sure.common;


public class KmConstants {
	
	public static final int YES_OR_NO_OPTION_YES = 1;//停用启用-启用
	public static final int YES_OR_NO_OPTION_NO = 0;//停用启用-停用
	
	public static final long OPERATION_TYPE_INSERT=11;//增加
	public static final long OPERATION_TYPE_DELETE=12;//删除
	public static final long OPERATION_TYPE_UPDATE=13;//更新
	public static final long OPERATION_TYPE_SELECT=14;//查询
	
	public static final int SUCCESS_OR_FAILD_OPTION_SUCCESS=1;//操作成功
	public static final int SUCCESS_OR_FAILD_OPTION_FAILD=0;//操作失败	
	
	//Session存放的管理员信息
	public static final String SESSION_ADMIN_ID       = "OLTAX_ADMIN_ID";
	public static final String SESSION_ADMIN_NAME     = "OLTAX_ADMIN_NAME";
	public static final String SESSION_ADMIN_REF      = "OLTAX_ADMIN_REF";
	public static final String SESSION_ADMIN_TERM_ID  = "OLTAX_ADMIN_TERM_ID";
	public static final String SESSION_ADMIN_DN       = "OLTAX_ADMIN_DN";
	
	// 任务状态
	public static final String TYPE_ID_TASK_STATUS = "status";    // 任务状态TYPE
	
	//密钥最小数量
	public static final String KEY_NUM_MIN="keyNumMin";
	
	//密钥生成数量
	public static final String KEY_NUM_EXE="keyNumExe";
	
	public static final long CODE_ID_TASK_STATUS_NOT_STARTED = 20;        //执行状态 - 尚未开始
	public static final long CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING = 21;   //执行状态 - 任务已启动
	
	public static final long CODE_ID_TASK_STATUS_EXECUTING   = 22;       //执行状态 - 正在执行
	public static final long CODE_ID_TASK_STATUS_MANUAL_INTERRUPTED = 23;//执行状态 - 人工中断Manual interrupt
	public static final long CODE_ID_TASK_STATUS_FINISHED = 24;          //执行状态 - 正常结束
	public static final long CODE_ID_TASK_STATUS_EXCEPTION = 25;         //执行状态 - 异常结束

	public static final long CODE_ID_TASK_STATUS_MANUAL_PAUSED = 26;      //执行状态 - 人工暂停
	public static final long CODE_ID_TASK_STATUS_MANUAL_RESUMED = 27;     //执行状态 - 人工继续
	

}
