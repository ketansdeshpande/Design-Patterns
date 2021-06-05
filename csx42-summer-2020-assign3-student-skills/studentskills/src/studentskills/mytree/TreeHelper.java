package studentskills.mytree;

import studentskills.util.Results;
import studentskills.util.MyLogger;
import studentskills.mytree.StudentRecord;
/**
* The tree of StudentRecord nodes.
*
* @author Ketan Deshpande
*/
public class TreeHelper implements TreeHelperI {
	protected StudentRecord root, existingStudentRecord;
	protected StringBuilder output = new StringBuilder();
	private final int id;

	/**
	* Initializes root and sets unique id to the tree instances.
	*
	* @param int idIn Unique id for the tree instance.
	*/
	public TreeHelper(int idIn) {
		root = null;
		this.id = idIn;
		MyLogger.writeMessage("TreeHelper : set unique id " + idIn + " to tree.", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	* Getter of the tree id.
	*
	*/
	public int getUniqueId() {
		return this.id;
	}

	/**
	* Inserts the node instance in the specified tree.
	*
	* @param StudentRecord studentRecord Node class' instance.
	*/
	@Override
	public void insert(StudentRecord studentRecord) {
		existingStudentRecord = searchStudentRecord(root, studentRecord);
		if(existingStudentRecord != null) {
			existingStudentRecord.firstName = studentRecord.firstName;
			existingStudentRecord.lastName = studentRecord.lastName;
			existingStudentRecord.major = studentRecord.major;
			existingStudentRecord.gpa = studentRecord.gpa;
			existingStudentRecord.skills = studentRecord.skills;
		} else {
			root = insertStudentRecord(root, studentRecord);
		}
		
		MyLogger.writeMessage("TreeHelper::insert() : Inserted/updated student record with bNumber " + studentRecord.bNumber, MyLogger.DebugLevel.TREE_HELPER);
	}

	/**
	* Recursive function to insert the node instance in the specified tree.
	*
	* @param StudentRecord root Root node.
	* @param StudentRecord studentRecord Node to insert.
	*/
	public StudentRecord insertStudentRecord(StudentRecord root, StudentRecord current) {
		if(root == null) {
			root = current;
			return root;
		}

		if(current.bNumber < root.bNumber)
			root.left = insertStudentRecord(root.left, current);
		else if(current.bNumber > root.bNumber)
			root.right = insertStudentRecord(root.right, current);

		return root;
	}

	/**
	* Recursive function to search the node instance in the specified tree.
	*
	* @param StudentRecord root Root node.
	* @param StudentRecord studentRecord Node to search.
	*/
	@Override
	public StudentRecord searchStudentRecord(StudentRecord root, StudentRecord studentRecord) {
		if(root == null || root.bNumber == studentRecord.bNumber)
			return root;

		if(root.bNumber > studentRecord.bNumber)
			return searchStudentRecord(root.left, studentRecord);

		return searchStudentRecord(root.right, studentRecord);
	}

	/**
	* Function to call the search recursive function.
	*
	* @param StudentRecord studentRecord Node to search.
	*/
	@Override
	public StudentRecord searchStudentNode(StudentRecord studentRecord) {
		return searchStudentRecord(root, studentRecord);
	}

	/**
	* Function to call the print recursive function.
	*
	*/
	public void print() {
       printRec(root);
    }

	/**
	* Recursive function to print the skills of the node.
	*
	* @param StudentRecord root Root node.
	*/
    void printRec(StudentRecord root) {
        if (root != null) {
            printRec(root.left);
			output.append(root.bNumber + " : ");
			output.append(root.skills);
			output.append('\n');
            printRec(root.right);
        }
    }

	/**
	* Prints skills to the output file.
	*
	* @param Results results Results instance to call the printToFile.
	* @param String filePath File name.
	*/
	@Override
	public void printNodes(Results results, String filePath) {
		print();
		results.printToFile(output.toString(), filePath);
		MyLogger.writeMessage("TreeHelper::printNodes() : Successfully printed nodes to the file.", MyLogger.DebugLevel.TREE_HELPER);
	}
}
