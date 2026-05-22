import random
import csv
from perceptron import Perceptron
import matplotlib.pyplot as plt

def f(x):
    return (x * 1.2) + 50

preloaded_weights = []

# Get Trained Weight Strings
neuron = Perceptron(2, 0.0001)
neuron.load_weights("AI_W3/weights/weights.csv")

# Test Against Unkown Data
points = 500
SIZE = 500

colors = []
x_values = []
y_values = []

for i in range(points):
    x = random.randint(0, SIZE)
    y = random.randint(0, SIZE)
    
    x_values.append(x)
    y_values.append(y)
        
    if neuron.activate([x, y]) == 0:
        colors.append("red")
    else:
        colors.append("green")

plt.xlim(0, SIZE)
plt.ylim(0, SIZE)

plt.scatter(x_values, y_values, c=colors, s = 10)
plt.plot([0, SIZE], [f(0), f(SIZE)], c="black")

plt.show()