with open("./dataFiles/data.csv") as file:
    lines = file.readline()
    lineList = lines.split(",")
    for i in lineList:
        print(i.strip( "\" "), end = ",")