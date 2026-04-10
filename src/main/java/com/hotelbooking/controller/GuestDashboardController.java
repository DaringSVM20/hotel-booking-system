package com.hotelbooking.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.hotelbooking.dao.BookingDAO;
import com.hotelbooking.dao.RoomDAO;
import com.hotelbooking.models.Room;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GuestDashboardController {

    @FXML private TableView<Room> roomsTable;
    @FXML private TableColumn<Room, String> roomNumberCol;
    @FXML private TableColumn<Room, String> roomTypeCol;
    @FXML private TableColumn<Room, Double> roomPriceCol;
    @FXML private Label welcomeLabel;
    @FXML private Label totalRoomsLabel;
    @FXML private Label availableRoomsLabel;
    @FXML private Label occupiedRoomsLabel;
    @FXML private Label myBookingsLabel;

    private RoomDAO roomDAO = new RoomDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private String username;

    public void setUsername(String username) {
        this.username = username;
        welcomeLabel.setText("  Welcome, " + username + " 👋");
        loadData();
    }

    @FXML
    public void initialize() {
        roomNumberCol.setCellValueFactory(d ->
            new javafx.beans.property.SimpleStringProperty(d.getValue().getRoomNumber()));
        roomTypeCol.setCellValueFactory(d ->
            new javafx.beans.property.SimpleStringProperty(d.getValue().getType()));
        roomPriceCol.setCellValueFactory(d ->
            new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getPricePerNight()));
    }

    private void loadData() {
        List<Room> all = roomDAO.getAllRooms();
        List<Room> available = all.stream()
            .filter(Room::isAvailable)
            .collect(Collectors.toList());
        long occupied = all.stream().filter(r -> !r.isAvailable()).count();

        totalRoomsLabel.setText(String.valueOf(all.size()));
        availableRoomsLabel.setText(String.valueOf(available.size()));
        occupiedRoomsLabel.setText(String.valueOf(occupied));
        myBookingsLabel.setText(String.valueOf(bookingDAO.getTotalBookings()));

        roomsTable.setItems(FXCollections.observableArrayList(available));
    }

    @FXML
    private void handleRefresh() {
        loadData();
    }

    @FXML
    private void openBooking() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/hotelbooking/fxml/booking.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New Booking");
            stage.setScene(new Scene(loader.load(), 700, 550));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/hotelbooking/fxml/login.fxml"));
            Stage stage = (Stage) roomsTable.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 680);
            scene.getStylesheets().add(
                getClass().getResource("/com/hotelbooking/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setWidth(1000);
            stage.setHeight(680);
            stage.setTitle("Hotel Booking System - Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}