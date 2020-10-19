package models;

public class Relationship {
    private int idInModalWindow;
    private String objectAPIname;
    private String childObject;
    private String parentField;
    private String isCascadeDeletionInSF;
    private String deletionRestrictedInSF;
    private String pseudoChildObject;
    private String relatedViaMapping;
    private String mappingRefersToAccountVia;
    private String mappingRefersToPseudoChildVia;

    private String soqlQqueryType;
    private String avgChildrenForOneparentRecord;
    private String useCascadeDeletion;

    public Relationship() {
    }

    public Relationship(int idInModalWindow, String objectAPIname) {
        this.idInModalWindow = idInModalWindow;
        this.objectAPIname = objectAPIname;
    }

    public Relationship(String childObject, String parentField,
                        String isCascadeDeletionInSF, String deletionRestrictedInSF) {
        this.childObject = childObject;
        this.parentField = parentField;
        this.isCascadeDeletionInSF = isCascadeDeletionInSF;
        this.deletionRestrictedInSF = deletionRestrictedInSF;
    }

    public Relationship(String childObject, String parentField, String soqlQqueryType,
                        String avgChildrenForOneparentRecord, String useCascadeDeletion, String isCascadeDeletionInSF,
                        String deletionRestrictedInSF) {
        this.childObject = childObject;
        this.parentField = parentField;
        this.soqlQqueryType = soqlQqueryType;
        this.avgChildrenForOneparentRecord = avgChildrenForOneparentRecord;
        this.useCascadeDeletion = useCascadeDeletion;
        this.isCascadeDeletionInSF = isCascadeDeletionInSF;
        this.deletionRestrictedInSF = deletionRestrictedInSF;

    }

    public String getObjectAPIname() {
        return objectAPIname;
    }

    public void setObjectAPIname(String objectAPIname) {
        this.objectAPIname = objectAPIname;
    }

    public String getChildObject() {
        return childObject;
    }

    public void setChildObject(String childObject) {
        this.childObject = childObject;
    }

    public String getParentField() {
        return parentField;
    }

    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    public String getIsCascadeDeletionInSF() {
        return isCascadeDeletionInSF;
    }

    public void setIsCascadeDeletionInSF(String isCascadeDeletionInSF) {
        this.isCascadeDeletionInSF = isCascadeDeletionInSF;
    }

    public String getDeletionRestrictedInSF() {
        return deletionRestrictedInSF;
    }

    public void setDeletionRestrictedInSF(String deletionRestrictedInSF) {
        this.deletionRestrictedInSF = deletionRestrictedInSF;
    }

    public String getPseudoChildObject() {
        return pseudoChildObject;
    }

    public void setPseudoChildObject(String pseudoChildObject) {
        this.pseudoChildObject = pseudoChildObject;
    }

    public String getRelatedViaMapping() {
        return relatedViaMapping;
    }

    public void setRelatedViaMapping(String relatedViaMapping) {
        this.relatedViaMapping = relatedViaMapping;
    }

    public String getMappingRefersToAccountVia() {
        return mappingRefersToAccountVia;
    }

    public void setMappingRefersToAccountVia(String mappingRefersToAccountVia) {
        this.mappingRefersToAccountVia = mappingRefersToAccountVia;
    }

    public String getMappingRefersToPseudoChildVia() {
        return mappingRefersToPseudoChildVia;
    }

    public void setMappingRefersToPseudoChildVia(String mappingRefersToPseudoChildVia) {
        this.mappingRefersToPseudoChildVia = mappingRefersToPseudoChildVia;
    }

    public String getSoqlQqueryType() {
        return soqlQqueryType;
    }

    public void setSoqlQqueryType(String soqlQqueryType) {
        this.soqlQqueryType = soqlQqueryType;
    }

    public String getAvgChildrenForOneparentRecord() {
        return avgChildrenForOneparentRecord;
    }

    public void setAvgChildrenForOneparentRecord(String avgChildrenForOneparentRecord) {
        this.avgChildrenForOneparentRecord = avgChildrenForOneparentRecord;
    }

    public String getUseCascadeDeletion() {
        return useCascadeDeletion;
    }

    public void setUseCascadeDeletion(String useCascadeDeletion) {
        this.useCascadeDeletion = useCascadeDeletion;
    }
}
