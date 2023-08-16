package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


    @State(Scope.Benchmark)
    public class StreamBenchmark {
        private List<Integer> numeros;

        @Setup(Level.Trial)
        public void setUp() {
            numeros = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                numeros.add(random.nextInt());
            }
        }

        @Benchmark
        public void stream(Blackhole bh) {
            for (int i = 0; i < numeros.size(); i++)
            {
                bh.consume(numeros.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList()));
            }
        }

        @Benchmark
        public void streamParallel(Blackhole bh) {
            for (int i = 0; i < numeros.size(); i++) {
                bh.consume(numeros.parallelStream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList()));
            }
        }
    }
