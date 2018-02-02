package it.istruzione.ptof.component.report;

import com.lowagie.text.Font;
 
public class PtofFonts {
    
	
    private static final int FONT_H3 = 7;
    private static final int FONT_H2 = 9;
    private static final int FONT_H1 = 16;
    private static final int FONT_H4 = 72;
	    
    public static final Font[] NORMAL = new Font[]{
    		new Font(Font.HELVETICA, FONT_H1, Font.NORMAL),
    		new Font(Font.HELVETICA, FONT_H2, Font.NORMAL),
    		new Font(Font.HELVETICA, FONT_H3, Font.NORMAL)
    };
    
    public static final Font[] BOLD = new Font[]{
    		new Font(Font.HELVETICA, FONT_H1, Font.BOLD),
    		new Font(Font.HELVETICA, FONT_H2, Font.BOLD),
    		new Font(Font.HELVETICA, FONT_H3, Font.BOLD)
    };
    
    
    public static final Font[] ITALIC = new Font[]{
    		new Font(Font.HELVETICA, FONT_H1, Font.ITALIC),
    		new Font(Font.HELVETICA, FONT_H2, Font.ITALIC),
    		new Font(Font.HELVETICA, FONT_H3, Font.ITALIC)
    };
    
    public static final Font[] BOLDITALIC = new Font[]{
    		new Font(Font.HELVETICA, FONT_H1, Font.BOLDITALIC),
    		new Font(Font.HELVETICA, FONT_H2, Font.BOLDITALIC),
    		new Font(Font.HELVETICA, FONT_H3, Font.BOLDITALIC),
    		new Font(Font.HELVETICA, FONT_H4, Font.BOLDITALIC)
    };
}
