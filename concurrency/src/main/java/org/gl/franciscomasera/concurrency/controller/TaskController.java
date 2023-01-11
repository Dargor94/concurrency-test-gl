package org.gl.franciscomasera.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.gl.franciscomasera.concurrency.domain.MyTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping("/race/{poolSize}")
    public ResponseEntity<String> taskRace(@PathVariable int poolSize) {

        log.info("Beginning program");
        final var executor = Executors.newFixedThreadPool(poolSize);
        final var completionService = new ExecutorCompletionService<MyTask>(executor);

        log.info("Loading tasks");
        for (int i = 1; i < poolSize + 1; i++) {
            final int delay = (int) ((Math.random() * (poolSize - 1) + 1));
            final var task = new MyTask(i, delay);
            completionService.submit(task);
        }

        MyTask task;
        try {
            log.info("Preparing results...");
            task = completionService.take().get();
            log.info("Winner is task n° {}", task.getTaskNumber());
            executor.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        var msg = String.format("Winner is task n° %s", task.getTaskNumber());
        return ResponseEntity.ok(msg);
    }

}
