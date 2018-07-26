package in.ayush.mharodoctor.models.getNearbyDoctors;

public class GetNearByDoctorsOutput {
    String status, message;
    Doctor[] list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Doctor[] getList() {
        return list;
    }

    public void setList(Doctor[] list) {
        this.list = list;
    }
}
