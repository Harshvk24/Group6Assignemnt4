# CV Deduplication Using Hash Tables

**Course:** COMP47500 – Advanced Algorithms  
**Group:** 6

## Project Description

This project simulates a CV deduplication system for recruitment platforms, where duplicate resumes may be submitted either accidentally or intentionally. The objective is to detect such duplicates efficiently using hashing techniques and compare the results with a baseline approach using Java's built-in HashSet.

We implemented a custom HashTable in Java using separate chaining to handle collisions. Each CV is hashed using the MD5 algorithm to ensure consistent identification. The experiment is run over 100 iterations, and key performance metrics are recorded.

## Contents

- `Application.java` – Main runner that performs the deduplication experiment
- `CV.java` – Represents a CV object and generates an MD5-based hash
- `CVGenerator.java` – Generates random CVs and injects duplicates
- `HashTable.java` – Custom hash table implementation
- `HashNode.java` – Node class used for chaining in the hash table
- `experiment_metrics.csv` – CSV file logging all experiment metrics
- `test/` – JUnit test files for each core class

## Experiment Details

- 1000 CVs per run
- 100 total runs
- 3%–6% random duplicate injection
- Metrics logged:
  - Number of duplicates injected and detected
  - Precision, recall, and classification counts (TP, FP, FN, TN)
  - Time taken (custom HashTable vs HashSet)
  - Memory usage in KB
  - Collision count
  - Load factor of the hash table

## Summary

This project demonstrates that a well-designed hash table can detect duplicate CVs with perfect accuracy while providing efficient performance. The implementation supports runtime metric tracking, scalability testing, and accurate baseline comparison.

All components are modular, unit tested, and documented to support reproducibility and extensibility.
