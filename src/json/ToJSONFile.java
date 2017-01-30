/**
*Class:             ToJSONFile.java
*Project:           Node Visualizer
*Author:            Jason Van Kerkhoven                                             
*Date of Update:    20/01/2016                                              
*Version:           1.0.1                                                      
*                                                                                   
*Purpose:           Basic interface to denote an object can be wrttien to a .json
*					file and can be read from a .json file.
* 
*Update Log:		v1.0.1
*						- added method for converting .json from raw bytes
*					v1.0.0
*						- null
*/
package json;


public interface ToJSONFile
{
	//write
	public JsonFile toJSON(String baseOffset);
	
	//read
	public void fromJSON(String JsonFile) throws JsonException;
	
	//read
	public void fromJSON(byte[] JsonFile) throws JsonException;
}