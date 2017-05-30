package cn.com.sure.common;

public class KmConstants {
	
	public static final int YES_OR_NO_OPTION_YES = 1;//ͣ������-����
	public static final int YES_OR_NO_OPTION_NO = 0;//ͣ������-ͣ��
	
	public static final long OPERATION_TYPE_INSERT=11;//����
	public static final long OPERATION_TYPE_DELETE=12;//ɾ��
	public static final long OPERATION_TYPE_UPDATE=13;//����
	public static final long OPERATION_TYPE_SELECT=14;//��ѯ
	
	public static final int SUCCESS_OR_FAILD_OPTION_SUCCESS=1;//�����ɹ�
	public static final int SUCCESS_OR_FAILD_OPTION_FAILD=0;//����ʧ��	
	
	// ����״̬
	public static final long TYPE_ID_TASK_STATUS = 2L;    // ����״̬TYPE
	
	public static final long CODE_ID_TASK_STATUS_NOT_STARTED = 20L;        //ִ��״̬ - ��δ��ʼ
	public static final long CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING = 21L;   //ִ��״̬ - ����������

	
	public static final long CODE_ID_TASK_STATUS_EXECUTING   = 22L;       //ִ��״̬ - ����ִ��
	public static final long CODE_ID_TASK_STATUS_MANUAL_INTERRUPTED = 23L;//ִ��״̬ - �˹��ж�Manual interrupt
	public static final long CODE_ID_TASK_STATUS_FINISHED = 24L;          //ִ��״̬ - ����
	public static final long CODE_ID_TASK_STATUS_EXCEPTION = 25L;         //ִ��״̬ - �쳣����

	public static final long CODE_ID_TASK_STATUS_MANUAL_PAUSED = 26L;      //ִ��״̬ - �˹���ͣ
	public static final long CODE_ID_TASK_STATUS_MANUAL_RESUMED = 27L;     //ִ��״̬ - �˹�����

}
