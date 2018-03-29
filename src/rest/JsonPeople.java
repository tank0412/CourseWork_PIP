package rest;

public class JsonPeople {
    public String nickname;
    public Long id;

    JsonPeople(){

    }
    JsonPeople(Long id, String nickname ){
        this.id = id;
        this.nickname = nickname;
    }
}
