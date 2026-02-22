
# 📚 CHALLENGE Literalura
OBJETIVO: Desenvolver um Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação. Os livros serão buscados através de uma API específica. 
<p align="center">
  <img 
    src="https://static01.nyt.com/images/2020/10/18/multimedia/18sp-ourfuture-libraries/18sp-ourfuture-libraries-mediumSquareAt3X.gif" 
    alt="CHALLENGE Literalura" 
    width="350"
    height="250"
  />
</p>

# 📎FUNCIONALIDADES

➡️ Fazer requisições as api do projeto gutendex;   
➡️ Tratar os dados recebidas para posteriormente migrar para o baco de dados;  
➡️ Enviar ao banco de dados;  
➡️ Separar a aplicação em dois perfis um com as informações do banco e outro usando o H2  

# 🛡️ESTRUTURA

│───📁br.com.challenge.literalura  
│   │       ├───📁controller  
│   │       │   └── 📄LivroController.java  
│   │       ├───📁dto  
│   │       │   ├── 📄AutorDto.java  
│   │       │   ├── 📄GutendexResponse.java  
│   │       │   └── 📄LivroDto.java  
│   │       ├───📁model  
│   │       │   ├── 📄Autor.java  
│   │       │   └── 📄Livros.java  
│   │       ├───📁principal  
│   │       │   └── 📄Principal.java  
│   │       ├───📁repository  
│   │       │   ├── 📄AutorRepository.java  
│   │       │   └── 📄LivrosRepository.java  
│   │       ├───📁service  
│   │       │   ├── 📄AutorService.java  
│   │       │   ├── 📄ConsumoApiService.java  
│   │       │   ├── 📄EstatisticaService.java  
│   │       │   └── 📄LivroService.java  
│   │       └───📁util  
│   │           └── 📄LiteraluraApplication.java  
│   └───📁resources  
│       ├── 📄application-dev.properties  
│       ├── 📄application-prod.properties  
│       └── 📄application.properties

# 🧑‍💻TECNOLOGIAS - STACKS
➡️ Java 21  
➡️ Spring Boot  
➡️ JPQL  
➡️ Driver PostgreSQL  
➡️ Maven

# 🧬 Como rodar o projeto?

1️⃣ Clone do repositório 
```bash  
git clone https://github.com/4909thalysson/literAlura.git
cd literalura
```
2️⃣Qual arquivo de configuração usar?  

Por padrão, o projeto está configurado para o ambiente de desenvolvimento (dev), que utiliza memória e não precisa de banco de dados.

O arquivo de configuração é: application.dev.properties.

Se você quiser rodar com banco local, utilize o arquivo application.prod.properties.

Nesse caso, será necessário configurar as variáveis de ambiente do seu banco PostgreSQL local  

3️⃣Como configurar o banco local?  

Para usar o banco local, configure as seguintes variáveis de ambiente no seu sistema (ou no IDE):  

DB_URL	URL do seu banco PostgreSQL  
DB_USERNAME	Usuário do banco
  
DB_PASSWORD	Senha do banco  

## IMAGENS DA APLICAÇÃO 
<img width="500" height="350" alt="Captura de tela 2026-02-22 000227" src="https://github.com/user-attachments/assets/8e5c2e92-13ae-400c-a7d3-f230d4b4935a" /> 

<img width="500" height="350" alt="Captura de tela 2026-02-22 000235" src="https://github.com/user-attachments/assets/f87df5a1-38f1-4df2-9a19-00bd70cafbd1" />  
 
## 🧑‍💻AUTOR
Thalysson O Martins  
<a href="https://www.linkedin.com/in/thalysson-de-oliveira-martins/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>   
</div>

## AGRADECIMENTO

Agradeço à equipe de instrutores da Alura Latam pelo suporte e esclarecimento de dúvidas ao longo deste challenge, bem como aos instrutores do programa ONE pelo direcionamento e incentivo durante o desenvolvimento do projeto.
