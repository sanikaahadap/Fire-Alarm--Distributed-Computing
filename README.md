# 🔥 Fire Alarm System — Distributed Computing Project

This is a distributed fire alarm simulation system built using **Java RMI** and a **Flask-based web interface**, simulating real-time sensor alerts and triggering visual + sound alarms.

---

## 📌 Features

- 🔁 Simulates 70 sensors (2 per room) from a CSV file
- 🚨 Triggers alerts when both smoke & CO levels > 8
- ⏱️ Prevents repeated alerts within 1 minute for same room
- 🌐 Displays active alerts in a **grid UI** (floor × room)
- 🎵 Plays an alarm sound and blinks sensor blocks on fire
- 💾 Stores alert data in a JSON file for the web interface to fetch

---

## 🧩 Tech Stack

- Java RMI (Client–Server Communication)
- Python Flask (JSON API Server)
- HTML/CSS/JS (Frontend UI)
- CSV for Sensor Data
- JSON for real-time alert passing

---
## 🛠️ How to Run

Follow the steps below to run the entire system end-to-end:

---

### ✅ 1. Compile Java Files

```bash
javac *.java
```

✅ 2. Start the RMI Server
```bash
java FireAlarmServer
```

You should see:

```arduino
✅ Fire Alarm Service running on port 2000...
```

✅ 3. Start the Flask Server
In a new terminal, run the Python Flask server:

```bash
python FireServer.py
```

You should see something like:

```nginx
Running on http://127.0.0.1:5000/
```


✅ 4. Run the Sensor Client
In a third terminal, run:

```bash
java SensorClient
```

This will:

Simulate sensor data from fire_alarm_sensors_70.csv

Trigger alerts if both smoke & CO levels > 8

Log alert data to alerts.json

✅ 5. View the Web Interface
Open the UI by directly opening index.html in a browser, or go to:

```bash
http://localhost:5000/index.html
```

When an alert is triggered:

The corresponding sensor block in the grid blinks red

An alarm sound (alert.mp3) plays

📁 Required Files
Ensure these files are in the same directory:

alerts.json — auto-created by SensorClient

index.html — web UI

alert.mp3 — alarm sound

FireServer.py — Flask server script

Java files (*.java) — compiled and executed

🧹 Notes
Recompile Java files if you make any changes: javac *.java

Use Ctrl + C in any terminal to stop servers

Ensure Python, Java, and Flask are installed properly

