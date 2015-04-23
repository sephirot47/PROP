package Presentacio;

////IMPORTS /////////////////////////////////////
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager{

//// CONSTRUCTOR ////////////////////////////////
    public FileManager(){}
    
//// METODES ///////////////////////////////////

    //path apunta a un .txt a la carpeta del projecte
    // es retorna una llista d'strings, cada element es una linia del fitxer de text

    public ArrayList<String> loadData(String path){
        ArrayList<String> list = new ArrayList(); //Creem array
        
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        FileReader fReader;
		try 
		{
			fReader = new FileReader(path);
	        BufferedReader reader = new BufferedReader(fReader); //Creem un buffer on desar cada l
	        
	        String l = "";
	        while ((l = reader.readLine()) != null){
	            list.add(l); //Afegim a la llista
	        }
	        
	        reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
        return list;   
    }
    
    // path es el nom.txt del fitxer que es generara(inexistent) (es poden incloure directoris si existeixen previament)
    // Es crea el fitxer i s'hi escriuen les linies contingudes en l'string
    public void exportData(String path, ArrayList<String> list){
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        File file = new File(path);
        file.getParentFile().mkdirs(); //Not sure al 100% que fa eso, pero pels puestos posava que era per crear el susodicho fitxer
        
        FileWriter writer;
		try 
		{
			writer = new FileWriter(path);
        
	        for (int i = 0; i < list.size(); ++i){
	            writer.write(list.get(i));
	            if (i != (list.size()-1)){
	                writer.write('\n');
	            }
	        }
	        writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}   
    }
    
    // path es el nom.txt del fitxer a guardar
    // Es sobreescriu el fitxer amb el contingut de la llista
    
    public void saveData(String path, ArrayList<String> list){
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        FileWriter writer;
		try 
		{
			writer = new FileWriter(path);
	        
	        for (int i = 0; i < list.size(); ++i){
	            writer.write(list.get(i));
	            if (i != (list.size()-1)){
	                writer.write('\n');
	            }
	        }
	        writer.close();    
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
    
    //TODO
    //path es un path a un fitxer existent
    //path deixa d'existir
    public void eraseData(String Path) {}
    
    //TODO
    //path es un path no nul
    //retorna cert si path existeix
    public void exists(String Path){}
    
    //TODO
    //path es un camí a un fitxer existent, newLine conté una linia de text
    //newLine queda concatenat a la ultima linia del fitxer de path
    public void addData(String Path, String newLine){}    
    
    
    

}

