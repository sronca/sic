package it.istruzione.commons.menu.mock.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.commons.menu.ItemMenu;
import it.istruzione.commons.menu.Menu;
import it.istruzione.commons.menu.MenuBuilder;
import it.istruzione.commons.menu.ItemMenu.TIPO_ITEM;
import it.istruzione.commons.menu.impl.CaricaMenuGestioneUtenzeSidi;
import it.istruzione.commons.security.SidiUser;

@Primary
@Service
public class CaricaMenuGestioneUtenzeSidiMOC implements MenuBuilder {

	@Autowired
	private MessageSource messageSource;

	private Map<String, String> vociMenu = new HashMap<String, String>();

	@Value("${application.development}")
	private String development;

	@Autowired
	private CaricaMenuGestioneUtenzeSidi caricaMenuGestioneUtenzeSidi;

	public Menu creaMenu(UserDetails userDetails) throws Exception {
		return test2(userDetails);

	}
 

	public Menu test2(UserDetails userDetails) throws Exception {
		Menu out = new Menu();
		out.setTitolo(messageSource.getMessage("label.menu.funzioni.vuoto", null, Locale.ITALY));
		LinkedHashMap<String, ItemMenu> menu = new LinkedHashMap<String, ItemMenu>();
		SidiUser sidiUser = (SidiUser) userDetails;
		
		if (sidiUser.getCurrentProfile()!=null && sidiUser.getCurrentProfile().getNome().equalsIgnoreCase("PTOF_U_SCU")){
			ItemMenu itemp0 = new ItemMenu();
			itemp0.setIdItem("00");
			itemp0.setTesto("Gestione PTOF");
			itemp0.setUrl("fn-gestione-documenti.do");
			itemp0.setTipoItem(TIPO_ITEM.FOGLIA);
			menu.put("00", itemp0);
			
			
			LinkedHashMap<String, ItemMenu> subItems_1 = new LinkedHashMap<String, ItemMenu>();
			ItemMenu sub_1_0 = new ItemMenu();
			sub_1_0.setIdItem("03");
			sub_1_0.setTesto("Report Completo");
			sub_1_0.setUrl("fn-mon-report-completo.do");
			sub_1_0.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_1.put("03", sub_1_0);
			
			ItemMenu sub_2_0 = new ItemMenu();
			sub_2_0.setIdItem("03");
			sub_2_0.setTesto("Cruscotto Fabbisogno");
			sub_2_0.setUrl("fn-mon-cruscotto-fabbisogno.do");
			sub_2_0.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_1.put("04", sub_2_0);
			
			ItemMenu sub_3_0 = new ItemMenu();
			sub_3_0.setIdItem("03");
			sub_3_0.setTesto("Statistiche");
			sub_3_0.setUrl("fn-mon-statistiche.do");
			sub_3_0.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_1.put("05", sub_3_0);
			
			ItemMenu sub_4_0 = new ItemMenu();
			sub_4_0.setIdItem("03");
			sub_4_0.setTesto("Dettaglio Fabbisogno");
			sub_4_0.setUrl("fn-mon-det-fab.do");
			sub_4_0.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_1.put("06", sub_4_0);
	 
			ItemMenu itemp1 = new ItemMenu();
			itemp1.setIdItem("01");
			itemp1.setTesto("Monitoraggio");
			itemp1.setTipoItem(TIPO_ITEM.RAMO);
			itemp1.setSubItems(subItems_1);
			menu.put("01", itemp1);
			
			ItemMenu itemp2 = new ItemMenu();
			itemp2.setIdItem("00");
			itemp2.setTesto("Cruscotto convalida Fabbisogno");
			itemp2.setUrl("fn-convalida-fabbisogno.do");
			itemp2.setTipoItem(TIPO_ITEM.FOGLIA);
			menu.put("02", itemp2);
			
			ItemMenu itemp3 = new ItemMenu();
			itemp3.setIdItem("00");
			itemp3.setTesto("Consultazione puntuale PTOF");
			itemp3.setUrl("fn-consultazione-puntuale.do");
			itemp3.setTipoItem(TIPO_ITEM.FOGLIA);
			menu.put("03", itemp3);
			
			LinkedHashMap<String, ItemMenu> subItems_2 = new LinkedHashMap<String, ItemMenu>();
			
			ItemMenu sub_5_0 = new ItemMenu();
			sub_5_0.setIdItem("00");
			sub_5_0.setTesto("Gestione catalogo documenti");
			sub_5_0.setUrl("fn-gestione-catalogo-documenti.do");
			sub_5_0.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_2.put("07", sub_5_0);
			
			ItemMenu sub_5_1 = new ItemMenu();
			sub_5_1.setIdItem("00");
			sub_5_1.setTesto("Gestione catalogo documenti decreti");
			sub_5_1.setUrl("fn-gestione-catalogo-documenti-decreti.do");
			sub_5_1.setTipoItem(TIPO_ITEM.FOGLIA);
			subItems_2.put("08", sub_5_1);
			
			ItemMenu itemp4 = new ItemMenu();
			itemp4.setIdItem("02");
			itemp4.setTesto("Amministrazione");
			itemp4.setTipoItem(TIPO_ITEM.RAMO);
			itemp4.setSubItems(subItems_2);
			menu.put("04", itemp4);
			
		}
		out.setMenu(menu);
		return out;
	
	}	
	
	
	public Menu test1(UserDetails userDetails) throws Exception {
		Populator p = new PopulatorBuilder().build();

		if (null == development || development.equalsIgnoreCase("false")) {
			return caricaMenuGestioneUtenzeSidi.creaMenu(userDetails);

		} else {

			Menu out = new Menu();
			out.setTitolo(messageSource.getMessage("label.menu.funzioni.vuoto", null, Locale.ITALY));
			LinkedHashMap<String, ItemMenu> menu = new LinkedHashMap<String, ItemMenu>();

			{
				ItemMenu itemp0 = new ItemMenu();
				itemp0.setIdItem("00");
				itemp0.setTesto("0:0");
				itemp0.setUrl("#");
				itemp0.setTipoItem(TIPO_ITEM.FOGLIA);

				// itemp0.setSubItems(subItems1);

				menu.put("0:0", itemp0);
			}

			{

				ItemMenu itemp = new ItemMenu();
				itemp.setIdItem("011");
				itemp.setTesto("0:1:1");
				itemp.setUrl("#)");
				itemp.setTipoItem(TIPO_ITEM.FOGLIA);

				LinkedHashMap<String, ItemMenu> subItems1 = new LinkedHashMap<String, ItemMenu>();
				subItems1.put("0:1:1", itemp);

				ItemMenu itemp2 = new ItemMenu();
				itemp2.setIdItem("012");
				itemp2.setTesto("0:1:2");
				itemp2.setUrl("#)");
				itemp2.setTipoItem(TIPO_ITEM.RAMO);

				{
					LinkedHashMap<String, ItemMenu> subItems3 = new LinkedHashMap<String, ItemMenu>();

					ItemMenu itemp3 = new ItemMenu();
					itemp3.setIdItem("1");
					itemp3.setTesto("0:1:2:1");
					itemp3.setUrl("#)");
					itemp3.setTipoItem(TIPO_ITEM.FOGLIA);

					subItems3.put("0:1:2:1", itemp3);

					ItemMenu itemp31 = new ItemMenu();
					itemp31.setIdItem("1");
					itemp31.setTesto("0:1:2:2");
					itemp31.setUrl("#)");
					itemp31.setTipoItem(TIPO_ITEM.FOGLIA);

					subItems3.put("0:1:2:2", itemp31);

					ItemMenu itemp32 = new ItemMenu();
					itemp32.setIdItem("0123");
					itemp32.setTesto("0:1:2:3");
					itemp32.setUrl("#");
					itemp32.setTipoItem(TIPO_ITEM.RAMO);

					subItems3.put("0:1:2:3", itemp32);

					{
						LinkedHashMap<String, ItemMenu> subItems4 = new LinkedHashMap<String, ItemMenu>();

						ItemMenu itemp43 = new ItemMenu();
						itemp43.setIdItem("1");
						itemp43.setTesto("0:1:2:3:1");
						itemp43.setUrl("#)");
						itemp43.setTipoItem(TIPO_ITEM.FOGLIA);

						subItems4.put("0:1:2:3:1", itemp43);

						ItemMenu itemp42 = new ItemMenu();
						itemp42.setIdItem("01232");
						itemp42.setTesto("0:1:2:3:2");
						itemp42.setUrl("#");
						itemp42.setTipoItem(TIPO_ITEM.RAMO);

						subItems4.put("0:1:2:3:2", itemp42);

						{
							LinkedHashMap<String, ItemMenu> subItems5 = new LinkedHashMap<String, ItemMenu>();

							ItemMenu itemp53 = new ItemMenu();
							itemp53.setIdItem("1");
							itemp53.setTesto("0:1:2:3:1");
							itemp53.setUrl("#");
							itemp53.setTipoItem(TIPO_ITEM.FOGLIA);

							subItems5.put("0:1:2:3:1", itemp53);

							ItemMenu itemp52 = new ItemMenu();
							itemp52.setIdItem("01232");
							itemp52.setTesto("0:1:2:3:2");
							itemp52.setUrl("#");
							itemp52.setTipoItem(TIPO_ITEM.FOGLIA);

							subItems5.put("0:1:2:3:2", itemp52);

							itemp42.setSubItems(subItems5);

						}

						itemp32.setSubItems(subItems4);

					}

					itemp2.setSubItems(subItems3);

				}

				subItems1.put("0:1:2", itemp2);

				ItemMenu itemp0 = new ItemMenu();
				itemp0.setIdItem("0");
				itemp0.setTesto("0:1");
				itemp0.setUrl("#");
				itemp0.setTipoItem(TIPO_ITEM.RAMO);

				itemp0.setSubItems(subItems1);

				menu.put("0:1", itemp0);
			}

			{
				ItemMenu itemp0 = new ItemMenu();
				itemp0.setIdItem("02");
				itemp0.setTesto("0:2");
				itemp0.setUrl("#");
				itemp0.setTipoItem(TIPO_ITEM.RAMO);
				menu.put("0:2", itemp0);
	 
				{
					LinkedHashMap<String, ItemMenu> subItems3 = new LinkedHashMap<String, ItemMenu>();
					ItemMenu itemp3 = new ItemMenu();
					itemp3.setIdItem("021");
					itemp3.setTesto("0:2:1");
					itemp3.setUrl("#)");
					itemp3.setTipoItem(TIPO_ITEM.RAMO);
					subItems3.put("0:2:1", itemp3);
					itemp0.setSubItems(subItems3); 
					{
						LinkedHashMap<String, ItemMenu> subItems4 = new LinkedHashMap<String, ItemMenu>();
						ItemMenu itemp4 = new ItemMenu();
						itemp4.setIdItem("0211");
						itemp4.setTesto("0:2:1:1");
						itemp4.setUrl("#)");
						itemp4.setTipoItem(TIPO_ITEM.FOGLIA);
						subItems4.put("0:2:1:1", itemp4);
						itemp3.setSubItems(subItems4); 
					}		

				}		

				
			}

			if (menu != null && !menu.values().isEmpty()) {
				out.setTitolo(messageSource.getMessage("label.menu.funzioni.title", null, Locale.ITALY));
				out.setMenu(menu);
			}

			return out;
		}
	}

	public Menu creaMenuSuperUtente() throws Exception {

		if (null != development) {
			return caricaMenuGestioneUtenzeSidi.creaMenuSuperUtente();
		} else {
			Menu menu = new Menu();
			menu.setTitolo("Menu Moc");
			menu.setMenu(new LinkedHashMap<String, ItemMenu>());
			return menu;
		}

	}

	public Map<String, String> getVociMenu() {
		return vociMenu;
	}

	public void setVociMenu(Map<String, String> vociMenu) {
		this.vociMenu = vociMenu;
	}

	public String getDesFunzioneById(BigDecimal prgFunz) throws Exception {

		String out = prgFunz.toString();
		return out;
	}

}
