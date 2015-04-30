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
import java.nio.file.Path;
import java.nio.file.Paths;

import Domini.Pair;
import Domini.Solution;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongRelation;

public class FileManager
{
	//// METODES ///////////////////////////////////

    //path apunta a un .txt a la carpeta del projecte
    // es retorna una llista d'strings, cada element es una linia del fitxer de text

    public static ArrayList<String> LoadData(String path) throws IOException
    {
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
    public static void SaveData(String path, ArrayList<String> list) throws IOException
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
            if (i != (list.size()-1))  writer.write('\n');
        }
        writer.close();    
    }
    
    public static void SaveSolution(Solution s) throws IOException
    {
    	String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date());
    	String filedir = "data/solutions/solution_" + date + "/";
    	
    	//arxiu de Graph(entrada)
    	SaveEntradaSolution(filedir, s.GetEntrada());

    	//arxiu de solucio(communities)
        SaveCommunitiesSolution(filedir, s.GetSongCommunities());
    	
    	//arxiu de info extra
    	{
    	ArrayList<String> lines = new ArrayList<String>();
    	lines.add( s.GetAlgorisme() ); //Algorisme usat
    	lines.add( String.valueOf( s.GetEntrada().GetAllNodes().size() ) ); //Nombre de cancons processades
    	lines.add( String.valueOf( s.GetGenerationTime() ) ); //Temps que ha tardat a generar la solucio
    	SaveData(filedir + "generationInfo.txt", lines);
    	}
    }
    
    public static void SaveEntradaSolution(String filedir, SongGraph entrada) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	Set<Song> songs = entrada.GetAllNodes();
    	ArrayList<Song> songsArray = new ArrayList<Song>();
    	for(Song s : songs) //Nodes pels que esta format el graph
    	{
    		String line = "(" + s.GetAuthor() + ", " + s.GetTitle() + ")";
    		lines.add(line);
    		
    		songsArray.add(s);
    	}
    	
    	
    	Set<SongRelation> edges = entrada.GetAllEdges();
    	for(SongRelation e : edges)
    	{
    		Pair<Song, Song> songSong = entrada.GetNodesConnectedBy(e);
    		Song s1 = songSong.GetFirst(), s2 = songSong.GetSecond();
    		
    		String line = songsArray.indexOf(s1) + ";" + songsArray.indexOf(s2) + ";" + e.GetWeight();
    		lines.add(line);
    	}
    	
    	SaveData(filedir + "entrada.txt",  lines);
    	
    	//Se guardara, por ejemplo:
    	/*
    	 * (victor, cuando sarpa el hamor)
    	 * (jfons, tramboliko)
    	 * (aina, mesigualno?)
    	 * 0;1;0.5    //del 0 al 1, con peso 0.5
    	 * 1;2;1.3	  //del 1 al 2, con peso 1.3
    	 * (hi ha un edge de la canco del victor al jfons, i del jfons a l'aina)
    	 */
    }

    public static void SaveCommunitiesSolution(String filedir, ArrayList<Set<Song>> songCommunities) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	int i = 0;
    	for(Set<Song> songs : songCommunities)
    	{
    		lines.add( String.valueOf(i++) );
	    	for(Song s : songs)
	    	{
	    		String line = "(" + s.GetAuthor() + ", " + s.GetTitle() + ")";
	    		lines.add(line);
	    	}
    	}
    	SaveData(filedir + "communitiesSolution.txt",  lines);
    }
    
    //path es un path a un fitxer existent
    //path deixa d'existir
    public static boolean EraseData(String path) {
    	  Path fileToLoad = Paths.get(path); //generem un path amb l'string
          fileToLoad = fileToLoad.toAbsolutePath();
          path = fileToLoad.toString();
          File file = new File(path);
          return file.delete();
    }
    
    //path es un path no nul
    //retorna cert si path existeix
    public static boolean Exists(String path){
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        File file = new File(path);
        file.getParentFile().mkdirs();
        if(file.exists()) return true; return false;
    	
    }
    
    //path es un camí a un fitxer existent, newLine conté una linia de text
    //newLine queda concatenat a la ultima linia del fitxer de path
    public static void AddData(String path, String newLine) throws IOException{
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        FileWriter w = new FileWriter(path, true);
        w.write(newLine);
        w.write("\n");
        w.close();
    	
    }    
}

