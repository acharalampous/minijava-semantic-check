import syntaxtree.*;
import visitor.*;
import java.io.*;

public class Main {

    public String file_type_check(String file_name) throws Exception{
        if(file_name == null) // no file given
            throw new Exception("No file provided");
        if(file_name.length() < 6) // invalid file type
            return null;

        String file_type = file_name.substring(file_name.length() - 5, file_name.length() - 1);
        if(file_type != ".java"){ // invalid file type
            return null;
        }        

        return file_name.substring(0, file_name.length() - 5);
    }
    public static void main (String [] args){
        if(args.length < 1){
            System.err.println("Usage: java Main <inputFile1> <inputFile2> .. <inputFileN>");
            System.exit(1);
        }

        System.out.println("Valid executon. Performing Semantic Analysis on files given.");
        /* Perform Semantic Check on all files provided */
        for(int i = 0; i < args.length; i++){
            FileInputStream fis = null;
            try{
                System.out.println("\n\n\n** " + args[i] + "**");
                
                fis = new FileInputStream(args[0]);

                MiniJavaParser parser = new MiniJavaParser(fis);
                
                Goal root = parser.Goal();
                System.err.println("\tProgram parsed successfully.\n\n");
    
                SymbolTable symbol_table = new SymbolTable();
    
                CollectVisitor collect_v = new CollectVisitor(symbol_table);
                root.accept(collect_v, null);
        
                System.out.println("\n\nDone Semantic Check.\n\n");
            
                String err_type = symbol_table.check_unknown();
                if(err_type != null){
                    throw new Exception("Undefined reference to type " + err_type + ".");
                }
        
                CheckVisitor check_v = new CheckVisitor(symbol_table);
        
                root.accept(check_v, null);
                    
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
}
