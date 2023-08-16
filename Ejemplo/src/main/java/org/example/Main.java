package org.example;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StreamBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();

       //ejercicio 3

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> generarRandom());
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> generarRandom());
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> generarRandom());
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> generarRandom());

        CompletableFuture<Integer> allFutures = CompletableFuture.allOf(future1, future2, future3, future4).thenApplyAsync(v -> {
                    return future1.join() + future2.join() + future3.join() + future4.join();
                });

        int sum = allFutures.join();
        System.out.println("La suma es: " + sum);

        // ejercicio 4
        CompletableFuture<Integer> futureMasRapido = CompletableFuture.anyOf(future1, future2, future3, future4)
                .thenApplyAsync(result -> (Integer) result);

        int valor = futureMasRapido.join();
        System.out.println("Valor de la tarea más rápida: " + valor);
    }

    private static int generarRandom() {
        try
        {
           int num= ThreadLocalRandom.current().nextInt(100, 501);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5001));
            return num;
        }
        catch (InterruptedException e)
        {
            throw  new RuntimeException(e);
        }

    }
}
