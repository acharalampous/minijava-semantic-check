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

            System.out.println("\n\nDone Semantic Check. These where stored:");

            System.out.println(symbol_table.get_set());
            System.out.println(symbol_table.get_subtypes());

            System.out.println("\n\nUnknown Types:");

            String err_type = symbol_table.check_unknown();
            if(err_type != null){
                System.out.println("Undefined reference to " + err_type);
            }

            symbol_table.print_offsets();
        }
        catch(ParseException ex){
            System.out.println(ex.getMessage());
        }
        catch(FileNotFoundException ex){
            System.err.println(ex.getMessage());
        }
        catch(Exception ex){
            System.err.println("sa");
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
