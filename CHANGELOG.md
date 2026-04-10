# Changelog

All notable changes to Hotel Booking System are documented here.

---

## [2.0.0] — 2026-04-10

### 🎨 Visual Overhaul
- Complete UI redesign with a warm, hotel-inspired color palette
- Accent color: `#E87F24` (burnt orange) dominates all interactive elements
- Background: `#FEFDDF` (warm cream) replaces generic grey
- Navigation bar: deep `#3B2A1A` (dark brown) with `#FFC81E` (gold) typography
- Georgia serif font throughout for an upscale, hospitality feel
- Global `styles.css` stylesheet replacing all inline FXML styles
- Stat cards with drop shadows, rounded corners, and color-coded categories
- TableView with dark header, gold column labels, warm alternating row colors
- Buttons with hover scale animations, drop shadows, and press feedback
- Login screen centered on cream background with elevated card and shadow
- Orange active tab highlight on dashboard TabPane

### 🔐 Role-Based Access Control
- Introduced `GUEST` role — limited view with available rooms only
- Admin/Manager roles retain full dashboard access
- Login screen now routes to correct dashboard based on role from database
- Guest dashboard shows 4 stat cards, available rooms table, and refresh button
- Guest panel restricted from: cancellation, reset, booking management

### 📄 PDF Invoice Download
- Added iText 5 dependency for PDF generation
- Download PDF button on every invoice screen
- File chooser dialog — guest picks save location
- PDF styled with hotel color theme: dark brown header, cream background, gold accents
- Full invoice breakdown: guest info, room details, nights, charges, 18% GST, grand total
- Success/error alert after download attempt

### 👥 Guest Management Panel
- New Guests tab added to admin dashboard
- Displays full guest records: ID, Name, Phone, Email
- Data refreshes with the global Refresh button

### 📋 Booking Form Improvements
- Red asterisk (*) indicators on all mandatory fields
- Inline error messages via status label
- Updated color scheme matching warm theme
- Estimated total now styled in orange accent

### 🛠 Technical Improvements
- App window fixed at 1000×680 with 800×600 minimum size
- Removed unintended maximize-on-startup behaviour
- CSS now applied to all new Stage instances (billing, booking)
- Removed duplicate `.tab-pane .tab-content-area` CSS block
- Booking cancellation automatically restores room availability
- Reset All Data clears invoices, bookings, guests and resets SQLite sequences

---

## [1.0.0] — 2026-03-28

### 🚀 Initial Release
- JavaFX 21 desktop application with Maven modular build
- SQLite persistent storage via JDBC — all data survives restarts in `hotel.db`
- MVC architecture with dedicated DAO layer
- Database-driven authentication — credentials stored in `users` table
- Admin dashboard with live stat cards: Total, Available, Occupied, Bookings
- Room management with availability tracking
- Booking form with DatePicker, ComboBox, and live price calculator
- Invoice generation with 18% GST calculation
- Booking cancellation with room restoration
- Reset All Data functionality
- Scene Builder FXML components throughout
- Five default rooms seeded (Single, Double, Suite categories)
- Three default admin users seeded (admin, manager, staff)