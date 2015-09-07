package javaes.main;

import java.util.TreeMap;
import javaes.cache.Cache;
import javaes.configuracao.Parametros;
import javaes.crawlerWeb.CrawlerController;
import javaes.xml.EscreverArquivoXM;

/**
 *
 * @author javaES
 */
public class Principal {

    public static void main(String[] args) {
        try {
            Cache.produtosCache = new TreeMap();
            CrawlerController.executarCrawler();

            if (Cache.produtosCache != null) {
                EscreverArquivoXM.gravaXMLListaProdutos(Cache.produtosCache, Parametros.DIRETORIO);
            }
            Cache.produtosCache = null;
        } catch (Exception erro) {
            System.out.println("Erro ao iniciar: " + erro.getMessage());
        }
    }
}
