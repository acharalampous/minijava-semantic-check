package tests.set_ex;

public class set_functions{
    
    private myset tset = null;

    public void ref_set(myset given_set){
        tset = given_set;
    }

    public void foo(){
        tset.add("LL");
        tset.add("Element");
        tset.add("Linked_List");
        System.out.println(tset.get_set());
        tset.add("Linked_List");
        tset.add("Linked_List");
        tset.add("Element");
        System.out.println(tset.get_set());
    }


}