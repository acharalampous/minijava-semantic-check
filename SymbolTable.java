import java.util.*;

public class SymbolTable{

    Set<String> primitive_t; // Primitive types of language (int, boolean, int[])
    Map<String, ClassContent> class_names; // Contains all class_names(types) declared in src file
    Map<String, String> subtypes; // Keeps all the pairs of <derived class, super class>
    Set<String> unknown_t; // Set that holds unknown types declared in classes, to be checked later

    /* Constructor */
    public SymbolTable(){
        class_names = new HashMap<>();
        subtypes = new HashMap<>();
        primitive_t = new HashSet<>(Arrays.asList("int", "boolean", "int[]"));
        unknown_t = new HashSet<>();
    }

    /* Adds a new class_name to set and initialize its content */
    public void store_class(String new_class){
        class_names.put(new_class, new ClassContent());
    }

    /* Adds a new pair of classes in map */ 
    public void store_subtype(String derived_class, String super_class){
        subtypes.put(derived_class, super_class);
    }

    /* Returns class_names set */
    public Map<String, ClassContent> get_set(){
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
        if(class_names.containsKey(new_class)){
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
        if(class_names.containsKey(derived_class)){
            return -1;
        }
        
        /* Check if super class is declared */
        if(!class_names.containsKey(super_class)){
            return -2;
        }
        else{
            store_class(derived_class); // store new class
            store_subtype(derived_class, super_class); // store subtype pair 
            return 0;
        }

    }


    /*
     * Given a class_name and a field type + name, it will store the field in class' content.
     * Will be checked if the field is redecleared. If yes, then returns fatal error.
     * Correct store: 0,
     * Class doesnt exists: -1,
     * Field is redecleared: -2.
     */
    public int add_class_field(String class_name, String type, String name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }

        Map<String, String> class_fields = cc.get_fields();
        if(class_fields.containsKey(name)){ // check if name was redecleared
            return -2;
        }

        class_fields.put(name, type);

        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(type)){ // check if type of field is primitive
            unknown_t.add(type);
        }
        return 0;
    }

    /*
     * After the first visitor pass, where all class types are collected. If a class has a field of
     * class type, it should be stored in unknown set. All names in unknown set are checked if are 
     * declared in class_names.
     * No undefined: NULL,
     * Undefined reference: undeclared_type;
     */ 

    public String check_unknown(){
        /* Parse set */
        for (String type : unknown_t) {
            /* Check if type name is defined */
            if(!class_names.containsKey(type)){
                return type;
            }
        }

        unknown_t.clear();
        return null;
    }
}
