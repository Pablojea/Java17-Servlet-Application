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
    static ArrayList<Album> albumes;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;    
    XPathFactory xpathFactory;   
    XPath xpath;  

    public DataModel(String rutaServlet, String urlInicial) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        this.urlInicial = urlInicial;
        urls = new ArrayList<String>();  
        albumes = new ArrayList<Album>();

        String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	    String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";	    
        File mumlSchema = new File (rutaServlet + "MuML.xsd");        

        xpathFactory = XPathFactory.newInstance();
        xpath = xpathFactory.newXPath();

        dbf = DocumentBuilderFactory.newInstance();
        //dbf.setValidating(false);
		//dbf.setNamespaceAware(true);
		//dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);        
		//dbf.setAttribute(JAXP_SCHEMA_SOURCE, mumlSchema); 
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
            System.out.println(se);  
                    
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
        String anho = temp.item(0).getTextContent().trim();   
        System.out.println("Año :   " + anho); 

        
        // obtiene los álbumes del fichero
        NodeList nlAlbum = archivo.getElementsByTagName("Album");

        for(int i = 0; i < nlAlbum.getLength(); i++){

            Album newAlbum = new Album();
            newAlbum.setYear(Integer.parseInt(anho));

            System.out.println("\nLeyendo un álbum: \n\n");
            Element eleAlbum = (Element)nlAlbum.item(i); 

            // obtiene el id del album
            String albumId = eleAlbum.getAttribute("aid");
            newAlbum.setAid(albumId);
            System.out.println("id de album =  " + albumId);

            // obtiene el formato del album
            String formato = eleAlbum.getAttribute("format");
            newAlbum.setFormats(formato);
            System.out.println("formatos del album =  " + formato);

            // obtiene el nombre del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Name";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET); 
            String nombreAlbum = temp.item(0).getTextContent().trim(); 
            newAlbum.setName(nombreAlbum);     
            System.out.println("Nombre :   " + nombreAlbum);

            // obtiene el pais del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Country";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);  
            String paisAlbum = temp.item(0).getTextContent().trim();    
            newAlbum.setCountry(paisAlbum);
            System.out.println("País :   " + paisAlbum);

            // obtiene el cantante/grupo del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Singer";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            
            if(temp.item(0) != null){ 

                String cantante = temp.item(0).getTextContent().trim();
                newAlbum.setSinger(cantante);   
                System.out.println("Cantante :   " + cantante);

            }

            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Group";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            if(temp.item(0) != null){
                
                String grupo = temp.item(0).getTextContent().trim();
                newAlbum.setGroup(grupo);
                System.out.println("Grupo :   " + grupo);
            }

            // obtiene el ISBN del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/ISBN";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET); 
            String isbn = temp.item(0).getTextContent().trim();   
            newAlbum.setIsbn(isbn);             
            System.out.println("ISBN :   " + isbn);

            // obtiene la compañía del album
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Company";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            if(temp.item(0) != null){  

                String companhia = temp.item(0).getTextContent().trim();  
                newAlbum.setCompany(companhia);                       
                System.out.println("Compañía :   " + companhia);
            }

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
                        
                        String review = temp3.item(w).getTextContent().trim();
                        newAlbum.setReview(review);
                        System.out.println("    Review: " + review);

                        }

                }
            }

            // obtiene las canciones del álbum
            exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song";
            temp = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
            
            
            for(int z = 0; z < temp.getLength(); z++){

                NodeList temp2;

                System.out.println("\n   Leyendo una canción");
                Song newSong = new Song();
                Element eleSong = (Element)temp.item(z);         

                // obtiene los atributos de la canción 
                String songId = eleSong.getAttribute("sid").trim();
                String songLang = eleSong.getAttribute("lang").trim();
                newSong.setSid(songId);
                newSong.setLang(songLang);
                System.out.println("        sid:   " + songId);
                System.out.println("        lang:   " + songLang);

                // obtiene el título de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Title";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);    
                String songTitle = temp2.item(0).getTextContent().trim();   
                newSong.setTitle(songTitle);       
                System.out.println("        Título :   " + songTitle);

                // obtiene la duración de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Duration";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);
                int songDuration = Integer.parseInt(temp2.item(0).getTextContent().trim());   
                newSong.setDuration(songDuration);         
                System.out.println("        Duración :   " + songDuration);

                // obtiene los géneros de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Genre";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);   
                String generos = "";
                for(int x = 0; x < temp2.getLength(); x++){

                    System.out.println("        Género :   " + temp2.item(x).getTextContent().trim());
                    generos += " " + temp2.item(x).getTextContent().trim(); 

                }     
                newSong.setGenres(generos);  

                // obtiene el compositor de la canción
                exp = "Music/Album[@aid='" + eleAlbum.getAttribute("aid") + "']/Song[@sid='" + eleSong.getAttribute("sid").trim() + "']/Composer";
                temp2 = (NodeList)xpath.evaluate(exp, archivo, XPathConstants.NODESET);   
                String composer = temp2.item(0).getTextContent().trim();   
                newSong.setComposer(composer);          
                System.out.println("        Composer :   " + composer);

                newSong.setAlbum(nombreAlbum);

                newAlbum.addSong(newSong);


            }

            albumes.add(newAlbum);

        }

    }


    // obtiene todos los langs que aparecen en los álbumes (sin repeticiones)
    static ArrayList<String> getQ2Langs(){

        ArrayList<String> langs = new ArrayList<>();

        for (Album album: albumes){

            ArrayList<String> albumLangs = album.getAlbumLangs();

            for(String lang: albumLangs){

                if(!langs.contains(lang))
                langs.add(lang);
            }

        }

        return langs;     

    }

    // Obtiene las canciones en un determinado idioma
    static ArrayList<Song> getQ2Songs(String lang){

        ArrayList<Song> songs = new ArrayList<Song>();

        for(Album album: albumes){

            for(Song song: album.getSongs()){

                if(song.getLang().equals(lang))
                songs.add(song);

            }
        }

        return songs;
        
    }


    // obtiene los álbumes de una compañía con canciones en un determinado idioma
    static ArrayList<Album> getQ2Albums(String lang, String sid){
       
        String company = "";  

        for(Album album: albumes){

            if(album.hasSong(sid)){
                company = album.getCompany();
                break;
            }
        }

        ArrayList<Album> albumesCompanhia = new ArrayList<>();

        for(Album album: albumes){
            
            if(album.getCompany().equals(company) && album.hasSongLang(lang)){

                albumesCompanhia.add(album);

            }
        }

        return albumesCompanhia;
    }

}