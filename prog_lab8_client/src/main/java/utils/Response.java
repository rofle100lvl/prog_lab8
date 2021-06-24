package utils;

import model.Flat;

import java.io.Serializable;
import java.util.ArrayList;


public class Response implements Serializable {
    private static final long serialVersionUID = -7879056808897113742L;
    private int code;
    private String requestText;
    private ArrayList<Flat> flats = null;

    public Response(int code, String requestText) {
        this.code = code;
        this.requestText = requestText;
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }

    public String getRequestText() {
        return requestText;
    }

    public int getCode() {
        return code;
    }

    public Response(int code, ArrayList<Flat> flats, String requestText) {
        this.code = code;
        this.flats = flats;
        this.requestText = requestText;
    }

    public void printResponse(){
        System.out.println("Статус ответа: " + String.valueOf(code));
        System.out.println("Ответ сервера: " + requestText);
    }
}
