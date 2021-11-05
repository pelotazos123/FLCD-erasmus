package model;

/**
 * Token class to save each of them
 */
public class Token {

    private Types type;
    private String value;

    public Types getType(){
        return type;
    }

    public void setType(Types type){
        this.type = type;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

}
