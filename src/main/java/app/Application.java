// src/main/java/app/Application.java
package app;

import cv.CV;
import hashtable.HashNode;
import hashtable.HashTable;
import utils.CVGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.*;

public class Application {
    private static final int NUM_CVS = 1000;
    private static final int NUM_RUNS = 100;
    private static final double DUPLICATE_RATE = 0.05; // 5% chance per CV

    public static void main(String[] args) {
        try (FileWriter logWriter = new FileWriter("experiment_metrics.csv")) {
            logWriter.write("Run,Total,InjectedDupes,Stored,DetectedDupes,TimeHash(ms),BaselineDupes,BaselineTime(ms),TP,FP,FN,TN,Memory(KB),Collisions,LoadFactor\n");

            for (int run = 1; run <= NUM_RUNS; run++) {
                HashTable<String, CV> cvStore = new HashTable<>(NUM_CVS);
                Set<String> baselineCVHashes = new HashSet<>();
                Map<String, Integer> hashCountMap = new HashMap<>();
                List<CV> generatedCVs = new ArrayList<>();
                Random rand = new Random();

                int tp = 0, fp = 0, fn = 0, tn = 0;
                int duplicatesHashing = 0;
                int duplicatesBaseline = 0;
                int duplicatesInjected = 0;

                long startHashing = System.nanoTime();

                for (int i = 0; i < NUM_CVS; i++) {
                    CV cv;
                    if (!generatedCVs.isEmpty() && rand.nextDouble() < DUPLICATE_RATE) {
                        cv = generatedCVs.get(rand.nextInt(generatedCVs.size()));
                        duplicatesInjected++;
                    } else {
                        cv = CVGenerator.generateRandomCV(i);
                    }
                    generatedCVs.add(cv);

                    String hash = cv.getHash();
                    hashCountMap.put(hash, hashCountMap.getOrDefault(hash, 0) + 1);

                    boolean wasAlreadyStored = cvStore.contains(hash);
                    if (wasAlreadyStored) {
                        duplicatesHashing++;
                        if (hashCountMap.get(hash) > 1) tp++; else fp++;
                    } else {
                        cvStore.put(hash, cv);
                        if (hashCountMap.get(hash) > 1) fn++; else tn++;
                    }
                }

                long endHashing = System.nanoTime();
                long hashingDuration = endHashing - startHashing;

                long startBaseline = System.nanoTime();
                for (CV cv : generatedCVs) {
                    if (baselineCVHashes.contains(cv.getHash())) {
                        duplicatesBaseline++;
                    } else {
                        baselineCVHashes.add(cv.getHash());
                    }
                }
                long endBaseline = System.nanoTime();
                long baselineDuration = endBaseline - startBaseline;

                // Estimate memory usage
                MemoryUsage heapUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                long usedMemoryKB = heapUsage.getUsed() / 1024;

                // Estimate collisions
                int collisions = 0;
                for (int i = 0; i < cvStore.getCapacity(); i++) {
                    HashNode<String, CV> node = cvStore.getBucket(i);
                    int chainLength = 0;
                    while (node != null) {
                        chainLength++;
                        node = node.next;
                    }
                    if (chainLength > 1) collisions += (chainLength - 1);
                }

                double loadFactor = cvStore.loadFactor();

                logWriter.write(run + "," + NUM_CVS + "," + duplicatesInjected + "," + cvStore.size() + ","
                        + duplicatesHashing + "," + (hashingDuration / 1_000_000) + ","
                        + duplicatesBaseline + "," + (baselineDuration / 1_000_000) + ","
                        + tp + "," + fp + "," + fn + "," + tn + ","
                        + usedMemoryKB + "," + collisions + "," + String.format("%.4f", loadFactor) + "\n");

                System.out.printf(" Run %2d | Injected: %3d | Duplicates Detected: %3d | Time: %4dms | TP: %3d | FP: %2d | FN: %2d | TN: %3d | Mem: %5dKB | Collisions: %3d | Load: %.4f\n",
                        run, duplicatesInjected, duplicatesHashing, hashingDuration / 1_000_000, tp, fp, fn, tn, usedMemoryKB, collisions, loadFactor);
            }

            System.out.println("\n Experiment completed. Metrics logged to experiment_metrics.csv");
        } catch (IOException e) {
            System.err.println(" Error writing metrics to file: " + e.getMessage());
        }
    }
}
