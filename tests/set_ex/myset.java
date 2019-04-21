package tests.set_ex;
import java.util.*;


public class myset{
    Set<String> names;

    public myset(){
        names = new HashSet<String>();
        names.clear();
    }

    public void add(String entry){
        names.add(entry);
    }

    public Set<String> get_set(){
        return this.names;
    }
}