package javaes.crawlerWeb;

import java.util.TreeMap;
import javaes.cache.Cache;
import javaes.configuracao.Parametros;
import javaes.modelo.Produto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author http://javaes.wordpress.com/
 */
public class HTMLJsoup {

    public HTMLJsoup(String html) {
        if (Cache.produtosCache == null) {
            Cache.produtosCache = new TreeMap();
        }
        try {
            // Parse the String into a Jsoup Document
            Document doc = Jsoup.parse(html);
            Elements body = doc.body().children();
            Elements produto = doc.select(Parametros.TAGPRODUTO);
            Elements preco = doc.select(Parametros.TAGPRECO);
            Elements produtoID = doc.select(Parametros.TAGPRODUTOID);
            Elements url = doc.select(Parametros.TAGURL);
            Elements imagem = doc.select(Parametros.TAGIMAGEM);
            for (int i = 0; i < produto.size(); i++) {
                Produto produtoObj = new Produto();
                //############ Preenche Objeto #################
                if (i < (produtoID.size() - 1)) {
                    produtoObj.setCodProduto(produtoID.get(i).val());
                } else {
                    produtoObj.setCodProduto("");
                }
                produtoObj.setDescProduto(produto.get(i).text());
                if (i < (imagem.size() - 1)) {
                    produtoObj.setImgProduto(imagem.get(i).attr("abs:src"));
                } else {
                    produtoObj.setImgProduto("");
                }
                if (i < (preco.size() - 1)) {
                    produtoObj.setPreco(preco.get(i).text());
                } else {
                    produtoObj.setPreco("");
                }
                if (i < (url.size() - 1) && !produtoObj.getCodProduto().isEmpty()) {
                    String urlProduto = this.recuperaURLProduto(url, produtoObj.getCodProduto());
                    produtoObj.setUrlProduto(urlProduto);
                } else {
                    produtoObj.setUrlProduto("");
                }
                if (Cache.produtosCache.containsKey(produtoObj.getCodProduto()) == false && !produtoObj.getCodProduto().isEmpty()
                        && !produtoObj.getDescProduto().isEmpty() && !produtoObj.getPreco().isEmpty() && !produtoObj.getPreco().isEmpty()) {
                    Cache.produtosCache.put(produtoObj.getCodProduto(), produtoObj);
                }
            }
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
    }

    private String recuperaURLProduto(Elements url, String idProduto) {
        for (int i = 0; i < url.size(); i++) {
            String[] vetURL = url.get(i).attr("abs:href").trim().split("/");
            if (vetURL[4].trim().equals(idProduto.trim())) {
                return url.get(i).attr("abs:href");
            }
        }
        return "";
    }
}
