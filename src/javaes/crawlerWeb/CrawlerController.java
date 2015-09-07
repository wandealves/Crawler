package javaes.crawlerWeb;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import javaes.configuracao.Parametros;

/**
 *
 * @author http://javaes.wordpress.com/
 */
public class CrawlerController {

    public static void executarCrawler() throws Exception {
        //crawlStorageFolder é uma pasta onde os dados de 
        //rastreamento intermediários é armazenado.
        String crawlStorageFolder = Parametros.DIRETORIO;
        //numberOfCrawlers mostra o número de threads simultâneos 
        //que deveria ser iniciado para rastreamento.
        int numberOfCrawlers = 10;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
         //Seja educado: Certifique-se de que nós não 
        //enviar mais de um pedido por segundo 
        //(1000 milissegundos entre as solicitações).
        config.setPolitenessDelay(1000);
         //Você pode definir a profundidade máxima de rastreamento aqui. 
        //O valor padrão é -1 para profundidade ilimitada
        config.setMaxDepthOfCrawling(-1);
         //Você pode definir o número máximo de páginas a engatinhar. 
        //O valor padrão é -1 para um número ilimitado de páginas
        config.setMaxPagesToFetch(600);
         //Você precisará configurar um proxy? Se assim for, você pode usar:
        //config.setProxyHost ("proxyserver.example.com");
        //config.setProxyPort (8080);
        //Se o seu proxy também precisa de autenticação:config.
        //setProxyUsername (username); config.getProxyPassword (password);

         //Este parâmetro de configuração pode ser usada para definir 
        //o seu rastreamento a ser resumable
        //(o que significa que você pode retomar o rastreamento a partir de uma 
        //previamente interrompida / caiu crawl). 
        //Nota: Se você ativar o recurso de retomada e deseja iniciar um novo rastreamento, 
        //você precisa apagar o conteúdo da rootFolder manualmente.
        config.setResumableCrawling(false);
        //Instanciar o controlador para este rastreamento.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
         //Para cada rastreamento, você precisa adicionar algumas urls semente. 
        //Estas são as primeiras URLs que são buscados e, em seguida, 
        //o rastreador começa seguintes links que são encontrados nestas páginas
        controller.addSeed(Parametros.URLSITE);
         //Iniciar o rastreamento. 
        //Esta é uma operação de bloqueio, o que significa que seu código vai 
        //chegar a linha após isso somente quando rastejando está terminado.
        controller.start(CrawlerWeb.class, numberOfCrawlers);
    }
}
