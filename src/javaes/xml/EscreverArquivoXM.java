package javaes.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javaes.modelo.Produto;

/**
 *
 * @author http://javaes.wordpress.com/
 */
public class EscreverArquivoXM {

    public static void gravaXMLListaProdutos(Map<String, Produto> produtos, String localArquivo) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("produtos", List.class);
        File arquivo = new File(localArquivo + "/produtos.xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(produtos).getBytes());
            gravar.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
