package cn.topcheer.halberd.app.api.framework.dto.dolphin;

import lombok.Data;

import java.util.Random;

@Data
public class TaskLocation {

    private long taskCode;
    private double x;
    private double y;


    public TaskLocation(long taskCode) {
        this.taskCode = taskCode;
        this.x = 300;
        this.y = 300;
    }


    // location : 1 -> 上 ，2 -> 中 ， 3 -> 下
    public static TaskLocation generateRandomTaskLocation(long taskCode,int number) {
        TaskLocation taskLocation = new TaskLocation(taskCode);
        Random random = new Random();
        taskLocation.setX(random.nextInt(1000));
        taskLocation.setY((number - 1) * 333 + random.nextInt(333));
        return taskLocation;

    }


}
