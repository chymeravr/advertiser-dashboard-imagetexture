package co.chimeralabs.advertiser.server.formDTO;

import java.util.List;

public class CM_DataRowActionDTO {
	private String action;
	private String target;
	private List<Long> idList;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<Long> getIdList() {
		return idList;
	}
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
}
