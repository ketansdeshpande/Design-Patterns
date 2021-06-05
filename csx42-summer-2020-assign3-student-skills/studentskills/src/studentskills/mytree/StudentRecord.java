package studentskills.mytree;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import studentskills.util.MyLogger;
import studentskills.util.TreeIdGetter;
import studentskills.util.InputProcessor;

/**
* The node of a tree which has the details about students.
*
* @author Ketan Deshpande
*/
public class StudentRecord implements SubjectI, ObserverI {
	public int bNumber;
	public String firstName, lastName, major;
	public double gpa;
	public Set<String> skills = new HashSet<String>();
	public StudentRecord left, right;

	/**
	* Sets the values to the node
	*
	* @param int bNumberIn Stores unique id bNumber.
	* @param String firstNameIn Stores firstName.
	* @param String lastNameIn Stores lastName.
	* @param double gpaIn Stores gpa.
	* @param String majorIn Stores major.
	* @param Set<String> skillsIn Stores skills.
	*/
	public void createNode(int bNumberIn, String firstNameIn, String lastNameIn, double gpaIn, String majorIn, Set<String> skillsIn) {
		this.bNumber = bNumberIn;
		this.firstName = firstNameIn;
		this.lastName = lastNameIn;
		this.major = majorIn;
		this.gpa = gpaIn;
		this.skills = skillsIn;
		this.left = this.right = null;
		MyLogger.writeMessage("StudentRecord::createNode() : Node creation done for bNumber " + bNumberIn, MyLogger.DebugLevel.STUDENT_RECORD);
	}

	/**
	* Subject method to call update on all observers
	*
	* @param Set<TreeHelperI> observers List of observers.
	*/
	@Override
	public void notifyAll(Set<TreeHelperI> observers) {
		for(TreeHelperI treeHelper : observers) {
			this.update(treeHelper);
		}
		MyLogger.writeMessage("StudentRecord::notifyAll() : Notified all observers.", MyLogger.DebugLevel.STUDENT_RECORD);
	}

	/**
	* Subject method to register the observers
	*
	* @param TreeHelperI treeHelper Tree object
	* 	on which the node to be inserted.
	*/
	@Override
	public void register(TreeHelperI treeHelper) {
		treeHelper.insert(this);
		int id = TreeIdGetter.getTreeId(treeHelper);
		MyLogger.writeMessage("StudentRecord::register() : Called on tree replica no " + id, MyLogger.DebugLevel.STUDENT_RECORD);
		Set<TreeHelperI> values = InputProcessor.replicaMap.get(id);
		this.notifyAll(values);
	}

	/**
	* Subject method to unregister the observers
	*
	*/
	@Override
    public void unregister() {}

	/**
	* Observer method to update the values in the observers
	*
	* @param TreeHelperI treeHelper Tree object
	* 	on which the node to be updated.
	*/
	@Override
	public void update(TreeHelperI treeHelper) {
		StudentRecord studentRecord = new StudentRecord();
		studentRecord.createNode(this.bNumber, this.firstName, this.lastName, this.gpa, this.major, this.skills);
		treeHelper.insert(studentRecord);
		MyLogger.writeMessage("StudentRecord::update() : Updated for tree id " + treeHelper.getUniqueId(), MyLogger.DebugLevel.STUDENT_RECORD);
	}

	@Override
	public String toString() {
		return "bNumber: " + bNumber +
				" firstName: " + firstName +
				" lastName: " + lastName +
				" major: " + major +
				" gpa: " + gpa +
				" skills: " + skills;
	}
}
