# Readers-And-Writers
Inter-Process Communication

Readers and Writer is a inter-process communication approach that consists on two kind of process accessing same resources: Readers and Writers. The resouces can be accessed by one or more Readers and modified by only one Writer. In case, this program implements this approach and another one in which all entities, this is, Readers and Writers, have exclusive access to the resource, where the resource is a static vector of strings for both. Inicially, the program measure the average execution time to acess the resource 50 times for each 100 entities in all proportion of (Reader)x(Writer) (0x100, 1x99, 2x98, ..., 100x0) and for both approach. Then, this is repeated 100 times and each average execution time and the difference of time execution for those approaches is putted on CSV files.

A simple script in R is on source too inside of folder 'ResultAnalysis'. This script take the CSV files and plot graphics to analyse times of executions and difference between the two approach.
