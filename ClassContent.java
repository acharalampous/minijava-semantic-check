import java.util.*;

public class ClassContent{
    Map<String, String> fields; // Maps the field's name of a Class with it's type
    Map<String, Vector<String>> methods; // Maps the method's name with its return type and arguments. Always on 0 its the return type  

    /* Constructor */
    public ClassContent(){
        fields = new HashMap<>();
        methods = new HashMap<>();
    }

    public Map<String, String> get_fields(){
        return fields;
    }

    
    public Map<String, Vector<String>> get_methods(){
        return methods;
    }

}