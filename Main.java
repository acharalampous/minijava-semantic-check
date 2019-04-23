import syntaxtree.*;
import visitor.*;
import java.io.*;

public class Main {
    public static void main (String [] args){
        if(args.length != 1){
            System.err.println("Usage: java Main <inputFile>");
            System.exit(1);
        }
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(args[0]);
            MiniJavaParser parser = new MiniJavaParser(fis);
            
            Goal root = parser.Goal();
            System.err.println("Program parsed successfully.\n\n");

            SymbolTable symbol_table = new SymbolTable();

            CollectVisitor vis = new CollectVisitor(symbol_table);
            root.accept(vis, null);

            System.out.println("\n\nDone Semantic Check.\n\n");

            // System.out.println(symbol_table.get_class_names());
            // System.out.println(symbol_table.get_subtypes());

            String err_type = symbol_table.check_unknown();
            if(err_type != null){
                throw new Exception("Undefined reference to type " + err_type + ".");
            }

            symbol_table.print_offsets();
        }
        catch(ParseException ex){
            System.out.println(ex.getMessage());
        }
        catch(FileNotFoundException ex){
            System.err.println(ex.getMessage());
        }
        catch(Exception error_msg){
            System.err.println("\n\n" + error_msg);
        }
        finally{
            try{
                if(fis != null) 
                    fis.close();
                }
            catch(IOException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
}
