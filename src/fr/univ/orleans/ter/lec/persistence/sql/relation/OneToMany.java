package fr.univ.orleans.ter.lec.persistence.sql.relation;

public class OneToMany implements SQLRelation {

	private String parentTableName;
	private String childTableName;

	private String relationName;

	public OneToMany(String parent, String child, String relName) {
		parentTableName = parent;
		childTableName = child;
		relationName = relName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Integer getRelationType() {
		return SQLRelation.RELTYPE_ONE_TO_MANY;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public String getParentTableName() {
		return parentTableName;
	}

	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}

	public String getChildTableName() {
		return childTableName;
	}

	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}
}
