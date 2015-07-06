package views;

import java.io.Serializable;

public class GenericView implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
