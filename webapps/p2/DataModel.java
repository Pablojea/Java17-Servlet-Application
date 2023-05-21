package p2;

import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.IOException;
import java.io.File;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;

public class DataModel {

    String urlInicial;
    ArrayList<String> urls; //Contiene las urls de los ficheros válidos (well-formed) 
    DocumentBuilderFactory dbf;
    DocumentBuilder db;    
    XPathFactory xpathFactory;   
    XPath xpath;  

    public DataModel(String rutaServlet, String urlInicial) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        this.urlInicial = urlInicial;
        urls = new ArrayList<String>();  

        String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	    String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";	    
        File mumlSchema = new File (rutaServlet + "MuML.xsd");        

        xpathFactory = XPathFactory.newInstance();
        xpath = xpathFactory.newXPath();

        dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
		dbf.setNamespaceAware(true);
		dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);        
		dbf.setAttribute(JAXP_SCHEMA_SOURCE, mumlSchema); 
        db = dbf.newDocumentBuilder();
        db.setErrorHandler(new ErrorHandler());

        System.out.println("\nDatamodel creado con éxito\n");        

    }

    void leerFicheros() throws XPathExpressionException{    
        
        // rellena el arraylist de urls well-formed a parsear
        obtenerUrls(urlInicial);

        // parsea los ficheros y almacena sus datos
        System.out.println("");
        System.out.println("");
        System.out.println("");

        for(String file: urls){

            System.out.println(file);

        }

    }

    // Obtiene de forma recursiva una lista de todos los urls de archivos well-formed  
    void obtenerUrls(String url) throws XPathExpressionException{      
         
        // guarda el prefijo de la url actual para crear futuras url
        int lastSlash = url.lastIndexOf("/");
        String prefijoInicial = url.substring(0, lastSlash + 1);     

        // Si el archivo es well-formed obtiene sus elementos MuML (otras urls)     
        try{

            System.out.println("Parseando " + url);
            Document doc = db.parse(url);            

            // comprobamos si contiene errores
            if(ErrorHandler.getError() != 0){

                System.out.println("El fichero " + url + " contiene errores del tipo: " + ErrorHandler.getError());
                ErrorHandler.cleanErrors();                

            }
            else{

                System.out.println("----- ARCHIVO OK -----");

                if(!urls.contains(url)){
                
                    urls.add(url);

                    // buen sitio para leer los datos de los ficheros a usar
                    obtenerDatos(doc);

                    
                }
            
                NodeList nlMuml = doc.getElementsByTagName("MuML");

                for(int mumlUrl = 0; mumlUrl < nlMuml.getLength(); mumlUrl++){

                    Element eleMuml = (Element)nlMuml.item(mumlUrl); 
                    String newUrl = eleMuml.getTextContent().indexOf("http") < 0 ? prefijoInicial + eleMuml.getTextContent() : eleMuml.getTextContent();
                    
                    if(!urls.contains(newUrl)){ 

                        obtenerUrls(newUrl);

                    }            
                }
            }     
        }
        catch(SAXException se) {

            System.out.println("----- NO WELL-FORMED:   " + url + "     -----");             
            return;          
            
        }
        catch(IOException ioe){

            ioe.printStackTrace();

        }      
        
    }


    // recibe un Document con el contenido de un archivo xml. Leerá sus álbumes y canciones y los volcará en los ArrayLists
    void obtenerDatos(Document archivo) throws XPathExpressionException{  

        NodeList temp;
        String exp;

        // obtiene el año del fichero
        exp = "Music/Year";
        temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);         
        System.out.println("Año :   " + temp.item(0).getTextContent().trim()); 

        
        // obtiene los álbumes del fichero
        NodeList nlAlbum = archivo.getElementsByTagName("Album");

        for(int i = 0; i < nlAlbum.getLength(); i++){

            System.out.println("\nLeyendo un álbum: \n\n");
            Element eleAlbum = (Element)nlAlbum.item(i); 

            // obtiene el id del album
            System.out.println("id de album =  " + eleAlbum.getAttribute("aid"));

            // obtiene el formato del album
            System.out.println("formatos del album =  " + eleAlbum.getAttribute("format"));

            // obtiene el nombre del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Name";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);         
            System.out.println("Nombre :   " + temp.item(0).getTextContent().trim());

            // obtiene el pais del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Country";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);         
            System.out.println("País :   " + temp.item(0).getTextContent().trim());

            // obtiene el cantante/grupo del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Singer";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);  
            if(temp.item(0) != null)       
            System.out.println("Cantante :   " + temp.item(0).getTextContent().trim());

            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Group";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            if(temp.item(0) != null)            
            System.out.println("Grupo :   " + temp.item(0).getTextContent().trim());

            // obtiene el ISBN del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/ISBN";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);                 
            System.out.println("ISBN :   " + temp.item(0).getTextContent().trim());

            // obtiene la compañía del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Company";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            if(temp.item(0) != null)                  
            System.out.println("Compañía :   " + temp.item(0).getTextContent().trim());

            // obtiene la review del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);

            if(temp.item(0) != null){ 

                NodeList temp3;
                temp3 = temp.item(0).getChildNodes();

                for(int w = 0; w <temp3.getLength(); w++){

                    if( temp3.item(w).getNodeType()==Node.TEXT_NODE &&
                        temp3.item(w).getTextContent()!=null &&
                        temp3.item(w).getTextContent().trim()!=""){
                        
                        System.out.println("    Review: " + temp3.item(w).getTextContent().trim());

                        }

                }
            }

            // obtiene las canciones del álbum
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            
            
            for(int z = 0; z < temp.getLength(); z++){

                NodeList temp2;

                System.out.println("\n   Leyendo una canción");
                Element eleSong = (Element)temp.item(z);         

                // obtiene los atributos de la canción       
                System.out.println("        sid:   " + eleSong.getAttribute("sid").trim());
                System.out.println("        lang:   " + eleSong.getAttribute("lang").trim());

                // obtiene el título de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Title";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);                
                System.out.println("        Título :   " + temp2.item(0).getTextContent().trim());

                // obtiene la duración de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Duration";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);                
                System.out.println("        Duración :   " + temp2.item(0).getTextContent().trim());

                // obtiene los géneros de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Genre";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);   

                for(int x = 0; x < temp2.getLength(); x++){

                    System.out.println("        Género :   " + temp2.item(x).getTextContent().trim());

                }       

                // obtiene el compositor de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Composer";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);                
                System.out.println("        Composer :   " + temp2.item(0).getTextContent().trim());


            }

        }

    }


    // ahora mismo se crean manualmente varios idiomas para poder mostrar algo en la primera pantalla
    static ArrayList<String> getQ2Langs(){

        ArrayList<String> langs = new ArrayList<>();
        langs.add("es");
        langs.add("en");
        langs.add("fr");
        langs.add("it");
        langs.add("de");
        langs.add("gl");

        return langs;

    }

    // en este momento see crean algunas canciones falsas para algunos de los idiomas creados
    // de momento sólo contienen título, idioma, y sid, para poder continuar a la última pantalla
    static ArrayList<Song> getQ2Songs(String lang){

        ArrayList<Song> allSongs = new ArrayList<>();

        for(int i = 0; i < 5; i++){

            Song newSong1 = new Song(
                "song es" + i,
                300,
                null,
                "Sawano",
                null,
                "es" + i,
                "es"
            );

            Song newSong2 = new Song(
                "song en" +i,
                300,
                null,
                "Sawano",
                null,
                "en" + i,
                "en"
            );

            allSongs.add(newSong1);
            allSongs.add(newSong2);
        }


        ArrayList<Song> langSongs = new ArrayList<>();

        for(Song song: allSongs){

            if(song.getLang().equals(lang)){
                langSongs.add(song);
            }
            
        }

        return langSongs;
    }



    static ArrayList<Album> getQ2Albums(String lang, String sid){

        ArrayList<Album> albums = new ArrayList<>();
        String tmpCompany ="";


        for(int i = 0; i < 10; i++){
            albums.add(new Album("album sony "+i, "spain", "sony records", "buena wea"));
        }

        for(int i = 0; i < 10; i++){
            albums.add(new Album("album jony "+i, "spain", "jony records", "bacano"));
        }

        for(int i = 0; i < 10; i++){
            Album newAlbum =new Album("album honey "+i, "spain", "honey records", "buena wea");
            newAlbum.addSong(new Song("megalocumbia", lang, sid));
            albums.add(newAlbum);
        }


        for(Album album: albums){

            if(album.hasSong(lang, sid)){
                tmpCompany = album.getCompany();
                break;
            }

        }

        ArrayList<Album> songAlbums = new ArrayList<>();

        for(Album album: albums){
            
            if(album.getCompany().equals(tmpCompany)){
                songAlbums.add(album);
            }
        }

        return songAlbums;

    }

}