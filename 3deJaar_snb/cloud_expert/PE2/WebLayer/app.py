from flask import Flask, render_template, request, redirect, url_for, flash, make_response
import urllib.request, json
import requests
import os

app = Flask(__name__)


@app.route('/')
def index():
    todos={}
    api_url = os.environ.get('API_URL')

    try:
        url = f"http://{api_url}:4000"
        response = requests.get(url, timeout=60)
        todos = json.loads(response.content)
    except Exception as ex:
        exc = str(ex)

    return render_template('index.html', todos=todos)

if __name__ == "__main__":
    app.run()