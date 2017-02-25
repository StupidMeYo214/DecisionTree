import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sample {

	public String classification;
	public Map<String , Attribute> attributes = new HashMap<String, Attribute> ();
	
	public Sample (String classificationVal, ArrayList<Attribute> attributelist)
	{
		this.classification=classificationVal;
		addAttributes(attributelist);
	}
	
	public void addAttributes(ArrayList<Attribute> attributelist)
	{
		for(int i=0; i<attributelist.size(); i++)
		{
			Attribute a = attributelist.get(i);
			attributes.put(a.getName(), a);//associate the attribute value to attribute name
		}
		
	}
	
	//return attribute list
	public ArrayList<Attribute> getAttributeList()
	{
		ArrayList<Attribute> al=new ArrayList<>();
		for(String key : attributes.keySet())
		{
			al.add(attributes.get(key));
		}
		return al;
	}
	
	public void removeAttribute(String name)//remove attribute according to the attribute name
	{
		attributes.remove(name);
	}
	
	public  Attribute getAttribute(String name)
	{
		return attributes.get(name);
	}
	
	public String getClassification()
	{
		return classification;
	}
	
	public ArrayList<String> getAttributeNames()
	{
		ArrayList<String> AList= new ArrayList<String>();
		
		for(String key : attributes.keySet())
		{
			AList.add(attributes.get(key).getName());
		}
		
		return AList;
	}
	
}
