# 🩺 Nabd (نَبض) - Revolutionary Health Monitoring System

<div align="center">

<img src="https://img.shields.io/badge/Nabd-1.0-red?style=for-the-badge&logo=heart&logoColor=white" alt="Nabd Logo" height="40" />

<br/><br/>

<a href="LICENSE">
   <img src="https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge" alt="License" />
</a>
<a href="https://laravel.com">
   <img src="https://img.shields.io/badge/Laravel-12.x-ff2d20?style=for-the-badge&logo=laravel&logoColor=white" alt="Laravel" />
</a>
<a href="https://reactjs.org">
   <img src="https://img.shields.io/badge/React-19.x-61dafb?style=for-the-badge&logo=react&logoColor=white" alt="React" />
</a>
<a href="https://www.typescriptlang.org">
   <img src="https://img.shields.io/badge/TypeScript-5.7-3178c6?style=for-the-badge&logo=typescript&logoColor=white" alt="TypeScript" />
</a>
<a href="https://tailwindcss.com">
   <img src="https://img.shields.io/badge/Tailwind%20CSS-4.0-38bdf8?style=for-the-badge&logo=tailwindcss&logoColor=white" alt="Tailwind CSS" />
</a>
<a href="https://ui.shadcn.com">
   <img src="https://img.shields.io/badge/Shadcn/ui-1.0-6366f1?style=for-the-badge" alt="Shadcn/ui" />
</a>

<br/><br/>

<h3>
   <span style="color:#F53003;">❤️</span>
   <strong>Where every heartbeat matters</strong>
   <span style="color:#22C55E;">•</span>
   <em>Real-time health monitoring redefined</em>
</h3>

</div>

---

## 🌟 Introduction

**Nabd** (Arabic: نَبض, meaning "pulse") is a groundbreaking IoT-powered health monitoring ecosystem that transforms how we understand and interact with our vital signs. Built with cutting-edge technology and designed for the future of healthcare, Nabd seamlessly bridges the gap between wearable devices and intelligent health insights.

This isn't just another health app—it's a comprehensive platform that monitors your heart rate, blood oxygen levels, blood pressure, glucose, and body temperature in real-time, providing actionable insights that could save lives.

### 🎯 Mission Statement
*"To democratize healthcare monitoring by making real-time vital sign analysis accessible, intelligent, and life-saving for everyone, everywhere."*

---

## ✨ Revolutionary Features

### 🚀 **Real-Time Vital Signs Monitoring**
- **Heart Rate**: Continuous monitoring with 1-second precision
- **SpO₂ (Blood Oxygen)**: Critical hypoxemia detection every 28-30 seconds
- **Blood Pressure**: Automated readings every 60 seconds
- **Glucose Levels**: Smart monitoring every 5 minutes
- **Body Temperature**: Daily sleep pattern analysis

### 🧠 **Intelligent Health Analytics**
- **Predictive Algorithms**: early warning systems
- **Pattern Recognition**: Sleep quality and health trend analysis
- **Emergency Detection**: Automatic critical condition alerts
- **Personalized Insights**: Tailored health recommendations

### 🔔 **Smart Alert System**
- **Instant Notifications**: Real-time danger threshold alerts
- **Emergency Contacts**: Automatic family/doctor notifications
- **Escalation Protocols**: Multi-tier alert management
- **Custom Thresholds**: Personalized warning levels

### 📈 **Reports & Analysis**
- **Health Summaries**: Daily, weekly, and monthly health reports
- **Trend Analysis**: Simple statistical insights into your health patterns
- **Data Export**: Export your health data for external use

### 🔒 **Security & Privacy**
- **Secure Authentication**: Laravel's built-in authentication system
- **Data Encryption**: Secure storage of sensitive health information
- **Local Storage**: SQLite database for fast, local data management
---

## 🚀 API Endpoints (Coming Soon)

*The following API endpoints are planned for the upcoming application:*

### 🔐 **Authentication**
```http
POST   /api/auth/login           # User authentication
POST   /api/auth/register        # New user registration
POST   /api/auth/logout          # User logout
GET    /api/auth/user            # Get authenticated user
```

### 👤 **User Management**
```http
GET    /api/user/profile         # Get user profile
PUT    /api/user/profile         # Update user profile
GET    /api/user/settings        # Get user preferences
PUT    /api/user/settings        # Update user settings
```

### 📊 **Health Data**
```http
POST   /api/vitals               # Submit vital signs data
GET    /api/vitals               # Retrieve health data
GET    /api/vitals/latest        # Get latest readings
GET    /api/vitals/trends        # Health trend data
DELETE /api/vitals/{id}          # Delete specific reading
```

### 📢 **Notifications**
```http
GET    /api/notifications        # Get user notifications
POST   /api/notifications/read   # Mark notifications as read
PUT    /api/notifications/settings # Update notification preferences
```

### 📋 **Reports**
```http
GET    /api/reports/daily        # Daily health summary
GET    /api/reports/weekly       # Weekly health summary
GET    /api/reports/monthly      # Monthly health summary
GET    /api/reports/export       # Export health data
```

---

## 🛠️ Installation & Setup

### Prerequisites

Ensure you have the following installed:
- **PHP 8.2+**
- **Node.js 18+**
- **Composer**
- **SQLite** (comes with PHP)
- **Git**

### ⚡ Quick Start

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Ahmadfbq/nabd-system.git
   cd nabd-system
   ```

2. **Install Backend Dependencies**
   ```bash
   composer install
   ```

3. **Install Frontend Dependencies**
   ```bash
   npm install
   ```

4. **Environment Configuration**
   ```bash
   cp .env.example .env
   php artisan key:generate
   ```

5. **Configure Database**
   ```env
   DB_CONNECTION=sqlite
   DB_DATABASE=database/database.sqlite
   ```

6. **Create Database File**
   ```bash
   touch database/database.sqlite
   ```

7. **Run Database Migrations**
   ```bash
   php artisan migrate --seed
   ```

8. **Build Assets**
   ```bash
   npm run build
   ```

9. **Start the Application**
   ```bash
   # Development mode
   composer run dev
   
   # Or separately:
   php artisan serve &
   npm run dev
   ```

10. **Access the Application**
    - Frontend: `http://localhost:5173`
    - Backend API: `http://localhost:8000`

### 🚀 Production Deployment

For production deployment, Laravel Cloud will be used in future releases:

```bash
# Build for production
npm run build

# Prepare for Laravel Cloud deployment
php artisan optimize
php artisan config:cache
php artisan route:cache
php artisan view:cache
```

---

## 🎨 UI Design Excellence

### 🌈 **Design Philosophy**
Nabd's interface embodies the perfect harmony between medical precision and human-centered design. Every pixel is crafted with accessibility, clarity, and emotional intelligence in mind.

### 🎯 **Design Principles**

#### **1. Medical-Grade Clarity**
- **High Contrast Ratios**: WCAG AAA compliance for critical health data
- **Typography Hierarchy**: Clear information prioritization
- **Color Psychology**: Intuitive health status indicators (green=good, amber=caution, red=critical)

#### **2. Responsive & Adaptive**
- **Mobile-First**: Designed for wearable and smartphone interactions
- **Cross-Platform**: Seamless experience across devices
- **Dark Mode**: Optimized for 24/7 monitoring scenarios

#### **3. Real-Time Data Visualization**
- **Live Charts**: Smooth, animated vital sign displays
- **Progress Indicators**: Visual health goal tracking
- **Heat Maps**: Pattern recognition visualization

### 🎭 **Component Architecture**

#### **Dashboard Components**
```tsx
// Real-time vital signs display
<VitalSignsGrid 
  heartRate={vitals.heartRate}
  spO2={vitals.spO2}
  bloodPressure={vitals.bloodPressure}
  glucose={vitals.glucose}
  temperature={vitals.temperature}
  realTime={true}
/>

// Health trend analysis
<HealthTrendChart 
  data={healthData}
  timeRange="24h"
  predictive={true}
/>

// Emergency alert system
<EmergencyAlertBanner 
  severity={alertSeverity}
  message={alertMessage}
  actions={emergencyActions}
/>
```

#### **Color System**
- **Primary Palette**: 
  - Nabd Red: `#F53003` (Life, urgency, critical alerts)
  - Medical Blue: `#0066CC` (Trust, stability, information)
  - Success Green: `#22C55E` (Healthy, normal ranges)
  - Warning Amber: `#F59E0B` (Caution, borderline readings)

#### **Typography Stack**
- **Primary**: Instrument Sans (Modern, readable, medical-friendly)
- **Monospace**: JetBrains Mono (Data display, technical information)
- **Arabic**: Noto Sans Arabic (Nabd branding, RTL support)

#### **Animation & Micro-interactions**
- **Pulse Animations**: Heartbeat-synchronized UI elements
- **Smooth Transitions**: 300ms ease-in-out for all state changes
- **Loading States**: Medical scanner-inspired loading animations
- **Success Feedback**: Gentle, reassuring confirmation animations

### 📱 **Responsive Breakpoints**
- **Mobile**: 320px - 768px (Primary wearable/phone interface)
- **Tablet**: 768px - 1024px (Doctor consultation mode)
- **Desktop**: 1024px+ (Comprehensive monitoring dashboard)

### 🎨 **Theme Variants**

#### **Light Theme (Medical Professional)**
```css
:root {
  --background: oklch(1 0 0);
  --foreground: oklch(0.145 0 0);
  --primary: oklch(0.205 0 0);
  --critical: oklch(0.577 0.245 27.325);
}
```

#### **Dark Theme (Patient Monitoring)**
```css
.dark {
  --background: oklch(0.145 0 0);
  --foreground: oklch(0.985 0 0);
  --primary: oklch(0.985 0 0);
  --critical: oklch(0.577 0.245 27.325);
}
```

---

## 🏗️ Architecture Highlights

### 🔧 **Technology Stack**
- **Frontend**: React 19 + TypeScript + Inertia.js
- **Backend**: Laravel 12 + PHP 8.2
- **Database**: SQLite (lightweight and efficient)
- **Styling**: Tailwind CSS 4.0 + Shadcn/ui
- **Real-time**: Laravel Echo (Reverb) + WebSockets
- **Testing**: Pest PHP + React Testing Library

### 🏛️ **Monolithic Architecture**
```
┌─────────────────────────────────────────────────────────────┐
│                    Nabd Health System                       │
├─────────────────────────────────────────────────────────────┤
│  Frontend (React + TypeScript + Inertia.js + Tailwindcss)   │
├─────────────────────────────────────────────────────────────┤
│  Backend (Laravel 12 + PHP 8.2)                             │
│  ├── Authentication & User Management                       │
│  ├── Vital Signs Data Processing                            │
│  ├── Basic Statistical Analysis                             │
│  ├── Alert & Notification System                            │
│  └── Report Generation                                      │
├─────────────────────────────────────────────────────────────┤
│  Database (SQLite)                                          │
│  ├── Users & Profiles                                       │
│  ├── Vital Signs Data                                       │
│  ├── Alerts & Notifications                                 │
│  └── System Logs                                            │
└─────────────────────────────────────────────────────────────┘
```

---

## 🌟 What Makes Nabd Different?

### 💡 **Innovation Highlights**
- **Simplicity First**: Clean, intuitive interface focused on essential features
- **Fast Performance**: SQLite ensures quick data access and minimal overhead
- **Reliable Monitoring**: Consistent vital sign tracking with basic analytics
- **Easy Setup**: Simple installation and configuration process
- **Responsive Design**: Works seamlessly across all device types

### 🏆 **Technical Excellence**
- **Lightweight Architecture**: Efficient monolithic design
- **Local Data Storage**: Fast SQLite database for optimal performance
- **Modern Tech Stack**: Latest Laravel and React versions
- **Security Focused**: Built-in Laravel security features
- **Future-Ready**: Prepared for Laravel Cloud deployment


---

## 🤝 Contributing

We welcome contributions from healthcare professionals, developers, and anyone passionate about improving healthcare technology. See our Contributing Guidelines for details.

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 🆘 Support

- **Documentation**: [docs.nabd.health](https://docs.nabd.health)
- **Issues**: [GitHub Issues](https://github.com/Ahmadfbq/nabd-system/issues)
- **Email**: ahmdb625@gmail.com

---

<div align="center">

**Built with ❤️ for a healthier world**

*Nabd - Because every heartbeat tells a story*

[![Follow on X](https://img.shields.io/badge/X-Follow-1da1f2?logo=x&logoColor=white&style=for-the-badge)](https://x.com/ahmad_fbq)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?logo=github&logoColor=white&style=for-the-badge)](https://github.com/Ahmadfbq)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077b5?logo=linkedin&logoColor=white&style=for-the-badge)](https://www.linkedin.com/in/ahmadfbq)

</div>