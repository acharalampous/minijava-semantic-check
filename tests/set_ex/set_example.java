package tests.set_ex;

public class set_example{
    
    public static void main(String[] args) {
        myset temp_set = new myset(); 
        set_functions f = new set_functions();    
        f.ref_set(temp_set);
        temp_set.add("TEST1");
        temp_set.add("TEST2");
        System.out.println(temp_set.get_set());
        f.foo();
        temp_set.add("TEST3");
        temp_set.add("TEST4");
        temp_set.add("TEST5");
        System.out.println(temp_set.get_set());

        
    }
}