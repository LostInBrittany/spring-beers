# Spring-Beers

## A Spring-based server-side companion to the [Stencil Beers](https://github.com/LostInBrittany/stencil-beers) and [Lit Beers](https://github.com/LostInBrittany/lit-element-beers) projects

The [Stencil Beers](https://github.com/LostInBrittany/stencil-beers) project is a small [Stencil JS](https://stenciljs.com/) tutorial that can be used on its own. But IMHO it is a pity to do only the client-side and mocking the server API with plain files. So here we have a companion project where we are going to do the server-side of [Stencil Beers](https://github.com/LostInBrittany/stencil-beers) using [Spring Boot](https://spring.io/projects/spring-boot).

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". Most Spring Boot applications need very little Spring configuration.


Spring Boot offers a fast way to build applications. It looks at your classpath and at beans you have configured, makes reasonable assumptions about what you’re missing, and adds it. With Spring Boot you can focus more on business features and less on infrastructure.

Spring Boot doesn’t generate code or make edits to your files. Instead, when you start up your application, Spring Boot dynamically wires up beans and settings and applies them to your application context.

## What are the objectives of this tutorial

Follow this tutorial to learn to build REST APIs in Java using Spring Boot, quickly an easily, without all the pain of the classic way. You will use [Spring Boot](https://spring.io/projects/spring-boot), with some drops of NoSQL databases (with [MongoDB](http://mongodb.com)).



## How is the tutorial organized ##

As many computers used for the course haven't Git, we have structurated the project to allow a Git-less use. The tutorial is divided in steps, each one in its own directory:

1. [Hello Spring Boot](./step-01/)
1. [Basic routing](./step-02/)
1. [Using the Beer class](./step-03/)
1. [Reading the JSON files](./step-04/)
1. [Serving the webapp](./step-05/)
1. [Mongo beers](./step-06/)
1. [Going CRUD](./step-07)
1. [Securing the application](./step-08)

In each step directory you have a README file that explain the objective of the step, that you will do in the working directory `app`. If you have problems or if you get lost, you also have the solution of each step in the step directories.
