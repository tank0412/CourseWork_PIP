package rest;

public class JsonMessage {
    public String name;
    public String message;
    public String time;

    public JsonMessage(){

    }
    public JsonMessage(String name, String message, String time){
        this.name = name;
        this.message = message;
        this.time = time;
    }
}
