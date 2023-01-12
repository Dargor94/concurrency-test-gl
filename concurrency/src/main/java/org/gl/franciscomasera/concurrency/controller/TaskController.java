package org.gl.franciscomasera.concurrency.controller;

import org.gl.franciscomasera.concurrency.domain.MyTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping("/race/{poolSize}")
    public ResponseEntity<String> taskRace(@PathVariable int poolSize) {

        System.out.println("Beginning program");
        final ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        final ExecutorCompletionService<MyTask> completionService = new ExecutorCompletionService<>(executor);

        System.out.println("Loading tasks");
        for (int i = 1; i < poolSize + 1; i++) {
            final int delay = (int) ((Math.random() * (poolSize - 1) + 1));
            final MyTask task = new MyTask(i, delay);
            completionService.submit(task);
        }

        MyTask task;
        try {
            System.out.println("Preparing results...");
            task = completionService.take().get();
            System.out.printf("Winner is task n° %d", task.getTaskNumber());
            executor.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String msg = String.format("Winner is task n° %s", task.getTaskNumber());
        return ResponseEntity.ok(msg);
    }

}
