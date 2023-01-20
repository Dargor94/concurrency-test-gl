package org.gl.franciscomasera.concurrency.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Slf4j
public class MyTask implements Runnable {
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
    }
}
