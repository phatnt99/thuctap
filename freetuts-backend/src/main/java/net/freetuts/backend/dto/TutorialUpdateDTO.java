package net.freetuts.backend.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TutorialUpdateDTO extends TutorialCreateDTO {
	private UUID       id;
	private List<UUID> ccategoryIds;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<UUID> getCcategoryIds() {
		return ccategoryIds == null ? new ArrayList<UUID>() : ccategoryIds;
	}

	public void setCcategoryIds(List<UUID> ccategoryIds) {
		this.ccategoryIds = ccategoryIds;
	}

}
