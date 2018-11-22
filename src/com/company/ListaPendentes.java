package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaPendentes {
    LinkedList<Integer> blocos;
    
    public ListaPendentes() {
        blocos = new LinkedList<Integer>();
    }
    
    public void insereBloco(int tamanho) {
        
        blocos.addLast(new Integer(tamanho));
        
    }

    //verifica se ha blocos livres e aloca pendente no que achar
    public void verificaBlocosLivres(ListaBlocosLivres blocosLivres, ListaBlocosOcupados blocosOcupados){
        Iterator it = blocos.iterator();
        
        while (it.hasNext()) {
            Integer b = (Integer) it.next();
            int tamanho = b.intValue();
            
            if (blocosLivres.existeBlocoLivre(tamanho)) {
                BlocoOcupado novoB = blocosLivres.retornaBloco(tamanho);
                System.out.println("Solicitação pendente de tamanho "+tamanho+" - Será inseririda no bloco:("+novoB.id+") "+novoB.endInicial+"-"+novoB.endFinal);
                blocosOcupados.insereBloco(novoB);//insere novo bloco na lista de blocos ocupados
                blocos.remove(b);//remove bloco da lista de pendentes
            }
        }
    }


}
