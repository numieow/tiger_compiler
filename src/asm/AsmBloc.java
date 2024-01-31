package asm;

import java.util.ArrayList;

public class AsmBloc {

    String category;
    String id;
    int imbrication;
    String codeTiger;
    ArrayList<String> codeAsm;
    int region;

    public AsmBloc(String id, ArrayList<String> asmCode, String codeTiger, int imbrication , String category){
        this.id = id;
        this.imbrication = imbrication;

        this.codeTiger = codeTiger;
        this.codeAsm = asmCode;
        this.category  = category;
    }
    public String getCategory() {
        return category;
    }

    public void addAsmBody(String asmCode) {
        
        this.codeAsm.set(1, this.codeAsm.get(1) + asmCode);
    }
    public int getRegion() {
        return region;
    }
    public void setRegion(int region) {
        this.region = region;
    }

    public void addAsmHead(String asmCode) {
        this.codeAsm.set(0, this.codeAsm.get(0) + asmCode);
    }

    public void addAsmTail(String asmCode) {
        this.codeAsm.set(2, this.codeAsm.get(2) + asmCode);
    }

    public String getAsm() {
        StringBuilder asm = new StringBuilder();
        for (String s : codeAsm) {
            asm.append(s);
        }
        return asm.toString();
    }
    
    public ArrayList<String> getAsmCode() {
        return codeAsm;
    }

    public void setAsmCode(ArrayList<String> asmCode) {
        this.codeAsm = asmCode;
    }

    public String getTiger() {
        return codeTiger;
    }
    public void setTiger(String tiger) {
        this.codeTiger = tiger;
    }

    public void setCodeTiger(String codeTiger) {
        this.codeTiger = codeTiger;
    }

    public void updateTiger(String tiger) {
        this.codeTiger = this.codeTiger.replace(tiger, "");
    }

    public void addTiger(String tiger) {
        this.codeTiger = this.codeTiger + tiger;
    }
}