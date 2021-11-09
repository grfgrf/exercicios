import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class java01 {

    public static void main(String[] args) {
        String[] vetorString = {"pomba","oi","astra","padaria"};
        int[] vetorInt = {5, 9, 9, 4, 5, 4, 9, 9, 2};
        int[] vetorVazio = {1};
        int[] vetorInt2 = {50, 1, 3, 2};
        int[] vetorAm = {70, 23, 50, 9, 30, 50, 3};
        int[] vetor45 = {5, 4, 9, 4, 9, 5};
        List<String> listaString = Arrays.asList("24", "a", "","B","xx","yyy","xxy", "ya", "zz","yy");
        List<Integer> listaInt = Arrays.asList(66, 88, 6,0, 8,9,29,19,99 -1);
        LinkedList<Integer> ll1 = new LinkedList<Integer>(Arrays.asList(7, 5, 9, 8, 1));
        LinkedList<Integer> ll2 = new LinkedList<Integer>(Arrays.asList(8, 4));
        Map<String,String> mapa1 =  new HashMap<>();
        mapa1.put("a","aaa"); mapa1.put("b","bbb"); mapa1.put("c","ccc");
        Map<String,String> mapVazio =  new HashMap<>();

        //sum3(vetorInt);
        //maxTriple(vetorInt);
        //makeLast(vetorInt);
        //reverse3(vetorInt);
        //biggerTwo(vetorInt,vetorInt2);
        //frontPiece(vetorInt, 2);
        //kElementsV3(vetorAm, 3);
        //kElements(vetorAm, 4);
        //linkedListsSum(ll, ll1);
        //somaListaLigada(ll1,ll2);
        //somaListaLigadaV2(ll1,ll2);
        //has23(vetorAm);
        //makeEnds(vetorInt);
        //contemUmNaPrimeiraPos(vetorInt,vetorVazio);
        //somaDoisPrimeiros(vetorInt);
        //middleWay(new int[] {1,2,3}, new int[] {1,2,3});
        //contaClumps(vetorInt2);
        //contemSomaLadosIguais(vetorInt);
        //quatroDepoisCinco(vetorInt);
        //seriesUp(3);
        //squareUp(5);
        //aContemB(vetorInt,new int[]{2,4});
        //fix34(new int[]{5, 3, 5, 4, 5, 4, 5, 4, 3, 5, 3, 5});
        //mirrorLen(vetorInt2);
        //primeiroMap(mapa1);
        //pairs(vetorString);
        //firstChar(vetorString);
        //word0(vetorString);
        //wordAppendEntendiErrado(new String[]{"a", "b", "b", "b", "a", "c", "a", "a", "a", "b", "a"});
        //wordAppend(new String[]{"xx", "xx", "yy", "xx", "zz", "yy", "zz", "xx"});
        //wordLen(new String [] {"this", "and", "that", "and"});
        //firstSwap(new String [] {"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"});
        //wordCount(new String [] {"a", "b", "a", "c", "b"});
        //allSwap(new String [] {"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"});
        //wordMultiple(new String [] {"a","b","aa","a"});
        //copies3(listaString);
        //doubling(listaInt);
        //square(listaInt);
        //addStar(listaString);
        //lower(listaString);
        //rightDigit(listaInt);
        //moreY(listaString);
        //noX(listaString);
        //no9(listaInt);
        hourglass1();
        //formataTelefone("--17-5 229 35-39475");
        //exercicioTabuleiroRainha();



    }
    public static void exercicioTabuleiroRainha(){

        int [][] novoTabuleiro = montaTabuleiroRandom();
        int [] idxsRainha = buscaPosicaoRainha(novoTabuleiro);
        printaMatriz(novoTabuleiro);
        System.out.println("Passos disponíveis para movimentação da rainha:");
        System.out.println("Vertical e Horizontal: "+somaHorizontalVertical(idxsRainha,novoTabuleiro));
        System.out.println("Diagonal: "+somaDiagonal(idxsRainha,novoTabuleiro));
        System.out.println("Total: "+passosDisponiveisTabuleiro(idxsRainha,novoTabuleiro));
    }
    public static int passosDisponiveisTabuleiro(int[] idxsRainha,int[][]novoTabuleiro) {
        return somaHorizontalVertical(idxsRainha,novoTabuleiro)+somaDiagonal(idxsRainha,novoTabuleiro);
    }
    public static int somaDiagonal(int[] idxsRainha,int[][]novoTabuleiro) { // o importante é funcionar
        int somaTudo = 0;

        int linhaRainha = idxsRainha[0],linhaRainhaSobe = idxsRainha[0],
            colunaRainha=idxsRainha[1],colunaRainhaSobe = idxsRainha[1];

        int contaEsqBaixo = 0;
        boolean achouPecaEsqBaixo = false;
        //dir baixo
       // int linhaRainha = idxsRainha[0], colunaRainha = idxsRainha[1];
        int contaDirBaixo = 0;
        boolean achouPecaDirBaixo = false;
        //conta esqCima
        int contaEsqCima = 0;
        boolean achouPecaEsqCima = false;
        //conta DirCima
        int contaDirCima = 0;
        boolean achouPecaDirCima = false;

        while ((linhaRainha < novoTabuleiro.length-1 || colunaRainha > 0)
             || (linhaRainha < novoTabuleiro.length - 1 && colunaRainhaSobe < novoTabuleiro.length-1)
            || ((colunaRainha >0 && linhaRainhaSobe >0)
                && novoTabuleiro[linhaRainhaSobe - 1][colunaRainha - 1] == 0)
                || ((colunaRainhaSobe < novoTabuleiro.length-1 && linhaRainhaSobe >0)
                && novoTabuleiro[linhaRainhaSobe - 1][colunaRainhaSobe + 1] == 0)
        )
        {
            if (!achouPecaEsqBaixo) {
                if ((linhaRainha < novoTabuleiro.length - 1 && colunaRainha >0)
                        && novoTabuleiro[linhaRainha + 1][colunaRainha-1] == 0) {

                    contaEsqBaixo++;

                } else achouPecaEsqBaixo = true;
            }
            if (!achouPecaDirBaixo) {
                if ((linhaRainha < novoTabuleiro.length - 1 && colunaRainhaSobe < novoTabuleiro.length-1)
                        && novoTabuleiro[linhaRainha + 1][colunaRainhaSobe+1] == 0) {

                    contaDirBaixo++;

                } else achouPecaDirBaixo = true;
            }
            if (!achouPecaEsqCima) {
                if ((colunaRainha >0 && linhaRainhaSobe >0)
                        && novoTabuleiro[linhaRainhaSobe - 1][colunaRainha - 1] == 0) {

                    contaEsqCima++;

                } else achouPecaEsqCima = true;
            }

            if (!achouPecaDirCima) {
                if ((colunaRainhaSobe < novoTabuleiro.length-1 && linhaRainhaSobe >0)
                        && novoTabuleiro[linhaRainhaSobe - 1][colunaRainhaSobe + 1] == 0) {

                    contaDirCima++;


                } else achouPecaDirCima = true;
            }
            linhaRainha++;
            colunaRainhaSobe++;
            colunaRainha--;
            linhaRainhaSobe--;


        }

        somaTudo= contaDirBaixo+contaDirCima+contaEsqCima+contaEsqBaixo;

        return somaTudo;
    }
    public static int somaHorizontalVertical(int[] idxsRainha,int[][]novoTabuleiro) {
        //idx para "baixo e direita"
        int linhaRainha = idxsRainha[0], colunaRainha = idxsRainha[1];
        int contaDescLinha = 0;
        int contaDescColuna = 0;
        boolean achouPecaLinha = false;
        boolean achouPecaColuna = false;

        //idx para "cima e para esquerda
        int linhaRainhaDesc = idxsRainha[0], colunaRainhaDesc = idxsRainha[1];
        int contaLinhaTras = 0;
        int contaColunaTras = 0;
        boolean  achouPecaLinhaDesc=false;
        boolean  achouPecaColunaDesc=false;

        //soma tudo
        int somaVerticalHorizontal= 0;


        while(linhaRainha < novoTabuleiro.length-1 ||
                colunaRainha < novoTabuleiro.length-1 ||
                linhaRainhaDesc> 0 ||
                colunaRainhaDesc> 0) {

            if(!achouPecaLinha) {
                if (linhaRainha < novoTabuleiro.length - 1 && novoTabuleiro[linhaRainha + 1][idxsRainha[1]] == 0) {

                    contaDescLinha++;

                } else achouPecaLinha = true;
            }
            if(!achouPecaColuna) {
                if (colunaRainha < novoTabuleiro.length - 1 && novoTabuleiro[idxsRainha[0]][colunaRainha + 1] == 0) {

                    contaDescColuna++;

                } else achouPecaColuna = true;
            }
            if(!achouPecaLinhaDesc) {
                if (linhaRainhaDesc > 0 && novoTabuleiro[linhaRainhaDesc - 1][idxsRainha[1]] == 0) {

                    contaLinhaTras++;

                } else achouPecaLinhaDesc = true;
            }
            if(!achouPecaColunaDesc) {
                if (colunaRainhaDesc > 0 && novoTabuleiro[idxsRainha[0]][colunaRainhaDesc - 1] == 0) {

                    contaColunaTras++;

                } else achouPecaColunaDesc = true;
            }

            linhaRainha++;
            colunaRainha++;
            linhaRainhaDesc--;
            colunaRainhaDesc--;

        }
        somaVerticalHorizontal= contaDescColuna+contaColunaTras+contaDescLinha+contaLinhaTras;

        return somaVerticalHorizontal;
    }
    public static int[][] montaTabuleiroRandom() {
        Random randomN = new Random();
        int nTabuleiro = randomN.ints(5,7).findFirst().getAsInt();
        int[][] tabuleiro = new int[nTabuleiro][nTabuleiro];
        //map pra guardar peças distintas hmmmmm
        //Map<String,ArrayList<ArrayList<Integer>>> posicoes = new HashMap<>();
        //rainha random
        int[] idxRainha ={randomN.ints(0,tabuleiro.length).findFirst().getAsInt(),
                          randomN.ints(0,tabuleiro.length).findFirst().getAsInt()
        };
        tabuleiro[idxRainha[0]][idxRainha[1]]=9;
        preencheTabuleiro(tabuleiro); //idx 0 ou 1 random


    return tabuleiro;
    }
    public static int[] buscaPosicaoRainha(int[][]matriz ){
        int [] resultado = new int[2];
        for (int i = 0; i <matriz.length; i++) {
            for (int j = 0; j <matriz.length; j++) {
                if(matriz[i][j]==9) {
                    resultado[0]=i;
                    resultado[1]=j;
                }
            }

        }


    return resultado;
    }
    public static void preencheTabuleiro(int[][] tabuleiro){
        Random randomN = new Random();
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int i1 = tabuleiro.length - 1; i1 >= 0; i1--) {
                if(tabuleiro[i][i1]!=9){
                    tabuleiro[i][i1] = randomN.ints(0,2).findFirst().getAsInt();
                }
            }
        }

    }

    public static void printaMatriz(int[][] matriz){
        for (int i = 0; i <matriz.length; i++) {
            for (int j = 0; j <matriz.length; j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void formataTelefone(String s1) {
        System.out.println(s1);
        s1 = s1.replace("-","").replace(" ","");
        if(s1.length()<4){
            System.out.println(s1);
        }
        else{
            int avancaIdx = 0;
            while(avancaIdx+1 <=s1.length()-3){
                if(s1.length()-avancaIdx==4) { //Se o grupo terminar em 4 caracteres, divide ao meio.
                    s1 = s1.substring(0,avancaIdx+2)+"-"+s1.substring(avancaIdx+2);
                    avancaIdx= s1.lastIndexOf("-");

                }
                else {

                    s1 = s1.substring(0,avancaIdx+3)+"-"+s1.substring(avancaIdx+3);
                    avancaIdx=s1.lastIndexOf("-")+1;


                }
            }
            System.out.println("modificada: "+s1);

        }
    }
    public static void hourglass1() {
        //List<List<Integer>> listaMatriz= new ArrayList<>();
        //int[][] matrix = listaMatriz.stream().map(  u  ->  u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
        /*int[][] matrix = new int[][]{{-9,-9,-9, 1,1,1},
                                     { 0,-9, 0, 4,3,2},
                                     {-9,-9,-9, 1,2,3},
                                     { 0, 0, 8, 6,6,0},
                                     { 0, 0, 0,-2,0,0},
                                     { 0, 0, 1, 2,4,0}};

         */
        int [] vetorResultado = new int[16];
        int idxResultado = 0;
        int[][] matrix = new int[][]{{-1,-1,0, -9,-2,-2},
                                     { -2,-1, -6, -8,-2,-5},
                                     {-1,-1,-1, -2,-3,-4},
                                     { -1, -9, -2, -4,-4,-5},
                                     { -7,-3, -3,-2,-9,-9},
                                     { -1, -3, -1,-2,-4,-5}};

        System.out.println(Arrays.deepToString(matrix));
        int resultado = 0;
        int desceJ = 0;
        int limiteJ = 3;

            while (desceJ <= 3) { //desce 1 linha
                int sempreTres = 3;
                int idx = 0;

                while (idx<=3) { //anda 1 coluna pra direita
                    int soma = 0;
                    int segundaLinha = 1;

                    for (int j = desceJ; j < limiteJ; j++) { //percorre os 7 valores e soma
                        System.out.println();
                        if(segundaLinha==2){ //segunda linha (valor do meio)
                            System.out.print("x "+matrix[j][idx+1]+" x");
                            soma +=matrix[j][idx+1];
                        }
                        else {
                            //primeira e terceira linha (3 valores)
                            for (int i = idx; i < sempreTres; i++) {
                                System.out.print(matrix[j][i]);
                                soma += matrix[j][i];
                            }
                        }
                        segundaLinha++;
                    }
                    System.out.println();
                    System.out.println("^ soma do conjunto: "+soma);

                    idx++;
                    sempreTres++;
                    vetorResultado[idxResultado++]=soma; //todos conjuntos com suas somas

                }
                limiteJ++;
                desceJ++;
            }

            System.out.println("Todas somas :"+Arrays.toString(vetorResultado));
            System.out.println("Maior "+Arrays.stream(vetorResultado).max().getAsInt());


    }
    public static void no9(List<Integer> nums) {
        //nums = nums.stream().filter(e -> e%10==2).map(e->e*2).collect(Collectors.toList());
        nums = nums.stream().map(e -> (e*e)+10).filter(e-> (e%10!=5) && (e%10!=6)).collect(Collectors.toList());
        System.out.println(nums);
    }
    public static void  noX(List<String> strings) {
        strings = strings.stream().map(e -> e.replace("x","")).collect(Collectors.toList());
        //strings = strings.stream().map(e -> e.concat("y")).filter(e -> e.contains("yy")).collect(Collectors.toList());
        System.out.println(strings);
    }
    public static void  moreY(List<String> strings) {
        strings = strings.stream().map(e -> "y"+e+"y").collect(Collectors.toList());
        System.out.println(strings);
    }
    public static void  rightDigit(List<Integer> nums) {
        nums = nums.stream().map(e ->  Integer.parseInt(e.toString().substring(e.toString().length()-1,e.toString().length())))
                            .collect(Collectors.toList());
        System.out.println(nums);
    }
    public static void lower(List<String> strings) {
      strings = strings.stream().map(e -> e.toLowerCase() ).collect(Collectors.toList());
        System.out.println(strings);

    }
    public static void addStar(List<String> strings) {
    strings = strings.stream().map(e -> e.concat("*")).collect(Collectors.toList());

        System.out.println(strings);
    }
    public static void square(List<Integer> nums) {
        nums = nums.stream().map(e ->(int) Math.pow(e,2)).collect(Collectors.toList());
        System.out.println(nums);
    }
    public static void doubling(List<Integer> nums) {
        List<Integer> resultado;
        resultado = nums.stream().map(e-> e*2).collect(Collectors.toList());
        System.out.println(resultado);
    }

    public static void copies3(List<String> strings) {
        List<String> oi;                            ////e.repeat(3) e+e+e
        strings = strings.stream().map(e -> String.join("",Collections.nCopies(3,e))).collect(Collectors.toList());
        System.out.println(strings);
    }
    //maps
    public static void wordMultiple(String[] strings) {
        Map<String,Boolean> resultado = new HashMap<>();
        for (String s : strings) {
            if(!resultado.containsKey(s)){
                resultado.put(s,false);
            }
            else {
                resultado.replace(s,true);
            }

        }
        System.out.println(resultado);

    }
    public static void allSwap(String[] strings) {
        ArrayList<Integer> guardaIdxUsados = new ArrayList<Integer>();
        Map<Character,Integer> mapPrimeiroIdx = new HashMap<>();
        System.out.println("original "+Arrays.toString(strings));
        for (int i=0; i< strings.length; i++) {
            String elemento = strings[i];
            Character chavePrimeiroChar = elemento.charAt(0);

            //fill primeiras aparições
            if(mapPrimeiroIdx.get(chavePrimeiroChar)==null){  //getordefault containsKey hmmm
                mapPrimeiroIdx.put(chavePrimeiroChar,i);
            }
            else {
                //idx da String[] já foi usado? S: não troca posição [] e guarda idx atualizado no Map
                                              //N: troca posição String[] e guarda idx na lista de usados
                if(guardaIdxUsados.contains(mapPrimeiroIdx.get(chavePrimeiroChar))){
                    mapPrimeiroIdx.replace(chavePrimeiroChar,i);
                }
                else {
                  String tempStr = elemento;
                  strings[i] = strings[mapPrimeiroIdx.get(chavePrimeiroChar)];
                  strings[mapPrimeiroIdx.get(chavePrimeiroChar)]=tempStr;
                  mapPrimeiroIdx.replace(chavePrimeiroChar,i);

                  guardaIdxUsados.add(i);
                }
            }
        }
        System.out.println("resultad "+Arrays.toString(strings));
    }
    public static void wordCount(String[] strings) {
        Map<String,Integer> resultado = new HashMap<>();

        for (String elementoStr : strings) {
            resultado.put(elementoStr,resultado.getOrDefault(elementoStr,0)+1);

        }
        System.out.println(resultado);
    }

    public static void firstSwap(String[] strings) {     //  lambda aqui
    Map<String,Map<Integer,Boolean>> mapIdxString = new HashMap<>();
    //Map<Integer,Boolean> mapInterno = new HashMap<>();
    System.out.println("original "+Arrays.toString(strings));

       for (int i = 0; i < strings.length; i++) {
           String elemento = strings[i];
           Map<Integer,Boolean> mapInterno = new HashMap<Integer, Boolean>();
            if(!mapIdxString.containsKey(String.valueOf(elemento.charAt(0))) ){
                mapInterno.put(i,true);
                mapIdxString.put(String.valueOf(elemento.charAt(0)), mapInterno);
            }
            else{
                if (mapIdxString.get(String.valueOf(elemento.charAt(0))).values().toArray()[0].equals(true)) {
                    //temporaria do valor idx string atual
                    String segundaAparencia =strings[i];
                    //primeiro valor guardado no Map da key
                    int primeiroIdxGuardado= (int) mapIdxString.get(String.valueOf(elemento.charAt(0))).keySet().toArray()[0];
                    //troca strings
                    strings[i]=strings[primeiroIdxGuardado];
                    strings[primeiroIdxGuardado]=segundaAparencia;
                    // idx que trocou 1x não troca mais
                    int idxReplace = (int) mapIdxString.get(String.valueOf(elemento.charAt(0))).keySet().toArray()[0];
                    mapIdxString.get(String.valueOf(elemento.charAt(0))).replace(idxReplace,false);
                }
            }
        }

        System.out.println("modifica "+Arrays.toString(strings));
    }
    public static void wordLen(String[] strings) {
        Map<String,Integer> resultado = new HashMap<>();
        for (String s : strings) {
            resultado.put(s,s.length());
        }
        System.out.println(resultado);
    }
    public static void wordAppend(String[] vetorString) {
        StringBuilder resultado = new StringBuilder("");
        Map<String,Integer> mapConta = new HashMap<>();

        for (String s : vetorString) {
            if(!mapConta.containsKey(s)){
                mapConta.put(s,1);

            }
            else{
                mapConta.put(s,mapConta.get(s)+1);
                if(mapConta.get(s)==2){
                    resultado.append(s);
                    mapConta.replace(s,0);
                }
            }
        }
        System.out.println(resultado.toString());
    }
    public static void wordAppendEntendiErrado(String[] vetorString) {
        String finalv2 = "";
        Map<String, Integer> resultado = new HashMap<>();
        for (String s : vetorString) {
            if (resultado.get(s) == null) {
                resultado.put(s, 1);
            } else {
                resultado.replace(s, resultado.get(s) + 1);
            }
        }
        resultado.entrySet().removeIf(e -> e.getValue()==1);

        System.out.println("antes build "+ resultado);
        for (Map.Entry<String, Integer> stringIntegerEntry : resultado.entrySet()) {
            int guardaReps = stringIntegerEntry.getValue();
            String guardaStr = stringIntegerEntry.getKey();
            StringBuilder resultadoStr = new StringBuilder();
            if(guardaReps%2==0){
                guardaReps=guardaReps/2;
                for (int i = 0; i < guardaReps; i++) {
                 resultadoStr.append(guardaStr);    
                }


            }
            else {
                guardaReps = (guardaReps-1)/2;
                for (int i = 0; i < guardaReps; i++) {
                    resultadoStr.append(guardaStr);
                }

            }
            finalv2 = resultadoStr.toString();

            }

        System.out.println(finalv2);

    }
    public static void word0(String[] vetorString){
        Map<String,Integer> mapVazio = new HashMap<>();
        for (String s : vetorString) {
            mapVazio.put(s,0);
        }
        System.out.println(mapVazio);
    }
    public static void firstChar(String[] vetorString){
        Map<String,String> mapVazio = new HashMap<>();
        for (String s : vetorString) {
            if(mapVazio.containsKey(String.valueOf(s.charAt(0))))
                mapVazio.replace(String.valueOf(s.charAt(0)),mapVazio.get(String.valueOf(s.charAt(0)))+s);
            else mapVazio.put(String.valueOf(s.charAt(0)),s);
        }
        System.out.println(mapVazio);
    }
    public static void pairs(String[] vetorString){
        Map<String,String> mapVazio = new HashMap<>();
        for (String s : vetorString) {
            mapVazio.put(String.valueOf(s.charAt(0)),String.valueOf(s.charAt(s.length()-1)));
        }
        System.out.println(mapVazio);
    }
    public static void primeiroMap(Map<String,String> map1){
        if(map1.containsKey("a") && !map1.get("a").isEmpty()){
            map1.put("b",map1.get("a"));
        }
        if(map1.containsKey("c")){
            map1.remove("c");
        }
        System.out.println(map1);
        /*if(mapVazio.containsKey("ice cream")){
            mapVazio.put("ice cream","cherry");
        }
        mapVazio.put("bread","butter");
        System.out.println(mapVazio);*/
    }
    public static void mirrorLen(int [] vetorInt){
        ArrayList<ArrayList<Integer> > listaVetor = new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> oi = new ArrayList<Integer>();
        ArrayList<Integer> oiInverso = new ArrayList<Integer>();
        int resultado;
        int contaW=0 , contaF=1;
        if (vetorInt.length==0){
            resultado=0;
            System.out.println("é 0");}
        else if (vetorInt.length==1) {
            resultado=1;
            System.out.println("é 1");}
        else{
            while (contaW < vetorInt.length) {
                for (int i = contaW; i <= contaF; i++) {
                    oi.add(vetorInt[i]);
                    oiInverso.add(vetorInt[i]);
                }
                listaVetor.add(oi);
                Collections.reverse(oiInverso);
                listaVetor.add(oiInverso);
                oi = new ArrayList<>();
                oiInverso = new ArrayList<>();
                contaF++;
                if (contaF == vetorInt.length) {

                    contaW++;
                    contaF = contaW + 1;
                }
                if (contaW == vetorInt.length - 1) {
                    // System.out.println("acabou");
                    break;
                }
            }

/*
        resultado = listaVetor
                   .stream()
                   .collect(Collectors.groupingBy(Function.identity(),
                    Collectors.counting()))
                   .entrySet().stream().filter(e -> e.getValue() > 1)
                   .map(Map.Entry::getKey).collect(Collectors.toSet())
                   .stream().max(Comparator.comparingInt(ArrayList::size)).get().size();
  */
            resultado = listaVetor.stream().filter(i -> Collections.frequency(listaVetor, i) > 1)
                    .collect(Collectors.toSet()).stream()
                    .max(Comparator.comparingInt(ArrayList::size)).get().size();
            System.out.println(listaVetor);
            System.out.println(resultado);
        }

    }

    public static void mirrorEmSequencia(int [] vetorInt){
        int idxI = 99;
        int idxF = 0;
        int resultado=0;
        int contaBateu =0;
        int primeiraconta=1;
        for (int i = 0; i < vetorInt.length-1; i++) {
            //System.out.println(vetorInt[i]);
            if(vetorInt[i] == vetorInt[i+1]-1 ||vetorInt[i] == vetorInt[i+1]+1) {
                if(idxI==99){
                    idxI=i;
                    idxF=i+1;
                }
                else {idxF=i+1;
                    if (vetorInt[idxI]==vetorInt[idxF]){
                        System.out.println("soma e go next");
                        resultado = idxF+1>resultado ? idxF+1: resultado;
                        System.out.println(resultado);
                    }
                }
                //printa elementos do group
                for (int iii = idxI; iii <= idxF; iii++) {
                    //System.out.println("grupo"+vetorInt[iii]);
                }
                primeiraconta++;
            } else {
                System.out.println("procura grupo invertido");
                for (int i1 = vetorInt.length - 1; i1 > idxF; i1--) {
                    for (int i2 = idxI; i2 <= idxF; i2++) {
                        if(vetorInt[i1]==vetorInt[i2]){
                            //System.out.println("eh igual");
                            contaBateu++;
                            idxI++;
                            break;
                        }
                    }

                }
                System.out.println(primeiraconta+" pri conta");
                System.out.println(contaBateu+"seg conta");
                if(primeiraconta==contaBateu){
                    if(contaBateu>resultado)resultado=contaBateu;
                }
                idxI = 99;
                idxF = 0;
                contaBateu=0;
            }

        }
        System.out.println("resu "+resultado);
    }
    public static void fix34(int [] vetor34){

            System.out.println(Arrays.toString(vetor34));
            int andaJ = 0;
            for (int i = 0; i < vetor34.length; i++) {
                if(vetor34[i]==3 && vetor34[i+1]!=4){
                    for (int j = andaJ; j <vetor34.length ; j++) { //não precisa começar do 0 hm
                        int guardaProx=vetor34[i+1];
                        if(vetor34[j]==4) {
                            vetor34[i+1]=vetor34[j];
                            andaJ = j+1;
                            vetor34[j] = guardaProx;
                            break;
                        }
                    }
                }
            }
            System.out.println(Arrays.toString(vetor34));
        }

    public static void aContemB(int[] outer, int[] inner){
        //System.out.println(Arrays.asList(outer).contains(Arrays.asList(inner)));

        Integer[] oi = Arrays.stream(outer).boxed().toArray(Integer[]::new);
        ArrayList<Integer> ou = new ArrayList<>(Arrays.asList(oi));

        Integer[] oi2 = Arrays.stream(inner).boxed().toArray(Integer[]::new);
        ArrayList<Integer> inn = new ArrayList<>(Arrays.asList(oi2));

        System.out.println(ou.containsAll(inn));
    }
    public static void squareUp(int n){
            int zeros = 0;
            ArrayList<Integer> oi = new ArrayList<Integer>();
            for (int i = 1; i <= n * n; i++) {
                if (zeros != 0) {
                    for (int j = 1; j <= n - zeros; j++) {
                        oi.add(j);
                    }
                    for (int j = 0; j < zeros; j++) {
                        oi.add(0);
                    }
                    zeros++;
                    i = i + n;
                } else {
                    oi.add(i);
                    if ((i) % n == 0) {
                        zeros++;
                    }
                }
            }
            System.out.println(oi);
            Collections.reverse(oi);
            int[] resultado = oi.stream().mapToInt(i -> i).toArray();
            System.out.println(Arrays.toString(resultado));
    }

    public static void seriesUp (int n){
            ArrayList<Integer> oi = new ArrayList<Integer>();
            while (n>0) {
                for (int i = n; i > 0; i--) {
                    oi.add(i);
                }
                n--;
            }
            System.out.println(oi);
            Collections.reverse(oi);

            int[] resultado = oi.stream().mapToInt(i->i).toArray();
            System.out.println(Arrays.toString(resultado));
        }
    public static void distanciaPrimeiroUltimo (int[] nums){
            //Integer[] vetorInteger = IntStream.of(vetorInt).boxed().toArray(Integer[]::new);
            Integer[] vetorInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
            ArrayList<Integer> listaVetor = new ArrayList<Integer>(Arrays.asList(vetorInteger));
            ArrayList<Integer> resultado = new ArrayList<Integer>();
            int sub;

            for (int i = 0; i < listaVetor.size(); i++) {
                sub= listaVetor.lastIndexOf(listaVetor.get(i))-listaVetor.indexOf(listaVetor.get(i))+1;
                resultado.add(sub);
            }
            if (resultado.isEmpty()) {
                System.out.println(0);
            }else System.out.println(Collections.max(resultado));
        }
    public static void quatroDepoisCinco (int[] vetor45){
            System.out.println("inic: "+Arrays.toString(vetor45));
            int andaJ=0;
            int guardaImaisUm;
            for (int i = 0; i < vetor45.length; i++) {
                if(vetor45[i]==4 && vetor45[i+1]!=5){
                    guardaImaisUm=vetor45[i+1];
                    for(int j = andaJ; j<vetor45.length;j++){
                        if(vetor45[j]==5){
                            if(j==0){
                                vetor45[i+1]=vetor45[j];
                                vetor45[j]=guardaImaisUm;
                                andaJ=j+1;
                                break;
                            }
                            else if(vetor45[j-1]!=4){
                                vetor45[i+1]=vetor45[j];
                                vetor45[j]=guardaImaisUm;
                                andaJ=j+1;
                                break;
                            }
                        }
                    }
                }
            }


            System.out.println("Nova: "+Arrays.toString(vetor45));

        }
    public static void temSomaLadosIguais (int[] nums){
            boolean resultado = false;

            for (int i = 0; i < nums.length - 1; i++) {
                //monta lado esquerdo do idx e soma
                int[] tempEsq = Arrays.copyOfRange(nums, 0, i + 1);
                int somaLadoE = Arrays.stream(tempEsq).sum();

                //monta lado direito do idx e soma
                int[] tempDir = Arrays.copyOfRange(nums, i + 1, nums.length);
                int somaLadoD = Arrays.stream(tempDir).sum();


                if (somaLadoD == somaLadoE) {
                    resultado = true;
                    break;
                } else if (somaLadoE > somaLadoD) break;


            }
            System.out.println(resultado);
        }
    public static void contaClumps (int[] nums) {
        int contaClumps = 0;
        boolean jaContou = false;

        for (int i = 0; i < nums.length-1; i++){
            if (nums[i]==nums[i+1]) {
                if (!jaContou == false){
                    jaContou = true;
                    contaClumps++;
                }
            } else jaContou=false;
        }
        System.out.println(contaClumps);
    }
    public static void middleWay(int[] a, int[] b) {
        int [] resultado = {a[1],b[1]};
        System.out.println(Arrays.toString(resultado));

    }
    public static void somaDoisPrimeiros(int[] vetor1) {
        int resultado = Arrays.stream(vetor1).limit(2).sum();
        System.out.println(resultado);
    }
    public static void contemUmPrimeiraPos(int[] vetor1, int[] vetor2) {
        int resultado;
        resultado = (vetor1.length >0 && vetor1[0]==1 ? 1:0) +
                    (vetor2.length >0 && vetor2[0]==1 ? 1:0);
        System.out.println(resultado);
    }
    public static void makeEnds(int[] nums) {
        int [] resultado = {nums[0],nums[nums.length-1]};
        System.out.println(Arrays.toString(resultado));
    }
    public static void has23(int[] nums) {
        boolean resultado = Arrays.stream(nums).anyMatch(e -> e==3||e==2);
        System.out.println(resultado);
    }

    public static void somaListaLigadaV2( LinkedList<Integer> ll1,LinkedList<Integer> ll2) {
        LinkedList<Integer> resultado = new LinkedList<Integer>();
        int soma = 0;
        int resto = 0;

        int idxLen2 = 0;
        System.out.println("l1: "+ll1);
        System.out.println("l2: "+ll2);

        for (int i = 0; i < ll1.size(); i++) {

            if(idxLen2<ll2.size()){
                soma =ll2.get(idxLen2)+ll1.get(i)+resto;
                idxLen2++;

            }
            else {
                soma = ll1.get(i)+resto;
            }
            if(soma>=10) {
                resultado.add(soma % 10);
                resto = 1;
            } else{
                resultado.add(soma);
                resto = 0;
            }
            if(resto==1 && i==ll1.size()-1) resultado.add(1);
        }
        System.out.println(resultado);
    }
    public static void somaListaLigada( LinkedList ll1,LinkedList ll2){
        LinkedList<Integer> igualaLen1 = new LinkedList<Integer>();
        LinkedList<Integer> igualaLen2 = new LinkedList<Integer>();
        LinkedList listaFinal = new LinkedList();
        int maiorLen = ll1.size()>ll2.size() ? ll1.size():ll2.size();
        LinkedList<Integer> menorLista = ll1.size()<ll2.size() ? ll1:ll2;
        LinkedList<Integer> maiorLista = ll1.size()>ll2.size() ? ll1:ll2;


        for (int i = 0; i<maiorLen;i++){
            if(menorLista.size()>i){
                igualaLen1.add(menorLista.get(i));
            } else igualaLen1.add(0);
            igualaLen2.add(maiorLista.get(i));

        }
        //listas para soma
        System.out.println("lista 1: "+igualaLen1);
        System.out.println("lista 2: "+igualaLen2);
        int soma = 0;
        int resto = 0;
        for (int i = 0; i <igualaLen1.size(); i++) {

            soma = igualaLen1.get(i)+igualaLen2.get(i)+resto;

            if (soma<10){listaFinal.add(soma);
                resto=0;}
            else {
                listaFinal.add(soma%10);

                resto=1;
            }
            if (i ==igualaLen1.size()-1 && resto == 1){
                listaFinal.add(resto);
            }

        }
        System.out.println("lista f: "+listaFinal);

    }
    /*
    public static ListaLigadaSimples montaLista2 () {
        ListaLigadaSimples ll2 = new ListaLigadaSimples(); //lista vazia
        ListaLigadaSimples.Node ll2_0 = new ListaLigadaSimples.Node(8); //primeiro no
        ListaLigadaSimples.Node ll2_1 = new ListaLigadaSimples.Node(4); //primeiro no

        ll2.head = ll2_0;
        ll2.head.next = ll2_1;
        return ll2;
    }
    public static ListaLigadaSimples montaLista1 (){
        ListaLigadaSimples oi = new ListaLigadaSimples(); //lista vazia
        ListaLigadaSimples.Node node0 = new ListaLigadaSimples.Node(5); //primeiro no
        ListaLigadaSimples.Node node1 = new ListaLigadaSimples.Node(6); //primeiro no
        ListaLigadaSimples.Node node2 = new ListaLigadaSimples.Node(3); //primeiro no

        oi.head=node0;
        oi.head.next=node1;
        oi.head.next.next=node2;

        return oi;

        ListaLigadaSimples.Node anda_lista1 = oi.head;
        while(anda_lista1!=null){
            System.out.println("data: "+anda_lista1.data);
            anda_lista1 = anda_lista1.next;
        }
        System.out.println("lista2");
        ListaLigadaSimples.Node anda_lista2 = ll2.head;
        while(anda_lista2!=null){
            System.out.println("data: "+anda_lista2.data);
            anda_lista2 = anda_lista2.next;
        }

    }
  */
    public static void kElementsV3( int [] arr, int k) {
         ArrayList<Integer> guardaIdxDosMax = new ArrayList<>();
         int guardaValorMax=0;
         int guardaIdx = 0;
         int contaWhile = 0;

         while (guardaIdxDosMax.size()<k) {
             for (int i = 0; i < arr.length; i++) {
                 if (!guardaIdxDosMax.contains(i)) {
                     if (arr[i] > guardaValorMax) {
                         guardaValorMax = arr[i];
                         guardaIdx = i;
                     }
                 }
             }
             guardaIdxDosMax.add(guardaIdx);
             guardaValorMax = 0;
        guardaIdx = 0;
        }
        for (Integer indices : guardaIdxDosMax) {
            System.out.println(arr[indices]);
        }
    }
    public static void kElementsV2( int [] arr, int k) {
       Integer[] oi = Arrays.stream(arr).boxed().toArray(Integer[]::new);
       ArrayList<Integer> resultado = new ArrayList<>(Arrays.asList(oi));
       System.out.println(resultado);
       for (int i = 0; i < k; i++) {
       System.out.println(Collections.max(resultado));
       resultado.remove(Collections.max(resultado));
       }


    }
    public static void kElements(int[] vetorAm, int k) {
    int [] temp;
    int [] ordenado =  Arrays.stream(vetorAm).sorted().toArray();
        System.out.println("sorted"+ Arrays.toString(ordenado));
        temp = IntStream.range(ordenado.length-k, ordenado.length).map(i -> ordenado[i]).toArray();
        Arrays.stream(temp).boxed().sorted(Collections.reverseOrder()).forEach(System.out::print);
    } //sorting :/
    public static void frontPiece(int[] nums) {
     List<Integer> guardaDois = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if(i==2)break;
            guardaDois.add(nums[i]);

        }
        int[] resultadoInt = guardaDois.stream().mapToInt(i->i).toArray();
        System.out.println("array int");
        Arrays.stream(resultadoInt).forEach(System.out::print);
        System.out.println();

        String [] resultadoStr = guardaDois.stream().map(e -> String.valueOf(e)).toArray(String[]::new);
        System.out.println("array string");
        Arrays.stream(resultadoStr).forEach(System.out::print);


    }
    public static void biggerTwo(int[] nums,int[] nums2) {

        if(Arrays.stream(nums2).sum()>Arrays.stream(nums).sum()){
           Arrays.stream(nums2).forEach(System.out::print);
        }
        else Arrays.stream(nums).forEach(System.out::print);
    }
    public static void reverse3(int[] nums) {
        //int [] invertido = IntStream.range(0, nums.length).map(i -> nums[nums.length-1-i]).toArray();
        //Arrays.stream(invertido).forEach(System.out::print);
        int [] invertido = new int[nums.length];
        int iConta = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            invertido[iConta]=nums[i];
            iConta++;
        }
        Arrays.stream(invertido).forEach(System.out::println);
    }
    public static void sum3(int[] nums) {
        System.out.println(Arrays.stream(nums).sum());
    }
    public static void makeLast(int[] nums) {
        int [] dobroLen = new int[nums.length*2];
        dobroLen[dobroLen.length-1] = nums[nums.length-1];

        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
        Arrays.stream(dobroLen).forEach(System.out::print);
    }
    public static void maxTriple(int[] nums) {
        int [] temp = {nums[0],nums[nums.length/2],nums[nums.length-1]};

        System.out.println(Arrays.stream(temp).max().getAsInt());
    }
}
