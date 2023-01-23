package org.gl.franciscomasera.concurrency.controller;

import org.gl.franciscomasera.concurrency.domain.TaskA;
import org.gl.franciscomasera.concurrency.domain.TaskB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    @Qualifier("myExecutor")
    private Executor myExecutor;

    @PostMapping
    public void taskRace() {

        var lock1 = new ReentrantLock();
        var lock2 = new ReentrantLock();

        var myTask1 = new TaskA(lock1, lock2);
        var myTask2 = new TaskB(lock1, lock2);

        var t1 = new Thread(myTask1);
        var t2 = new Thread(myTask2);

        myExecutor.execute(t1);
        myExecutor.execute(t2);


    }

}
