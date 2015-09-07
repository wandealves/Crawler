package javaes.modelo;

import java.io.Serializable;

/**
 *
 * @author http://javaes.wordpress.com/
 */
public class Produto implements Serializable {

    private static final long serialVersionUID = 3672618296486617806L;
    private String codProduto;
    private String descProduto;
    private String preco;
    private String urlProduto;
    private String imgProduto;

    public Produto() {
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(String imgProduto) {
        this.imgProduto = imgProduto;
    }
}
