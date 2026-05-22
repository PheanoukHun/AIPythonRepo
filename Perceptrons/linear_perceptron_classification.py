import random

import matplotlib as mpl
import matplotlib.pyplot as plt

# Linear Function
def f(x):
    return x * 1.2 + 50;

# Check Desirability
def desired(x, y) -> bool:
    return f(x) < y

# Random State Seed Set for Reproducibility
random.seed(123134123415531)

# CONSTANTS
SIZE = 300
N = 500

# Setting The Graph
plt.xscale("linear")
plt.yscale("linear")

plt.xlim(0, SIZE)
plt.ylim(0, SIZE)

# Drawing the Linear Line on the Graph
line_x = [0,SIZE]
line_y = [f(0), f(SIZE)]
plt.plot(line_x, line_y, c="blue")

# Creating the Points
raw_x_vals = [random.randint(0, SIZE) for i in range(N)]
raw_y_vals = [random.randint(0, SIZE) for i in range(N)]
area = 3

# Filtering the Points Based on Desireability
non_desired_x = []
non_desired_y = []

desired_x = []
desired_y = []

for i in range(N):
    if desired(raw_x_vals[i], raw_y_vals[i]):
        desired_x.append(raw_x_vals[i])
        desired_y.append(raw_y_vals[i])
    else:
        non_desired_x.append(raw_x_vals[i])
        non_desired_y.append(raw_y_vals[i])

# Drawing the Points onto the Graph
plt.scatter(non_desired_x, non_desired_y, s=area, c="green", alpha=0.5)
plt.scatter(desired_x, desired_y, s=area, c="orange", alpha=0.5)

# Prints the Desired Answers
print("Desired Output:", "X\t | Y", sep="\n")
for i in range(len(desired_x)):
    print(desired_x[i], "|", desired_y[i])

# Showing the Final Result
plt.show()