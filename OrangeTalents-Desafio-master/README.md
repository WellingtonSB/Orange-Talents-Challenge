# DesafioOrangeTalents

# Consumindo API REST Fipe Com SpringFramework 

   # Sobre mim:

Meu nome é Guilherme Goulart Cruvinel, tenho 23 anos e atualmente trabalho como Analista de Sistemas Jr. Estou cursando Sistemas de Informação na UFU, estou no 3º período, amante de jogos, leitura, tecnologia, séries e afins. Tenho me aventurado com Kotlin / Android Studio e também com Java de maneira geral. Mas chega de falar sobre mim e vamos para o projeto!

   # Orange Talents: Desafio

Link do GitHub com o Projeto: [https://github.com/SpectroWolf/OrangeTalents-Desafio](https://github.com/SpectroWolf/OrangeTalents-Desafio)

Como parte do processo para o Orange Talents da ZUP, tive o desafio de construir uma API REST que irá gerenciar um cadastro de Veículos / Usuários e gerenciamento deles, usando a tabela Fipe para pegar os dados dos veículos, até aqui sem problemas (ou quase), a API informada pelo desafio foi a: [https://deividfortuna.github.io/fipe/](https://deividfortuna.github.io/fipe/) porém após vários testes (vários mesmo, coisa de 4 a 5 horas) tentando fazer ela funcionar, não conseguia nenhuma resposta sem ser null, sempre retornava conforme abaixo:

![1](https://user-images.githubusercontent.com/52512867/120226817-4fe2c080-c21e-11eb-97e8-fb932ec56ad3.png)

Configuração do @FeignClient

![2](https://user-images.githubusercontent.com/52512867/120226901-86204000-c21e-11eb-9c44-cf9215b71785.png)

Devido a isso utilizei outra API que também consulta a tabela FIPE da mesma forma que foi a: [http://fipeapi.appspot.com](http://fipeapi.appspot.com/) (Foi mal examinadores! Era isso ou não entregar o projeto). Print com o código idêntico somente alterando a API:

![3](https://user-images.githubusercontent.com/52512867/120226965-aa7c1c80-c21e-11eb-85d7-90bb0c5544f4.png)

Configurações do @FeingClient com a outra API, podemos notar que só foi alterado o link da mesma.

![4](https://user-images.githubusercontent.com/52512867/120226993-b962cf00-c21e-11eb-9819-c0b4f738ec0a.png)

Mas então, beleza o desafio é criar um gerenciamento de Usuários e Veículos para disponibilização do rodízio de carros, ok, quais eram os critérios exigidos?

- Criar dois cadastros, um deles para o usuário com nome, e-mail, CPF e data de nascimento, importante ressaltar que o e-mail e CPF devem ser únicos, e outro para o carro com marca, modelo do veículo, ano e valor.
- Criar um *endpoint (aquela parte do link por exemplo: "meusitelegal.com/endpoint" onde esse endpoint é um ponto de acesso da sua aplicação)* que retornará o usuário com a lista de todos seus veículos cadastrados.
- Criar três *endpoints:* Cadastro do usuário, cadastrado do veículo e a listagem dos veículos para um usuário específico. Devemos considerar os seguintes fatores:
    - Caso os cadastros estejam corretos, é necessário voltar o Status 201. Caso hajam erros de preenchimento de dados, o Status deve ser 400.
    - Caso a busca esteja correta, é necessário voltar o Status 200. Caso haja erro na busca, retornar o status adequado e uma mensagem de erro amigável.

    ---

    # Tecnologias

    Para esse projeto iremos utilizar algumas as seguintes tecnologias:

    - Linguagem de programação Java (versão 8 ou superior)
    - SpringFramework (SpringBoot e SpringCloudFeign) → (Boot para facilitar construção da API REST e o Cloud para facilitar a comunicação da API) *Ambos meninos facilitadores*
    - Hibernate → (Para realizar o gerenciamento do Banco de Dados)
    - API REST → (Para comunicação seguindo os padrões REST "Requisição e Resposta" *De forma geral, para consultar nossos dados*)
    - IDE Intellij → (Para o código em si)
    - Insomnia REST →(Testarmos nossas requisições)

    ---

    # Das Classes e Interfaces

    Antes de tudo, antes de Classes/Interfaces e afins, nós precisamos configurar (mesmo que seja uma configuração bem básica) nosso Banco de Dados, aqui utilizado o Banco H2 por sua simplicidade de implementação, segue configurações para o projeto.

    ![5](https://user-images.githubusercontent.com/52512867/120227325-53c31280-c21f-11eb-96a4-965f3398abdb.png)

    Seguindo no tema "Antes de Tudo" temos o coração do projeto, sem ele nada funciona, isso mesmo estamos falando da nossa Application, aqui no projeto definida como DesafioOrangeApplication.

  ![6](https://user-images.githubusercontent.com/52512867/120227340-59205d00-c21f-11eb-813f-6acaaa29921e.png)

    Ela também é uma classe, porém diferente das outras da aplicação, só temos uma dela no projeto, onde iremos inicializar nosso Spring, com a anotação @SpringBootAplication e também iremos autorizar que o Spring Cloud Feign possa ser utilizado no projeto, com a anotação @EnableFeignClients. Beleza e o que mais ela faz? "Apenas" isso, inicia nosso projeto e permite que tudo funcione corretamente.

    Dando sequência vamos para as classes Model, mas Guilherme o que são classes Model? Justamente o que o nome nos diz, elas são "Modelos", ou seja são classes onde decidiremos nossas Entidades (@Entity, que termo diferente é esse, Entity? Oh meu leitor entusiasmado, justamente é a anotação que colocamos numa classe para deixar nosso código ciente que ela irá virar uma Tabela no nosso Banco de Dados).

    Por exemplo a classe Usuario:

    ![7](https://user-images.githubusercontent.com/52512867/120227355-5de51100-c21f-11eb-9874-39b930aa40cb.png)

    Complementando, dentro da @Entity nós definimos os nomes das colunas com o @Column e também colocamos os Setters and Getters, que nada mais são que nossos métodos de invocar (e não é a invocação do mal que vocês pensaram) nossos atributos da classe, para utilização no código por exemplo: 

    ![8](https://user-images.githubusercontent.com/52512867/120227368-62112e80-c21f-11eb-82a3-ecbc734c3534.png)

    Também temos na primeira imagem uma notação de @OneToMany, nada mais é do que dizer que um Usuario pode ter mais de 1 Veiculo.

    Seguindo essa dinâmica, enquanto o assunto é Banco de Dados, nada mais justo que falar da Interface Repository.

   ![9](https://user-images.githubusercontent.com/52512867/120227375-663d4c00-c21f-11eb-8e69-c4021ce98b68.png)

    Onde nós faremos a comunicação entre a Entidade que foi criada e nosso banco de dados, aqui representado no trecho "extends JpaRepository<Usuario, Long>", cada @Entity deve ter seu próprio @Repository.

    Mas vale lembrar, que somente o @Repository não consegue fazer nada sozinho, por isso pedimos ajuda de outra classe para fazer nossas manipulações, isso mesmo você acertou meu caro leitor entusiasmado, estamos falando da classe Service.

    ![10](https://user-images.githubusercontent.com/52512867/120227380-69d0d300-c21f-11eb-982f-897a520ed77d.png)

    Como todas as Classes / Interfaces anteriores nós precisamos da anotação @Service para defini-la segundo as métricas do Spring, mas beleza o que ela efetivamente faz? Dentro da Service nós configuramos as funções que serão chamadas quando o usuário final realizar alguma ação. 

    Está muito vago? Vamos pegar como exemplo a função "vincular" ali do print, como o nome já diz, ela vincula algo, esse algo é um veiculo a um usuário. Portanto todas essas "ações" que serão realizadas que utilizarão de uma @Entity, são feitas na nossa service, no exemplo mostramos a UsuarioService, Serviços do Usuário.

    Mas Guilherme, e aquele @Autowired ali?

    Parabéns meu bom leitor entusiasmado, muito perspicaz da sua parte, percebe que embaixo do @Autowired nós chamamos nossos Repositórios ou @Repository, esse rapazinho está ai para que não precisemos declarar nem definir nada sobre os Repositórios novamente, então sim, é basicamente um atalho para acessar tudo que o Repositório tem de uma maneira direta, ou o nome chique "Injeção de Dependência".

    Beleza Guilherme, entendi até aqui, mas ainda assim, onde que vai ser chamado ou utilizado os serviços da @Service? 

    Boa pergunta, chegamos nos nossos Controllers ou Controladores, aqui é onde o filho chora e a mãe não vê, onde a magia acontece, dentro dos Controllers nós temos todas as chamadas que serão realizadas pelo nosso sistema, lembra aquele tal de Breakpoint? É aqui que ele fica.

    ![11](https://user-images.githubusercontent.com/52512867/120227385-6d645a00-c21f-11eb-8231-532fa0d12937.png)

    Como sempre precisamos colocar a notação @RestCrontroller igual todas nossas classes e interfaces para trás, e vamos chamar nossos métodos, de Requisição e Resposta (Isso é o brabo do REST), aqui nós teremos nossos métodos POST, GET, PUT, DEL mas neste projeto usamos apenas POST, GET e PUT (Postar ou Criar / Pegar / Colocar ou Atualizar) respectivamente.

    Sim, respondendo sua pergunta que você ainda não fez meu bom leitor entusiasmado, vamos falar um pouco sobre o Spring Cloud Feign que foi somente citado até agora.

    Basicamente ele é uma versão SUUUUUUUUUUPER simplificado de consumir uma API, ou seja ele vai lá no link da API destino e trás as informações de volta com base no que passarmos para ele.

    Ele é composto de um @Service como já vimos a cima e sabemos o que é, aqui funciona da mesma forma.

    ![12](https://user-images.githubusercontent.com/52512867/120227404-72c1a480-c21f-11eb-8237-8da976b9c1ec.png)

    Aqui chamado de FipeService, pois irá acessar a API da Tabela FIPE.

    Também possui um @Controller igual o que já foi citado aqui.

    ![13](https://user-images.githubusercontent.com/52512867/120227410-76552b80-c21f-11eb-9e60-69f70b54b5f5.png)

    Para finalizar não posso esquecer do nosso Helper, que é literalmente um "Ajudante" conforme diz o nome, onde ele irá realizar operações mais complexas de lógica, dando uma mãozinha pro service, ambos são bem amigos e se apoiam no trabalho para ninguém ficar sobrecarregado, aqui usamos o Helper para nossa logística de veículos, saber se os nossos veículos estarão disponíveis para uso naquele dia específico.

 ![14](https://user-images.githubusercontent.com/52512867/120227417-7a814900-c21f-11eb-8686-dbe8d354f8ad.png)

   ---

   # Padrão de Projeto

   Caso você já conheça sobre Padrão de Projetos, você deve ter percebido que foi utilizado o padrão MVC (Model, View e Controller), para facilitar nossa implementação do código, não foi utilizado o "View" pois não temos um Frond-End para esse projeto. Segue organização do projeto.

   ![15](https://user-images.githubusercontent.com/52512867/120227427-8240ed80-c21f-11eb-87f7-debf0d1c1a69.png)

   ---

   # Funcionamento da API

   Cadastro do usuário:

   ![16](https://user-images.githubusercontent.com/52512867/120227436-8967fb80-c21f-11eb-8496-115869046d87.png)

   Erro ao cadastrar com campo vazio:

   ![17](https://user-images.githubusercontent.com/52512867/120227439-8cfb8280-c21f-11eb-90e5-44109b7c1354.png)

   Cadastro de veículo, aqui utilizado pelo próprio Path:

   ![18](https://user-images.githubusercontent.com/52512867/120227483-9f75bc00-c21f-11eb-8a34-74bcf02829d9.png)

   Quando veículo não existe:
   
   ![19](https://user-images.githubusercontent.com/52512867/120227494-a4d30680-c21f-11eb-9207-9442b2b78c8c.png)

   Vinculando o veículo com o usuário pelo id de ambos:

   ![20](https://user-images.githubusercontent.com/52512867/120227510-ac92ab00-c21f-11eb-9c34-dcc56790b209.png)

   Trazendo os veículos cadastrados para um usuário com a validação se estão disponíveis para uso no rodízio:

   ![21](https://user-images.githubusercontent.com/52512867/120227525-b2888c00-c21f-11eb-8fd0-188ad7876e46.png)

   ---

   # Conclusão

   Foi bastante desafiador e produtivo a realização desse desafio, não conhecia várias das tecnologias utilizadas, aprendi bastante e espero que esse blogpost possa dar uma noção a você, sim você meu bom leitor entusiasmado, sobre esse grande universo que é o desenvolvimento em Java com SpringFramework. Fico por aqui, muito obrigado!
