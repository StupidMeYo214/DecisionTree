import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class  ConstructDT {
	
		public static DTreeNode ConstructDTree(SampleSet ss, int i)
	{
		DTreeNode root =new DTreeNode();
		root.DTreeNode(ss, i);
		
		root.isLeaf();//judging whether the node is leaf node
		if(!root.isLeaf)//if its not a leaf node, split
		{
			root.left=ConstructDTree(root.getSubSet1(), 2*i);
			root.right=ConstructDTree(root.getSubSet2(), 2*i+1);
		}
		else
			{root.classification=root.classification();
			root.left=null;
			root.right=null;}
			
	return root;

	}
		
	//find the node in decision tree according to the index number
	public static DTreeNode findNode(DTreeNode n,int i)
		{	
		 if(n != null){
		        if(n.nodeNum==i){
		           return n;
		        } else {
		           
		        	DTreeNode temp= findNode(n.left, i);
		            if(temp==null) {
		                temp = findNode(n.right, i);
		            }
		            return temp;
		         }
		    } else {
		        return null;
		    }
		}

	//visit the decision tree and return a linked list that contain all the index in the decision tree
	public static LinkedList<Integer> getIndexNumber(DTreeNode n,LinkedList<Integer> index)
	{
	
		if(n!=null&&n.left!=null&n.right!=null)
		{
			index.add(n.nodeNum);
			getIndexNumber(n.left,index);
			getIndexNumber(n.right,index);
		}
		return index;
	}
	
		//make a node as a leaf node. i.e. prune the node
	public static void prunning(DTreeNode n,int i)
	{
		findNode(n, i).classification=findNode(n, i).classification();
		findNode(n, i).left=null;
		findNode(n, i).right=null;
		findNode(n, i).decisionAttribute="Leaf";
	}
	
	//prune the given number of nodes in a decision tree
	public static DTreeNode prunning(int i,DTreeNode n)
	{
		LinkedList<Integer> index=new LinkedList<>();
		index=getIndexNumber(n,index);
		//System.out.println(index.size());//test
		for(int j=0; j<i; j++)
		{
			Random rand=new Random();
			int randR=1+rand.nextInt(index.size()-1);
			int idx=index.get(randR);
			prunning(n, idx);
			index.clear();
			index=getIndexNumber(n, index);
		}
		return n;
	}
	
	public static void main(String args[])
	{

		Scanner sc=new Scanner(System.in);
		System.out.println("Please input the number of node to prune: ");
		int pruneNumber=sc.nextInt();
		
		Scanner sc2=new Scanner(System.in);
		System.out.println("Please inpu the training file name: ");
		String trName=sc2.nextLine();
		
		System.out.println("Please inpu the validate file name: ");
		String vlName=sc2.nextLine();
		
		System.out.println("Please inpu the testing file name: ");
		String testName=sc2.nextLine();
		
		Scanner sc3=new Scanner(System.in);
		System.out.println("Print the tree? 1-Yes, 0-No : ");
		int print=sc3.nextInt();
	
		//get training, validation and test samples	
		SampleSet ss=getSampleSet.getSampleSet(trName);
		SampleSet valiSet=getSampleSet.getSampleSet(vlName);
		SampleSet testSet=getSampleSet.getSampleSet(testName);
		
		//construct tree
		DTreeNode dt = ConstructDT.ConstructDTree(ss,1); 

		//print the decision tree
		if(print==1){
			System.out.println("***************************************ID3 DECISION TREE***************************************");
			System.out.println("");
			dt.printNode(dt,0);}
	
		double accuracy=testAccuracy.testAccuracy(testSet, dt);
		double accuracy2=testAccuracy.testAccuracy(valiSet, dt);
		System.out.println("Before prunning the validation accuracy is: "+accuracy2);
		System.out.println("Before prunning the test accuracy of the decition tree is: "+accuracy);
	
		//prunning
		ConstructDT.prunning(pruneNumber,dt);
		if(print==1){
			System.out.println("***************************************AFTER PRUNNING***************************************");
	
			System.out.println("");
			dt.printNode(dt, 0);}
	
		double accuracy1=testAccuracy.testAccuracy(testSet, dt);
		double accuracy3=testAccuracy.testAccuracy(valiSet, dt);
		System.out.println("After prunning the validation accuracy is: "+accuracy3);
		System.out.println("After prunning the test accuracy of the decition tree is: "+accuracy1);

	}
}
