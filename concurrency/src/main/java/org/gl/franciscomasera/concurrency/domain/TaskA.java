package org.gl.franciscomasera.concurrency.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j
@AllArgsConstructor
public class TaskA implements Runnable {

    private final ReentrantLock lock1;
    private final ReentrantLock lock2;

    @Override
    public void run() {
        try {

            lock1.lock();
            sleep(1000);
            log.info("Thread ".concat(Thread.currentThread().getName()).concat(" acquired first lock"));

            lock2.lock();
            log.info("Thread ".concat(Thread.currentThread().getName()).concat(" acquired second lock"));

            lock2.unlock();
            lock1.unlock();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
