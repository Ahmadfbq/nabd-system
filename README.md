# 💓 Nabd (نَبض) – Smart Health Monitoring System v1.0

> A comprehensive, cloud-powered health monitoring solution designed for real-time tracking and analysis of vital signs using a wearable device.

---

## 📜 Project Overview

**Nabd (نَبض)** is an intelligent health monitoring system that continuously tracks vital signs such as:

- 💗 Heart Rate  
- 🩸 Blood Pressure  
- 🧪 Blood Sugar (Glucose)  
- 🌡️ Body Temperature  
- 🌬️ Blood Oxygen Levels (SpO₂)  

It provides **real-time analysis**, detects **potential illnesses**, and can **alert emergency contacts** when dangerous patterns are detected.

---

## 🎯 Objective

To develop a scalable and intelligent health monitoring platform that:

- Receives continuous readings from wearable devices.
- Detects anomalies in vital signs.
- Predicts sleeping patterns.
- Sends emergency alerts (with user consent).
- Suggests improvements in health habits.

---

## ⚙️ System Architecture
![IoT Health System - Overall Architecture](https://github.com/user-attachments/assets/b5b2ab5d-f29f-412b-90f3-c28e306abd03)

The system is built using a **Microservices Architecture** with:

- 🧠 **Spring Boot** (Microservices backend)
- 📦 **Docker** (Containerization)
- ☁️ **Microsoft Azure** (Cloud hosting & scaling)
- 🌐 **NGINX** (API Gateway)
- 🎨 **Vue.js** (Frontend)

---

## 🧬 Microservices

| Service | Function |
|--------|----------|
| **User Service** | Account creation & login |
| **Health Data Service** | Receives and stores sensor data |
| **Analysis Service** | Detects illnesses, patterns |
| **Notification Service** | Sends emergency alerts & suggestions |

---

## 🩻 Monitored Metrics

| Measurement | Method | Upload Frequency |
|-------------|--------|------------------|
| Heart Rate | PPG sensor | Every 1s |
| Blood Oxygen (SpO₂) | Infrared PPG | Every 30s |
| Blood Pressure | PPG estimates | Every 60s |
| Blood Sugar | CGM sensor | Every 5 min |
| Body Temperature | Skin contact | Once daily (during sleep) |

---

## 📊 Detection Logic

| Metric | Normal Range | Danger Threshold |
|--------|--------------|------------------|
| Heart Rate | 60–100 bpm | < 60 or > 100 bpm |
| SpO₂ | 95–100% | < 95% (severe if < 90%) |
| Blood Pressure | <120/80 mmHg | ≥130/80 mmHg |
| Glucose | 70–99 mg/dL | ≥126 mg/dL (diabetes) |
| Temp. | 36.1–37.2°C | >38°C (emergency if >41°C) |

---

## 🔍 Illness Detection

| Illness | Criteria |
|---------|----------|
| Diabetes | Fasting glucose ≥126 mg/dL (multiple days) |
| Hypertension | BP ≥130/80 mmHg (repeatedly) |
| Fever | Temp >38°C sustained for 2+ hours or peaks |

---

## 🖼️ Frontend Preview

- ✅ **Welcome Page**
  ![image](https://github.com/user-attachments/assets/6ad65ede-8236-49ab-89fc-4d98abe8f6d9)

- 🔐 **Login & Registration**
![image](https://github.com/user-attachments/assets/5e2efa5f-84dd-4dd7-b02c-0c71efb95dbf)
![image](https://github.com/user-attachments/assets/03c45c43-2317-428b-858d-dd20a6ef0269)

- 🏠 **Home Dashboard**
  ![image](https://github.com/user-attachments/assets/234db481-7607-4c80-942d-7b2d680d0d2e)

- 👤 **User Profile**
  ![image](https://github.com/user-attachments/assets/9296300c-472c-471f-b38e-3855237404b5)
  ![image](https://github.com/user-attachments/assets/35cbf101-3d0a-4e77-a341-0bb0d1fa1f2b)
  ![image](https://github.com/user-attachments/assets/1a5bf6eb-8e67-42bf-bf91-69efe637435a)


---

## 🏁 Conclusion

Nabd (نَبض) serves as a pioneering platform that bridges wearable tech and cloud intelligence, empowering users with insights into their well-being and responding to emergencies with agility and accuracy.

---

> _"Your health, your pulse, your data — intelligently monitored."_  
