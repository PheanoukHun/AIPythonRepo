import random
import time

arr1 = [[random.randint(1,10) for i in range(500)] for i in range(1000)]
arr2 = [[random.randint(1,10) for i in range(1000)] for i in range(500)]

arr3 = [[0 for i in range (750)] for i in range(1000)]

start = time.time()
for i in range(len(arr1)):
    for j in range(len(arr2[0])):
        sum = 0
        for k in range(len(arr1[0])):
            sum += arr1[i][k] * arr2[k][j]
        arr3[i][j] = sum
    print(i, end=", ")
end = time.time()

print(f"\nRuntime: {end - start} seconds")
