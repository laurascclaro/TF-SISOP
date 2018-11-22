package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaBlocosLivres {

    LinkedList<BlocoLivre> blocos;

    public ListaBlocosLivres() {
        blocos = new LinkedList<BlocoLivre>();//inicializa lista de blocos do tipo Bloco
    }

    public void insereBloco(int inicioBloco, int fimBloco) {
        BlocoLivre novoB = new BlocoLivre(inicioBloco, fimBloco);//inicializa novo bloco
        
        if (blocos.size() == 0) {
            blocos.add(novoB);//se nao houver nenhum bloco inserido, adiciona o novo (em qualquer lugar)
        } else {
            Iterator it = blocos.iterator();//percorrer lista de blocos
            int posicao = 0;//contador para posicoes da lista
            BlocoLivre anterior = null;//variavel para armazenar bloco que ainda não foi concatenado
            while (it.hasNext()) {
                BlocoLivre b = (BlocoLivre) it.next();//bloco que está na lista
                
                if ( b.endInicial > novoB.endFinal ) {
                    blocos.add(posicao,novoB);//adiciona-se em determinada posicao o novo bloco
                    return;//encerra if
                } else if ( b.endInicial == novoB.endFinal )  {
                    b.endInicial = novoB.endInicial;//end inicial bloco b passa a ser o end inicial do bloco inserido
                    if (anterior != null)
                        if (anterior.endFinal == b.endInicial) {
                            b.endInicial = anterior.endInicial;//atribui ao end inicial do bloco b o end inicial do bloco anterior
                            blocos.remove(anterior);//remove o anterior
                        }
                    return;
                }
                posicao++;//incrementa posicao
                anterior = b; //precisa do anterior caso vá concatenar o anterior com o novo bloco
            }
            blocos.addLast(novoB); //adiciona bloco à ultima posicao da lista
        }
    }

    public void insereBloco(BlocoLivre nb) {
        insereBloco(nb.endInicial,nb.endFinal);
    }
    
    public boolean existeBlocoLivre(int tamanho) {
        Iterator it = blocos.iterator();
        boolean achou = false;

        while (it.hasNext()) {
            BlocoLivre b = (BlocoLivre) it.next();

            //verifica se ha blocos disponiveis
            if ( (b.endFinal - b.endInicial) >= tamanho ) {
                achou = true;
                break;
            }
        }
        return achou;
    }

    //percorre blocos livres para remover bloco livre e criar novo bloco ocupado
     public BlocoOcupado retornaBloco(int tamanho) {
         Iterator it = blocos.iterator();
         BlocoOcupado novoB = null;

         while (it.hasNext()) {
             BlocoLivre b = (BlocoLivre) it.next();

             if ( (b.endFinal - b.endInicial) > tamanho ) {
                 novoB = new BlocoOcupado(b.endInicial, b.endInicial+tamanho);
                 b.endInicial += tamanho;
                 break;
             } if ( (b.endFinal - b.endInicial) == tamanho ) {
                 novoB = new BlocoOcupado(b.endInicial, b.endInicial+tamanho);
                 blocos.remove(b);
                 break;
             }
         }
         return novoB;

     }

     //calcula tamanho total do bloco
     public int tamanhoTotal() {
        Iterator it = blocos.iterator();
        int tamanho = 0;

        while (it.hasNext()) {
            BlocoLivre b = (BlocoLivre) it.next();

            tamanho += (b.endFinal - b.endInicial);

        }

        return tamanho;
    }

    //imprime blocos livres e seus respectivos tamanhos
    public void imprimeBlocos() {
        Iterator it = blocos.iterator();

        System.out.println("Blocos Livres:");
        while (it.hasNext()) {
            BlocoLivre b = (BlocoLivre) it.next();

            System.out.println(b.endInicial+"-"+b.endFinal+" (Tamanho = "+(b.endFinal-b.endInicial)+")");
        }

    }
}

