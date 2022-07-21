<h1 align="center">
  Restfull API for Digititles Imagine Web Application
  <br>
  <img src="https://cdn.miloszgilga.pl/digititles-imagine-project-logo.png" width="170">
  <br>
</h1>
<p align="center" style="font-size: 1.2rem;">
RestAPI created for ReactJS application using Java and Spring Boot framework. To create this programming interface I used JPA and Hibernate to provide a connection between object-oriented programming and storing data as tables in mySQL database.
</p>

> See this website at [digititlesimagine.com.pl](https://digititlesimagine.com.pl/) <br>
> See Front-end for this website written in ReactJS in my repo: [ReactJS_Web_Application_Digititles_Imagine](https://github.com/Milosz08/ReactJS_Web_Application_Digititles_Imagine)

<hr/>

## About the Project
I created the programming interface with the Spring Boot Framework in Java. The application contains standard endpoints that match the REST pattern. The application allows you to modify database entries (created in mySQL and managed by JPA, Hibernate, and JSQL) and upload and download files from the corresponding endpoints from the server.

## Exceptions
The program has a fairly advanced exception operation. The response of the exceptions is as a JSON document, together with the corresponding HTTP exception code and the current server time. This is necessary to log client-side errors.

## Security (JWT)
This API is fully protected by JWT technology. Without authorization, only GET methods and selected endpoints with POST method are available. To handle the rest, a Bearer token is required (this is automatically generated upon successful customer login and is deleted immediately upon logout).

## Clone and Installation
If you want to clone and work with this repository, use the built-in interface in your IDE (for example Intelij Idea or Eclipse EE) or use the clone project algorithm with git bash:<br>
1. Open Git Bash.
2. Change the current working directory to the location where you want the cloned directory.
3. Type `git clone` and then paste the URL you copied earlier.
  
```
$ git clone https://github.com/Milosz08/SpringBoot_RestfullApi_Digititles_Imagine
```

## License
This application is on MIT License.
