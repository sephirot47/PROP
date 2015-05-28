package Persistencia;

////IMPORTS /////////////////////////////////////
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import Domini.Community;
import Domini.Node;
import Domini.Pair;
import Domini.Reproduction;
import Domini.Solution;
import Domini.SongSolution;
import Domini.Song;
import Domini.Edge;
import Domini.User;

public class FileManager
{
	//// METODES ///////////////////////////////////

    //path apunta a un .txt a la carpeta del projecte
    // es retorna una llista d'strings, cada element es una linia del fitxer de text

    public static ArrayList<String> loadData(String path) throws IOException
    {
    	
    	if (!new File(path).exists()) {
    		saveData(path,new ArrayList<String>());
    	}
        ArrayList<String> list = new ArrayList<String>(); //Creem array
        
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        FileReader fReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fReader); //Creem un buffer on desar cada l
        
        String l = "";
        while ((l = reader.readLine()) != null){
            list.add(l); //Afegim a la llista
        }
        
        reader.close();
		
        return list;   
    }
    
    // path es el nom.txt del fitxer a guardar
    // Es sobreescriu el fitxer amb el contingut de la llista
    public static void saveData(String path, ArrayList<String> list) throws IOException
    {
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        File file = new File(path);
        if(!file.exists()) file.getParentFile().mkdirs(); //Not sure al 100% que fa eso, pero pels puestos posava que era per crear el susodicho fitxer
        
        FileWriter writer = new FileWriter(path);
        
        for (int i = 0; i < list.size(); ++i)
        {
            writer.write(list.get(i));
            writer.write("\r\n");
        }
        writer.close();    
    }
    
    //path es un path a un fitxer existent
    //path deixa d'existir
    public static boolean eraseData(String path) 
    {
    	  Path fileToLoad = Paths.get(path); //generem un path amb l'string
          fileToLoad = fileToLoad.toAbsolutePath();
          path = fileToLoad.toString();
          File file = new File(path);
          return file.delete();
    }
    
    public static boolean deleteDirectory(String path) {
    	File directory = new File(path);
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i].getAbsolutePath());
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    public static ArrayList<String> listFilesOf(String path)
    {
    	Path fpath = Paths.get(path); //generem un path amb l'string
    	fpath = fpath.toAbsolutePath();
        File file = new File( fpath.toString() );
        ArrayList<String> content = new ArrayList<String>();
        String[] sss = file.list();
        for(String fileInDir : sss) content.add(fileInDir);
        return content;
    }
    
    //path es un path no nul
    //retorna cert si path existeix
    public static boolean exists(String path)
    {
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        File file = new File(path);
        file.getParentFile().mkdirs();
        if(file.exists()) return true; return false;
    }
    
    public static void createDir(String path)
    {
    	new File(path).mkdirs();
    }
    
    //path es un cami a un fitxer existent, newLine conte una linia de text
    //newLine queda concatenat a la ultima linia del fitxer de path
    public static void addData(String path, String newLine) throws IOException
    {
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        FileWriter w = new FileWriter(path, true);
        w.write(newLine);
        w.write("\r\n");
        w.close();
    	
    }    
  
}
