import math
import statistics

def getSTDev(arr):

    stDev = 0
    mean = getMean(arr)

    for num in arr:
        stDev = math.pow(num - mean, 2)
    
    stDev /= len(arr)
    stDev = math.sqrt(stDev)

    return stDev

def getMean(arr):
    sum = 0

    for num in arr:
        sum += num

    return sum / len(arr)

#print(getMean([1, 2, 3, 4, 5, 6, 7, 8]))
#print(statistics.mean([1, 2, 3, 4, 5, 6, 7, 8]))
print(getSTDev([1, 2, 3, 4, 5, 6, 7, 8]))
print(statistics.stdev([1, 2, 3, 4, 5, 6, 7, 8]))