public class testAccuracy {

	public static double testAccuracy(SampleSet testSet, DTreeNode DT)
{
	double counter=0;
	double size=testSet.samples.size();

	for(int i=0; i<testSet.samples.size(); i++)
	{
		DTreeNode n=DT;
		String realVal=testSet.samples.elementAt(i).getClassification();
		//System.out.println(realVal);
		while(n.left!=null&&n.right!=null)
		{
			String decisionName=n.getDecisionAttribute();
			
			if(testSet.getSample(i).getAttribute(decisionName).getValue().equals("0"))
				n=n.left;
			else
				n=n.right;
		}
		
	
		if(realVal.equals(n.classification))
			counter++;
	}
	
	System.out.println("The sample size of the test set is: "+size+",  "+counter+" samples get correct classification result.");
		double result=counter/size;
		return result;
	}

}