package org.gl.franciscomasera.concurrency.controller;

import org.gl.franciscomasera.concurrency.domain.TaskA;
import org.gl.franciscomasera.concurrency.domain.TaskB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Qualifier("executorA")
    private Executor myExecutor;

    @PostMapping
    public ResponseEntity<String> taskRace() {
        var taskA = new TaskA();
        var taskB = new TaskB();

        myExecutor.execute(taskA);
        myExecutor.execute(taskB);

        return ResponseEntity.ok("Ok");
    }

}
