# 🏥 HealthCure Android Application

A comprehensive healthcare management Android application built with Java, featuring multi-user modules for seamless healthcare service delivery and patient care management.

## 📖 Description

HealthCure is a complete healthcare ecosystem mobile application that connects patients, doctors, pharmacies, and administrators in one unified platform. The app streamlines healthcare processes through appointment booking, patient history management, medication alerts, vital signs tracking, and comprehensive healthcare service coordination.

## ✨ Key Features

### 👨‍⚕️ **Doctor Module**
- **Patient Management**: View and manage patient records
- **Appointment Scheduling**: Accept/decline appointment requests
- **Medical History**: Access comprehensive patient history
- **Prescription Management**: Create and manage digital prescriptions
- **Vital Signs Monitoring**: Track patient BP, heart rate, and other metrics
- **Consultation Notes**: Add detailed consultation records

### 👤 **Patient Module**
- **Profile Management**: Complete patient profile with medical history
- **Appointment Booking**: Schedule appointments with preferred doctors
- **Medicine Alerts**: Automated medication reminders and schedules
- **Health Tracking**: BP flow charts and vital signs monitoring
- **Medical Records**: Access personal health records and reports
- **Prescription History**: View current and past prescriptions

### 🏪 **Pharmacy Module**
- **Prescription Processing**: Receive and process digital prescriptions
- **Inventory Management**: Track medicine stock and availability
- **Order Fulfillment**: Manage prescription orders and delivery
- **Patient Communication**: Notify patients about prescription status
- **Medicine Information**: Comprehensive drug database

### 👨‍💼 **Admin Module**
- **User Management**: Manage doctors, patients, and pharmacy accounts
- **System Analytics**: Healthcare service statistics and reports
- **Appointment Oversight**: Monitor booking and scheduling systems
- **Data Management**: Backup and data integrity maintenance
- **System Configuration**: App settings and feature management

## 🛠️ Technology Stack

- **Language**: Java
- **Platform**: Android (API Level 21+)
- **IDE**: Android Studio
- **Database**: SQLite (Local) + Firebase/MySQL (Cloud)
- **Architecture**: MVP (Model-View-Presenter)
- **Authentication**: Firebase Authentication
- **Notifications**: Firebase Cloud Messaging (FCM)
- **Charts**: MPAndroidChart library
- **Image Handling**: Glide library

## 📋 Prerequisites

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK API Level 21 (Android 5.0) or higher
- Java Development Kit (JDK) 8 or higher
- Firebase project setup
- Gradle 7.0+

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/aliyanissohaib/healthcure-android-app.git
cd healthcure-android-app
```

### 2. Firebase Configuration
1. Create a new Firebase project
2. Add Android app to Firebase project
3. Download `google-services.json`
4. Place it in the `app/` directory

### 3. Database Setup
```sql
-- Create tables for local SQLite database
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_type TEXT NOT NULL,
    username TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE appointments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER,
    doctor_id INTEGER,
    appointment_date TEXT,
    status TEXT DEFAULT 'pending',
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES users(id),
    FOREIGN KEY (doctor_id) REFERENCES users(id)
);
```

### 4. Build and Run
1. Open project in Android Studio
2. Sync Gradle files
3. Connect Android device or start emulator
4. Click Run button or press `Shift + F10`

## 📱 App Architecture

```
app/
├── src/main/
│   ├── java/com/healthcure/
│   │   ├── activities/
│   │   │   ├── MainActivity.java
│   │   │   ├── LoginActivity.java
│   │   │   ├── DashboardActivity.java
│   │   │   └── modules/
│   │   │       ├── doctor/
│   │   │       ├── patient/
│   │   │       ├── pharmacy/
│   │   │       └── admin/
│   │   ├── adapters/
│   │   │   ├── AppointmentAdapter.java
│   │   │   ├── PatientAdapter.java
│   │   │   └── MedicineAdapter.java
│   │   ├── models/
│   │   │   ├── User.java
│   │   │   ├── Patient.java
│   │   │   ├── Doctor.java
│   │   │   ├── Appointment.java
│   │   │   └── Medicine.java
│   │   ├── database/
│   │   │   ├── DatabaseHelper.java
│   │   │   └── FirebaseManager.java
│   │   ├── utils/
│   │   │   ├── SharedPrefsHelper.java
│   │   │   ├── NotificationHelper.java
│   │   │   └── ChartHelper.java
│   │   ├── fragments/
│   │   │   ├── AppointmentsFragment.java
│   │   │   ├── ProfileFragment.java
│   │   │   └── HistoryFragment.java
│   │   └── services/
│   │       ├── MedicineReminderService.java
│   │       └── NotificationService.java
│   ├── res/
│   │   ├── layout/
│   │   ├── drawable/
│   │   ├── values/
│   │   └── menu/
│   └── AndroidManifest.xml
```

## 🔐 User Authentication Flow

```java
// Login Activity Example
public class LoginActivity extends AppCompatActivity {
    private void authenticateUser(String email, String password, String userType) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    redirectToModule(userType);
                } else {
                    showError("Invalid credentials");
                }
            });
    }
    
    private void redirectToModule(String userType) {
        Intent intent;
        switch (userType) {
            case "DOCTOR":
                intent = new Intent(this, DoctorDashboardActivity.class);
                break;
            case "PATIENT":
                intent = new Intent(this, PatientDashboardActivity.class);
                break;
            case "PHARMACY":
                intent = new Intent(this, PharmacyDashboardActivity.class);
                break;
            case "ADMIN":
                intent = new Intent(this, AdminDashboardActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
        finish();
    }
}
```

## 📊 Key Functionalities

### **Appointment Booking System**
- Real-time doctor availability checking
- Appointment conflict prevention
- Automated confirmation notifications
- Rescheduling and cancellation options

### **Medicine Alert System**
```java
public class MedicineReminderService extends JobIntentService {
    public void scheduleMedicineReminder(Medicine medicine, long reminderTime) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, MedicineReminderReceiver.class);
        intent.putExtra("medicine_name", medicine.getName());
        
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
            this, medicine.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent
        );
    }
}
```

### **BP Flow Chart Tracking**
- Interactive blood pressure charts using MPAndroidChart
- Historical data visualization
- Trend analysis and health insights
- Export functionality for medical consultations

### **Patient History Management**
- Comprehensive medical record storage
- Document upload and management
- Search and filter capabilities
- Secure data encryption

## 🎨 User Interface

### **Patient Dashboard**
```
┌─────────────────────────────────────┐
│  HealthCure - Patient Portal        │
├─────────────────────────────────────┤
│  👤 Welcome, John Doe               │
│                                     │
│  🏥 Quick Actions                   │
│  [Book Appointment] [View History]  │
│  [Medicine Alerts]  [BP Tracking]   │
│                                     │
│  📅 Upcoming Appointments           │
│  ┌─────────────────────────────┐   │
│  │ Dr. Smith - Cardiology      │   │
│  │ Tomorrow, 10:00 AM          │   │
│  │ [Reschedule] [Cancel]       │   │
│  └─────────────────────────────┘   │
│                                     │
│  💊 Medicine Reminders              │
│  ⏰ Aspirin - In 2 hours            │
│  ⏰ Vitamin D - Tomorrow 8:00 AM    │
│                                     │
│  📊 Latest BP Reading: 120/80       │
│  [View Chart] [Add Reading]         │
└─────────────────────────────────────┘
```

## 🔧 Configuration

### **App Configuration**
```java
// AppConfig.java
public class AppConfig {
    public static final String BASE_URL = "https://healthcure-api.com/";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "healthcure.db";
    
    // Notification settings
    public static final String MEDICINE_REMINDER_CHANNEL = "medicine_reminders";
    public static final String APPOINTMENT_CHANNEL = "appointments";
    
    // Chart settings
    public static final int MAX_BP_ENTRIES = 30;
    public static final String DATE_FORMAT = "dd/MM/yyyy";
}
```

### **Permissions Required**
```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## 📊 Database Schema

### **Core Tables**
```sql
-- Users table
CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    user_type TEXT CHECK(user_type IN ('PATIENT','DOCTOR','PHARMACY','ADMIN')),
    full_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    phone TEXT,
    specialization TEXT, -- for doctors
    license_number TEXT, -- for doctors/pharmacies
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Patient health records
CREATE TABLE health_records (
    id INTEGER PRIMARY KEY,
    patient_id INTEGER,
    record_type TEXT,
    record_data TEXT,
    recorded_date TEXT,
    doctor_id INTEGER,
    FOREIGN KEY (patient_id) REFERENCES users(id)
);

-- Medicine prescriptions
CREATE TABLE prescriptions (
    id INTEGER PRIMARY KEY,
    patient_id INTEGER,
    doctor_id INTEGER,
    medicine_name TEXT,
    dosage TEXT,
    frequency TEXT,
    duration INTEGER,
    prescribed_date TEXT,
    FOREIGN KEY (patient_id) REFERENCES users(id)
);
```

## 🚀 Advanced Features

### **Real-time Notifications**
- Firebase Cloud Messaging integration
- Push notifications for appointments
- Medicine reminder alerts
- Emergency health alerts

### **Data Analytics**
- Patient health trend analysis
- Appointment statistics
- Medicine adherence tracking
- System usage analytics

### **Security Features**
- Encrypted local database
- Secure API communication
- User authentication with JWT tokens
- HIPAA compliance considerations

## 🤝 Contributing

We welcome contributions! Areas for improvement:

- 🏥 Additional medical modules
- 📱 Enhanced UI/UX design
- 🔐 Advanced security features
- 📊 More detailed analytics
- 🌐 Telemedicine integration
- 💳 Payment gateway integration

### **Development Guidelines**
1. Follow Android coding standards
2. Write comprehensive unit tests
3. Document all public methods
4. Use proper error handling
5. Follow MVVM/MVP architecture patterns

## 🧪 Testing

### **Unit Testing**
```bash
./gradlew test
```

### **Instrumentation Testing**
```bash
./gradlew connectedAndroidTest
```

### **Test Coverage**
- Authentication flows
- Database operations
- API integrations
- UI interactions
- Notification systems

## 📱 APK Build

### **Debug Build**
```bash
./gradlew assembleDebug
```

### **Release Build**
```bash
./gradlew assembleRelease
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ⚠️ Medical Disclaimer

This application is for educational and demonstration purposes. It should not replace professional medical advice, diagnosis, or treatment. Always consult healthcare professionals for medical decisions.

## 🏷️ Tags

`android` `java` `healthcare` `medical-app` `patient-management` `doctor-booking` `medicine-reminder` `health-tracking` `firebase` `mobile-development`

---

*Revolutionizing healthcare with technology! 🏥📱*
