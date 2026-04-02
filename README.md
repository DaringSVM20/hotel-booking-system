# 🏨 Hotel Booking System

A full-stack desktop application built with **JavaFX 21**, **Maven**, and **SQLite** for managing hotel room bookings, guests, and invoices. Developed as a mini project for the Object Oriented Software Development Lab (OOSDL) at MIT Manipal.

---

## ✨ Features

- 🔐 **Database-driven authentication** — Login with role-based access (Admin, Manager, Staff)
- 📊 **Live dashboard** — Real-time stats for total, available, and occupied rooms
- 📋 **Dual-tab view** — Separate tabs for Room Overview and All Bookings
- 🛏️ **New Booking form** — Guest details, room selection, date pickers with live price preview
- 🧾 **Invoice generation** — Auto-calculates room charges + 18% GST, stores invoice in DB
- ❌ **Booking cancellation** — Cancel bookings with confirmation dialog, auto-frees the room
- 🔄 **Refresh & Reset** — Refresh dashboard live data or reset all bookings/guests/invoices
- 💾 **Persistent storage** — All data saved in SQLite (`hotel.db`), survives app restarts
- 🎨 **CSS styling** — Global stylesheet with reusable classes, hover effects, themed components

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 (Eclipse Adoptium) | Core language |
| JavaFX | 21 | GUI framework |
| Maven | 3.x | Build & dependency management |
| SQLite (via xerial) | 3.42.0.0 | Embedded database |
| JDBC | Built-in | Database connectivity |
| Scene Builder | FXML | UI component design |
| JavaFX CSS | — | Styling & layouts |

---

## 📁 Project Structure

```
HotelBookingSystem/
├── pom.xml
├── hotel.db                        # Auto-created on first run
└── src/main/
    ├── java/com/hotelbooking/
    │   ├── module-info.java
    │   ├── App.java
    │   ├── DatabaseHelper.java
    │   ├── controller/
    │   │   ├── LoginController.java
    │   │   ├── DashboardController.java
    │   │   ├── BookingController.java
    │   │   └── BillingController.java
    │   ├── dao/
    │   │   ├── UserDAO.java
    │   │   ├── RoomDAO.java
    │   │   ├── GuestDAO.java
    │   │   ├── BookingDAO.java
    │   │   └── InvoiceDAO.java
    │   └── models/
    │       ├── Room.java
    │       ├── Guest.java
    │       ├── Booking.java
    │       └── Invoice.java
    └── resources/com/hotelbooking/
        ├── fxml/
        │   ├── login.fxml
        │   ├── dashboard.fxml
        │   ├── booking.fxml
        │   └── billing.fxml
        └── css/
            └── styles.css
```

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or higher ([Eclipse Adoptium](https://adoptium.net))
- Maven 3.x (or use the VS Code Extension Pack for Java which bundles it)

> JavaFX and SQLite are **not** manually installed — Maven downloads them automatically.

### Run the app

```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/hotel-booking-system.git
cd hotel-booking-system

# Build
mvn clean install -DskipTests

# Run
mvn javafx:run
```

### Default credentials

| Username | Password | Role |
|---|---|---|
| admin | admin123 | ADMIN |
| manager | manager123 | MANAGER |
| staff | staff123 | STAFF |

---

## 🗄️ Database

The app uses **SQLite** via JDBC. The `hotel.db` file is created automatically in the project root on first run. No database setup required.

**Tables:** `users`, `rooms`, `guests`, `bookings`, `invoices`

**Seeded on first run:**
- 6 rooms (2x Single @ ₹1500, 2x Double @ ₹2500, 2x Suite @ ₹5000)
- 3 default users (admin, manager, staff)

---

## 📸 Screens

| Screen | Description |
|---|---|
| Login | Username/password auth against SQLite users table |
| Dashboard | Stats cards + tabbed room/booking tables + nav actions |
| New Booking | Guest form + room dropdown + date pickers + live price |
| Invoice | Full billing breakdown with 18% GST |

---

## 🏗️ Architecture

The project follows the **MVC pattern** with a dedicated DAO layer:

- **Model** — Plain Java classes (`Room`, `Guest`, `Booking`, `Invoice`)
- **View** — FXML files + CSS stylesheet
- **Controller** — JavaFX controllers handle UI events
- **DAO** — Data Access Objects handle all JDBC/SQL operations

---

## 📦 Extra Features (OOSDL Rubric)

| Feature | Implementation |
|---|---|
| Maven modular build | `pom.xml` + `module-info.java` + `javafx-maven-plugin` |
| Persistent storage (JDBC) | SQLite via xerial JDBC driver, `hotel.db` |
| Screen design with layouts | JavaFX CSS stylesheet, VBox/HBox/BorderPane/TabPane |
| Billing management | Auto invoice with 18% GST, stored in DB |
| Scene Builder & components | FXML with TableView, DatePicker, ComboBox, TabPane, etc. |

---

## 🔮 Planned / Future

- [ ] Role-based access control (restrict reset to ADMIN only)
- [ ] PDF invoice export
- [ ] Revenue analytics with JavaFX Charts
- [ ] Password hashing (BCrypt)
- [ ] Web version (Spring Boot + React)

---

## 📄 License

MIT License — free to use, modify, and distribute.

---

*Built with ❤️ at MIT Manipal | OOSDL Mini Project | Sem 4, 2025-26*