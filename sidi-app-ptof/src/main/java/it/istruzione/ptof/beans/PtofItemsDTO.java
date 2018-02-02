package it.istruzione.ptof.beans;

public abstract class PtofItemsDTO extends BaseDTO implements PtofTableItemActionFilterDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8782062210488530668L;
	
	private boolean modificabile = true, visualizzabile = true, cancellabile = true;

	public boolean isModificabile() {
		return modificabile;
	}

	public void setModificabile(boolean modificabile) {
		this.modificabile = modificabile;
	}

	public boolean isVisualizzabile() {
		return visualizzabile;
	}

	public void setVisualizzabile(boolean visualizzabile) {
		this.visualizzabile = visualizzabile;
	}

	public boolean isCancellabile() {
		return cancellabile;
	}

	public void setCancellabile(boolean cancellabile) {
		this.cancellabile = cancellabile;
	}
}
