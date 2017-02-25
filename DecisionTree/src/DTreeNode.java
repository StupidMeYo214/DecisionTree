public class DTreeNode {

	public int nodeNum;
	public SampleSet ss;
	boolean isLeaf=false;
	public DTreeNode left;
	public DTreeNode right;
	public String classification="";
	public String decisionAttribute;
	public SampleSet subset1;
	public SampleSet subset2;
	
	public void DTreeNode(SampleSet ss, int i)//Node constructor
	{this.ss=ss;
	this.nodeNum=i;
	this.decisionAttribute=ss.DecisionAttributeName();
	}
	
	public String classification()
	//if the node is leaf node, then use this method to get the classification
	{
		int counter=0;
		//iterating the sample sets in the node
		for(int i=0; i<ss.samples.size();i++)
		{
		if(ss.samples.elementAt(i).getClassification().equals("1"))
		//counting the number of corresponding classification value that equals to 1 
		counter++;
		}
		counter=counter*2;
		//deciding the classification value
		if(counter>ss.samples.size())
			return "1";
		else
			return "0";
	}
	
	public SampleSet getSubSet1()
	//split the sample set, use the new data set (subset) to construct child node. 
	{	String dName=ss.DecisionAttributeName();
		SampleSet subset=new SampleSet();
		//iterating the samples in sample set
		for(int i=0; i<ss.samples.size();i++)
		{
			Sample sa=new Sample(ss.samples.get(i).getClassification(), ss.samples.get(i).getAttributeList());
			Attribute dA=sa.getAttribute(dName);
			String deciAttrVal = dA.getValue();
			//get the attribute value corresponding to the decision attribute
			if(deciAttrVal.equals("0"))
			{	//if the value = 0, then add the sample to new subset
				sa.removeAttribute(dName);
				subset.addSample(sa);
			}
		}
		return subset;
	}
	
	//similar as the method above, get subset that decision attribute value = 1. 
	//And delete the used attribute.
	public SampleSet getSubSet2()
	{
		String dName=ss.DecisionAttributeName();
		SampleSet subset=new SampleSet();
		//String dV=ss.DecisionAttributeName();
		for(int i=0; i<ss.samples.size();i++)
		{
			Sample sa=new Sample(ss.samples.get(i).getClassification(), ss.samples.get(i).getAttributeList());
			Attribute dA=sa.getAttribute(dName);
			//System.out.println(dA.getValue()+"  "+dA.getName());
			String deciAttrVal = dA.getValue();
			if(deciAttrVal.equals("1"))
			{	
				sa.removeAttribute(dName);
				subset.addSample(sa);
			}
		}
		subset.attributeNames.remove(dName);
		return subset;
	}
	
	//judging whether the node is a leaf node:
	//1) the classifications are pure 2) no more attribute to split on 3)the attribute value is pure
	public void isLeaf()
	{	
		if( decisionAttribute.equals("Leaf"))
			{isLeaf=true;
			return;}
	}
	
	public String getDecisionAttribute()
	{return decisionAttribute;}
	
	public static void printNode(DTreeNode n,int i)
	{
		
		if(n.left!=null||n.right!=null)
		{
			for(int j=0; j<i; j++)
			{System.out.print("| ");}
			System.out.print(n.decisionAttribute+" "+"= ");
			if(n.left!=null)
			System.out.println("0 : "+n.left.classification+"-------the index of the node is: "+n.nodeNum);	
			
			printNode(n.left,i+1);
			
			for(int j=0; j<i; j++)
			{System.out.print("| ");}
			System.out.print(n.decisionAttribute+" = ");
			if(n.right!=null)
			System.out.println("1 : "+n.right.classification+"-------the index of the node is: "+n.nodeNum);
			
			
			printNode(n.right,i+1);
			
			
		}
	}
	
}
