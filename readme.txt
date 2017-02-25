Source code is in the "DecisionTree" document
This project is exported as executable jar file. It can be executed in cmd command lines. 
Print java -jar " 'rout of the jar file'\DecisionTree.jar" (eg. java -jar "E:\DecisionTree.jar"    if you put the jar file in E:\)
After execution, the program will give you following prompts one after another:
"Please input the number of node to prune"-----input the number
"please input the training file name"-----input the csv file name[for dataset1, training_set.csv; for dataset2, training_set2.csv]
"please input the validate file name"-----input the csv file name[for dataset1, validation_set.csv; for dataset2, validation_set2.csv]
"please input the test file name"-----input the csv file name[for dataset1, test_set.csv; for dataset2, test_set2.]
"Print the tree? 1-Yes, 0-No"-----if you want to print the tree, then enter 1, else enter 0.(it will print both original tree and pruned tree)