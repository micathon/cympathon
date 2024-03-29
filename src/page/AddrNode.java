package page;

import iconst.PageTyp;

public class AddrNode {
	
	private short header;
	private int addr;
	public static final int NONVAR = 0;
	public static final int LOCVAR = 1;
	public static final int FLDVAR = 2;
	public static final int GLBVAR = 3;
	
	public AddrNode(int header, int addr) {
		this.header = (short) header;
		this.addr = addr;
	}
	
	public int getHeader() {
		return header;
	}
	
	public void setHeader(int header) {
		this.header = (short) header;
	}

	public int getAddr() {
		return addr;
	}
	
	public void setAddr(int addr) {
		this.addr = addr;
	}
	
	public boolean isInt() {
		int hdrPgTyp = header & 0xF;
		boolean rtnval;

		rtnval = (hdrPgTyp == PageTyp.INTVAL.ordinal()) ||
			(hdrPgTyp == PageTyp.BOOLEAN.ordinal());
		return rtnval;
	}
	
	public boolean hasValue() {
		return ((header & 0x0100) != 0);
	}
	
	public void setValue() {
		header |= 0x0100;
	}
	
	public boolean isPtr() {
		return ((header & 0x0200) != 0);
	}
	
	public void setPtr() {
		header |= 0x0200;
	}
	
	public boolean isInz() {
		return ((header & 0x0400) != 0);
	}
	
	public void setInz() {
		header |= 0x0400;
	}
	
	public boolean isInzValid() {
		return (isInz() || getHdrNonVar());
	}
	
	public void setHdrPgTyp(PageTyp pgtyp) {
		short mask = (short)(pgtyp.ordinal());
		header = (short)((header & 0xFFF0) | mask);
	}
	
	public PageTyp getHdrPgTyp() {
		PageTyp rtnval = PageTyp.values()[header & 0xF];
		return rtnval;
	}
	
	public int getHdrLocVarTyp() {
		short locVarTyp = (short)((header & (short)0x0030) >>> 4);
		return (int)locVarTyp;
	}

	public void setHdrLocVarTyp(int locVarTyp) {
		int mask = 0xFFCF;
		header &= mask;
		locVarTyp &= 0x3;
		header |= (locVarTyp << 4);
	}
	
	public void setHdrNonVar() {
		setHdrLocVarTyp(NONVAR);
	}
	
	public void setHdrLocVar() {
		setHdrLocVarTyp(LOCVAR);
	}
	
	public void setHdrFldVar() {
		setHdrLocVarTyp(FLDVAR);
	}
	
	public void setHdrGlbVar() {
		setHdrLocVarTyp(GLBVAR);
	}
	
	public boolean getHdrNonVar() {
		return (getHdrLocVarTyp() == NONVAR);
	}
	
	public boolean getHdrLocVar() {
		return (getHdrLocVarTyp() == LOCVAR);
	}
	
	public boolean getHdrFldVar() {
		return (getHdrLocVarTyp() == FLDVAR);
	}
	
	public boolean getHdrGlbVar() {
		return (getHdrLocVarTyp() == GLBVAR);
	}
	
}
