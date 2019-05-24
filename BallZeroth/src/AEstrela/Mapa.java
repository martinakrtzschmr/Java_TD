package AEstrela;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin.akretzschmar
 */
public class Mapa {

    public static List<No> mapa = new ArrayList();
    public static int linhas;
    public static int colunas;

    public Mapa(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        criaMapa();
        configuraMapa();
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public static List<No> getMapa() {
        return mapa;
    }

    public static void criaMapa() {
        int contador = 0;
        for (int x = 0; x < linhas; x++) {
            for (int y = 0; y < colunas; y++) {
                No no = new No(contador, x, y);
                mapa.add(no);

                contador++;
            }
        }
    }

    public static void configuraMapa() {
        for (No no : mapa) {
            no.vizinhos.addAll(acharCantos(no));
            no.vizinhos.addAll(acharOrtogonais(no));
        }
    }

    public static List<No> acharCantos(No no) {
        int id = no.getId();
        List<No> list = new ArrayList();

        //calcular linha
        int linhaDoNo = (no.getId() / linhas) + 1;
        //calcula coluna
        int colunaDoNo = (no.getId() % colunas) + 1;

        //pega canto superior esquerda
        if (linhaDoNo > 1 && colunaDoNo > 1) {
            list.add(mapa.get((id - colunas) - 1));
        }
        //pega canto superior direita
        if (linhaDoNo > 1 && colunaDoNo < colunas) {
            list.add(mapa.get((id - colunas) + 1));
        }

        //pegar canto infoerior esquerdo
        if (linhaDoNo < mapa.size() / linhas && colunaDoNo > 1) {
            list.add(mapa.get((id + colunas) - 1));
        }
        //pegar canto inferior direito
        if (linhaDoNo < mapa.size() / linhas && colunaDoNo < colunas) {
            list.add(mapa.get((id + colunas) + 1));
        }

        return list;
    }

    public static List<No> acharOrtogonais(No no) {
        //calcular linha
        int linhaDoNo = (no.getId() / linhas) + 1;
        //calcula coluna
        int colunaDoNo = (no.getId() % colunas) + 1;
        List<No> list = new ArrayList();
        int id = no.getId();
        //pegar vizinho esquerdo
        if (colunaDoNo > 1) {
            list.add(mapa.get(id - 1));
        }
        //pegar vizinho direito
        if (colunaDoNo < colunas) {
            list.add(mapa.get(id + 1));
        }
        //pegar vizinho cima
        if (linhaDoNo > 1) {
            list.add(mapa.get((id - linhas) + 1));
        }
        //pegar vizinho baixo
        if (linhaDoNo < mapa.size() / linhas) {
            list.add(mapa.get(id + colunas));
        }

        return list;
    }
}
