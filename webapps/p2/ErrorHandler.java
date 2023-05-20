package p2;

import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.ParserConfigurationException;


class ErrorHandler extends DefaultHandler
{
	static int error;

	  
	public ErrorHandler()
	{
        error = 0;
	}

	public void warning(SAXParseException spe) 
	{				
        System.out.println("\nWARNING DETECTADO\n" + spe.toString());    
		error = 1;
	}

	public void error(SAXParseException spe) 
	{	
        System.out.println("\nERROR DETECTADO\n" + spe.toString());    
		error = 2;
	}

	public void fatalerror(SAXParseException spe) 
    {		
        System.out.println("\nFATAL ERROR DETECTADO\n" + spe.toString());    
		error = 3;
	}

	static public int getError()
	{
		return error;
	}

	static public void cleanErrors()
	{
		error = 0;
	}
}