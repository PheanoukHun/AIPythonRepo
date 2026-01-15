totalSpaces = 22

for i in range(6):
    print("\\\\" * i, end="")
    print("!" * (totalSpaces - (i * 4)), end="")
    print("//" * i)
