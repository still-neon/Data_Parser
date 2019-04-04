package by.stn.data_parser.entity;

import lombok.Getter;

public abstract class AbstractEntity implements Entity {
	@Getter
	private Long id;

	public AbstractEntity(Long id) {
		this.id = id;
	}
}