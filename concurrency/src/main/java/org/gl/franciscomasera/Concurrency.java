package org.gl.franciscomasera;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;


@Slf4j
public class Concurrency {
    public static void main(String[] args) {

        log.info("Beginning program");
        final int poolSize = 10;
        final var executor = Executors.newFixedThreadPool(poolSize);
        final var completionService = new ExecutorCompletionService<MyTask>(executor);

        log.info("Loading tasks");
        for (int i = 1; i < poolSize + 1; i++) {
            final var task = new MyTask(String.valueOf(i));
            completionService.submit(task);
        }

        try {
            log.info("Preparing results...");
            var task = completionService.take().get();
            log.info("Winner is task n° {}", task.getTaskName());
            executor.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}