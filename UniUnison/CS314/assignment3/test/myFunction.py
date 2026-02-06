import math
import random
import statistics

def getSTDev(arr):

    sum_of_squared_diffs = 0
    mean = getMean(arr)

    for num in arr:
        sum_of_squared_diffs += math.pow(num - mean, 2)
    
    variance = sum_of_squared_diffs / (len(arr) - 1)
    stDev = math.sqrt(variance)

    return stDev

def getMean(arr):
    sum = 0

    for num in arr:
        sum += num

    print(sum)

    return sum / len(arr)

randNums = [i for i in range(1, 14)]
print("\nRandom Array:", *randNums)
print

print("\nMy Function (Mean):", getMean(randNums))
print("Python's Function (Mean):", statistics.mean(randNums))

print("\nMy Funciton (Standard Deviation):", getSTDev(randNums))
print("Python's Funciton (Standard Deviation):", statistics.stdev(randNums), "\n")