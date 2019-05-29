package com.hinext.maxis7567.karjoo.tools;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    int a=0;
    TimerTask timerTask;
    Timer timer=new Timer(false);
    private Tick tick;

    public MyTimer(final Tick tick){
        this.tick = tick;
        timerTask= new TimerTask(){
            public void run() {
                a++;
                tick.OnTick(a);
            }

        };
    }
    public void run(long delay){
        timer.schedule(timerTask,1000,delay);
    }

}
