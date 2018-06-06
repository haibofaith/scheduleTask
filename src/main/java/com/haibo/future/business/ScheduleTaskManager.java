package com.haibo.future.business;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;


public class ScheduleTaskManager {

    public static void main(String[] args) throws SchedulerException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("taskname","haibo新建任务");
        map.put("taskInfo","从数据库获取一条数据的任务");
        new ScheduleTaskManager().task(map);
    }


    public void task(HashMap<String, Object> map) throws SchedulerException
    {

        //通过SchedulerFactory来获取一个调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //引进作业程序
        JobDetail jobDetail = JobBuilder.newJob(JobBase.class).withIdentity("jobDetail-s1","jobDetailGroup-s1").build();

        //通过jobDataMap传递数据到任务中
        for (Object key:map.keySet()){
            jobDetail.getJobDataMap().put(key.toString(),map.get(key));
        }

        //new一个触发器 设置作业执行间隔 设置作业执行次数
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("jobDetail-s1","jobDetailGroup-s1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();

        //作业和触发器设置到调度器中
        scheduler.scheduleJob(jobDetail, simpleTrigger);

        //启动调度器
        scheduler.start();
    }
}
