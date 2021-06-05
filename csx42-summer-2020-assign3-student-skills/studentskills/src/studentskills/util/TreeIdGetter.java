package studentskills.util;

import studentskills.mytree.TreeHelperI;

/**
* TreeIdGetter is a utility to get the tree unique Id
*
* @author Ketan Deshpande
*/

public class TreeIdGetter {
	public static int getTreeId(TreeHelperI treeHelper) {
		return treeHelper.getUniqueId();
	}
}