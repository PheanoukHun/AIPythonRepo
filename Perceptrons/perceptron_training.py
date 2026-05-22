import random
from perceptron import Perceptron
import matplotlib.pyplot as plt

# Setting the Random Seed
# random.seed(898430928)

# Math Function
def f(x) -> float:
    return (x * 1.2) + 50

# Function for Checking the Desireability of an Point
def check_desireable(x, y) -> bool:
    return f(x) < y

# X and Y Max
X_MAX = 500
Y_MAX = 500

# Changing the Range of the plot
plt.xlim(0, X_MAX)
plt.ylim(0, Y_MAX)

# Creating the Line
line_x = [0, X_MAX]
line_y = [f(0), f(X_MAX)]

# Setting up the Variables for the Perceptrons
points = 1000

# Creating the Random Points to Train On
x_points = [random.randint(0, X_MAX) for i in range(points)]
y_points = [random.randint(0, Y_MAX) for i in range(points)]

# Checking Each Point for Desireability
labels = []
for i in range(points):
    if check_desireable(x_points[i], y_points[i]):
        labels.append(1)
    else:
        labels.append(0)
        
# Creating the Perceptron
neuron = Perceptron(2, 0.0001)

# Training the Perceptron
for i in range(10000):
    for j in range(points):
        neuron.train([x_points[j], y_points[j]], labels[j])

# Drawing the Line onto the Screen
plt.plot(line_x, line_y, c = "black")

# Drawing the Points on the Screen 
correct = 0
for i in range(points):
    
    if (neuron.activate([x_points[i], y_points[i]]) == labels[i]):
        color = "green"
        correct += 1
    else:
        color = "red"
    
    plt.scatter(x_points[i], y_points[i], c = color, s = 7)

# Update the X-axis with the accuracy of the Perceptron
plt.xlabel("Accuracy: " + str(round((correct/points) * 100, 2)) + "%")

# Show the Graph
plt.show()

# Export the Weight of the Perceptron
with open("AI_W3/weights/weights.csv", "w") as file:
    for i in range(len(neuron.weights) - 1):
        file.write(str(neuron.weights[i]) + ",")
    file.write(str(neuron.weights[-1]))

print(*neuron.weights, sep="\n")