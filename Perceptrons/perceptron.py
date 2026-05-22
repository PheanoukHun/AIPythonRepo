import random
import csv

class Perceptron:
    
    def __init__(self, input_count, learning_rate = 0.0001):
        self.lr = learning_rate
        self.get_random_weight(input_count)
    
    def get_random_weight(self, input_count):
        # Gets the Random Weight
        self.weights = [random.uniform(-1, 1) for i in range(input_count + 1)]

    def load_weights(self, file_path):
        with open(file_path) as file:
            reader = csv.reader(file)
            string_weights = next(reader)
            self.weights = [float(x) for x in string_weights]
                

    def activate(self, inputs):
        sum = 0
        
        for i in range(len(inputs)):
            sum += self.weights[i] * inputs[i]
        
        sum += self.weights[-1]
        
        if sum > 0:
            return 1
        return 0
    
    def train(self, inputs, desired):
        
        # Getting the Error Value
        guess = self.activate(inputs)
        error = desired - guess
    
        # Adjust Weights
        for i in range(len(inputs)):
            self.weights[i] += self.lr * error * inputs[i]
        
        # Adjust Bias Weight
        self.weights[-1] += error * self.lr