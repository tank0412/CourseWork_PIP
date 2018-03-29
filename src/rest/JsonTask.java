package rest;

import lombok.Data;

@Data
public class JsonTask {
    public Long id;
    public String customerName;
    public String description;
    public String name;
    public Long reward;
    public Integer leadtime;
    public String comment;

    public JsonTask(){

    }
    public JsonTask(Long id, String customerName, String description, String name, Long reward,
                    Integer leadtime, String comment){
        this.id = id;
        this.customerName = customerName;
        this.description = description;
        this.name = name;
        this.reward = reward;
        this.leadtime = leadtime;
        this.comment = comment;
    }
}
