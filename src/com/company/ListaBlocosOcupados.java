package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaBlocosOcupados {

    LinkedList<BlocoOcupado> blocos;

    public ListaBlocosOcupados() {
        blocos = new LinkedList<BlocoOcupado>();//inicializa lista de blocos do tipo Bloco
    }

    public void insereBloco(BlocoOcupado bo) {
        System.out.println("Inserindo em: (" +bo.id+") "+ bo.endInicial + " - " + bo.endFinal);
        
        blocos.addLast(bo);//insere novo bloco ocupado no final da lista

    }
    
    public BlocoLivre liberaBloco(int idBloco){
        Iterator it = blocos.iterator();
        //percorre lista de blocos ocupados para liberar bloco de acordo com seu id (entrada pelo arquivo de texto)
        while (it.hasNext()) {
            BlocoOcupado b = (BlocoOcupado) it.next();
            
            if (b.id == idBloco) {
                blocos.remove(b);
                BlocoLivre novoBloco = new BlocoLivre(b.endInicial,b.endFinal);//cria bloco livre
                return novoBloco;
            }
        }

        return null;
    }

    public void imprimeBlocos() {
        Iterator it = blocos.iterator();
        
        System.out.println("Blocos Ocupados:");

        while (it.hasNext()) {
            BlocoOcupado b = (BlocoOcupado) it.next();

            System.out.println(b.id+":"+b.endInicial+"-"+b.endFinal);
        }

    }
}

