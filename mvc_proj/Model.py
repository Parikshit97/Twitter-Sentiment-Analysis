import json
import numpy as np
import pickle
from flask import Flask, jsonify

class modelClass:

    @staticmethod
    def predict(dfu):
        model = pickle.load(open('/Users/parikshitnarang/Desktop/ahemdabad.pickle','rb'))
        prediction = model.predict(dfu)
        print(prediction)
        return prediction