package org.duang.task;
public class QueryExpireScale{

	//判断作业是否执行的旗标  
    private boolean isRunning = false;  
    //定义任务执行体  
    public void run()    
    {  
        if (!isRunning)  
        {  
            System.out.println("开始调度自动打卡");  
        }  
    }  

}
