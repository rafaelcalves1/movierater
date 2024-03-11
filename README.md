# Movie Rater

## Sobre o Projeto
Este projeto foi criado com o objetivo de aplicar os resultados de estudos. O projeto foi desenvolvido utilizando Kotlin, Jetpack Compose. 
 * Arquitetura
   * Foi aplicado a modularização no projeto para isolar as respectivas responsabilidade, melhorando a escalabilidade. Atualmente temos os seguintes módulos: ":data" e ":app" 
   * Como neste projeto está sendo utilizado o jetpack compose, foi optado por utilizar a arquitetura de atividade única (single-activity), juntamente com a arquitetura MVVM.
  
 * Injeção de dependencias
   * Foi escolhido o KOIN, pela facilidade de implementação e utilização ( particularmente em projetos multi-modulos).
     * Como temos dois modulos somente, optei por deixar uma variavel que representa o modulo de dependencia do respectivo modulo.
    
 * Outras Tecnologias:
   * Retrofit2
   * Paging 3
   * Coroutines
   * Coil
