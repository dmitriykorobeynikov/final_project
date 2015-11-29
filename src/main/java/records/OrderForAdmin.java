package records;


import java.sql.Date;

public class OrderForAdmin {
    private int id;
    private int user_id;
    private String type_of_work;
    private String volume;
    private Date finish_date;
    private String apartment;

    public OrderForAdmin(int id, int user_id, String type_of_work, String volume, Date finish_date, String apartment) {
        this.id = id;
        this.user_id = user_id;
        this.type_of_work = type_of_work;
        this.volume = volume;
        this.finish_date = finish_date;
        this.apartment = apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType_of_work() {
        return type_of_work;
    }

    public void setType_of_work(String type_of_work) {
        this.type_of_work = type_of_work;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
