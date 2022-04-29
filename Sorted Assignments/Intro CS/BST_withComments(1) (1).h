// A binary search tree (BST) class to store elements of type int

#include <queue>
#include <stack>
#include <iostream>
using namespace std;

int MAX(int, int);

class BSTNode
{
public:
	BSTNode(int val, BSTNode* left, BSTNode* right);
	~BSTNode();
	int GetVal();
	BSTNode* GetLeft();
	BSTNode* GetRight();
	
private:
	int val; //For integers, this serves as both key and value
	BSTNode* left;
	BSTNode* right;
	int depth, height;
	friend class BST;
};



class BST
{
public:
	BST();
	~BST();
	
	void Clear(void);
	bool IsEmpty(void);
	
	
	bool Search(int val);
	void Insert(int val);
		
	bool DeleteNode(int val);
		
	void PreorderDFT(void);
	void PreorderDFT(BSTNode* node);
	void PostorderDFT(BSTNode* node);
	void InorderDFT(BSTNode* node);
	void Visit(BSTNode* node);
		
	void BFT(void);
		
	void ComputeNodeDepths(void);
	void ComputeNodeHeights(void);
	
	BSTNode * getRoot() const {return root;}
	
private:
	BSTNode *root;

	bool Search(int val, BSTNode* node);

	void DelSingle(BSTNode* ptr, BSTNode* prev);
	void DelDoubleByCopying(BSTNode* node);
	
	void ComputeDepth(BSTNode* node, BSTNode* parent);
	void ComputeHeight(BSTNode* node);
	
	void Clear(BSTNode* node);
};


BSTNode::BSTNode(int val_, BSTNode* left_, BSTNode* right_)
{
	val = val_;
	left = left_;
	right = right_;
	depth = height = -1; // not computed yet
}

BSTNode::~BSTNode()
{
}

int BSTNode::GetVal()
{
	return val;
}


BSTNode* BSTNode::GetLeft()
{
	return left;
}


BSTNode* BSTNode::GetRight()
{
	return right;
}


BST::BST(void)
{
	root = NULL;
}


BST::~BST()
{
	Clear();
}


bool BST::IsEmpty(void)
{
	return root == NULL;
}

// Iterative implementation of Search
// Asymptotic Complexity:
//		Best case: O(1) - If the value is at the root.
//		Worst case: O(height), where the height is O(logn) in balanced trees
//					and can be as bad as O(n) in non-balanced trees.

bool BST::Search(int x)
{
	BSTNode* node = root; //Always start the search from the root
	
	while(node != NULL) 
	{
        // If the value that we are looking for is equal to
        // the value of the current tree node, we are done 
		if(x == node->val) 
			return true;

        // If the value that we are looking for is smaller than the 
        // value of the current tree node, go left; else, go right
		if(x < node->val)
			node = node->left;
		else
			node = node->right;
	}
    // If the loop completes, node will ncessarily be NULL,
    // which means that val was not found in the tree
	return false; 
}

// Recursive implementation of Search
// In a recursive implementation of the Search function, 
// the public Search will call the private recursive 
// Search as follows:

// bool BST::Search(int x)
// {
//      return Search(x, root);
// }

// x is the value that we are looking for
// node is the root of the sub-tree that we are searching in.
// Initially node is the root of the entire tree.
// Then the recursion will be limiting the search to
// smaller and smaller sub-trees 

// The asymptotic complexity of the recursive and the iterative search functions
// are the same. In some cases, a recursive function is more readable and easier to
// implement than its iterative equivailent. However, recursion requires more
// time to execute than loops due to the cost of function calls.

bool BST::Search(int x, BSTNode* node)
{
    // If recursion reaches a NULL node, x does not exist in the tree
	if(node == NULL)
		return false;

    // If recursion reaches a sub-tree whose root is equal to x, 
    // we are done 
	if(x == node->val)
		return true;

    // If the root of the current sub-tree is not the value that we
    // are looking for, do the search in either the left sub-tree
    // or the right sub-tree, depending on the value of x  
	if(x < node->val)
		return Search(x, node->left);
	else
		return Search(x, node->right);
}


void BST::Insert(int x)
{
    // Search for the right place for x starts from the root.
    // We also need to store the previous node as we move down
    // the tree, because the search will stop at a NULL pointer
    // and the previous of that NULL will be the parent of the
    // inserted node 
	BSTNode *node = root, *prev = NULL;
   
    // Create a new node with NULL children, because we always 
    // insert a LEAF node. There is no insertion in the middle
    // of a tree
	BSTNode *newNode = new BSTNode(x, NULL, NULL);
	
    // Loop to search for the right position for x
	while(node != NULL)
	{
		prev = node;
		if(x < node->val)
			node = node->left;
		else
			node = node->right;
	}
    // When the loop terminates, node will always be NULL, which is
    // the NULL node to be replaced with the new node.
    // prev will be the parent of that NULL and therefore the parent
    // of the inserted new node      

    // Special handling of an empty tree is needed, because the 
    // inserted new node will have no parent in which a left/right 
    // pointer will need to be changed 
	if(IsEmpty() == true)
		root = newNode;

    // If the inserted new node is smaller than its parent, set the
    // the left pointer in the parent to the new node. Otherwise,
    // set the right pointer in the parent to the new node. 
	else if(x < prev->val)
		prev->left = newNode;
	else
		prev->right = newNode;
}

// Breadth-first traversal uses a queue
void BST::BFT(void)
{
    // Create a queue to control the BFT.
    // Pay attention to the types used here.
    // The elements in the queue are pointers to BSTNode objects.
    // Storing BSTNode objects in the queue won't be space efficient,
    // because those objects may have large sizes.  
	queue<BSTNode*> queue;
	BSTNode* node = root;
	
    // If the tree is empty, there will be no nodes to traverse.
	if(node == NULL) return;
	
    // Initially, put the root in the queue
	queue.push(node);

	while(queue.empty() == false)
	{
        // Take a node out of the queue, process it and then insert 
        // its children into the queue.
		node = queue.front();
		queue.pop();

		Visit(node);
		if(node->left != NULL)
			queue.push(node->left);
		if(node->right != NULL)
			queue.push(node->right);
	}
}


// Non-recursive implementation of PreorderDFT
// Depth-first traversal uses a stack
void BST::PreorderDFT()
{
    // Create a stack to control the DFT.
    // Pay attention to the types used here.
    // The elements in the stack are pointers to BSTNode objects.
    // Storing BSTNode objects in the stack won't be space efficient,
    // because those objects may have large sizes.  
	stack<BSTNode*> stack;
	BSTNode* node = root;
	
    // If the tree is empty, there will be no nodes to traverse.
	if(node == NULL) return;
	
    // Initially, put the root in the stack
	stack.push(node);

	while(stack.empty() == false)
	{
        // Take a node out of the stack, process it and then insert 
        // its children into the stack.
        // Because elements that are pushed last will be popped 
        // first, and we want to visit the left children before 
        // the right children, we will have to push the right child
        // before the left child for each node. 
		node = stack.top(); 
		stack.pop();
		Visit(node);
		if(node->right != NULL)
			stack.push(node->right);
		if(node->left != NULL)
			stack.push(node->left);
	}
}

// Recursive implementation of PreorderDFT
// Recursive DFT implicitly uses the system's run-time stack
void BST::PreorderDFT(BSTNode* node)
{
	if(node == NULL) return;
	Visit(node); // Process the node before all of its children 
	PreorderDFT(node->left);
	PreorderDFT(node->right);
}

// Recursive implementation of PostorderDFT
// Recursive DFT implicitly uses the system's run-time stack
void BST::PostorderDFT(BSTNode* node)
{
	if(node == NULL) return;
	PostorderDFT(node->left);
	PostorderDFT(node->right);
	Visit(node); // Process the node after all of its children
}

// Recursive implementation of InorderDFT
// Recursive DFT implicitly uses the system's run-time stack
void BST::InorderDFT(BSTNode* node)
{
	if(node == NULL) return;
	InorderDFT(node->left);

	// Process the node after processing its left subtree 
	// but before processing its right subtree 
	Visit(node);  
	
	InorderDFT(node->right);
}

// The processing done at each node is simply printing its value 
void BST::Visit(BSTNode* node)
{
	cout << node->val << ", ";
}



// Deletes from the tree the node with the given value.
// Asymptotic complexity:
//		The delete function includes two steps: a search step + a delete step.
//		
//		- The search step is O(height) in the worst case and O(1) in the best 
//		case as explained in the search function.
//
//		- The delete step may be O(1) or O(height) depending on whether there
//	    is a search for a replacement or not and where the replacement is found.
//      Search for a replacement is done only if the deleted node has two children.
//      Note that the search for a replacement happens after the search for the
//      deleted node itself.
//		
//		Examples: 
//			- If the node to be deleted is at the root and has two children, the
//			search for the deleted node itself will be O(1) but the delete 
//          operation (search for a replacement then copy) will be O(height).
//
//			- If the node to be deleted is a leaf node, then the search for the
//          deleted node will be O(height) and the delete will be O(1) 
//          (no replacement is needed at all).
//
//			- If the node to be deleted is the root node and has a single
//			child, the search for the deleted node will be O(1) and the 
//          delete will be O(1) as well, because there will be no search for
//          a replacement.
//
//		Therefore, the delete function is O(height) in the worst case and O(1) 
//      in the best case.

bool BST::DeleteNode(int val)
{
	BSTNode * node = root;
	BSTNode * prev = NULL;
	
	if (IsEmpty() == true)
		return false;

	// This loop searches for the node to be deleted 
	while (node != NULL)
	{
		if (node->val == val)
			break;
		
		prev = node;
		if (val < node->val)
			node = node->left;
		else
			node = node->right;
	}
	
	// If node is not found, return false without deleting anything
	if (node == NULL)
		return false;
	
	// If the node has a single child, call DelSingle; else, call DelDouble
	if (node->left == NULL || node->right == NULL)
		DelSingle(node, prev);
	else
		DelDoubleByCopying(node);
	
	return true;
}


// Deletes a node with at most one child.
// This function is O(1) in the best and worst case.
void BST::DelSingle(BSTNode* ptr, BSTNode * prev)
{
    // Special handling if the node to be deleted is the root
	if (ptr == root) {
		// The new root becomes child that is not NULL
		if (root->left != NULL)
			root = root->left;
		else
			root = root->right;
	// If the node to be deleted is the left child of its parent,
	// set the parent's left child to the only child of the deleted node
	} else if (ptr == prev->left) {
		if (ptr->right != NULL)
			prev->left = ptr->right;
		else
			prev->left = ptr->left;
	// If the node to be deleted is the right child of its parent,
	// set the parent's right child to the only child of the deleted node
	} else {
		if (ptr->right != NULL)
			prev->right = ptr->right;
		else
			prev->right = ptr->left;
	}
	
	delete ptr;
}

// Deletes a node with exactly two children.
// This function is O(height) in the worst case and O(1) in the best case.
void BST::DelDoubleByCopying(BSTNode* node)
{
	// The replacement will be the largest node in the left subtree.
	// So, start searching for a replacement by going left
	BSTNode * rep = node->left; 
	BSTNode * prev = node;
	
	// Then keep going right until you reach a node with no right,
	// as the largest node in the left subtree is the rightmost 
	// node in the left subtree.
	while (rep->right != NULL) {
		prev = rep;
		rep = rep->right;
	}
	
	// Copy the value of the replacement into the node to be deleted 
	node->val = rep->val;

	// Then delete the replacement node using DelSingle, because that
	// node does not have a right child.
	DelSingle(rep, prev);
}


void BST::ComputeNodeDepths(void)
{
	ComputeDepth(root, NULL);
}


// Computes the depths for all of the nodes in the tree.
// Pre-order traversal, because the parent's depth has to be
// computed before computing the children's depths.
void BST::ComputeDepth(BSTNode* node, BSTNode* parent)
{
	if(node == NULL) return;

	// The base of the computation is setting the root's depth to 0 
	if(node == root)
		node->depth = 0;
	else // For all other nodes, the depth is one plus the parent's depth
		node->depth = parent->depth + 1;
	ComputeDepth(node->left, node);
	ComputeDepth(node->right, node);
}



void BST::ComputeNodeHeights(void)
{
	ComputeHeight(root);
}

// Computes the heights for all of the nodes in the tree.
// Post-order traversal, because children's heights have
// to be computed before computing the parent's height
void BST::ComputeHeight(BSTNode* node)
{
	if(node == NULL) return;

	// By setting these variables to -1, the height computation formula (last 
	// line in this function) will compute a zero height for a leaf node, because
	// the leaf node does not have any children. This will be the base of the computation
	int leftHeight = -1, rightHeight = -1;
	
	// Recursively compute the heights of all children
	// in the left and right sub-trees
	ComputeHeight(node->left);
	ComputeHeight(node->right);
	
	if(node->left)
		leftHeight = node->left->height;
	if(node->right)
		rightHeight = node->right->height;

	// The height of a node is one plus the height of the deepest subtree below it
	node->height = MAX(leftHeight, rightHeight) + 1;
}


void BST::Clear(void)
{
	Clear(root);
	root = NULL;
}

// Deleting nodes is best done in postorder, because we cannot
// delete a node until we have taken its left and its right.  
void BST::Clear(BSTNode* node)
{
	if(node == NULL) return;
	Clear(node->left);
	Clear(node->right);
	delete node;
}

int MAX(int x, int y)
{
	return x>y ? x: y;
}