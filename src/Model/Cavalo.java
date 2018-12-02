/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lucas
 */
public class Cavalo {
    
    private String id;
    private int cor;
    private boolean vencedor;
    
    private int linha;
    private int coluna;
    
    public Cavalo(String id, int cor){
        this.id = id;
        this.cor = cor;
        this.vencedor = false;
        this.linha = this.coluna = -1;
    }   
    
    public Cavalo(String id, int cor, int linha, int coluna){
        this.id = id;
        this.cor = cor;
        this.vencedor = false;
        this.linha = linha;
        this.coluna = coluna;
    }  
    
    public Cavalo(String id){
        this.id = id;
        this.vencedor = false;
        this.cor = this.linha = this.coluna = -1;
    }  
    
    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor() {
        this.vencedor = true;
    } 
    
    public String getId() {
        return this.id;
    }       
    
    public int getCor() {
        return cor;
    }

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public void setCor(int cor) {
		this.cor = cor;		
	}    
    
    
    
}
