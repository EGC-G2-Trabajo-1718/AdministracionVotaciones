# AdministracionVotaciones v1
Repositorio para el módulo de administración de votaciones.

Subsistema de la asignatura de EGC 17/18.

Objetivos: ofrecer las herramientas necesarias para crear,editar y borrar una votación, incluyendo preguntas y respuestas.

Tecnologías:

	- Java 1.8 -> Eclipse Jee Neon 3
	- Sistema de gestión de biliotecas: Maven
	- Biblioteca para API: Jackson
	

A continuación se muestra los pasos a seguir para desplegar el módulo de administración de votaciones:


-En primer lugar, descargamos la máquina virtual del siguiente enlace: https://drive.google.com/file/d/1nrXjmWwJRuOUIWOtVEZmiqczZaee96Mk/view

-Importamos como servicio virtualizado el archivo descargado en virtual box y lo ejecutamos

-A continuación, nos logueamos en la máquina debian con el usuario: “egc” y contraseña: “egc”

-Una vez logueados clonamos el repositorio https://github.com/EGC-G2-Trabajo-1718/integracion.git en la carpeta ~/scripts

-En la carpeta tools se encuentran los scripts, para desplegar todos los subsistemas escribimos en el terminal: “bash start.sh” en ~/scripts/integracion/tools

Con esto tenemos nuestro subsistema desplegado.

-También podemos desplegar todos los subsistemas manteniendo un contenedor con el código de nuestra máquina nativa para hacer cambios, con el siguiente script:
“bash deploy-shared.sh” 

Antes de usar este Script, debemos crear una carpeta compartida con la máquina virtual y desplegar en ella el subsistema al que vamos a hacerle cambios.
Con esto tenemos nuestro subsistema desplegado e integrado.

*Nota: la contraseña de super user es “egc”.
