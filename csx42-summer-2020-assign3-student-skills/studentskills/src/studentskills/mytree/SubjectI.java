package studentskills.mytree;

import java.util.Set;

/**
* SubjectI interface for subjects
*	
* @author Ketan Deshpande
*/
public interface SubjectI {
	public void register(TreeHelperI treeHelper);
    public void unregister();
	public void notifyAll(Set<TreeHelperI> observers);
}
