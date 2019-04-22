import java.util.*;

public class SymbolTable{

    Set<String> primitive_t; // Primitive types of language (int, boolean, int[])
    Map<String, ClassContent> class_names; // Contains all class_names(types) declared in src file
    Map<String, String> subtypes; // Keeps all the pairs of <derived class, super class>
    Set<String> unknown_t; // Set that holds unknown types declared in classes, to be checked later

    Vector<String> temp_method_pars; // Vector that holds parameters of the next method to be stored

    /* Constructor */
    public SymbolTable(){
        class_names = new LinkedHashMap<>();
        subtypes = new HashMap<>();
        primitive_t = new HashSet<>(Arrays.asList("int", "boolean", "int[]"));
        unknown_t = new HashSet<>();
        temp_method_pars = null;
    }

    /* Adds a new class_name to set and initialize its content */
    public void store_class(String new_class){
        class_names.put(new_class, new ClassContent());
    }

    /* Adds a new pair of classes in map after all fields/methods of super_class are stored in derived. */ 
    public void store_subtype(String derived_class, String super_class){

        ClassContent cc = class_names.get(derived_class);
        ClassContent scc = class_names.get(super_class);

        /* Inherite super's fields */ 
        Map<String, String> s_fields = scc.get_fields();
        cc.inherite_fields(s_fields);

        /* Inherite super's s_fields(super of super) */
        s_fields = scc.get_s_fields();
        cc.inherite_fields(s_fields);

        /* Inherite super's methods */ 
        Map<String, Vector<String>> s_methods = scc.get_methods();
        cc.inherite_methods(s_methods);

        /* Inherite super's s_methods(super of super) */
        s_methods = scc.get_s_methods();
        cc.inherite_methods(s_methods);

        /* Set offsets */
        cc.set_f_offset(scc.get_f_next_offset());
        cc.set_m_offset(scc.get_m_next_offset());

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
        /* Check if class was already declared */
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

        /* Add field and its offset */
        class_fields.put(name, type);
        cc.add_f_offset(type, name);

        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(type)){ // check if type of field is primitive
            unknown_t.add(type);
        }
        return 0;
    }

    
    public int check_class_method(String class_name, String return_type, String name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }

        Map<String, Vector<String>> class_methods = cc.get_methods();
        if(class_methods.containsKey(name)){ // check if method was redecleared
            return -2;
        }

        /* Create new vector and place return type at 0 index */
        temp_method_pars = new Vector<>();
        temp_method_pars.add(return_type);

        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(return_type)){ // check if type of field is primitive
            unknown_t.add(return_type);
        }
        return 0;
    }

    public void add_method_par(String type){
        temp_method_pars.add(type);

        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(type)){ // check if type of field is primitive
            unknown_t.add(type);
        }
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


    /* Push new string(type) to temporary method parameters */
    public void add_temp_par(String type){
        temp_method_pars.add(type);
    }

    /* Assignes to given class name the parameter vector */
    public int add_class_method(String class_name, String method_name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }


        int result = overload_check(class_name, method_name);
        if(result < 0){
            return result;
        }
        
        cc.add_method(method_name, temp_method_pars);

        /* Add offset if method is not overidding super's */
        if(result == 0){
            cc.add_m_offset(method_name);
        }

        return 0;
    }

    /*
     * Given a class name and method, must check if method is overidding and not overloading.
     * First, it checks if class_name is derived and if yes checks if method_name is declared in super class.
     * If it is, it must have the same return type and arguments.
     * Method Overloading: -1.
     * Method declaration with no overidding: 0,
     * Method declaration with overidding: 1,
     */
    public int overload_check(String class_name, String method_name){
        /* Get super class methods */
        ClassContent cc = this.class_names.get(class_name);
        Map<String, Vector<String>> s_methods = cc.get_s_methods();

        /* Search if method is declared */
        Vector<String> method_pars = s_methods.get(method_name);
        if(method_pars == null){ // method not declared
            return 0;
        }

        /* Check super's method pars with derived class method pars are the same */
        if(method_pars.size() != temp_method_pars.size()){
            return -1; // method overloading
        }
        else{
            for(int i = 0; i < method_pars.size(); i++){ // check if all argument types are the same
                if(!(method_pars.elementAt(i).equals(temp_method_pars.elementAt(i))))
                    return -1; // found different type
            }
        }

        return 1;
    }

    /* Clears the temporary method parameters */
    public void clear_temp_pars(){
        temp_method_pars = null;
    }

    public void print_offsets(){

        System.out.println("Printing Class Offsets:");
        /* Print offset for all classes' fields and methods */
        for (Map.Entry<String, ClassContent> entry : this.class_names.entrySet()){
            String class_name = entry.getKey();
            System.out.println("\n\n- Class " + class_name + " -");

            ClassContent cc = entry.getValue();
            
            /* Print fields */
            Vector<NameOffset> f_offsets = cc.get_f_offsets(); 
            System.out.println("  -- Fields --");

            for(NameOffset field : f_offsets){
                System.out.println("    " + class_name + "." + field.get_name() + " : " + field.get_offset());
            }

            /* Print methods */
            Vector<NameOffset> m_offsets = cc.get_m_offsets(); 
            System.out.println("  -- Methods --");

            for(NameOffset field : m_offsets){
                System.out.println("    " + class_name + "." + field.get_name() + " : " + field.get_offset());
            }
        }
    }
}
