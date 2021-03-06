package com.smlearning.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI tree模型
 * 
 * 
 */
public class Tree implements java.io.Serializable,Comparable<Tree>{

    private static final long serialVersionUID = -8643403005281289794L;
    private String id;
	private String text;
	private String state = "closed";// open,closed
	private boolean checked = false;
	private Object attributes;
	private List<Tree> children;
	private String iconCls;
	private String pid;
	

	public Tree(){}
	
	public Tree(String id,String text){
	  this.id = id;
	  this.text = text;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	

  public void addChild(Tree tree){
	  if(children == null){
	    children = new ArrayList<Tree>();
	  }
	  children.add(tree);
	}

  @Override
  public int compareTo(Tree o) {
    int localId = Integer.parseInt(this.id);
    int oid = Integer.parseInt(o.getId());
    if (localId > oid) {
        return 1;
    }
    return -1;
  }

  
}
