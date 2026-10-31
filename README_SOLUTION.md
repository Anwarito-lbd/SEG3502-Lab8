# SEG3502 – Lab 8 : serveur GraphQL Employee (Spring Boot + Kotlin + MongoDB)

Étudiant 1 : Anwar — N° 300254189  
Étudiant 2 : Manasse — N° 300268472  
Dépôt GitHub : https://github.com/Anwarito-lbd/SEG3502-Lab8.git  
Rapport : RAPPORT_LAB8.pdf

## Travail réalisé
- `server/src/main/resources/graphql/employee.graphqls` : type `Employee`, input `CreateEmployeeInput`,
  `Query { employees, employeeById }`, `Mutation { newEmployee, deleteEmployee }`.
- `server/.../resolvers/EmployeesResolver.kt` : résolveurs `@QueryMapping` / `@MutationMapping` utilisant `EmployeesRepository` (MongoDB).
- `client/.npmrc` (`legacy-peer-deps=true`) : le code de départ a un conflit de dépendances pair (apollo-angular 5 / Angular 18) qui fait échouer `npm install` sans cette option.

## Exécution
1. MongoDB : `docker run -d -p 27017:27017 --name mongodb mongo`
2. Serveur (JDK 21) : `cd server && ./gradlew bootRun` → http://localhost:9000/graphql (GraphiQL : /graphiql)
3. Client : `cd client && npm install && npm start` → http://localhost:4200

Exemple :
```bash
curl localhost:9000/graphql -H 'Content-Type: application/json' -d '{"query":"{ employees { id name city salary } }"}'
```
