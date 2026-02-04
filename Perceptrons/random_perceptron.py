import random

sum = 0
nums_of_connections = random.randint(5, 100)
threshold = 0.3 * nums_of_connections

print("\nRANDOM PERCEPTRON TRAINING\n")

sum_input = 0
while(sum_input <= int(0.4 * threshold) + 1):
    sum_input = 0
    inputs = [random.randint(0, 1) for i in range(nums_of_connections)]
    for i in range(len(inputs)):
        sum_input += inputs[i]

print("Inputs:", inputs)

while (sum < threshold):
    sum = 0
    weights = [round(random.uniform(0.25, 1), 2) for i in range(nums_of_connections)]
    
    print("\nTrial Weights:", weights)
    
    for i in range(len(inputs)):
        sum += inputs[i] * weights[i]
    
print("\nPERCEPTRON TRAINED")
print("Weights:", weights, "\n")