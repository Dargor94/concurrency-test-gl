package org.gl.franciscomasera.concurrency.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j
@AllArgsConstructor
public class TaskB implements Runnable {

    private final ReentrantLock lock1;
    private final ReentrantLock lock2;

    @Override
    public void run() {
        try {

            lock2.lock();
            sleep(1000);
            log.info("Thread ".concat(Thread.currentThread().getName()).concat("acquired second lock"));

            lock1.lock();
            log.info("Thread ".concat(Thread.currentThread().getName()).concat("acquired first lock"));

            lock1.unlock();
            lock2.unlock();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
