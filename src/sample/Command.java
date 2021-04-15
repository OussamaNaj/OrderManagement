package sample;

public class Command {
    private String code;
    private String command;
    private String reservation;
    private String modification;
    private String Price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(String price) {
        Price = Price;
    }

    public String getPrice() {
        return Price;
    }

    public Command(String code,String command, String reservation, String modification,String Price) {
        this.command = command;
        this.reservation = reservation;
        this.modification = modification;
        this.code = code;
        this.Price = Price ;
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }
}

