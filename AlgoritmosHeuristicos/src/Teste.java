import java.util.Arrays;
public class Teste {

	public static void main(String[] args) {
        //declaração da classe
        //os dois Arrays
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {12, 3, 5, 8, 9, 6, 5, 3, 11, 23};
        //organiza o array
        Arrays.sort(a);
        Arrays.sort(b);
        //percorre o array a e verifica se cada elemento dele está no b
        for(int i=0;i<a.length;i++){
            /*binarySearch() retorna o indice de onde foi encontrado o valor, e caso ele nao exista retorna um valor negativo*/
            if(Arrays.binarySearch(b, a[i])>0){
                 System.out.println(a[i]);
            }
        }

	}

}
