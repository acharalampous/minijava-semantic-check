import syntaxtree.*;
import visitor.*;
import java.io.*;

public class Main {

    public static void main (String[] args){
        if(args.length < 1){
            System.err.println("Usage: java Main <inputFile1> <inputFile2> .. <inputFileN>");
            System.exit(1);
        }

        System.out.println("Valid execution. Performing Semantic Analysis on files given.");
        /* Perform Semantic Check on all files provided */
        for(int i = 0; i < args.length; i++){
            FileInputStream fis = null;
            try{
                System.out.println("\n\n\n");
                for(int j = 0; j < args[i].length() + 6; j++)
                    System.out.print("*");
                
                System.out.flush();

                System.out.println("\n** " + args[i] + " **");

                for(int j = 0; j < args[i].length() + 6; j++)
                    System.out.print("*");
                
                System.out.flush();
                System.out.println("\n");
                

                fis = new FileInputStream(args[i]);

                MiniJavaParser parser = new MiniJavaParser(fis);
                
                Goal root = parser.Goal();
                System.err.println("  ->Program Parsed Successfully.");
    
                SymbolTable symbol_table = new SymbolTable();
    
                CollectVisitor collect_v = new CollectVisitor(symbol_table);
                root.accept(collect_v, null);
        
                System.out.println("  ->Program Symanticly Checked.");
            
                String err_type = symbol_table.check_unknown();
                if(err_type != null){
                    throw new Exception("Error: Unknown type " + err_type);
                }
        
                CheckVisitor check_v = new CheckVisitor(symbol_table);
        
                root.accept(check_v, null);
                    
                symbol_table.print_offsets();
            }
            catch(ParseException ex){
                System.out.println("\n\t** " + ex.getMessage());
            }
            catch(FileNotFoundException ex){
                System.err.println("\n\t** " + ex.getMessage());
            }
            catch(Exception error_msg){
                System.err.println("\n\t** " + error_msg.getMessage());
            }
            finally{
                try{
                    if(fis != null) 
                        fis.close();
                    }
                catch(IOException ex){
                    System.err.println("\n\t** " + ex.getMessage());
                }
            }
        }
    }
}
