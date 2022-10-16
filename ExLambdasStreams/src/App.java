import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;

public class App {

    public static void main(String[] args) {
         List<Pessoa> lista = Arrays.asList(
                new Pessoa(1, "Huguinho", Departamento.FINANCEIRO, 40),
                new Pessoa(4, "Zezinho", Departamento.FINANCEIRO, 32),
                new Pessoa(3, "Luizinho", Departamento.VENDAS, 57),
                new  Pessoa(9, "Patinhas",   Departamento.VENDAS, 40),
                new Pessoa(10, "Donald", Departamento.GERENCIA, 54),
                new Pessoa(2, "Margarida", Departamento.FINANCEIRO, 40),
                new Pessoa(8, "Joe Doe", Departamento.VENDAS, 34),
                new Pessoa(5, "Jane Doe", Departamento.VENDAS, 22),
                new Pessoa(6, "Sr Smith", Departamento.VENDAS, 40),
                new Pessoa(7, "Sra Smith", Departamento.GERENCIA, 39),
                new Pessoa(11, "Trinity", Departamento.VENDAS, 34),
                new Pessoa(14, "Morpheus", Departamento.FINANCEIRO, 22),
                new Pessoa(16, "AgenteSmith", Departamento.VENDAS, 47),
                new Pessoa(13, "Neo", Departamento.GERENCIA, 29)
        );

        /***
         * Exercício
         *      - escreva as consultas solicitadas utilizando
         *        apenas expressões lambda e operações de agregação
          */
        System.out.println("1. Funcionários do setor de vendas:");
        List<Pessoa> vendas = lista.stream()
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.VENDAS)
                                    .collect(Collectors.toList());
        vendas.forEach(p->System.out.printf("\t -> %s\n", p.getNome()));

        System.out.println("2. Funcionários do setor de vendas com idade entre 20 e 30 anos");
        List<Pessoa> vendas2030 = lista.stream()
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.VENDAS)
                                    .filter((Pessoa p) -> p.getIdade() >= 20 && p.getIdade() <= 30)
                                    .collect(Collectors.toList());
        vendas2030.forEach(p->System.out.printf("\t %s -> %d\n", p.getNome(), p.getIdade()));

        System.out.println("3. Nomes (em maiúsculas) dos funcionários do setor de vendas (usando reduce)");
        List<String>nomeLista = lista.stream()     
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.VENDAS)
                                    .map(p -> p.getNome().toUpperCase())
                                    .collect(Collectors.toList());
        nomeLista.forEach(p -> System.out.printf("\t -> %s\n", p));

        System.out.println("4. Todos os gerentes:");
        List<Pessoa> gerentes = lista.stream()
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.GERENCIA)
                                    .collect(Collectors.toList());
        gerentes.forEach(p->System.out.printf("\t  -> %s\n", p.getNome()));

        System.out.println("5. Idade média dos gerentes:");
        double idadeMediaGerente = lista.stream()
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.GERENCIA)
                                    .mapToInt(Pessoa::getIdade)
                                    .average()
                                    .getAsDouble();
        System.out.println(Math.round(idadeMediaGerente));

        System.out.println("6. Funcionarios ordenados pelo código:");
        List<Pessoa> funcLista = lista.stream()
                                    .sorted(Comparator.comparingInt(Pessoa::getCodigo))
                                    .collect(Collectors.toList());

        funcLista.forEach(p -> System.out.printf("\t %s -> %d\n", p.getNome(),p.getCodigo()));

        System.out.println("7. Funcionários ordenados pela idade+nome:");
        lista = lista.stream()
                    .sorted(Comparator.comparing(Pessoa::getIdade).thenComparing(Pessoa::getNome))
                    .collect(Collectors.toList());

        lista.forEach(System.out::println);

        System.out.println("8. Criar uma nova lista apenas com os funcionarios do financeiro:");
        List<Pessoa> funcFinanc = lista.stream()
                                    .filter((Pessoa p) -> p.getDpto() == Departamento.FINANCEIRO)
                                    .collect(Collectors.toList());

        funcFinanc.forEach(p -> System.out.printf("\t -> %s  \n", p.getNome()));

        System.out.println("9. Nome e setor da pessoa mais jovem:");
        Pessoa maisJovem = lista.stream()   
                                    .min(Comparator.comparing(Pessoa::getIdade))
                                    .orElseThrow(NoSuchElementException::new);

        System.out.printf("\t %s -> %s", maisJovem.getNome(), maisJovem.getDpto());
    }
}
