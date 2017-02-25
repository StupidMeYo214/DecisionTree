

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class getSampleSet {
	public static  SampleSet getSampleSet(String file)
	{
		InputStream stream=getSampleSet.class.getResourceAsStream(file);
		SampleSet ss=new SampleSet();
		BufferedReader reader = null;
		List<String> attributenames=new ArrayList<String>();
		String classVal="";
		ArrayList<Attribute> attList=new ArrayList<Attribute>();
		try{
			String readerLine;
			reader = new BufferedReader(new InputStreamReader(stream));
			int i=0;
			while((readerLine = reader.readLine())!=null)
			{   
				ArrayList<String> al=CSVtoAL(readerLine);
				if(i==0)
				attributenames=al.subList(0, al.size()-1);
				if(i>0)
				{	
					
					for(int j=0; j<attributenames.size(); j++)
					{
					Attribute attr=new Attribute(attributenames.get(j), al.get(j));
					attList.add(attr);
					classVal=al.get(al.size()-1);
					
					}
					Sample s=new Sample(classVal, attList);
					ss.addSample(s);
				}
				i++;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try{if(reader!=null)
				reader.close();}
			catch(IOException readerException)
			{
				readerException.printStackTrace();
			}
		}
		return ss;
		//System.out.println(ss.classifications);
	}
	
public static ArrayList<String> CSVtoAL(String readerRST)
{
	ArrayList<String> result = new ArrayList<String>();
	
	if(readerRST != null)
	{
		String[] splitdata = readerRST.split("\\s*,\\s*");
		for(int i=0; i<splitdata.length;i++)
		{
			if(!(splitdata[i]==null)||!(splitdata[i].length()==0))
			{
				result.add(splitdata[i].trim());
			}
		}
	}
	return result;
}

}
