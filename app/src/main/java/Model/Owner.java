package Model;

public class Owner {

    String username;
    String vehicleNo;
    String fuelType;

    public Owner() {
    }

    public Owner(String username, String vehicleNo, String fuelType) {
        this.username = username;
        this.vehicleNo = vehicleNo;
        this.fuelType = fuelType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
