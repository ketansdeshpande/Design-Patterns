package studentskills.mytree;

import studentskills.util.Results;
import studentskills.mytree.StudentRecord;

/**
* TreeHelperI interface for treehelpers
*	
* @author Ketan Deshpande
*/
public interface TreeHelperI {
	public void insert(StudentRecord studentRecord);

	public StudentRecord searchStudentNode(StudentRecord studentRecord);

	public StudentRecord searchStudentRecord(StudentRecord root, StudentRecord studentRecord);
	
	public int getUniqueId();

	public void printNodes(Results results, String filePath);
}
