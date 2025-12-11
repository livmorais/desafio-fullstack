# Documentação sobre as Rotas da Aplicação


Esse documento descreve todas as rotas disponíveis, organizadas em duas categorias principais:


- **Rotas de Frontend (Views JSP)** – controllers que retornam páginas renderizadas.
- **Rotas da API (REST)** – controllers que retornam dados em formato JSON.


---


## Fluxo Geral da Aplicação


### Acesso inicial
**URL:** `http://localhost:8080/`  
Exibe a listagem de cursos e categorias, além do botão **Entrar**.
Obs: exibe apenas as categorias que estão vinculadas a algum curso(ativo).


### Após entrar no sistema
**URL:** `/admin/home`  
Apresenta as seguintes opções:


- Gerenciamento de Categorias  
- Gerenciamento de Cursos  


### Gerenciamento de Categorias
**URL:** `/admin/categories`  
Permite listar, cadastrar e editar categorias.


### Gerenciamento de Cursos
**URL:** `/admin/courses`  
Permite listar, cadastrar, editar, ativar/inativar cursos e visualizar detalhes.


---


# 1. Rotas de Frontend (Views JSP)


Controllers que retornam páginas JSP renderizadas pelo Spring MVC.


---


## 1.1 LoginController


### Página Inicial / Login  
**GET /**  
**Descrição:** Primeira tela, exibe a listagem de cursos e categorias.


---


## 1.2 CategoryController


### Página de listagem das Categorias  
**GET /admin/categories**  
Exibe a lista completa de categorias.


### Página de Cadastro de Categoria  
**GET /admin/category/new**


### Salvar Nova Categoria  
**POST /admin/category/new**


### Página de Edição de Categoria  
**GET /admin/category/edit/{id}**


### Atualizar Categoria  
**POST /admin/category/{id}/edit**


---


## 1.3 CourseController


### Página de listagem dos Cursos  
**GET /admin/courses**


### Página de Cadastro de Curso  
**GET /admin/course/new**


### Salvar Novo Curso  
**POST /admin/course/new**


### Inativar Curso  
**POST /course/{code}/inactive**


### Ativar Curso  
**POST /course/{code}/active**


### Página de Edição de Curso  
**GET /admin/course/{id}/edit**


### Atualizar Curso  
**POST /admin/course/{id}/edit**


### Detalhes do Curso  
**GET /admin/course/{code}/details**


---


# 2. Rotas da API (REST)


Controllers que retornam respostas JSON (via `@RestController`).


---


## 2.1 RegistrationController


### Criar Matrícula  
**POST /registration/new**  
**Content-Type:** `application/json`  


**Body esperado:**
```json
{
  "courseCode": "java-mid",
  "studentEmail": "johndoe@gmail.com"
}
```


### Relatório de Cursos mais acessados e com status Ativo


**GET /registration/report**


### Relatório de Cursos mais acessados e com status Inativo


**GET /registration/report/inactive**


---


## 2.2 UserController
### Criar Usuário Aluno


**POST /user/newStudent**
**Content-Type:** `application/json`


**Body esperado:**
```json
{
  "name": "student",
  "email": "student@gmail.com",
  "password": "newstudent"
}
```


### Criar Usuário Instrutor


**POST /user/newInstructor**
**Content-Type:** `application/json`


Body esperado:
```json
{
  "name": "instructor",
  "email": "instructor@gmail.com",
  "password": "newinstructor"
}
```


### Listar Todos os Usuários


**GET /user/all**

### Videos do front e api

https://github.com/user-attachments/assets/22b2c612-33be-493e-be1e-c5cb94c4a158

https://github.com/user-attachments/assets/7331a72b-cbb9-4e9d-9001-378882a0307b

https://github.com/user-attachments/assets/f6b9f0f2-1ecc-4074-a568-85759e5f1bd7

https://github.com/user-attachments/assets/a197ce1b-e8a2-4d89-8a0a-d340d40b1b5c


