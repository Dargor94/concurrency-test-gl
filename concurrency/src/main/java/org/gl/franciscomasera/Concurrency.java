package org.gl.franciscomasera;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;


@Slf4j
public class Concurrency {
    public static void main(String[] args) {

        log.info("Beginning program");
        int poolSize = 4;
        var executor = Executors.newScheduledThreadPool(poolSize);

        var competitors = new ArrayList<MyTask>();

        log.info("Loading task");
        for (int i = 0; i < 100; i++) {
            competitors.add(new MyTask(String.valueOf(i)));
        }


        log.info("Running task");
        var completionService = new ExecutorCompletionService<MyTask>(executor);
        competitors.
                parallelStream().
                forEach(completionService::submit);

        log.info("Preparing results...");
        try {
            completionService.take();
            var competitor = completionService.poll().get();
            log.info("Winner is task n° {}", competitor.getTaskName());
            executor.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}