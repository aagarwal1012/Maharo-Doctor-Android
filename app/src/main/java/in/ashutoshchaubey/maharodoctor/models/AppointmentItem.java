package in.ashutoshchaubey.maharodoctor.models;

public class AppointmentItem {

    private int hour, minute, dd, yyyy, id;
    private String docName, status, mm;

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

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppointmentItem(String time, int id, String docName, String status) {

        if (time != null){
            this.hour = Integer.parseInt(time.substring(16, 18));
            this.minute = Integer.parseInt(time.substring(19, 21));
            this.dd = Integer.parseInt(time.substring(8, 10));
            this.mm = time.substring(4, 7);
            this.yyyy = Integer.parseInt(time.substring(11, 15));
        }
        this.id = id;
        this.docName = docName;
        this.status = status;
    }
}
