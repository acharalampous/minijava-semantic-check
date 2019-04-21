import java.util.*;

public class SymbolTable{

    Set<String> class_names; // Contains all class_names(types) declared in src file
    Map<String, String> subtypes; // Keeps all the pairs of <derived class, super class>

    /* Constructor */
    public SymbolTable(){
        class_names = new HashSet<>();
        subtypes = new HashMap<>();
    }

    /* Adds a new class_name to set */
    public void store_class(String new_class){
        class_names.add(new_class);
    }

    /* Adds a new pair of classes in map */ 
    public void store_subtype(String derived_class, String super_class){
        subtypes.put(derived_class, super_class);
    }

    /* Returns class_names set */
    public Set<String> get_set(){
        return class_names;
    }

    /* Returns the subtypes map */
    public Map<String, String> get_subtypes(){
        return subtypes;
    }

    /* 
     * Given a new class name, checks if not already declared(stored), and stores it in Symbol Tables.
     * Returns 0 if valid, else -1 in case of error
     */
     public int add_class(String new_class){
        /* Check if class name was already declared */
        if(class_names.contains(new_class)){
            return -1;
        }
        else{
            store_class(new_class);
            return 0;
        }
    }

    /* 
     * Given a pair of classes, first checks if derived_class is not already declared and then 
     * if super_class is declared. If both are valid, it stores the derived class in class_names
     * and then the pair in the subtypes Map
     */
    public int add_subtype(String derived_class, String super_class){
        /* Check if class\ was already declared */
        if(class_names.contains(derived_class)){
            return -1;
        }
        
        /* Check if super class is declared */
        if(!class_names.contains(super_class)){
            return -2;
        }
        else{
            store_class(derived_class); // store new class
            store_subtype(derived_class, super_class); // store subtype pair 
            return 0;
        }

    }


}
