arr = []
size = 5

for i in range(size):
    arr.append([])
    for j in range(i):
        arr[i].append(0)
    for j in range(size - i):
        arr[i].append(1)

print(*arr, sep="\n")