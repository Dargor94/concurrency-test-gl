package org.gl.franciscomasera.concurrency.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

@Getter
@RequiredArgsConstructor
public class MyTask implements Callable<MyTask> {

    private final int taskNumber;
    @Getter(AccessLevel.NONE)
    private final int delay;
    @Getter(AccessLevel.NONE)
    private int progress;

    @Override
    public MyTask call() throws InterruptedException {
        Thread.sleep(delay);
        while (progress < 100) {
            progress += (Math.random() * 15) + 1;
        }
        return this;
    }

}
