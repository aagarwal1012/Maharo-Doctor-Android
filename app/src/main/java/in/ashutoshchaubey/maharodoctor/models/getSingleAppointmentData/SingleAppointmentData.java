package in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData;

import in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output.Info;

public class SingleAppointmentData {

    String name, doctor, time;
    int verified, completed, idsitings;
    Info info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getIdsitings() {
        return idsitings;
    }

    public void setIdsitings(int idsitings) {
        this.idsitings = idsitings;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
