package org.gl.franciscomasera.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.gl.franciscomasera.concurrency.domain.MyTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @PostMapping
    public ResponseEntity<String> taskRace() throws InterruptedException {

        var myTask = new MyTask();

        var firstTask = new Thread(myTask);
        var secondTask = new Thread(myTask);
        log.info("Init");
        firstTask.start();
        secondTask.start();
        firstTask.join();
        secondTask.join();

        return ResponseEntity.ok("Ok");
    }

}
