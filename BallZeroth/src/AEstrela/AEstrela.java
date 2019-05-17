package AEstrela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author martin.akretzschmar
 */
public class AEstrela {
    public static List<No> listaFechada = new ArrayList();
    public static List<No> listaAberta = new ArrayList();
    public static List<No> caminho = new ArrayList();    
    public static int colunasDoMapa = 0;
    public static int linhasDoMapa = 0;
    public static int tamanhoDoMapa = 0;
   
    public static List<No> aEstrela(No noInicial, No noDestino, Mapa mapa)
    {
        colunasDoMapa = mapa.getColunas();
        linhasDoMapa = mapa.getLinhas();
        tamanhoDoMapa = mapa.getMapa().size();
        
        listaFechada.clear();
        listaAberta.clear();
        caminho.clear();
        boolean achouCaminho = false;
    
        No noAtual = noInicial;
        listaAberta.add(noInicial);
        
        while(!achouCaminho)
        {
            noAtual = procularMenorF();
            listaAberta.remove(noAtual);
            listaFechada.add(noAtual);
            achouCaminho = noAtual.equals(noDestino);
            
            
            for(No no: noAtual.getVizinhos())
            {
                if(no.estaBloqueado() || listaFechada.contains(no))
                {
                    continue;
                }else{
                    if(!listaAberta.contains(no))
                    {
                        listaAberta.add(no);
                        no.setPai(noAtual);
                        no.setH(calcularH(no, noDestino));
                        no.setG(calcularG(no, noAtual));
                        no.setF(calcularF(no));
                    }else{
                        if(no.getG()<noAtual.getG())
                        {
                            no.setPai(noAtual);
                            no.setG(calcularG(noAtual, no));
                            no.setF(calcularF(no));
                        }
                    
                    }
                }
            
            }
            if(listaAberta.isEmpty())
            {
                System.out.println("Não achou ");
                return null;
            }
        }
        
        return montaCaminho(noInicial, noDestino, mapa);
    }
    
    public static No procularMenorF() {
        Collections.sort(listaAberta, Comparator.comparing(No::getF));
        return listaAberta.get(0);
        
    }
    
    
       
    public static float calcularF(No no)
    {
        return no.getG()+ no.getH();

    }
    
    public static float calcularG(No noAtual, No noVizinho)
    {
        if (noVizinho.getId() % colunasDoMapa == noAtual.getId() % colunasDoMapa || noVizinho.getId() + 1 == noAtual.getId() || noVizinho.getId() - 1 == noAtual.getId()) {
            return noVizinho.getG() + 10;
        } else {
            return noVizinho.getG() + 14;
        }
        
    }
 
    
    public static float calcularH(No noAtual, No noDestino)
    {
        int posicaoDestinoX = (noDestino.getId()%colunasDoMapa)+1;
        int posicaoNoAtualX = (noAtual.getId()%colunasDoMapa)+1;
        
        int distanciaX = posicaoDestinoX > posicaoNoAtualX ? posicaoDestinoX - posicaoNoAtualX : posicaoNoAtualX - posicaoDestinoX;
        
        int posicaoDestinoY = (noDestino.getId()/linhasDoMapa)+1;
        int posicaoNoAtualY = (noAtual.getId()/linhasDoMapa)+1;
        
        int distanciaY = posicaoDestinoY > posicaoNoAtualY ? posicaoDestinoY - posicaoNoAtualY : posicaoNoAtualY - posicaoDestinoY;
        
        float distanciaTotal = (float)Math.sqrt((Math.pow(distanciaX, 2)+Math.pow(distanciaY, 2)))*10;
                
        return distanciaTotal;
    }

    private static List<No> montaCaminho(No noInicial, No noDestino, Mapa mapa) {
        List<No> listaAuxiliar = new ArrayList();
        No noAtual = noDestino;
        int contador = 0;
        while (!listaAuxiliar.contains(noInicial) || contador > tamanhoDoMapa)
        {
            listaAuxiliar.add(noAtual);
            
            noAtual = noAtual.getPai();
                        
            contador++;
        }
        Collections.reverse(listaAuxiliar);
        
        
        //imprimir caminho
        System.out.println("Caminho: ");
        for(No no: listaAuxiliar)
        {
            System.out.print(" -> " + no.getId());
        }
        //inicio artificio apenas para printar caminho
        for(No no: mapa.getMapa())
        {
          if(!listaAuxiliar.contains(no))  no.setPai(null);
          
        }
        //fim do artificio
        
        System.out.println("");
        desenha(mapa);
        System.out.println("Fim ! ");
        
        //retorno do caminho
        return listaAuxiliar;
    }
    
    public static void desenha(Mapa mapa){
      System.out.println("");
      for (int i = 0; i<mapa.getLinhas(); i++)
        {
            for (int j = 0; j<mapa.getColunas(); j++)
            {
              No no = mapa.getMapa().get((i*mapa.getColunas())+j);
              if(no.getPai() != null ){
                System.out.print("[-]");
              }else if(no.estaBloqueado()){
                System.out.print("[X]");
              }else{
                System.out.print("[ ]");
              }
            }
             System.out.println();
        }
    }
}
