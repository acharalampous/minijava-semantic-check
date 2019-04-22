/* Class that implements the pair of class' field/method offset. Holds the name and offset */
public class NameOffset{
    private String name; // name of field/method
    private int offset; // offset of field/method

    /* Constructor */
    public NameOffset(String name, int offset){
        this.name = name; 
        this.offset = offset;
    }

    /* Accesors */
    public String get_name(){
        return this.name;
    }

    public int get_offset(){
        return this.offset;
    }
}