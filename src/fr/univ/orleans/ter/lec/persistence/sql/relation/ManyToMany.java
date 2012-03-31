package fr.univ.orleans.ter.lec.persistence.sql.relation;

public class ManyToMany implements SQLRelation {

	private String intermediateTableName;
	private String rightPartnerName;
	private String leftPartnerName;

	private String relationName;

	public ManyToMany(String intermediateClassName, String rightPartnerName,
			String leftPartnerName, String relName) {
		super();
		this.intermediateTableName = intermediateClassName;
		this.rightPartnerName = rightPartnerName;
		this.leftPartnerName = leftPartnerName;
		this.relationName = relName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getIntermediateTableName() {
		return intermediateTableName;
	}

	public void setIntermediateTableName(String intermediateClassName) {
		this.intermediateTableName = intermediateClassName;
	}

	public String getRightPartnerName() {
		return rightPartnerName;
	}

	public void setRightPartnerName(String rightPartnerName) {
		this.rightPartnerName = rightPartnerName;
	}

	public String getLeftPartnerName() {
		return leftPartnerName;
	}

	public void setLeftPartnerName(String leftPartnerName) {
		this.leftPartnerName = leftPartnerName;
	}

	public Integer getRelationType() {
		return SQLRelation.RELTYPE_MANY_TO_MANY;
	}

	public String getRelationName() {
		return this.relationName;
	}
}
