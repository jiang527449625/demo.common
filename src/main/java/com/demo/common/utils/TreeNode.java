package com.demo.common.utils;

import java.util.Set;
import java.util.TreeSet;

public class TreeNode implements Comparable<TreeNode>{

	private String id;//树节点id

	private String name;//树节点名称

	private String module;//树节点模块名-和前段angular js 模块名对应
	
	private String parentId;//树节点父节点id

	private int sort;//排序号

    private String url;//树节点url资源

    private String iconUrl;//树节点图标路径

    private String type;//树节点类型，[mudule, field]
	
    private String description;//树节点描述信息

	private Set<TreeNode> child = new TreeSet<TreeNode>();//树节子点集合
	
	private boolean leaf;//是否叶子节点
	
	private boolean expanded;//节点是否展开
	
	private boolean editable;//是否已可编辑

	private int lever;//排序号
	
 
    
    public int getLever() {
		return lever;
	}

	public void setLever(int lever) {
		this.lever = lever;
	}


	private boolean isEnabled;
    
	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	
	public void addChild(TreeNode node) {
		
		this.child.add(node);
	}
	
    public Set<TreeNode> getChild() {
        return child;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	
	public void setChild(Set<TreeNode> child) {
		this.child = child;
	}

	public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }	

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
	public int compareTo(TreeNode node) {
		int result = sort > node.getSort() ? 1 : -1;
		return result;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}


	public void setChildren(Set<TreeNode> children) {
		this.child = children;
	}
	
}
