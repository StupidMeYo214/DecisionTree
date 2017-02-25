import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;


import java.lang.Math;

public class SampleSet {
	public Vector<Sample> samples = new Vector<Sample>();//put samples into a vector list, giving them index
	public HashSet<String> classifications = new HashSet<String>();//collect classification types in sample sets.
	public ArrayList<String> attributeNames = new ArrayList<String>();//collect attribute(names) types in sample set.
	
	public void addSample(Sample s)
	{
		samples.add(s);
		if((samples.isEmpty()) || !classifications.contains(s.getClassification()))
		{
			classifications.add(s.getClassification());
		}
		
		if(samples.isEmpty())
		{
			attributeNames=s.getAttributeNames();
		}
		else
		{
			for(int i=0; i<s.getAttributeNames().size();i++)
			{
				if(!attributeNames.contains(s.getAttributeNames().get(i)))
						{
					attributeNames.add(s.getAttributeNames().get(i));
						}
			}
		}
	}
		
	public int getSize()
	{
		return samples.size();
	}
	
	public boolean isEmpty()
	{
		return samples.isEmpty();
	}
	
	public Sample getSample(int i)//return sample in sample set according to the index
	{
		return samples.elementAt(i);
	}
	
	//return the name of attribute that the DTree split on, according to the IG
	public String DecisionAttributeName()
	{   //use hashmap to store attribute names and corresponding entrophy value
		HashMap<String, Double> A_E=new HashMap<String,Double>();
	if(attributeNames.isEmpty())
		{
			return "Leaf";
		}
	if(classifications.size()==1)
		{
		return "Leaf";
		}
		
	else
		{
		for(int i=0; i<attributeNames.size(); i++)//for each attribute
		{
			double counter=0;//number of attribute's value=1
			double counter1=0;//number of classification =1
			double counter2=0;//number of both attribute's value and classification equals 1
			double row=samples.size();
			double entrophy0=0;
			double entrophy1=0;
			double entrophyA=0;
			double IG=0;
			String AName=attributeNames.get(i);//get the name of the attribute
			
			for(int j=0; j<samples.size();j++)//iterate in each sample
			{	Sample s1=samples.elementAt(j);
				Attribute a1=s1.getAttribute(AName);
				String Val=a1.getValue();//get the value of the attribute in specific sample
				String cVal=s1.getClassification();//get the value of classification in specific sample
				if(Val.equals("1"))
				counter++;//counting the number of 1 of the given attribute in sample set
				if(cVal.equals("1"))
				counter1++;//counting the number of classification that equals 1
				if(Val.equals("1")&&cVal.equals("1"))
				counter2++;//counting the number of samples that has 1 for classification and attribute value
			}
			//calculate information gain
			if(counter==0 || counter==row)
			{IG=0;}
			
			else
			{
				double p1=(row-counter-counter1+counter2)/(row-counter);//p(A=0,C=0 | A=0)
				double p2=1-p1;//p(A=0,C=1 | A=0)
				double p3=counter2/counter;//p(A=1,C=1 | A=1)
				double p4=1-p3;//p(A=1,C=0 | A=1)
				double p5=counter/row;//p(A=1)
				double p6=1-p5;//p(A=0)
				
				if(p1==0)
				{entrophy0=-(p2*Math.log(p2)/Math.log(2));}
				else if(p2==0)
				{entrophy0=-(p1*Math.log(p1)/Math.log(2));}
				else 
				{entrophy0=-(p1*Math.log(p1)/Math.log(2))-(p2*Math.log(p2)/Math.log(2));}
				
				if(p3==0)
				{entrophy1=-(p4*Math.log(p4)/Math.log(2));}
				else if(p4==0)
				{entrophy1=-(p3*Math.log(p3)/Math.log(2));}
				else 
				{entrophy1=-(p3*Math.log(p3)/Math.log(2))-(p4*Math.log(p4)/Math.log(2));}
				
				entrophyA=p5*entrophy1+p6*entrophy0;
				double entrophyC = -((counter1/row)*Math.log(counter1/row)/Math.log(2))-((1-counter1/row)*Math.log(1-counter1/row)/Math.log(2));
				IG=entrophyC-entrophyA;
			}

			if(IG!=0)
			A_E.put(AName, IG);//mapping the attribute name and corresponding information gain value
									           //in order to return the name of attribute that has the highest IG
		}
		
		double tempVal=0;
		String tempName=null;
		if(!A_E.isEmpty())
		{
			for(String key:A_E.keySet())//visiting the elements in map
		{
			if(A_E.get(key)>tempVal)//if the IG of an attribute has higher value, record its value and name
			{
				tempVal=A_E.get(key);
				tempName=key;
			}
		}
		return tempName;//return the name of attribute that has the highest IG
		}
		else
			return "Leaf";//if the IG=0, return String"Leaf".
		}
	}

	//deleting the attribute in a sample set according to the given attribute name
	public void deleteAttribute(String attrName)
	{
		attributeNames.remove(attrName);
		for(int i=0; i<samples.size(); i++)
		{
			samples.get(i).removeAttribute(attrName);
		}
	}
}
