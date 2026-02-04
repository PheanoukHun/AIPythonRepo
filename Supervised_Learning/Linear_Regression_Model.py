class Trainer:
    
    # Initializing the Trainer Objects with the Required Values
    def __init__(self, x_list, y_list):
        self.x_list = x_list
        self.y_list = y_list
        self.points = len(self.x_list)
        self.lr = 0.00001
        self.weight = 0
        self.bias = 1
        self.cost = 10000
    
    # Calculates the Mean Squared Error Value
    def calculate_MSE(self):
        total = 0
        for i in range(self.points):
            total += (self.y_list[i] - (self.weight * self.x_list[i] + self.bias)) ** 2
        return total / self.points
    
    # Training the Model to Become More Accurate
    def train(self, iterations):
        for i in range(iterations):
            self.update_weights()
        self.cost = self.calculate_MSE()
    
    # Updating the Weights to make the Model More Accurate
    def update_weights(self):
        wx = None
        w_deriv = 0
        b_deriv = 0
        
        for i in range(self.points):
            wx = self.y_list[i] - (self.weight * self.x_list[i] + self.weight)
            w_deriv += -2 * wx * self.x_list[i]
            b_deriv += -2 * wx
        
        self.weight -= (w_deriv / self.points) * self.lr
        self.bias -= (b_deriv / self.points) * self.lr
    
    def predict(self, x):
        pass

def train_model(inputs, expected_out) -> Trainer:
    model = Trainer(inputs, expected_out)
    
    while model.cost > 1:
        model.train()
    
    return model

if __name__ == "__main__":
    
    def f(x):
        return 5 * x + 10
    
    model = train_model(
        [i for i in range(50)],
        [f(i) for i in range(50)]
    )
    
    print(model.predict(100))