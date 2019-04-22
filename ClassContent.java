import java.util.*;

public class ClassContent{
    Map<String, String> fields; // Maps the field's name of a Class with it's type
    Map<String, String> s_fields; // Like fields, but holds fields of superclasses

    Map<String, Vector<String>> methods; // Maps the method's name with its return type and arguments. Always on 0 its the return type
    Map<String, Vector<String>> s_methods; // Like methods, but holds methods of superclasses    
    
    Vector<NameOffset> f_offsets; // Vector that holds Pairs of <field, offset>
    Vector<NameOffset> m_offsets; // Vector that holds Pairs of <method, offset>

    int f_next_offset; // Offset of next field
    int m_next_offset; // Offset of next method

    /* Constructor */
    public ClassContent(){
        fields = new HashMap<>();
        s_fields = new HashMap<>();

        methods = new HashMap<>();
        s_methods = new HashMap<>();

        f_offsets = new Vector<>();
        m_offsets = new Vector<>();

        f_next_offset = 0;
        m_next_offset = 0;

    }

    public void add_method(String name, Vector<String> parameters){
        methods.put(name, parameters);
    }

    public void add_s_method(String name, Vector<String> parameters){
        s_methods.put(name, parameters);
    }

    public void add_f_offset(String type, String name){
        /* Add new field and offset */
        f_offsets.add(new NameOffset(name, this.f_next_offset));
        
        /* Compute new next offset */
        int offset = 0;
        if(type.equals("int"))
            offset = 4;
        else if(type.equals("boolean"))
            offset = 1;
        else
            offset = 8;

        this.f_next_offset += offset;
    }

    public void add_m_offset(String name){
        m_offsets.add(new NameOffset(name, this.m_next_offset));

        this.m_next_offset += 8; // increase next offset with a size of pointer
    }

    public void set_f_offset(int off){
        this.f_next_offset = off;
    }

    public void set_m_offset(int off){
        this.m_next_offset = off;
    }

    public Map<String, String> get_fields(){
        return fields;
    }

    public Map<String, String> get_s_fields(){
        return s_fields;
    }

    public Map<String, Vector<String>> get_methods(){
        return methods;
    }
    
    public Map<String, Vector<String>> get_s_methods(){
        return s_methods;
    }

    public int get_f_next_offset(){
        return f_next_offset;
    }

    public int get_m_next_offset(){
        return m_next_offset;
    }

    public Vector<NameOffset> get_f_offsets(){
        return f_offsets;
    }

    public Vector<NameOffset> get_m_offsets(){
        return m_offsets;
    }



    public void inherite_fields(Map<String, String> s_fields){
        this.s_fields.putAll(s_fields);
    }
    public void inherite_methods(Map<String, Vector<String>> s_methods){
        this.s_methods.putAll(s_methods);
    }


    
}