package br.game.castleduel.util;

public abstract class UniqueId {
	private static int ID_GENERATOR = 0;
	protected final int id;
	
	public UniqueId() {
		id = ID_GENERATOR++;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isInstance(obj))
			return false;
		UniqueId other = (UniqueId) obj;
		return id == other.id;
	}
}
