package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Billing;
@Path("/bill_details")


public class BillingService {
	
	
	Billing billingeObj = new Billing();

	 
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
	 {
		return billingeObj.readbilling();
	 }
	
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertBilling(
		
  @FormParam("AccNo") String AccNo,
  @FormParam("BillUnit") String BillUnit)
	{
		String output = billingeObj.insertbilling( AccNo,  BillUnit );
		return output;
	}
	

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateBilling(String sData)
	{
//Convert the input string to a JSON object (Passing values)
	JsonObject BillingObject = new JsonParser().parse(sData).getAsJsonObject();

 //Read the values from the JSON object
 	String bID = BillingObject.get("bID").getAsString();
 	String AccNo = BillingObject.get("AccNo").getAsString();
 	String BillUnit = BillingObject.get("BillUnit").getAsString();
 
 	String output = billingeObj.updatebilling( bID, AccNo,  BillUnit);
 		return output;
	}



@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String sData)
	{
//Convert the input string to an XML document
	Document doc = Jsoup.parse(sData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String bID = doc.select("bID").text();
 String output = billingeObj.deletebilling(bID);
 		return output;
	}
}
//done