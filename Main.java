import syntaxtree.*;
import visitor.*;
import java.io.*;

public class Main {

    public static String file_type_check(String file_name) throws Exception{
        if(file_name == null) // no file given
            throw new Exception("Cannot perform Semantic Analysis. No file given.");
        if(file_name.length() < 6) // invalid file type
            throw new Exception("Cannot perform Semantic Analysis. Invalid File Type given.");

            
        String file_type = file_name.substring(file_name.length() - 5, file_name.length());
        if(!file_type.equals(".java")){ // invalid file type
            throw new Exception("Cannot perform Semantic Analysis. Invalid File Type given.");
            
        }

        /* Find index of file_name in string(if path specified) */
        int file_index = file_name.lastIndexOf("/");

        return file_name.substring(file_index + 1, file_name.length() - 5); // return file name, without .java extension
    }

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
                System.out.println("\n\n\n** " + args[i] + " **");
                String file_name = file_type_check(args[i]);

                fis = new FileInputStream(args[i]);

                MiniJavaParser parser = new MiniJavaParser(fis);
                
                Goal root = parser.Goal();
                System.err.println("\tProgram Parsed Successfully.");
    
                SymbolTable symbol_table = new SymbolTable();
    
                CollectVisitor collect_v = new CollectVisitor(symbol_table, file_name);
                root.accept(collect_v, null);
        
                System.out.println("\tProgram Symanticly Checked.");
            
                String err_type = symbol_table.check_unknown();
                if(err_type != null){
                    throw new Exception("Error: Unknown type " + err_type);
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
