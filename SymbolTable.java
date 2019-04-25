/*******************************/
/* SymbolTable.java */

/* Name:    Andreas Charalampous
 * A.M :    1115201500195
 * e-mail:  sdi1500195@di.uoa.gr
 */
/********************************/
import java.util.*;


/* Implementation of a Symbol Table that it will be used for semantic check for MiniJava Language */

public class SymbolTable{

    Set<String> primitive_t; // Primitive types of language (int, boolean, int[])
    Map<String, ClassContent> class_names; // Contains all class_names(types) declared in src file
    Map<String, Vector<String>> subtypes; // Keeps all the pairs of <derived class, super classes> 
    Set<String> unknown_t; // Set that holds unknown types declared in classes, to be checked later

    Vector<NameType> temp_method_pars; // Vector that holds parameters of the next method to be stored
    Map<String, String> current_scope; // Holds all the declared variables with their type, in current scope


    
    /* Constructor */
    public SymbolTable(){
        class_names = new LinkedHashMap<>();
        subtypes = new HashMap<>();
        primitive_t = new HashSet<>(Arrays.asList("int", "boolean", "int[]"));
        unknown_t = new HashSet<>();
        temp_method_pars = null;
        current_scope = null;
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

    /* Adds a new pair of subtyping classes in map after all variables/methods of super_class are stored in derived. */ 
    public void store_subtype(String derived_class, String super_class){
        /* Get both's content */
        ClassContent cc = class_names.get(derived_class);
        ClassContent scc = class_names.get(super_class);

        /* Inherite super's variables */ 
        Map<String, String> s_variables = scc.get_variables();
        cc.inherite_variables(s_variables);

        /* Inherite super's s_variables(super of super) */
        s_variables = scc.get_s_variables();
        cc.inherite_variables(s_variables);
        
        /* Inherite super's methods */ 
        Map<String, Vector<NameType>> s_methods = scc.get_methods();
        cc.inherite_methods(s_methods);

        /* Inherite super's s_methods(super of super) */
        s_methods = scc.get_s_methods();
        cc.inherite_methods(s_methods);

        /* Set offsets */
        cc.set_v_offset(scc.get_v_next_offset());
        cc.set_m_offset(scc.get_m_next_offset());
        
        /* Create vector that will keep all superclasses of derived_class */
        Vector<String> superclasses = new Vector<>();
        superclasses.add(super_class); // insert "mother" class

        Vector<String> s_superclasses = subtypes.get(super_class);
        /* If there are "grandmother" classes, store them as superclasses of derived_class */
        if(s_superclasses != null){
            for (String s_class : s_superclasses){
                superclasses.add(s_class);
            }
        }

        subtypes.put(derived_class, superclasses);
    }
    
    
    /*
     * Given a class_name and a variables type + name, it will store the variable in class' content.
     * Will be checked if the variable is redecleared. If yes, then returns fatal error.
     * Correct store: 0,
     * Class doesnt exists: -1,
     * Variable redeclared: -2.
     */
    public int add_class_variable(String class_name, String type, String name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }
        
        /* Check if name was redecleared */
        Map<String, String> class_variables = cc.get_variables();
        if(class_variables.containsKey(name)){ 
            return -2;
        }
        
        /* Add variables and it's offset */
        class_variables.put(name, type);
        cc.add_v_offset(type, name);
        
        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(type)){ // check if type of variable is primitive
            unknown_t.add(type);
        }
        return 0;
    }
    
    
    /* Given a class and a method's name and return type, checks if method is redecleared in class.
     * Then the temp_methdo_pars are initialized, in order to keep methods return type and argument.
     * Valid method: 0.
     * Invalid Class: -1,
     * Method Redeclared: -2,
     */
    public int check_class_method(String class_name, String return_type, String name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }

        Map<String, Vector<NameType>> class_methods = cc.get_methods();
        if(class_methods.containsKey(name)){ // check if method was redecleared
            return -2;
        }
        
        /* Create new vector and place return type at 0 index */
        temp_method_pars = new Vector<>();
        temp_method_pars.add(new NameType(null, return_type));
        
        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(return_type)){ // check if type of variable is primitive
            unknown_t.add(return_type);
        }
        return 0;
    }
    
    /* Add given type to current method's arguments, if there is no redeclaration
     * Valid argument : 0
     * Argument redeclaration: -1
     */
    public int add_method_par(String type, String name){

        /* Check if argument is redeclared */
        for(NameType arg : temp_method_pars){
            if(name.equals(arg.get_name())) // found argument with the same name
                return -1;
        }
        temp_method_pars.add(new NameType(name, type)); // push back type
        
        /* If type is unknown, may be declared later. Save it to check after first visitor pass */
        if(!primitive_t.contains(type)){ // check if type of variable is primitive
            unknown_t.add(type);
        }

        return 0;
    }
    
    
    /* Assignes to given class name the filled temp_parameter vector */
    public int add_class_method(String class_name, String method_name){
        /* Get class content */
        ClassContent cc = this.class_names.get(class_name);
        if(cc == null){ // class name not found
            return -1;
        }
        
        /* Check if method overloads super's class method */
        int result = overload_check(class_name, method_name);
        if(result < 0){
            return result;
        }
        
        cc.add_method(method_name, temp_method_pars);

        /* Add offset if method is not overidding super's */
        if(result == 0){
            cc.add_m_offset(method_name);
        }

        clear_temp_pars();

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
        Map<String, Vector<NameType>> s_methods = cc.get_s_methods();
        
        /* Search if method is declared */
        Vector<NameType> method_pars = s_methods.get(method_name);
        if(method_pars == null){ // method not declared
            return 0;
        }
        
        /* Check super's method pars with derived class method pars are the same */
        if(method_pars.size() != temp_method_pars.size()){
            return -1; // method overloading
        }
        else{
            for(int i = 0; i < method_pars.size(); i++){ // check if all argument types are the same
                String s_arg_type = method_pars.elementAt(i).get_type();
                String arg_type = temp_method_pars.elementAt(i).get_type();
                if(!(arg_type.equals(s_arg_type)))
                    return -1; // found different type
                }
        }
        
        return 1;
    }
    
    /*
    * After the first visitor pass, where all class types are collected. If a class has a variable/method of
    * class type, it should be stored in unknown set. All names in unknown set are checked if are declared
    * in class_names.
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
     
    /* Prints all classes' variables and methods offsets */
    public void print_offsets(){
        System.out.println("Printing Class Offsets:");
        /* Print offset for all classes' variables and methods */
        for (Map.Entry<String, ClassContent> entry : this.class_names.entrySet()){
            String class_name = entry.getKey();
            System.out.println("\n\n- Class " + class_name + " -");
            
            ClassContent cc = entry.getValue();
            
            /* Print variables */
            Vector<NameOffset> v_offsets = cc.get_v_offsets(); 
            System.out.println("  -- Variables --");

            for(NameOffset var : v_offsets){
                System.out.println("    " + class_name + "." + var.get_name() + " : " + var.get_offset());
            }

            /* Print methods */
            Vector<NameOffset> m_offsets = cc.get_m_offsets(); 
            System.out.println("  -- Methods --");
            
            for(NameOffset method : m_offsets){
                System.out.println("    " + class_name + "." + method.get_name() + " : " + method.get_offset());
            }
        }
    }

    /* Inserts a new variable name and type in current scope. 
     * If the name is redeclared or undefined type, return error.
     * Valid Declaration: 0.
     * Redeclaration: -1,
     * Undefined type: -2,
     */

    public int insert(String type, String name){
        /* Check for redeclaration */
        if(current_scope.containsKey(name))
            return -1;
        
        /* Check if valid type(primitive or class) */
        if((!primitive_t.contains(type)) && (!class_names.containsKey(type)))
            return -2;

        /* Insert variable in symbol table */
        current_scope.put(name, type);

        return 0;
    }

    /* Inserts given's class method's arguments in current scope hash map */
    public void insert_arguments(String class_name, String method_name){
        ClassContent cc = class_names.get(class_name);
        Vector<NameType> arguments = (cc.get_methods()).get(method_name);
        
        /* Add every argument in hashmap */
        for(NameType arg : arguments){
            insert(arg.get_type(), arg.get_name());
        }
    }

    public boolean is_valid_type(String type){
        return class_names.containsKey(type);
    }
    
    /* Accesors */
    public Map<String, ClassContent> get_class_names(){ return class_names; }
    public Map<String, Vector<String>> get_subtypes(){ return subtypes; }
    
    
    /* Mutators */
    /* Adds a new class_name to set and initialize its content */
    public void store_class(String new_class){ class_names.put(new_class, new ClassContent()); } 
    public void clear_temp_pars(){ temp_method_pars = null; }
    public void enter_scope(){ current_scope = new HashMap<>(); }
    public void exit_scope(){ current_scope = null;}
}