from flask import Flask, request, jsonify
import json
from Model import modelClass
import numpy as np
import pandas as pd

class Handler:

    @staticmethod
    def build_request(raw):
        dfu = []
        for hid, fi in raw.items():
            lt = [fi['f1'], fi['f2'], fi['f3'], fi['f4'], fi['f5'], fi['f6'], fi['f7'], fi['f8'], fi['f9'], fi['f10'], fi['f11'], fi['f12'], fi['f13'], fi['f14'], fi['f15'], fi['f16'], fi['f17'], fi['f18']]
            dfu = dfu + [lt]
            print(dfu)
        return dfu


    @staticmethod
    def build_response(raw, pred_resp):
        json_obj = {}
        i=0
        for hid, fi in raw.items():
            # for i in range (0, len(raw)):
            json_obj[hid] = pred_resp[i]
            i = i + 1
        return json_obj