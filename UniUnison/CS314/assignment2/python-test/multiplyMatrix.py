arr1 = [[2, 4], [5, 2], [1, 5], [3, 6]]
arr2 = [[3, 4, 1], [1, 8, 4]]

arr3 = [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]

for i in range(len(arr1)):
    for j in range(len(arr2[0])):
        sum = 0
        for k in range(len(arr1[0])):
            sum += arr1[i][k] * arr2[k][j]
        arr3[i][j] = sum

print(*arr3, sep="\n")
