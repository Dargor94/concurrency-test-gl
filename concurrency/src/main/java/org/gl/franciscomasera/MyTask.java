package org.gl.franciscomasera;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
@Getter
@RequiredArgsConstructor
public class MyTask implements Callable<MyTask> {

    private final String taskName;
    private final int delay;
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
