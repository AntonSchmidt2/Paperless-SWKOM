package org.openapitools.entites;

public class DocumentEntity {

  private String myId;
  private String myName;

  public DocumentEntity(String myId, String myName) {
    this.myId = myId;
    this.myName = myName;
  }

  public String getMyId() {
    return myId;
  }

  public String getMyName() {
    return myName;
  }
}
