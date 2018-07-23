package in.ashutoshchaubey.maharodoctor.models;

/**
 * Created by ashutoshchaubey on 23/07/18.
 */

public class AppointmentItem {

    private int hour, minute, dd, mm, yyyy, id;
    private String docName, clinicName, cityName, status;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getYyyy() {
        return yyyy;
    }

    public void setYyyy(int yyyy) {
        this.yyyy = yyyy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppointmentItem(int hour, int minute, int dd, int mm, int yyyy, int id, String docName, String clinicName, String cityName, String status) {

        this.hour = hour;
        this.minute = minute;
        this.dd = dd;
        this.mm = mm;
        this.yyyy = yyyy;
        this.id = id;
        this.docName = docName;
        this.clinicName = clinicName;
        this.cityName = cityName;
        this.status = status;
    }
}
