package com.mycompany.a3;

public interface ICollider {


	public boolean detectCollision(ICollider nextObj);

	public void handleCollision(Object otherCheck,GameWorld gw);

	public void addColl(ICollider x);

	public void removeColObj(ICollider x);

//	public void setCol(boolean b);
}
