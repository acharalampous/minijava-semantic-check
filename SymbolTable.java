import java.util.*;

public class SymbolTable{

    Set<String> class_names;
    Map<String, String> subtypes;

    public SymbolTable(){
        class_names = new HashSet<>();
        subtypes = new HashMap<>();
    }

    public void add_class(String new_class){
        class_names.add(new_class);
    }

    public void add_subtype(String derived_class, String super_class){
        subtypes.put(derived_class, super_class);
    }

    public Set<String> get_set(){
        return class_names;
    }

    public Map<String, String> get_subtypes(){
        return subtypes;
    }
}
