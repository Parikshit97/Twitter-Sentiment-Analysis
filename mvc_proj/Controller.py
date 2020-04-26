from flask import Flask, jsonify, request
import pickle
import numpy as np
from Handler import Handler
from Model import modelClass
app = Flask(__name__)


@app.route('/getprediction', methods=['POST'])
def func():
	raw_data = request.get_json(force=True)
	print("Raw_data:",raw_data)
	dfu = Handler.build_request(raw_data)
	pred_resp = modelClass.predict(dfu)
	pred_resp = Handler.build_response(raw_data, pred_resp)
	return pred_resp

if __name__ == '__main__':
    app.run()
