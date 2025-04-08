from flask import Flask, send_from_directory

app = Flask(__name__)

# Serve the alert JSON
@app.route('/alert')
def get_alert():
    return send_from_directory('.', 'alerts.json')

# Serve the UI from index.html
@app.route('/')
def serve_ui():
    return send_from_directory('.', 'index.html')

if __name__ == '__main__':
    print("Starting Fire Server...")
    app.run(port=5000)
