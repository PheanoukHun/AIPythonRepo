import math
import statistics

def getSTDev(arr):

    sum_of_squared_diffs = 0
    mean = getMean(arr)
    print(f"DEBUG: Mean: {mean}")

    for num in arr:
        sum_of_squared_diffs += math.pow(num - mean, 2)
    
    print(f"DEBUG: Sum of squared differences: {sum_of_squared_diffs}")
    
    # The standard deviation is the square root of the variance.
    # The variance is the sum of squared differences divided by N-1 (sample) or N (population).
    # Since the user compares to statistics.stdev (which is sample N-1 by default),
    # the correct divisor is N-1.
    
    
    variance = sum_of_squared_diffs / (len(arr) - 1)
    print(f"DEBUG: Variance: {variance}")
    stDev = math.sqrt(variance)

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