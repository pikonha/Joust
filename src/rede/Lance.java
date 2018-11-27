/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import Model.ImagemTabuleiro;
import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author Lucas
 */
public class Lance implements Jogada {
    
    ImagemTabuleiro imagemTabuleiro;

    public Lance(ImagemTabuleiro imagemTabuleiro) {
        super();
        this.imagemTabuleiro = imagemTabuleiro;
    }

    public ImagemTabuleiro getImagemTabuleiro() {
        return imagemTabuleiro;
    }
  
}
