# DESENVOLVIMENTO DE UM JOGO DO TIPO CAMPO MINADO
**UNIVERSIDADE FEDERAL DA PARAÍBA**  
**CENTRO DE INFORMÁTICA**  
**BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO**  

 

**ARTHUR RAMÓN SOUZA FERREIRA MARTINS**  
**DAVI BARATTO**  
**GABRIEL DANTAS DE SOUSA**



**João Pessoa**
**2022**  



## INTRODUÇÃO
<p> A escolha do projeto foi realizada pela equipe, visando pôr em prática todo o conhecimento lecionado durante a disciplina, e claro, de forma
desafiadora. O Campo Minado é um puzzle game simples, onde  o objetivo do usuário é revelar todos os campos que não estão minados, evitando 
aqueles que escondem bombas a partir das dicas obtidas durante o jogo. </p>

<p> O projeto teve como planejamento final, definido pela equipe, criar o jogo com uma interface gráfica simples e amigável, dando ao usuário a opção
de escolher entre diferentes níveis de dificuldade. Também foi definido que a dificuldade do jogo seria relacionado com a variação do tamanho do 
tabuleiro. </p>

<p> Também tinha-se a ideia de realizar os testes dos métodos e das classes, utilizando  um método de desenvolvimento  específico para  tal serviço. 
Contudo esta parte não foi realizada. </p>

<p> Como o Campo Minado é um jogo com poucas possibilidades de movimentos e eventos, a modelagem do projeto foi relativamente simples. Embora alguns 
ajustes tenham sido realizados na modelagem durante o processo de programação do jogo, não houve nenhuma alteração disruptiva que fizesse ser 
necessário refazer alguma etapa do desenvolvimento. </p>

<p> A versão final do jogo apresenta duas possibilidades para o usuário: jogar o jogo com uma interface gráfica ou jogar em uma versão desenvolvida 
para o terminal. Como o desenvolvimento começou pela versão para terminal, o que a equipe julgou ser o mais simples, o código acabou ficando na versão 
final. </p>

<p>Código do projeto: https://github.com/ArthurRamonSouza/minefield-game/tree/main </p>

## MODELAGEM DO PROBLEMA 
<p>A modelagem do problema foi a primeira etapa a ser realizada, embora tenha sido alterada diversas vezes no decorrer do desenvolvimento do projeto.
Um diagrama de classes foi feito e pode ser encontrado tanto no repositório no GitHub, junto com o projeto, quanto no link a seguir: </p>

[Diagrama de Classes](https://drive.google.com/file/d/1pzfRuRritGkpE3DFf80lTCx55bA8_UuO/view?usp=sharing).

<p> O relacionamento das classes pode ser mais facilmente entendido visualizando o diagrama de classes. </p>

As classes principais, as que reúnem todas as classes de modelo e onde estão localizados várias regras lógicas, são as classes: [JFrameMenu](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/view/JFrameMenu.java)  e  
[TerminalGameMain](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/main/TerminalGameMain.java). As classes no pacote view  são chamadas pela classe  [JFrameMenu](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/view/JFrameMenu.java)  e servem para criar a parte gráfica do jogo. Já a classe [TerminalGameMain](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/main/TerminalGameMain.java),
junto com as classes de modelo, servem para rodar o código relativo à versão para terminal. 

O projeto foi todo realizado seguindo os conceitos da programação orientada a objeto, portanto a abstração está fortemente presente, sendo utilizada para 
criar as classes, que são baseadas nos principais elementos do jogo. Como boa prática, as classes foram bem encapsuladas, tendo atributos e métodos privados, 
getters e setters e sendo separadas em pacotes. A herança foi bastante utilizada para desenvolver a parte gráfica, visto que as classes, como por exemplo, 
[JFrameMenu](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/view/JFrameMenu.java) e [JButtonSpace](https://github.com/ArthurRamonSouza/minefield-game/blob/main/src/main/java/view/JButtonSpace.java) herdam de classes como a JFrame e JButton.

Para o desenvolvimento da parte gráfica, também foram utilizadas interfaces como a JComponent, Runnable, ActionListener e a Serializable. Por último, também 
é possível citar diversos casos de polimorfismo no projeto, como os overloads de construtores ou as implementações de métodos abstratos de algumas classes
e interfaces.  

Após a base do diagrama ser finalizada, foram definidas algumas práticas a fim de manter o código padronizado. Os padrões definidos foram relacionados 
ao: uso da língua inglesa para desenvolver o projeto (comentários, versionamento do código,  nomenclatura de métodos e variáveis), versionamento do código, 
utilização de pacotes ([packages](https://github.com/ArthurRamonSouza/minefield-game/tree/main/src/main/java)) para organizar as classes e a criação de classes com propósito único.  

## FERRAMENTAS UTILIZADAS
<p> As ferramentas utilizadas para desenvolver este projeto foram: as IDE's Eclipse e Visual Studio Code, a API Swing do Java, o Git e o GitHub, e o 
Diagrams.net. </p>

<p> As duas IDE's foram utilizadas durante o desenvolvimento do projeto por conta de preferências pessoais e o VSCode possui uma integração bem intuitiva 
para utilizar o git e enviar os códigos para o repositório remoto. O versionamento foi utilizado para prevenir possíveis perdas de código. Como nem todos 
os integrantes da equipe tinham conhecimentos de versionamento de código, tal tarefa ficou encarregada para um único membro. </p>

<p> A API Swing foi utilizada para fazer toda a criação da parte gráfica e também é responsável pela renderização dos elementos gráficos.
Por fim, o  Diagrams.net foi utilizado para criar o diagrama de classes do projeto.

Voltando para a parte do código, temos as classes divididas em três pacotes: o [main](https://github.com/ArthurRamonSouza/minefield-game/tree/main/src/main/java/main), o [model](https://github.com/ArthurRamonSouza/minefield-game/tree/main/src/main/java/model) e o [view](https://github.com/ArthurRamonSouza/minefield-game/tree/main/src/main/java/view). O pacote main tem apenas a classe principal que roda 
o código do jogo na versão de terminal. Já o pacote model, possui os modelos utilizados em ambas as versões do jogo. Por fim, o pacote view possui todas as 
telas do jogo que compõem a versão gráfica. </p>

<p> No projeto também há algumas pastas de testes, mas como citado antes, a ideia de criar classes específicas para a realização desses testes foi descontinuada
por falta de conhecimento e tempo. O processo de teste foi realizado apenas utilizando as ferramentas de debug das próprias IDE's. </p>

## CONSIDERAÇÕES FINAIS
<p>	O projeto foi concluído e os resultados desejados no planejamento inicial foram atingidos. Apesar de ser uma ideia simples, o jogo de Campo Minado é um desafio
lógico tanto para quem joga, quanto para quem o programa. Várias vezes bugs foram encontrados ou o progresso de desenvolvimento do jogo ficou estagnado devido aos 
erros de lógica gerados durante a programação do jogo. Também houve um atraso no desenvolvimento da parte gráfica do jogo devido a falta de conhecimento nesta parte, 
porém após um tempo de estudo, este obstáculo foi superado. </p>

“Aprendi a programar orientado a objeto, logo estou mais familiarizado com os conceitos. Uma solução para o problema já vem na minha cabeça, estruturada o máximo 
possível em classes, seguindo os conceitos da POO. Como feedback da disciplina, apesar de ter tido um contato reduzido devido ao período que participei do projeto 
Hackatruck, gostei bastante da metodologia da professora e, obviamente, da linguagem utilizada. ” [Arthur Ramón](https://github.com/ArthurRamonSouza)

“Já conhecia alguns conceitos de programação orientado a objetos mas nunca tinha programado de fato com ela, a disciplina me ajudou a tentar estruturar mais os problemas para encaixá-los no POO. Como feedback da disciplina, gostei bastante de como a professora trouxe aulas dinâmicas e sempre tentando deixar o conteúdo mais leve e interessante para nós. Porém acho que a primeira parte da cadeira foi muito extensa e poderia ser reduzida, pelo fato dos alunos acabarem de sair de Introdução a Programação, acho que a parte da sintaxe do Java poderia ser passada de forma mais rápida para focar ainda mais no POO. Fora isso, não deixou nada a desejar.” - Davi Baratto
	
‘’Nunca tinha programado antes seguindo os conceitos da programação orientada a objeto, mas esta cadeira me proporcionou o conhecimento necessário para começar a desenvolver utilizando poo. A didática foi boa, me mantendo instigado para aprender esses conhecimentos que são muito utilizados no mercado de trabalho. Como sugestão para melhorias, acho que a parte inicial da cadeira poderia ser um pouco mais resumida, já que muitos dos conceitos os alunos já viram na cadeira de introdução a programação.’’ - Gabriel Dantas 

