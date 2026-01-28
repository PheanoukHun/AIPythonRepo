import random
import time

n = 1000
m = 1000
p = 500

arr1 = [[random.randint(1,10) for i in range(p)] for i in range(n)]
arr2 = [[random.randint(1,10) for i in range(m)] for i in range(p)]

arr3 = [[0 for i in range (n)] for i in range(m)]

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
