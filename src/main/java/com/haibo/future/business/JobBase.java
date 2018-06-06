package com.haibo.future.business;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class JobBase implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = (String) dataMap.get("taskname");
        String age = (String) dataMap.get("taskInfo");
        System.out.println("In SimpleQuartzJob - executing its JOB at "
                + new Date() + " by " + name+":"+age);
    }
}
