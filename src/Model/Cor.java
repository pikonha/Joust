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
public enum Cor {
    BRANCO,
    PRETO;
    
    public String toString(Cor cor) {
        return cor == BRANCO ? "Branco" : "Preto";
    }
    
}
