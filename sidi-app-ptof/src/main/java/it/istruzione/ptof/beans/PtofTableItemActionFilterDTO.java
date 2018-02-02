package it.istruzione.ptof.beans;

/**
 * @author mcatanzaro
 * use: nel caso in cui abbiamo un lista di valori in una tabella e alcuni items non possono essere modificabili ... 
 */
public interface PtofTableItemActionFilterDTO {

	boolean isModificabile();

	void setModificabile(boolean modificabile);

	boolean isCancellabile();

	void setCancellabile(boolean modificabile);

	boolean isVisualizzabile();

	void setVisualizzabile(boolean modificabile);

}